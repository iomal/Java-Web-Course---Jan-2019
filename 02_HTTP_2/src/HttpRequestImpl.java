
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class HttpRequestImpl implements HttpRequest {
    private String httpVersion;
    private String method;
    private String requestUrl;
    private Map<String, String> headers;
    private List<HttpCookie> cookies;
    private Map<String, String> bodyParams;

    public HttpRequestImpl(List<String> request) throws IOException {
        init(request);
    }

    @Override
    public Map<String, String> getHeaders() {
        return this.headers;
    }

    @Override
    public Map<String, String> getBodyParameters() {
        return this.bodyParams;
    }

    @Override
    public String getMethod() {
        return this.method;
    }

    @Override
    public void setMethod(String method) {
        this.method = method;
    }

    @Override
    public String getRequestUrl() {
        return this.requestUrl;
    }

    @Override
    public String getHttpVersion() {
        return this.httpVersion;
    }

    @Override
    public void setRequestUrl(String requestUrl) {
        this.requestUrl = requestUrl;
    }

    @Override
    public void addHeader(String header, String value) {
        this.headers.put(header, value);
    }

    @Override
    public void addBodyParameter(String parameter, String value) {
        this.bodyParams.put(parameter, value);
    }

    @Override
    public boolean isResource() {
        return this.requestUrl.contains(".");
    }

    private void init(List<String> request) throws IOException {
        this.httpVersion = request.get(0).split("\\s+")[2];
        this.method = request.get(0).split("\\s+")[0];
        this.requestUrl = request.get(0).split("\\s+")[1];
           this.headers = request.stream()
                .peek(header -> {
                    if (header.contains("Cookie:")) {
                        this.cookies=new ArrayList<>();
                        Arrays.asList(header.substring("Cookie: ".length()).split("; "))
                                .forEach(kv ->
                                {
                                    HttpCookie cookie = new HttpCookie(kv.split("=")[0],
                                            kv.split("=")[1]);
                                    this.cookies.add(cookie);
                                });
                    }
                })
                .filter(header -> header.contains(": ") && !header.contains("Cookie:"))
                .map(header -> Arrays.asList(header.split(": ")))
                .collect(Collectors.collectingAndThen(Collectors
                        .toMap(b -> b.get(0), a -> a.get(1)), Collections::unmodifiableMap));
        String body = request.stream().skip(1).filter(header -> !header.contains(": ")
                && !"\r\n".equals(header) && !"".equals(header)).findFirst().orElse(null);
        if (body != null) {
            Deque<String> steck = new ArrayDeque<>();
            Collections.addAll(steck, body.split("&"));
            Map<String, String> bodyParamstoCopy = new LinkedHashMap<>();
            while (steck.size() > 0) {
                String pair = steck.pollFirst();
                String key = pair.split("=")[0];
                String value = pair.split("=")[1];
                bodyParamstoCopy.put(key, value);
            }
            this.bodyParams = Collections.unmodifiableMap(bodyParamstoCopy);
        }

    }
}
