package common;

import java.util.List;

public abstract class AdventOfCodeBase {
    protected String inputFilename;
    protected List<String> lines;
    protected Part part;

    public AdventOfCodeBase(String inputFilename) {
        this.inputFilename = inputFilename;
        this.lines = FileUtils.getInputLines(inputFilename);
        this.part = Part.PART_ONE;
    }

    public AdventOfCodeBase(String inputFilename, Part part) {
        this.inputFilename = inputFilename;
        this.lines = FileUtils.getInputLines(inputFilename);
        this.part = part;
    }

    protected boolean isPart1() {
        return Part.PART_ONE.equals(part);
    }

    protected boolean isPart2() {
        return Part.PART_TWO.equals(part);
    }

    public abstract String run();

}
