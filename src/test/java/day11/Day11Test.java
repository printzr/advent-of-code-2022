package day11;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Day11Test {

    @Test
    void part1() {
        Day11 day11 = new Day11("/input/day11_input.txt");
        System.out.println("day10.part1() = " + day11.part1());
        //67830
    }

    @Test
    void part1Sample() {
        Day11 day11 = new Day11("/input/day11_sample_input.txt");
        String result = day11.part1();
        System.out.println("day10.part1() = " + result);
//        assertEquals(10605, Integer.valueOf(result));
        assertEquals("2713310158", result);
    }

    @Test
    void part2() {
        Day11 day11 = new Day11("/input/day11_input.txt");
        String result = day11.part2();;
        System.out.println(result);
    }

    @Test
    void part2Sample() {
        Day11 day11 = new Day11("/input/day10_sample_input.txt");
        String result = day11.part2();
        System.out.println("result = " + result);

        String expectedResult = "##..##..##..##..##..##..##..##..##..##..\n" +
                "###...###...###...###...###...###...###.\n" +
                "####....####....####....####....####....\n" +
                "#####.....#####.....#####.....#####.....\n" +
                "######......######......######......####\n" +
                "#######.......#######.......#######.....";

        assertEquals(expectedResult, result);
    }
}