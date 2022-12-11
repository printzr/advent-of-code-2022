package day10;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Day10Test {

    @Test
    void part1() {
        Day10 day10 = new Day10("/input/day10_input.txt");
        System.out.println("day10.part1() = " + day10.part1());
        //13180
    }

    @Test
    void part1Sample() {
        Day10 day10 = new Day10("/input/day10_sample_input.txt");
        String result = day10.part1();
        System.out.println("day10.part1() = " + result);
        assertEquals(13140, Integer.valueOf(result));
    }

    @Test
    void part2() {
        Day10 day10 = new Day10("/input/day10_input.txt");
        String result = day10.part2();;
        System.out.println(result);
        //EZFCHJAB
    }

    @Test
    void part2Sample() {
        Day10 day10 = new Day10("/input/day10_sample_input.txt");
        String result = day10.part2();
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