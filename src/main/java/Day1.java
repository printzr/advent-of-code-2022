import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Day1 {
    public static void main(String[] args) throws Exception {
        InputStream inputStream = Day1.class.getResourceAsStream("/input/day1_input.txt");
        try (BufferedReader br
                     = new BufferedReader(new InputStreamReader(inputStream))) {

            int maxValue = 0;
            int group = 0;

            List<Integer> allGroups = new ArrayList<>();

            String line;
            while ((line = br.readLine()) != null) {
                //resultStringBuilder.append(line).append("\n");
                System.out.println("line = " + line);
                if( line.trim().length() == 0 ) {
                    System.out.println("Group:" + group + " MaxValue:"+maxValue);
                    allGroups.add(group);
                    if( group > maxValue ) {
                        maxValue = group;
                        group = 0;
                        System.out.println("maxValue = " + maxValue);
                    } else {
                        group = 0;
                        System.out.println("group = " + group);
                    }
                } else {
                    group = group + Integer.valueOf(line).intValue();
                }
            }

            System.out.println("maxValue = " + maxValue);
            Collections.sort(allGroups, Collections.reverseOrder());
            System.out.println("allGroups.get(0) = " + allGroups.get(0));
            System.out.println("allGroups.get(0) = " + allGroups.get(1));
            System.out.println("allGroups.get(0) = " + allGroups.get(2));
            int topThree = allGroups.get(0) + allGroups.get(1) + allGroups.get(2);
            System.out.println("topThree = " + topThree);
        }
    }

}
