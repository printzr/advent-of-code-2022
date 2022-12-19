package day19;

import common.Part;
import day18.Day18;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Day19Test {

    private static final String INPUT = "/input/day19_input.txt";
    private static final String SAMPLE_INPUT = "/input/day19_sample_input.txt";


    @Test
    void run_part1_sample() {
        Day18 day = new Day18(SAMPLE_INPUT, Part.PART_ONE);
        String result = day.run();
        System.out.println("result= " + result);
        assertEquals(0, Integer.valueOf(result));
    }

    @Test
    void run_part1() {
        Day18 day = new Day18(INPUT, Part.PART_ONE);
        String result = day.run();
        System.out.println("result= " + result);
        assertEquals(0, Integer.valueOf(result));
    }

    @Test
    void run_part2_sample() {
        Day18 day = new Day18(SAMPLE_INPUT, Part.PART_TWO);
        String result = day.run();
        System.out.println("result= " + result);
        assertEquals(0, Integer.valueOf(result));
    }

    @Test
    void run_part2() {
        Day18 day = new Day18(INPUT, Part.PART_TWO);
        String result = day.run();
        System.out.println("result= " + result);
        assertEquals(0, Integer.valueOf(result));
    }
}