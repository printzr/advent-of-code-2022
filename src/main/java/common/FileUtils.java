package common;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class FileUtils {
    public static List<String> getInputLines(String filename) {
        List<String> lines = new ArrayList<>();
        try {
            InputStream inputStream = FileUtils.class.getResourceAsStream(filename);
            try (BufferedReader br
                         = new BufferedReader(new InputStreamReader(inputStream))) {
                String line;
                while ((line = br.readLine()) != null) {
                    lines.add(line);
                }
            }
        } catch (Exception ex) {
            System.err.println(ex);
        }

        return lines;
    }
}
