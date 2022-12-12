package day9;

import common.Part;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Day9Test {

    @Test
    void part1Sample() {
        Day9 day9 = new Day9("/input/day9_sample_input.txt", Part.PART_ONE);
        String result = day9.run();
        System.out.println("result = " + result);
    }

    @Test
    void part1() {
        Day9 day9 = new Day9("/input/day9_input.txt", Part.PART_ONE);
        String result = day9.run();
        System.out.println("result = " + result);
    }

    @Test
    void distanceFormula() {
        int x1=0;
        int x2=1;
        int y1=0;
        int y2=2;

        double result = Math.sqrt(Math.pow((x1- x2),2)+Math.pow((y1- y2),2));
        System.out.println("result = " + result);
        System.out.println("Double.valueOf(result).intValue() = " + Double.valueOf(result).intValue());
    }

    @Test
    void part2Sample() {
        Day9 day9 = new Day9("/input/day9_sample_part2_input.txt", Part.PART_TWO);
        String result = day9.run();
        System.out.println("result = " + result);
        assertEquals("36", result);
    }

    @Test
    void part2() {
        Day9 day9 = new Day9("/input/day9_input.txt", Part.PART_TWO);
        String result = day9.run();
        System.out.println("result = " + result);
        //2273
    }
}