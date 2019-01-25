import javafx.util.converter.ByteStringConverter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
        String line = buffer.readLine();
        String result = "", name;
        List<String> urls = Arrays.asList(line.split("\\s+"));

        List<String> request = new ArrayList();
        while ((line = buffer.readLine()) != null && !line.isEmpty()) {
            request.add(line);
        }
        request.add("\r\n");
        if ((line = buffer.readLine()) != null) {
            request.add(line);
        }
        HttpRequest httpRequest = new HttpRequestImpl(request);
        HttpResponse httpResponse = new HttpResponseImpl(httpRequest,urls);
        System.out.println(httpResponse);
    }
}
