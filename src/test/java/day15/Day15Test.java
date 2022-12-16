package day15;

import common.Part;
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
        assertEquals(5181556, Integer.valueOf(result));
    }

    @Test
    void run_part2_sample() {
        Day15 day = new Day15(SAMPLE_INPUT, Part.PART_TWO);
        String result = day.run();
        System.out.println("result= " + result);
        assertEquals(56000011, Integer.valueOf(result));
    }

    @Test
    void run_part2() {
        Day15 day = new Day15(INPUT, Part.PART_TWO);
        String result = day.run();
        day.part2MaxCoordinate = 4000000;
        System.out.println("result= " + result);
        assertEquals("12817603219131", result);
    }

}