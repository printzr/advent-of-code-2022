package day17;

import common.Part;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Day17Test {

    private static final String INPUT = "/input/day17_input.txt";
    private static final String SAMPLE_INPUT = "/input/day17_sample_input.txt";


    @Test
    void run_part1_sample() {
        Day17 day = new Day17(SAMPLE_INPUT, Part.PART_ONE);
        String result = day.run();
        System.out.println("result= " + result);
        assertEquals(0, Integer.valueOf(result));
    }

    @Test
    void run_part1() {
        Day17 day = new Day17(INPUT, Part.PART_ONE);
        String result = day.run();
        System.out.println("result= " + result);
        assertEquals(0, Integer.valueOf(result));
    }

    @Test
    void run_part2_sample() {
        Day17 day = new Day17(SAMPLE_INPUT, Part.PART_TWO);
        String result = day.run();
        System.out.println("result= " + result);
        assertEquals(0, Integer.valueOf(result));
    }

    @Test
    void run_part2() {
        Day17 day = new Day17(INPUT, Part.PART_TWO);
        String result = day.run();
        System.out.println("result= " + result);
        assertEquals(0, Integer.valueOf(result));
    }
}