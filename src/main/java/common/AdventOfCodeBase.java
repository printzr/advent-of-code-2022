package common;

import java.util.List;

public abstract class AdventOfCodeBase {
    protected String inputFilename;
    protected List<String> lines;


    public AdventOfCodeBase(String inputFilename) {
        this.inputFilename = inputFilename;
        this.lines = FileUtils.getInputLines(inputFilename);
    }

    public abstract String part1();
    public abstract String part2();


}
