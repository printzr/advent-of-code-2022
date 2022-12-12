package day10;

import common.AdventOfCodeBase;
import common.Part;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Day10 extends AdventOfCodeBase {

    public Day10(String inputFilename, Part part) {
        super(inputFilename, part);
    }

    @Override
    public String run() {
        List<Integer> results = processRegisterResults();

        if( isPart1() ) {
            int result = 20 * results.get(20)
                    + 60 * results.get(60)
                    + 100 * results.get(100)
                    + 140 * results.get(140)
                    + 180 * results.get(180)
                    + 220 * results.get(220);
            return Integer.valueOf(result).toString();
        } else {
            List<String> screen = new ArrayList<>();
            for (int i = 0; i < 6; i++) {
                String[] screenRow = new String[40];
                for (int j = 0; j < 40; j++) {
                    Integer position = ((40 * i) + j) + 1;
                    Integer positionValue = results.get(position);
                    if (positionValue - 1 <= j && j <= positionValue + 1) {
                        screenRow[j] = "#";
                    } else {
                        screenRow[j] = ".";
                    }
                }
                String row = Arrays.stream(screenRow).collect(Collectors.joining());
                screen.add(row);
            }

            return screen.stream().collect(Collectors.joining("\n"));
        }

    }

    private List<Integer> processRegisterResults() {
        Integer currentRegister = 1;
        List<Integer> results = new ArrayList<>();
        results.add(currentRegister);

        for (String line : lines) {
            String[] parts = line.split(" ");
            String command = parts[0];
            Integer value = parts.length == 1 ? null : Integer.valueOf(parts[1]);
            if ("noop".equals(command)) {
                results.add(currentRegister);
            } else if ("addx".equals(command)) {
                results.add(currentRegister);
                results.add(currentRegister);
                currentRegister = currentRegister + value;
            }
        }
        return results;
    }


}

