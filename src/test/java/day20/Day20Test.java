package day20;

import common.Part;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Day20Test {

    private static final String INPUT = "/input/day20_input.txt";
    private static final String SAMPLE_INPUT = "/input/day20_sample_input.txt";


    @Test
    void run_part1_sample() {
        Day20 day = new Day20(SAMPLE_INPUT, Part.PART_ONE);
        String result = day.run();
        System.out.println("result= " + result);
        assertEquals(33, Integer.valueOf(result));
    }

    @Test
    void run_part1() {
        Day20 day = new Day20(INPUT, Part.PART_ONE);
        String result = day.run();
        System.out.println("result= " + result);
        assertEquals(0, Integer.valueOf(result));
        //1014 to low
    }

    @Test
    void run_part2_sample() {
        Day20 day = new Day20(SAMPLE_INPUT, Part.PART_TWO);
        String result = day.run();
        System.out.println("result= " + result);
        assertEquals(0, Integer.valueOf(result));
    }

    @Test
    void run_part2() {
        Day20 day = new Day20(INPUT, Part.PART_TWO);
        String result = day.run();
        System.out.println("result= " + result);
        assertEquals(0, Integer.valueOf(result));
    }
}