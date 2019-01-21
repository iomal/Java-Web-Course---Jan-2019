import java.io.*;
import java.lang.reflect.Field;
import java.net.URLDecoder;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static class HTTP_Request {
        private Map<String, String> request;
        private String dateHeader;
        private String hostHeader;
        private String contentTypeHeader;
        private List<String> authorizationHeader;
        private Map<String, String> body;

        private HTTP_Request() {
        }

        private void setRequest(Map<String, String> request) {
            this.request = request;
        }

        private void setDateHeader(String dateHeader) {
            this.dateHeader = dateHeader;
        }

        private void setHostHeader(String hostHeader) {
            this.hostHeader = hostHeader;
        }

        private void setContentTypeHeader(String contentTypeHeader) {
            this.contentTypeHeader = contentTypeHeader;
        }

        private void setAuthorizationHeader(List<String> authorizationHeader) {
            this.authorizationHeader = authorizationHeader;
        }

        private void setBody(Map<String, String> body) {
            this.body = body;
        }


        private Map<String, String> getRequest() {
            return this.request;
        }

        private String getDateHeader() {
            return this.dateHeader;
        }

        private String getHostHeader() {
            return this.hostHeader;
        }

        private String getContentTypeHeader() {
            return this.contentTypeHeader;
        }

        private List<String> getAuthorizationHeader() {
            return this.authorizationHeader;
        }

        private Map<String, String> getBody() {
            return this.body;
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
            result = String.format(
                    request.getRequest().get("httpVersion") + " " + "404 Not Found\r\n" +
                            addNotNullHeaders(request) + "\r\nThe requested functionality was not found.");
            System.out.println(result);
        } else if (request.getAuthorizationHeader() == null) {
            result = String.format(
                    request.getRequest().get("httpVersion") + " " + "401 Unauthorized\r\n" +
                            addNotNullHeaders(request) +
                            "\r\nYou are not authorized to access the requested " +
                            "functionality.");
            System.out.println(result);

        }
        else {
            String decodeStr = request.getAuthorizationHeader().get(0);
            name = new String(Base64.getDecoder().decode(decodeStr.getBytes()));
            result = String.format(
                    request.getRequest().get("httpVersion") + " " + "200 OK\r\n" + addNotNullHeaders(request));

            if (request.getBody() != null && request.getBody().entrySet().size() != 0 &&
                    "POST".equals(request.getRequest().get("method"))) {

                result += String.format("\r\nGreetings " + name + "! You have successfully created %s" +
                        " with ", request.getBody().values().toArray()[0]) +
                        request.getBody().entrySet().stream().skip(1)
                                .map((kVp) -> kVp.getKey() + " - " + kVp.getValue()).
                                collect(Collectors.joining(", ")) + ".";

            } else if ("GET".equals(request.getRequest().get("method"))) {
                result+= String.format("\r\nGreetings %s!\r\n", name);
            } else if ("POST".equals(request.getRequest().get("method")) && (request.getBody() == null ||
                    request.getBody().entrySet().size() == 0)) {
                result = String.format(
                        request.getRequest().get("httpVersion") + " " + "400 Bad Request\r\n" +
                                addNotNullHeaders(request) + "\r\nThere was an error with the requested functionality " +
                                "due to malformed request.");
            }
            System.out.println(result);
        }
    }

    private static String addNotNullHeaders(HTTP_Request request) {
        String lines = "";
        Field[] fields = HTTP_Request.class.getDeclaredFields();
        for (int i = 1; i < 4; i++) {
            fields[i].setAccessible(true);
            try {
                if ((fields[i].get(request) != null) && ("dateHeader".equals(fields[i].getName()) ||
                        "hostHeader".equals(fields[i].getName()) || "contentTypeHeader".equals(fields[i].getName()))) {
                    lines += convertHeaderKey(fields[i]) + ": " + fields[i].get(request) + "\r\n";
                }
            } catch (final IllegalAccessException e) {
                lines += fields[i].getName() + " > " + e.getClass().getSimpleName();
            }
        }
        return lines;
    }

    private static String convertHeaderKey(Field field) {
        String result = "";
        String headerKey = field.getName().split("Header")[0];
        headerKey = headerKey.substring(0, 1).toUpperCase().concat(headerKey.substring(1));
        List<String> resultArr = Arrays.asList(headerKey.split("(?=\\p{Upper})"));
        if (resultArr.size() > 1)
            result = resultArr.get(0) + "-" + resultArr.get(1);
        else result = headerKey;
        return result;
    }

    private static void parseInputReq(String line, HTTP_Request request) {
        String headerKey = line.split("\\s+")[0];
        String headerValue = line.split("\\s+")[1];
        switch (headerKey) {
            case ("Date:"):
                request.setDateHeader(headerValue);
                break;
            case ("Host:"):
                request.setHostHeader(headerValue);
                break;
            case ("Content-Type:"):
                request.setContentTypeHeader(headerValue);
                break;
            case ("Authorization:"):
                request.setAuthorizationHeader(new ArrayList<String>() {{
                    add(line.split("\\sBasic\\s")[1]);
                }});
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
