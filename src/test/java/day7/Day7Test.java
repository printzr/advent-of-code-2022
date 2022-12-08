package day7;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Day7Test {

    private Day7 day7 = new Day7();

    @Test
    void runChallenge() throws Exception {
        String result = day7.runChallenge("/input/day7_input.txt");
        System.out.println("result = " + result);
    }

    @Test
    void runChallengeSample() throws Exception {
        String result = day7.runChallenge("/input/day7_sample_input.txt");
        System.out.println("result = " + result);
    }

}