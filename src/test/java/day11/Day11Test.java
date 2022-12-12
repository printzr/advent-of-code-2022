package day11;

import common.Part;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Day11Test {

    @Test
    void part1() {
        Day11 day11 = new Day11("/input/day11_input.txt", Part.PART_ONE);
        String result = day11.run();
        System.out.println("day10.part1() = " + result);
        assertEquals(67830, Integer.valueOf(result));
    }

    @Test
    void part1Sample() {
        Day11 day11 = new Day11("/input/day11_sample_input.txt", Part.PART_ONE);
        String result = day11.run();
        System.out.println("day11.part1() = " + result);
        assertEquals(10605, Integer.valueOf(result));
    }

    @Test
    void part2() {
        Day11 day11 = new Day11("/input/day11_input.txt", Part.PART_TWO);
        String result = day11.run();;
        System.out.println(result);
        assertEquals("15305381442", result);
    }

    @Test
    void part2Sample() {
        Day11 day11 = new Day11("/input/day10_sample_input.txt", Part.PART_TWO);
        String result = day11.run();
        System.out.println("result = " + result);
        assertEquals("2713310158", result);
    }
}