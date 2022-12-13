package day12;

import common.AdventOfCodeBase;
import common.Part;

import java.lang.reflect.Array;
import java.util.*;
import java.util.stream.Collectors;

public class Day12 extends AdventOfCodeBase {

    public Day12(String inputFilename, Part part) {
        super(inputFilename, part);
    }

    @Override
    public String run() {
        final List<Square> squares = getSquares();
        final Square end = squares.stream().filter(Square::isEnd).findFirst()
                .orElseThrow(() -> new IllegalArgumentException("No end square found"));

        if( isPart1() ) {
            final Square start = squares.stream().filter(Square::isStart).findFirst()
                    .orElseThrow(() -> new IllegalArgumentException("No start square found"));
            return String.valueOf(dijkstra(start, end, squares));
        } else {
           int min = squares.stream()
                    .filter(x->x.getHeight() == 'a')
                    .map(x->dijkstra(x,end, squares))
                    .collect(Collectors.toList())
                    .stream().min(Integer::compare).get();
           return String.valueOf(min);
        }

    }

    //https://en.wikipedia.org/wiki/Dijkstra%27s_algorithm
    private int dijkstra(final Square start, final Square end, final List<Square> squares) {
        final PriorityQueue<Square> queue = new PriorityQueue<>();
        queue.offer(start);
        final Map<Square, Integer> distances = new HashMap<>();
        distances.put(start, 0);
        while (!queue.isEmpty()) {
            final Square current = queue.poll();
            final int dist = distances.get(current);
            final List<Square> neighbours = current.getNeighbours(squares);
            for (final Square n : neighbours) {
                int ndist = dist;
                ndist++;
                if (ndist < distances.getOrDefault(n, Integer.MAX_VALUE)) {
                    distances.put(n, ndist);
                    queue.add(n);
                }
            }
        }

        return distances.entrySet().stream()
                .filter(e -> e.getKey().equals(end)).
                collect(Collectors.toList()).stream()
                .map(Map.Entry::getValue)
                .mapToInt(d -> d)
                .min().orElse(Integer.MAX_VALUE);
    }

    private List<Square> getSquares() {
        final List<Square> squares = new ArrayList<>();
        for (int y = 0; y < lines.size(); y++) {
            final String line = lines.get(y);
            for (int x = 0; x < line.length(); x++) {
                final char height = line.charAt(x);
                final Square s = new Square(x, y, height);
                if (height == 'S') {
                    s.setHeight('a');
                    s.setStart(true);
                } else if (height == 'E') {
                    s.setHeight('z');
                    s.setEnd(true);
                }
                squares.add(s);
            }

        }
        return squares;
    }


    public class Square implements Comparable<Square> {
        private final int x;
        private final int y;
        private int height;
        private boolean start;
        private boolean end;

        public Square(final int x, final int y) {
            this.x = x;
            this.y = y;
        }

        public Square(final int x, final int y, final int height) {
            this.x = x;
            this.y = y;
            this.height = height;
        }

        public List<Square> getNeighbours(final List<Square> allSquares) {
            final List<Square> squares = new ArrayList<>();
            Square s = new Square(x - 1, y);
            if (allSquares.contains(s) && allSquares.get(allSquares.indexOf(s)).getHeight() <= (this.height + 1)) {
                squares.add(allSquares.get(allSquares.indexOf(s)));
            }
            s = new Square(x + 1, y);
            if (allSquares.contains(s) && allSquares.get(allSquares.indexOf(s)).getHeight() <= (this.height + 1)) {
                squares.add(allSquares.get(allSquares.indexOf(s)));
            }
            s = new Square(x, y - 1);
            if (allSquares.contains(s) && allSquares.get(allSquares.indexOf(s)).getHeight() <= (this.height + 1)) {
                squares.add(allSquares.get(allSquares.indexOf(s)));
            }
            s = new Square(x, y + 1);
            if (allSquares.contains(s) && allSquares.get(allSquares.indexOf(s)).getHeight() <= (this.height + 1)) {
                squares.add(allSquares.get(allSquares.indexOf(s)));
            }
            return squares;
        }

        @Override
        public String toString() {
            return "Square [x=" + x + ", y=" + y + ", height=" + height + "]";
        }

        @Override
        public int hashCode() {
            final int prime = 31;
            int result = 1;
            result = prime * result + x;
            result = prime * result + y;
            return result;
        }

        @Override
        public boolean equals(final Object obj) {
            if (this == obj)
                return true;
            if (obj == null)
                return false;
            if (getClass() != obj.getClass())
                return false;
            final Square other = (Square) obj;
            if (x != other.x)
                return false;
            if (y != other.y)
                return false;
            return true;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }

        public int getHeight() {
            return height;
        }

        public void setStart(final boolean start) {
            this.start = start;
        }

        public boolean isStart() {
            return this.start;
        }

        public void setEnd(final boolean end) {
            this.end = end;
        }

        public boolean isEnd() {
            return this.end;
        }

        public void setHeight(final int height) {
            this.height = height;
        }

        @Override
        public int compareTo(final Square o) {
            if (this.y != o.getY()) {
                return Integer.compare(this.y, o.getY());
            } else {
                return Integer.compare(this.x, o.getX());
            }
        }
    }
}

