package day8;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Day8Test {

    @Test
    public void testPart1SampleData() throws Exception {
        Day8 day8 = new Day8();
        int result = day8.runPart1("/input/day8_sample_input.txt");
        System.out.println("result = " + result);
        assertEquals(21, result);
    }

    @Test
    public void testPart1() throws Exception {
        Day8 day8 = new Day8();
        int result = day8.runPart1("/input/day8_input.txt");
        System.out.println("result = " + result);
    }

    @Test
    public void testPart2SampleData() throws Exception {
        Day8 day8 = new Day8();
        int result = day8.runPart2("/input/day8_sample_input.txt");
        System.out.println("result = " + result);
        assertEquals(8, result);
    }

    @Test
    public void testPart2Data() throws Exception {
        Day8 day8 = new Day8();
        int result = day8.runPart2("/input/day8_input.txt");
        System.out.println("result = " + result);
    }

}