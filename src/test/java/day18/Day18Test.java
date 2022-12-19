package day18;

import common.Part;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Day18Test {

    private static final String INPUT = "/input/day18_input.txt";
    private static final String SAMPLE_INPUT = "/input/day18_sample_input.txt";


    @Test
    void run_part1_sample() {
        Day18 day = new Day18(SAMPLE_INPUT, Part.PART_ONE);
        String result = day.run();
        System.out.println("result= " + result);
        assertEquals(64, Integer.valueOf(result));
    }

    @Test
    void run_part1_sample2() {
        Day18 day = new Day18("/input/day18_sample_input2.txt", Part.PART_ONE);
        String result = day.run();
        System.out.println("result= " + result);
        assertEquals(10, Integer.valueOf(result));
    }

    @Test
    void run_part1() {
        Day18 day = new Day18(INPUT, Part.PART_ONE);
        String result = day.run();
        System.out.println("result= " + result);
        assertEquals(4244, Integer.valueOf(result));
    }

    @Test
    void run_part2_sample() {
        Day18 day = new Day18(SAMPLE_INPUT, Part.PART_TWO);
        String result = day.run();
        System.out.println("result= " + result);
        assertEquals(58, Integer.valueOf(result));
    }

    @Test
    void run_part2() {
        Day18 day = new Day18(INPUT, Part.PART_TWO);
        String result = day.run();
        System.out.println("result= " + result);
        assertEquals(0, Integer.valueOf(result));
    }

}