package day15;

import common.AdventOfCodeBase;
import common.Part;
import day14.Day14;
import org.checkerframework.checker.units.qual.A;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;


public class Day15 extends AdventOfCodeBase {

    public Day15(String inputFilename, Part part) {
        super(inputFilename, part);
    }

    public int rowCheck = 10;
    private static final int GRID_OFFSET = 0;
    private static final String BEACON = "B";
    private static final String SENSOR = "S";
    private static final String NO_BEACON = "#";

    @Override
    public String run() {
        int answer = 0;

        List<Sensor> sensorList = new ArrayList<>();
        List<Beacon> beaconList = new ArrayList<>();

        for( String line : lines ) {
            line = line.replace("Sensor at x=","");
            line = line.replace(", y=", ",");
            line = line.replace(": closest beacon is at x=",",");
            String[] parts = line.split(",");
            Beacon beacon = Beacon.of(Integer.valueOf(parts[2]),Integer.valueOf(parts[3]));
            Sensor sensor = Sensor.of(Integer.valueOf(parts[0]),Integer.valueOf(parts[1]), beacon);
            sensorList.add(sensor);
            beaconList.add(beacon);
        }

        int sensorMaxX = sensorList.stream().mapToInt(s->s.x).max().getAsInt();
        int sensorMinX = sensorList.stream().mapToInt(s->s.x).min().getAsInt();
        int beaconMaxX = beaconList.stream().mapToInt(b->b.x).max().getAsInt();
        int beaconMinX = beaconList.stream().mapToInt(b->b.x).min().getAsInt();

        int left = Math.min(sensorMinX, beaconMinX);
        int right = Math.min(sensorMaxX, beaconMaxX);

        String[] rowResult = new String [right+2];
        int resultOffset = 2;
        for( int i = left; i<right; i++ ) {
//            System.out.println("i = " + i);
            rowResult[i+resultOffset]=".";
            Point p = Point.of(i,10);
            Optional<Beacon> beaconAtPoint = beaconList.stream().filter(b->b.equals(p)).findFirst();
            if( beaconAtPoint.isPresent() ) {
                System.out.println("beaconAtPoint = " + beaconAtPoint);
                rowResult[i+resultOffset]=BEACON;
                continue;
            }

            for( Sensor sensor : sensorList ) {
                int sensorDeadZone = sensor.distanceFrom(sensor.beacon);
//                System.out.println("sensorDeadZone = " + sensorDeadZone);
                int distanceFromSensor = sensor.distanceFrom(p);
//                System.out.println("distanceFromSensor = " + distanceFromSensor);

                if( distanceFromSensor <= sensorDeadZone ) {
//                    System.out.println("add from position:"+i);
                    rowResult[i+resultOffset]=NO_BEACON;
                    answer++;
                    break;
                }
            }
        }
        System.out.println("Arrays.stream(rowResult) = " + Arrays.stream(rowResult).collect(Collectors.joining()));

        return ""+answer;
    }

    public String run2() {
        int answer = 0;
        String[][] grid = parseGrid();

        for( int y = 0; y<grid.length; y++ ) {
            System.out.print(String.format("%03d", y-GRID_OFFSET));
            for( int x = 0; x< grid[0].length; x++) {
                System.out.print(grid[y][x]);
            }
            System.out.print("\n");
        }

        /*
-02############.#.###########...
-01###########.###############..
000####S#####################...
001###############.######S##....
002##############.S########.....
003################SB#####......
004######################.......
005#####################........
006.#####################.......
007#.########S#######S####......
008##.###########.#########.....
009###.#########...#########....
010###.B#########.###########...
011##S#..#####################..
012#####..#####################.
013######..#.#################..
014#######..#.###S#######S###...
015B#######..###############....
016###########SB###########.....
017################S######....B.
018####S##################......
019######..##############.......
020#####....###S.#####S#........
021.###......#....#####.........
022..#.............###....B.....
023.................#...........
         */

        System.out.print("\n");
        System.out.print("\n");
        int checkHeight = rowCheck+GRID_OFFSET;
        for( int x = 0; x< grid[0].length; x++) {
            String value = grid[checkHeight][x];
            System.out.print(value);
            if( NO_BEACON.equals(value) || SENSOR.equals(value)) {
                answer++;
            }
        }


        return ""+answer;
    }

    private String[][] parseGrid() {
        List<Sensor> sensorList = new ArrayList<>();
        List<Beacon> beaconList = new ArrayList<>();

        for( String line : lines ) {
            line = line.replace("Sensor at x=","");
            line = line.replace(", y=", ",");
            line = line.replace(": closest beacon is at x=",",");
            String[] parts = line.split(",");
            sensorList.add(Sensor.of(Integer.valueOf(parts[0]),Integer.valueOf(parts[1])));
            beaconList.add(Beacon.of(Integer.valueOf(parts[2]),Integer.valueOf(parts[3])));
        }

        int sensorMaxX = sensorList.stream().mapToInt(s->s.x).max().getAsInt();
        int sensorMaxY = sensorList.stream().mapToInt(s->s.y).max().getAsInt();
        int beaconMaxX = beaconList.stream().mapToInt(b->b.x).max().getAsInt();
        int beaconMaxY = beaconList.stream().mapToInt(b->b.y).max().getAsInt();
        int width = GRID_OFFSET + Math.max(sensorMaxX, beaconMaxX);
        int height = GRID_OFFSET + Math.max(sensorMaxY, beaconMaxY);

        String[][] grid = new String[height][width];
        for( int y = 0; y<grid.length; y++ ) {
            for( int x = 0; x< grid[0].length; x++) {
                grid[y][x] = ".";
            }
        }

        for( int i=-0; i<sensorList.size(); i++) {
            Sensor sensor = sensorList.get(i);
            Beacon beacon = beaconList.get(i);
            grid[sensor.y][sensor.x] = SENSOR;
            grid[beacon.y][beacon.x] = BEACON;

            int distance = sensor.distanceFrom(beacon);
            for( int y = 0; y<grid.length; y++ ) {
                for( int x = 0; x< grid[0].length; x++) {
                    if( SENSOR.equals(grid[y][x]) || BEACON.equals(grid[y][x])) {
                        //Skip
                    }
                    else if( distance >= sensor.distanceFrom(Point.of(x,y))) {
                        grid[y][x] = NO_BEACON;
                    }
                }
            }
        }

        return grid;
    }

    private static class Sensor extends Point {

        Beacon beacon;
        public Sensor(int x, int y) {
            super(x,y);
        }

        public Sensor(int x, int y, Beacon beacon) {
            super(x,y);
            this.beacon = beacon;
        }

        public static Sensor of(int x, int y) {
            return new Sensor(x,y);
        }

        public static Sensor of(int x, int y, Beacon beacon) {
            return new Sensor(x,y, beacon);
        }
    }

    private static class Beacon extends Point {

        public Beacon(int x, int y) {
            super(x, y);
        }

        public static Beacon of(int x, int y) {
            return new Beacon(x,y);
        }
    }

    private static class Point {
        int x;
        int y;

        public Point(int x, int y) {
            this.x = x + GRID_OFFSET;
            this.y = y + GRID_OFFSET;
        }

        public static Point of(int x, int y) {
            return new Point(x,y);
        }

        public int distanceFrom(Point p) {
            return Math.abs(x-p.x) + Math.abs(y-p.y);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Point point = (Point) o;
            return x == point.x && y == point.y;
        }
    }

}

