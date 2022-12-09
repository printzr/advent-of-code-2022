package day9;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Day9Test {

    @Test
    void part1Sample() {
        Day9 day9 = new Day9("/input/day9_sample_input.txt");
        String result = day9.part1();
    }

    @Test
    void part1() {
        Day9 day9 = new Day9("/input/day9_input.txt");
        String result = day9.part1();
    }

    @Test
    void part2Sample() {
        Day9 day9 = new Day9("/input/day9_sample_input.txt");
        String result = day9.part2();
    }

    @Test
    void part2() {
        Day9 day9 = new Day9("/input/day9_input.txt");
        String result = day9.part2();
    }
}