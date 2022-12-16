package day15;

import common.AdventOfCodeBase;
import common.Part;
import org.apache.commons.lang3.StringUtils;

import java.util.*;


public class Day15 extends AdventOfCodeBase {

    public Day15(String inputFilename, Part part) {
        super(inputFilename, part);
    }

    public int rowCheck = 10;
    private static final int GRID_OFFSET = 0;
    private static final String BEACON = "B";
    private static final String SENSOR = "S";
    private static final String NO_BEACON = "#";

    //part2
    public int part2MaxCoordinate = 20;

    @Override
    public String run() {
        int answer = 0;


        List<Point> sensors = new ArrayList<>();
        List<Point> beacons = new ArrayList<>();
        List<Integer> distances = new ArrayList<>();
        List<Integer> alreadyBeacons = new ArrayList<>();
        for( String line : lines ) {
            line = line.replace("Sensor at x=","");
            line = line.replace(", y=", ",");
            line = line.replace(": closest beacon is at x=",",");
            String[] parts = line.split(",");
            int x1 = Integer.parseInt(parts[0]);
            int y1 = Integer.parseInt(parts[1]);
            int x2 = Integer.parseInt(parts[2]);
            int y2 = Integer.parseInt(parts[3]);

            if (y2 == rowCheck) {
                alreadyBeacons.add(x2);
            }
            sensors.add(Point.of(x1, y1));
            beacons.add(Point.of(x2, y2));
            distances.add(Math.abs(x1 - x2) + Math.abs(y1 - y2));
        }

        if(isPart1()) {
            for (int x = -5000000; x < 5000000; x++) {
                if (alreadyBeacons.contains(x)) {
                    continue;
                }
                boolean possible = checkPossible(x,rowCheck,sensors,distances, 0);
                if (!possible) {
                    answer++;
                }
            }
        }

        if (isPart2()) {
            TreeMap<Point, Point> sensorsTree = new TreeMap<>();
            TreeSet<Point> points = new TreeSet<Point>();

            for( int i = 0; i<sensors.size(); i++) {
                Point sensor = sensors.get(i);
                Point beacon = beacons.get(i);
                Point s = new Point(sensor.x, sensor.y);
                Point b = new Point(beacon.x, beacon.y);
                points.add(s);
                points.add(b);
                sensorsTree.put(s, b);
            }

            String lastResult = "";
            for(Map.Entry<Point, Point> me : sensorsTree.entrySet())
            {
                Point s = me.getKey();
                Point b = me.getValue();
                long dist = s.getDistM(b);
                String newResult = scanRing(s, dist, points, sensorsTree);
                if(StringUtils.isNotEmpty(newResult)) {
                    lastResult = newResult;
                }
            }
            return lastResult;
        }
        return "" + answer;
    }

    public String scanRing(Point s, long ring, TreeSet<Point> points, TreeMap<Point, Point> sensorsTree)
    {
        long count = ring+1;
        LinkedList<Point> dirs = new LinkedList<>();
        dirs.add(new Point(1,1)); // down right
        dirs.add(new Point(-1,1)); // down left
        dirs.add(new Point(-1,-1)); // up left
        dirs.add(new Point(1, -1)); //up right

        Point cur = s.add(new Point(0, -ring-1));

        String lastResult = "";
        for(Point dir : dirs)
            for(long step =0; step<count; step++)
            {
                cur = cur.add(dir);
                if (!pointImpossible(cur, points, sensorsTree))
                {
                    System.out.println(cur);
                    long n = cur.x * 4000000 + cur.y;
                    lastResult = ""+n;
                }
            }

        return lastResult;
    }

    public boolean pointImpossible(Point p, TreeSet<Point> points, TreeMap<Point, Point> sensorsTree)
    {
        if (p.x <= 0) return true;
        if (p.y <= 0) return true;
        if (p.x > 4000000) return true;
        if (p.y > 4000000) return true;


        if (points.contains(p)) return true;

        for(Map.Entry<Point, Point> me : sensorsTree.entrySet())
        {
            Point s = me.getKey();
            Point b = me.getValue();
            long dist = s.getDistM(b);
            long d = p.getDistM(s);
            if (d <= dist)
            {
                return true;
            }

        }
        return false;

    }

    public boolean checkPossible(int x, int y, List<Point> sensors, List<Integer> distances, int wiggle) {
        for (int i = 0; i < sensors.size(); i++) {
            if (Math.abs(x - sensors.get(i).x) + Math.abs(y - sensors.get(i).y) + wiggle <= distances.get(i)) {
                return false;
            }
        }
        return true;
    }


    private static class Point implements Comparable<Point>
    {
        final long x;
        final long y;
        final long z;
        final long w;

        public static Point of(long x, long y) {
            return new Point(x,y);
        }
        public static Point of(int x, int y) {
            return new Point(Integer.valueOf(x).longValue(),Integer.valueOf(y).longValue());
        }
        public Point(long x, long y)
        {
            this(x,y,0L,0L);
        }
        public Point(long x, long y, long z)
        {
            this(x,y,z,0L);
        }
        public Point(long x, long y, long z, long w)
        {
            this.x = x;
            this.y = y;
            this.z = z;
            this.w = w;
        }

        @Override
        public String toString()
        {
            return String.format("(%d %d %d %d)", x,y, z, w);
        }

        @Override
        public int hashCode()
        {
            return toString().hashCode();
        }

        @Override
        public boolean equals(Object o)
        {
            return toString().equals(o.toString());
        }

        @Override
        public int compareTo(Point p)
        {
            if (x < p.x) return -1;
            if (x > p.x) return 1;

            if (y < p.y) return -1;
            if (y > p.y) return 1;

            if (z < p.z) return -1;
            if (z > p.z) return 1;

            if (w < p.w) return -1;
            if (w > p.w) return 1;

            return 0;
        }

        public Point add(Point p)
        {
            return new Point(x + p.x, y +  p.y, z+p.z, w+p.w);
        }
        public Point mult(long m)
        {
            return new Point(x * m, y * m, z * m, w * m);
        }

        public long getDistM(Point p)
        {
            long dx=Math.abs(p.x - x);
            long dy=Math.abs(p.y - y);
            long dz=Math.abs(p.z - z);
            long dw=Math.abs(p.w - w);

            return dx+dy+dz+dw;

        }


    }

}

