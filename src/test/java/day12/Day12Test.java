package day12;

import common.Part;
import day11.Day11;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Day12Test {

    @Test
    void run_part1_sample() {
        Day12 day = new Day12("/input/day12_sample_input.txt", Part.PART_ONE);
        String result = day.run();
        System.out.println("result= " + result);
        assertEquals(31, Integer.valueOf(result));
    }

    @Test
    void run_part1() {
        Day12 day = new Day12("/input/day12_input.txt", Part.PART_ONE);
        String result = day.run();
        System.out.println("result= " + result);
        assertEquals(520, Integer.valueOf(result));
    }

    @Test
    void run_part2_sample() {
        Day12 day = new Day12("/input/day12_sample_input.txt", Part.PART_TWO);
        String result = day.run();
        System.out.println("result= " + result);
        assertEquals(29, Integer.valueOf(result));
    }

    @Test
    void run_part2() {
        Day12 day = new Day12("/input/day12_input.txt", Part.PART_TWO);
        String result = day.run();
        System.out.println("result= " + result);
        assertEquals(508, Integer.valueOf(result));
    }
}