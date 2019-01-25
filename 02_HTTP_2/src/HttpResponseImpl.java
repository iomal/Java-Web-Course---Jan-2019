import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.*;
import java.util.stream.Collectors;

public class HttpResponseImpl implements HttpResponse {
    private String responseLine;
    private HttpStatusCode statusCode;
    private Map<String, String> headers;
    private byte[] content;

    public HttpResponseImpl() {
        this.headers = new LinkedHashMap<>();
        this.content = new byte[0];
    }

    public HttpResponseImpl(HttpRequest request, List<String> urls) {
        init(request, urls);
    }

    private void init(HttpRequest request, List<String> urls) {
        this.headers = request.getHeaders().entrySet().stream().filter(header -> "Date".equals(header.getKey())
                || "Content-Type".equals(header.getKey()) || "Host".equals(header.getKey()))
                .collect(Collectors.collectingAndThen(Collectors
                                .toMap(a -> (String) a.getKey(), b -> (String) b.getValue()),
                        Collections::unmodifiableMap));
        if (!urls.contains(request.getRequestUrl())) {
            this.statusCode = HttpStatusCode.NOT_FOUND;
            this.responseLine = request.getHttpVersion() + " " + this.statusCode + "\r\n";
            this.content = "The requested functionality was not found.".getBytes();
        } else if (!request.getHeaders().containsKey("Authorization")) {
            this.statusCode = HttpStatusCode.UNAUTHORIZED;
            this.responseLine = request.getHttpVersion() + " " + this.statusCode + "\r\n";
            this.content = "You are not authorized to access the requested functionality.".getBytes();
        } else {
            String decodeStr = request.getHeaders().get("Authorization");
            String name = new String(Base64.getDecoder().decode(decodeStr.substring("Basic ".length()).getBytes()));
            if (request.getBodyParameters() != null && request.getBodyParameters().entrySet().size() != 0 &&
                    "POST".equals(request.getMethod())) {
                this.statusCode = HttpStatusCode.OK;
                this.responseLine = request.getHttpVersion() + " " + this.statusCode + "\r\n";
                this.content = (String.format("Greetings " + name + "! You have successfully created %s" +
                        " with ", request.getBodyParameters().values().iterator().next()) +
                        request.getBodyParameters().entrySet().stream().skip(1)
                                .map((kVp) -> kVp.getKey() + " - " + kVp.getValue()).
                                collect(Collectors.joining(", ")) + ".").getBytes();

            } else if ("GET".equals(request.getMethod())) {
                this.statusCode = HttpStatusCode.OK;
                this.responseLine = request.getHttpVersion() + " " + this.statusCode + "\r\n";
                this.content = ("Greetings " + name + "!").getBytes();
            } else if ("POST".equals(request.getMethod()) && (request.getBodyParameters() == null ||
                    request.getBodyParameters().entrySet().size() == 0)) {
                this.statusCode = HttpStatusCode.BAD_REQUEST;
                this.responseLine = request.getHttpVersion() + " " + this.statusCode + "\r\n";
                  this.content="There was an error with the requested functionality due to malformed request."
                          .getBytes();
            }
        }
    }

    @Override
    public Map<String, String> getHeaders() {
        return this.headers;
    }

    @Override
    public int getSatusCode() {
        return this.statusCode.getCode();
    }

    @Override
    public byte[] getContent() {
        if (this.content == null) {
            this.content = new byte[0];
        }
        return this.content.clone();
    }

    @Override
    public byte[] getBytes() throws IOException {
        String str = this.responseLine + this.getHeaders() +
                "\r\n" + Arrays.toString(this.getContent());
        return str.getBytes();
    }


    @Override
    public void addHeader(String header, String value) {
        this.headers.put(header, value);
    }

    @Override
    public String toString() {
        return this.responseLine + this.getHeaders()
                .entrySet().stream().map((kVp) -> kVp.getKey() + ": " + kVp.getValue()).
                        collect(Collectors.joining("\r\n")) + "\r\n\r\n" + new String(this.getContent());
    }
}
