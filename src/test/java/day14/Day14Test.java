package day14;

import common.Part;
import day13.Day13;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Day14Test {

    private static final String INPUT = "/input/day14_input.txt";
    private static final String SAMPLE_INPUT = "/input/day14_sample_input.txt";


    @Test
    void run_part1_sample() {
        Day13 day = new Day13(SAMPLE_INPUT, Part.PART_ONE);
        String result = day.run();
        System.out.println("result= " + result);
        assertEquals(0, Integer.valueOf(result));
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