import utils.FileUtils;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Day5 {
    private static String INPUT_FILENAME = "/input/day5_input.txt";

    public static void main(String[] args) throws Exception {
        int total = 0;
        List<String> lines = FileUtils.getInputLines(INPUT_FILENAME);

        /*
            [V] [G]             [H]
            [Z] [H] [Z]         [T] [S]
            [P] [D] [F]         [B] [V] [Q]
            [B] [M] [V] [N]     [F] [D] [N]
            [Q] [Q] [D] [F]     [Z] [Z] [P] [M]
            [M] [Z] [R] [D] [Q] [V] [T] [F] [R]
            [D] [L] [H] [G] [F] [Q] [M] [G] [W]
            [N] [C] [Q] [H] [N] [D] [Q] [M] [B]
             1   2   3   4   5   6   7   8   9

                [V] [G]             [H]
            [Z] [H] [Z]         [T] [S]
            [P] [D] [F]         [B] [V] [Q]
            [B] [M] [V] [N]     [F] [D] [N]
            [Q] [Q] [D] [F]     [Z] [Z] [P] [M]
            [M] [Z] [R] [D] [Q] [V] [T] [F] [R]
            [D] [L] [H] [G] [F] [Q] [M] [G] [W]
            [N] [C] [Q] [H] [N] [D] [Q] [M] [B]
             1   2   3   4   5   6   7   8   9
         */

        List<String> one = new ArrayList<>();
        one.addAll(List.of("N","D","M","Q","B","P","Z"));
        List<String> two = new ArrayList<>();
        two.addAll(List.of("C","L","Z","Q","M","D","H","V"));
        List<String> three = new ArrayList<>();
        three.addAll(List.of("Q","H","R","D","V","F","Z","G"));
        List<String> four = new ArrayList<>();
        four.addAll(List.of( "H","G","D","F","N" ));
        List<String> five = new ArrayList<>();
        five.addAll(List.of("N","F","Q"));
        List<String> six = new ArrayList<>();
        six.addAll(List.of("D","Q","V","Z","F","B","T"));
        List<String> seven = new ArrayList<>();
        seven.addAll(List.of("Q","M","T","Z","D","V","S","H"));
        List<String> eight = new ArrayList<>();
        eight.addAll(List.of("M","G","F","P","N","Q"));
        List<String> nine = new ArrayList<>();
        nine.addAll(List.of("B","W","R","M"));

        List<List<String>> columns = List.of(one, two, three, four, five, six, seven, eight, nine);

        for (String line : lines) {
            System.out.println("line = " + line);
            printColumns(columns);

            String[] parts = line.split(" ");
            int count = Integer.valueOf(parts[1]);
            int from = Integer.valueOf(parts[3])-1;
            int to = Integer.valueOf(parts[5])-1;

            List<String> fromList = columns.get(from);
            List<String> items = fromList.subList(fromList.size()-count,fromList.size());
            System.out.println("items = " + items);
            List<String> toList = columns.get(to);
            toList.addAll(items);

            for( int i = 0; i < count; i++) {
                fromList.remove(fromList.size()-1);
            }

//            for( int i = 0; i < count; i++) {
//                List<String> fromList = columns.get(from);
//                String item = fromList.remove(fromList.size()-1);
//                List<String> toList = columns.get(to);
//                toList.add(item);
//            }


        }
        System.out.println("FINAL");
        printColumns(columns);

        StringBuilder builder = new StringBuilder();
        for( List<String> l : columns) {
            builder.append(l.get(l.size()-1));
        }
        System.out.println("GUESS = " + builder.toString());

        System.out.println("total = " + total);
    }

    private static void printColumns(List<List<String>> columns) {
        for( int i = 1; i <= columns.size(); i++) {
            System.out.println("[" +i+"]"+columns.get(i-1));
        }
    }

}

