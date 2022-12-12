package day10;

import common.Part;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Day10Test {

    @Test
    void part1() {
        Day10 day10 = new Day10("/input/day10_input.txt", Part.PART_ONE);
        System.out.println("day10.part1() = " + day10.run());
        //13180
    }

    @Test
    void part1Sample() {
        Day10 day10 = new Day10("/input/day10_sample_input.txt", Part.PART_ONE);
        String result = day10.run();
        System.out.println("day10.part1() = " + result);
        assertEquals(13140, Integer.valueOf(result));
    }

    @Test
    void part2() {
        Day10 day10 = new Day10("/input/day10_input.txt", Part.PART_TWO);
        String result = day10.run();;
        System.out.println(result);
        //EZFCHJAB
    }

    @Test
    void part2Sample() {
        Day10 day10 = new Day10("/input/day10_sample_input.txt", Part.PART_TWO);
        String result = day10.run();
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