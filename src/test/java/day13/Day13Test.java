package day13;

import common.Part;
import day12.Day12;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Day13Test {

    private static final String INPUT = "/input/day13_input.txt";
    private static final String SAMPLE_INPUT = "/input/day13_sample_input.txt";


    @Test
    void run_part1_sample() {
        Day13 day = new Day13(SAMPLE_INPUT, Part.PART_ONE);
        String result = day.run();
        System.out.println("result= " + result);
        assertEquals(13, Integer.valueOf(result));
    }

    @Test
    void run_part1() {
        Day13 day = new Day13(INPUT, Part.PART_ONE);
        String result = day.run();
        System.out.println("result= " + result);
//        assertEquals(0, Integer.valueOf(result));
    }

    @Test
    void run_part2_sample() {
        Day13 day = new Day13(SAMPLE_INPUT, Part.PART_TWO);
        String result = day.run();
        System.out.println("result= " + result);
        assertEquals(0, Integer.valueOf(result));
    }

    @Test
    void run_part2() {
        Day13 day = new Day13(INPUT, Part.PART_TWO);
        String result = day.run();
        System.out.println("result= " + result);
//        assertEquals(0, Integer.valueOf(result));
    }

}