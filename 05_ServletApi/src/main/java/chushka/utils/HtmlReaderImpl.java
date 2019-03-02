package chushka.utils;

import org.springframework.core.io.ClassPathResource;

import java.io.*;

public class HtmlReaderImpl implements HtmlReader {
    public String readFile(String path) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(
                new FileInputStream(new ClassPathResource(path).getFile())));
        StringBuilder stringBuilder = new StringBuilder();
        String line = "";
        while ((line = br.readLine()) != null) {
            stringBuilder.append(line);
            stringBuilder.append(System.lineSeparator());
        }
        return stringBuilder.toString();
    }
}
