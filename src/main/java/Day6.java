import utils.FileUtils;

import java.util.ArrayList;
import java.util.List;

public class Day6 {
    private static String INPUT_FILENAME = "/input/day6_input.txt";

    public static void main(String[] args) throws Exception {
        List<String> lines = FileUtils.getInputLines(INPUT_FILENAME);
        String line = lines.get(0);
        System.out.println("line = " + line);
        char[] chars = line.toCharArray();

        for( int i = 0; i < chars.length; i++ ) {
            List<Character> set = new ArrayList<>();
            for( int j = i; j < (i+14); j++ ) {
                set.add(chars[j]);
            }
            long distinct = set.stream().distinct().count();
            if( distinct == 14 ) {
                System.out.println("i = " + i);
                System.out.println("ANSWER:"+(i+14));
                break;
            }
        }

    }


}

