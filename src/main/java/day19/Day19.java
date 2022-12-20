package day19;

import common.AdventOfCodeBase;
import common.Part;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Day19 extends AdventOfCodeBase {

    public Day19(String inputFilename, Part part) {
        super(inputFilename, part);
    }

    @Override
    public String run() {
        long answer = 0;

        List<Blueprint> blueprints = new ArrayList<>();

        Pattern r = Pattern.compile("(\\d+)");

        for( String line : lines ) {
            Matcher m = r.matcher(line);
            List<Integer> numbers = m.results().map(MatchResult::group).map(Integer::valueOf).collect(Collectors.toList());
            Blueprint blueprint = new Blueprint(numbers);
            System.out.println(blueprint);
            blueprints.add(blueprint);
        }

        for( Blueprint blueprint : blueprints ) {
            blueprint.geodesOpened = determineGeodesOpened(blueprint);
        }

        answer = blueprints.stream().map(Blueprint::qualityLevel).reduce(0,Integer::sum);

        return "" + answer;
    }

    private int determineGeodesOpened(Blueprint blueprint) {
        Robots robots = new Robots();
        Resources resources = new Resources();

        //24 minutes
        for( int i = 1; i<=24; i++ ) {
            System.out.println("minute = " + i);
            RobotFactory factory = new RobotFactory(blueprint);
            factory.consumeResources(resources, robots, (23-i));
            robots.harvestResouces(resources);
            factory.buildRobots(robots);
            System.out.println("resources = " + resources);
            System.out.println("robots = " + robots);
            System.out.println("\n");
        }
        return resources.geodes;
    }

    private class RobotFactory {
        Blueprint blueprint;
        int pendingOreRobots = 0;
        int pendingClayRobots = 0;
        int pendingObsidianRobots = 0;
        int pendingGeodeRobots = 0;

        public RobotFactory( Blueprint blueprint) {
            this.blueprint = blueprint;
        }

        public void consumeResources(Resources resources, Robots robots, int remainingTime) {
            if( blueprint.geodeRobotBluePrint.canBuild(resources)){
                System.out.println("Start geode robot");
                pendingGeodeRobots++;
                blueprint.geodeRobotBluePrint.consumeResources(resources);
            }
            else if( blueprint.obsidianRobotBluePrint.canBuild(resources)
            && robots.obsidianRobots < blueprint.maxObsidianPerMinute()
                    && (resources.obsidian+remainingTime*robots.obsidianRobots<remainingTime*blueprint.maxObsidianPerMinute())){
                System.out.println("Start obsidian robot");
                pendingObsidianRobots++;
                blueprint.obsidianRobotBluePrint.consumeResources(resources);
            }
            else if( blueprint.clayRobotBluePrint.canBuild(resources)
                    && robots.clayRobots < blueprint.obsidianRobotBluePrint.clayCost
                    && (resources.clay+remainingTime*robots.clayRobots<remainingTime*blueprint.obsidianRobotBluePrint.clayCost)){
                System.out.println("Start clay robot");
                pendingClayRobots++;
                blueprint.clayRobotBluePrint.consumeResources(resources);
            }
            else if( blueprint.oreRobotBluePrint.canBuild(resources)
                    && (robots.oreRobots < blueprint.maxOrePerMinute())
                    && (resources.ore+remainingTime*robots.oreRobots<remainingTime*blueprint.maxOrePerMinute())){
                System.out.println("Start ore robot");
                pendingOreRobots++;
                blueprint.oreRobotBluePrint.consumeResources(resources);
            }
        }

        public void buildRobots(Robots robots) {
            robots.buildOreRobot(pendingOreRobots);
            robots.buildClayRobot(pendingClayRobots);
            robots.buildObsidianRobot(pendingObsidianRobots);
            robots.buildGeodeRobot(pendingGeodeRobots);
        }
    }

    private class Robots {
        int oreRobots = 1;
        int clayRobots = 0;
        int obsidianRobots = 0;
        int geodesRobots = 0;

        public void buildOreRobot(int num) {
            oreRobots+=num;
        }
        public void buildClayRobot(int num) {
            clayRobots+=num;
        }
        public void buildObsidianRobot(int num){
            obsidianRobots+=num;;
        }
        public void buildGeodeRobot(int num){
            geodesRobots+=num;
        }

        public void harvestResouces(Resources resources) {
            resources.collectOre(oreRobots);
            resources.collectClay(clayRobots);
            resources.collectObsidian(obsidianRobots);
            resources.collectGeode(geodesRobots);
        }

        @Override
        public String toString() {
            return "Robots{" +
                    "oreRobots=" + oreRobots +
                    ", clayRobots=" + clayRobots +
                    ", obsidianRobots=" + obsidianRobots +
                    ", geodesRobots=" + geodesRobots +
                    '}';
        }
    }

    private class Resources {
        int ore = 0;
        int clay = 0;
        int obsidian = 0;
        int geodes = 0;

        public void collectOre(int num) {
            ore+=num;
        }
        public void collectClay(int num) {
            clay+=num;
        }
        public void collectObsidian(int num){
            obsidian+=num;
        }
        public void collectGeode(int num){
            geodes+=num;
        }

        @Override
        public String toString() {
            return "Resources{" +
                    "ore=" + ore +
                    ", clay=" + clay +
                    ", obsidian=" + obsidian +
                    ", geodes=" + geodes +
                    '}';
        }
    }

    private class Blueprint {
        int id;
        RobotBluePrint oreRobotBluePrint;
        RobotBluePrint clayRobotBluePrint;
        RobotBluePrint obsidianRobotBluePrint;
        RobotBluePrint geodeRobotBluePrint;
        int geodesOpened=0;

        public Blueprint(int id, RobotBluePrint oreRobotBluePrint, RobotBluePrint clayRobotBluePrint, RobotBluePrint obsidianRobotBluePrint, RobotBluePrint geodeRobotBluePrint) {
            this.id = id;
            this.oreRobotBluePrint = oreRobotBluePrint;
            this.clayRobotBluePrint = clayRobotBluePrint;
            this.obsidianRobotBluePrint = obsidianRobotBluePrint;
            this.geodeRobotBluePrint = geodeRobotBluePrint;
        }

        public int maxOrePerMinute() {
            List<Integer> oreCosts = List.of(
                    oreRobotBluePrint.oreCost
                    , clayRobotBluePrint.oreCost
                    , obsidianRobotBluePrint.oreCost
                    , geodeRobotBluePrint.oreCost
            );
            return oreCosts.stream().max(Comparator.naturalOrder()).get();
        }

        public int maxClayPerMinute() {
            List<Integer> clayCosts = List.of(
                    oreRobotBluePrint.clayCost
                    , clayRobotBluePrint.clayCost
                    , obsidianRobotBluePrint.clayCost
                    , geodeRobotBluePrint.clayCost
            );
            return clayCosts.stream().max(Comparator.naturalOrder()).get();
        }

        public int maxObsidianPerMinute() {
            List<Integer> obsidianCosts = List.of(
                    oreRobotBluePrint.obsidianCost
                    , clayRobotBluePrint.obsidianCost
                    , obsidianRobotBluePrint.obsidianCost
                    , geodeRobotBluePrint.obsidianCost
            );
            return obsidianCosts.stream().max(Comparator.naturalOrder()).get();
        }

        @Override
        public String toString() {
            return "Blueprint{" +
                    "id=" + id +
                    ", oreRobotBluePrint=" + oreRobotBluePrint +
                    ", clayRobotBluePrint=" + clayRobotBluePrint +
                    ", obsidianRobotBluePrint=" + obsidianRobotBluePrint +
                    ", geodeRobotBluePrint=" + geodeRobotBluePrint +
                    '}';
        }

        public Blueprint(List<Integer> numbers) {
            this.id = numbers.get(0);
            this.oreRobotBluePrint = new RobotBluePrint(numbers.get(1),0,0);
            this.clayRobotBluePrint = new RobotBluePrint(numbers.get(2),0,0);
            this.obsidianRobotBluePrint = new RobotBluePrint(numbers.get(3),numbers.get(4),0);
            this.geodeRobotBluePrint = new RobotBluePrint(numbers.get(5),0,numbers.get(6));
        }

        public int qualityLevel() {
            System.out.println("id = " + id);
            System.out.println("geodesOpened = " + geodesOpened);
            System.out.println("(id&geodesOpened) = " + (id*geodesOpened));
            return id * geodesOpened;
        }
    }

    private class RobotBluePrint {
        int oreCost;
        int clayCost;
        int obsidianCost;

        public RobotBluePrint(int oreCost, int clayCost, int obsidianCost) {
            this.oreCost = oreCost;
            this.clayCost = clayCost;
            this.obsidianCost = obsidianCost;
        }

        public boolean canBuild(Resources resources) {
            return oreCost <= resources.ore
                    && clayCost <= resources.clay
                    && obsidianCost <= resources.obsidian;
        }

        public void consumeResources(Resources resources){
            resources.ore-=oreCost;
            resources.clay-=clayCost;
            resources.obsidian-=obsidianCost;
        }

        @Override
        public String toString() {
            return "RobotBluePrint{" +
                    "oreCost=" + oreCost +
                    ", clayCost=" + clayCost +
                    ", obsidianCost=" + obsidianCost +
                    '}';
        }
    }

}
