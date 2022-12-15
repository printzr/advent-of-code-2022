package day15;

import common.Part;
import day14.Day14;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Day15Test {

    private static final String INPUT = "/input/day15_input.txt";
    private static final String SAMPLE_INPUT = "/input/day15_sample_input.txt";


    @Test
    void run_part1_sample() {
        Day15 day = new Day15(SAMPLE_INPUT, Part.PART_ONE);
        String result = day.run();
        System.out.println("result= " + result);
        assertEquals(26, Integer.valueOf(result));
    }

    @Test
    void run_part1() {
        Day15 day = new Day15(INPUT, Part.PART_ONE);
        day.rowCheck = 2000000;
        String result = day.run();
        System.out.println("result= " + result);
        assertEquals(0, Integer.valueOf(result));
    }

    @Test
    void run_part2_sample() {
        Day15 day = new Day15(SAMPLE_INPUT, Part.PART_TWO);
        String result = day.run();
        System.out.println("result= " + result);
        assertEquals(93, Integer.valueOf(result));
    }

    @Test
    void run_part2() {
        Day15 day = new Day15(INPUT, Part.PART_TWO);
        String result = day.run();
        System.out.println("result= " + result);
        assertEquals(26139, Integer.valueOf(result));
    }

}