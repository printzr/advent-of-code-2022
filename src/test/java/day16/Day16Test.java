package day16;

import common.Part;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Day16Test {

    private static final String INPUT = "/input/day16_input.txt";
    private static final String SAMPLE_INPUT = "/input/day16_sample_input.txt";


    @Test
    void run_part1_sample() {
        Day16 day = new Day16(SAMPLE_INPUT, Part.PART_ONE);
        String result = day.run();
        System.out.println("result= " + result);
        assertEquals(1651, Integer.valueOf(result));
    }

    @Test
    void run_part1() {
        Day16 day = new Day16(INPUT, Part.PART_ONE);
        String result = day.run();
        System.out.println("result= " + result);
        assertEquals(0, Integer.valueOf(result));
    }

    @Test
    void run_part2_sample() {
        Day16 day = new Day16(SAMPLE_INPUT, Part.PART_TWO);
        String result = day.run();
        System.out.println("result= " + result);
        assertEquals(0, Integer.valueOf(result));
    }

    @Test
    void run_part2() {
        Day16 day = new Day16(INPUT, Part.PART_TWO);
        String result = day.run();
        System.out.println("result= " + result);
        assertEquals(0, Integer.valueOf(result));
    }

}