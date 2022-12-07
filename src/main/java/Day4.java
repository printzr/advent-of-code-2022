import utils.FileUtils;
import java.util.List;

public class Day4 {
    private static String INPUT_FILENAME = "/input/day4_input.txt";

    public static void main(String[] args) throws Exception {
        int total = 0;
        List<String> lines = FileUtils.getInputLines(INPUT_FILENAME);

        for (String line : lines) {
            System.out.println("line = " + line);
            String x = line.split(",")[0];
            String y = line.split(",")[1];
            System.out.println("x = " + x);
            System.out.println("y = " + y);

            int xMin = Integer.parseInt(x.split("-")[0]);
            int xMax = Integer.parseInt(x.split("-")[1]);

            int yMin = Integer.parseInt(y.split("-")[0]);
            int yMax = Integer.parseInt(y.split("-")[1]);

            //Part1
//                if( (xMin <= yMin && xMax >= yMax) || (yMin <= xMin && yMax >= xMax)) {
//                    total = total + 1;
//                }

            if (xMin <= yMin) {
                if (xMax >= yMin) {
                    total = total + 1;
                }
            } else {
                if (yMax >= xMin) {
                    total = total + 1;
                }
            }
        }


        System.out.println("total = " + total);
        //821 is correct
    }

}

