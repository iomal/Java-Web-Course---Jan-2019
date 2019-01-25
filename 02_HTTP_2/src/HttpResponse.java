import java.io.IOException;
import java.util.Map;

public interface HttpResponse {
    Map<String, String> getHeaders();

    int getSatusCode();

    byte[] getContent();

    byte[] getBytes() throws IOException;

    void addHeader(String header, String value);
}
