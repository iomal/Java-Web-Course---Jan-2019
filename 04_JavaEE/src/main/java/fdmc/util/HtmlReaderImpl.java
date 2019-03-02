package fdmc.util;

import org.springframework.core.io.ClassPathResource;

import java.io.*;


public class HtmlReaderImpl implements HtmlReader {

    @Override
    public String readHtmlFile(String filePath) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(
                new FileInputStream(new ClassPathResource(filePath).getFile())));
        StringBuilder stringBuilder = new StringBuilder();
        String line="";
        while((line=bufferedReader.readLine())!=null){
            stringBuilder.append(line).append(System.lineSeparator());
        }
        return stringBuilder.toString();
    }
}
