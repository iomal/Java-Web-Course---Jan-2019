import java.io.*;
import java.lang.reflect.Field;
import java.net.URLDecoder;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static class HTTP_Request {
        private Map<String, String> request;
        private Map<String, String> headers;
        private Map<String, String> body;

        private HTTP_Request() {
            this.headers = new LinkedHashMap<>();
        }

        private void setRequest(Map<String, String> request) {
            this.request = request;
        }

        private void setBody(Map<String, String> body) {
            this.body = body;
        }


        private Map<String, String> getRequest() {
            return this.request;
        }

        private Map<String, String> getHeaders() {
            return Collections.unmodifiableMap(this.headers);
        }

        private Map<String, String> getBody() {
            return this.body;
        }

        private void addHeader(String headerKey, String headerValue) {
            this.headers.put(headerKey, headerValue);
        }

    }

    public static void main(String[] args) throws IOException {
        BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
        String line = buffer.readLine();
        String result = "", name;
        List<String> urls = Arrays.asList(line.split("\\s+"));
        HTTP_Request request = new HTTP_Request();
        line = buffer.readLine();
        String[] requestLineArr = line.split("\\s+");
        request.setRequest(new HashMap<String, String>() {{
            put("method", requestLineArr[0]);
            put("url", requestLineArr[1]);
            put("httpVersion", requestLineArr[2]);
        }});
        line = buffer.readLine();
        while (line != null && !"".equals(line)) {
            parseInputReq(line, request);
            line = buffer.readLine();
        }

        if ((line = buffer.readLine()) != null) {
            Map<String, String> body = extractBodyParams(line);
            request.setBody(body);
        }

        if (!urls.contains(request.getRequest().get("url"))) {
            result = request.getRequest().get("httpVersion") + " " + "404 Not Found\r\n" +
                    addNotNullHeaders(request) + "\r\nThe requested functionality was not found.";
            System.out.println(result);
        } else if (!request.getHeaders().containsKey("Authorization")) {
            result = request.getRequest().get("httpVersion") + " " + "401 Unauthorized\r\n" +
                    addNotNullHeaders(request) +
                    "\r\nYou are not authorized to access the requested " +
                    "functionality.";
            System.out.println(result);

        } else {
            String decodeStr = request.getHeaders().get("Authorization");
            name = new String(Base64.getDecoder().decode(decodeStr.getBytes()));
            result = request.getRequest().get("httpVersion") + " " + "200 OK\r\n" + addNotNullHeaders(request);

            if (request.getBody() != null && request.getBody().entrySet().size() != 0 &&
                    "POST".equals(request.getRequest().get("method"))) {

                result += String.format("\r\nGreetings " + name + "! You have successfully created %s" +
                        " with ", request.getBody().values().toArray()[0]) +
                        request.getBody().entrySet().stream().skip(1)
                                .map((kVp) -> kVp.getKey() + " - " + kVp.getValue()).
                                collect(Collectors.joining(", ")) + ".";

            } else if ("GET".equals(request.getRequest().get("method"))) {
                result += String.format("\r\nGreetings %s!\r\n", name);
            } else if ("POST".equals(request.getRequest().get("method")) && (request.getBody() == null ||
                    request.getBody().entrySet().size() == 0)) {
                result = request.getRequest().get("httpVersion") + " " + "400 Bad Request\r\n" +
                        addNotNullHeaders(request) + "\r\nThere was an error with the requested functionality " +
                        "due to malformed request.";
            }
            System.out.println(result);
        }
    }

    private static String addNotNullHeaders(HTTP_Request request) {
        StringBuilder headers = new StringBuilder();
        request.getHeaders().entrySet().stream().filter( header->"Date".equals(header.getKey())||
                "Content-Type".equals(header.getKey())||"Host".equals(header.getKey()))
                .forEach((kvp) -> headers.append(kvp.getKey()).append(": ")
                .append(kvp.getValue()).append(System.lineSeparator()));
        return headers.toString();
    }

    private static void parseInputReq(String line, HTTP_Request request) {
        String headerKey = line.substring(0,line.indexOf(":"));
        String headerValue = line.substring(line.indexOf(":")+2);
        switch (headerKey) {
            case ("Date"):
                request.addHeader(headerKey,headerValue);
                break;
            case ("Host"):
                request.addHeader(headerKey,headerValue);
                break;
            case ("Content-Type"):
                request.addHeader(headerKey,headerValue);
                break;
            case ("Authorization"):
                request.addHeader("Authorization", headerValue.substring("Basic ".length()));
                break;
            default:
                //Do nothing;
                break;
        }
    }

    private static Map<String, String> extractBodyParams(String line) throws UnsupportedEncodingException {
        Map<String, String> body = new LinkedHashMap<>();
        line = line.replace("+", " ");
        StringTokenizer bodyParams = new StringTokenizer(line, "&");
        while (bodyParams.hasMoreTokens()) {
            String pair = bodyParams.nextToken();
            StringTokenizer pairs = new StringTokenizer(pair, "=");
            while (pairs.hasMoreTokens()) {
                String key = pairs.nextToken();
                String value = URLDecoder.decode(pairs.nextToken(), "UTF-8");
                body.put(key, value);
            }
        }
        return body;
    }

}
