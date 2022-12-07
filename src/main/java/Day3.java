import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import static java.util.Map.entry;
public class Day3 {

    
    public static void main(String[] args) throws Exception {
        Map<Character, Integer> values = Map.ofEntries(
                entry(Character.valueOf('a'),Integer.valueOf(1)),
                entry(Character.valueOf('b'),Integer.valueOf(2)),
                entry(Character.valueOf('c'),Integer.valueOf(3)),
                entry(Character.valueOf('d'),Integer.valueOf(4)),
                entry(Character.valueOf('e'),Integer.valueOf(5)),
                entry(Character.valueOf('f'),Integer.valueOf(6)),
                entry(Character.valueOf('g'),Integer.valueOf(7)),
                entry(Character.valueOf('h'),Integer.valueOf(8)),
                entry(Character.valueOf('i'),Integer.valueOf(9)),
                entry(Character.valueOf('j'),Integer.valueOf(10)),
                entry(Character.valueOf('k'),Integer.valueOf(11)),
                entry(Character.valueOf('l'),Integer.valueOf(12)),
                entry(Character.valueOf('m'),Integer.valueOf(13)),
                entry(Character.valueOf('n'),Integer.valueOf(14)),
                entry(Character.valueOf('o'),Integer.valueOf(15)),
                entry(Character.valueOf('p'),Integer.valueOf(16)),
                entry(Character.valueOf('q'),Integer.valueOf(17)),
                entry(Character.valueOf('r'),Integer.valueOf(18)),
                entry(Character.valueOf('s'),Integer.valueOf(19)),
                entry(Character.valueOf('t'),Integer.valueOf(20)),
                entry(Character.valueOf('u'),Integer.valueOf(21)),
                entry(Character.valueOf('v'),Integer.valueOf(22)),
                entry(Character.valueOf('w'),Integer.valueOf(23)),
                entry(Character.valueOf('x'),Integer.valueOf(24)),
                entry(Character.valueOf('y'),Integer.valueOf(25)),
                entry(Character.valueOf('z'),Integer.valueOf(26)),
                entry(Character.valueOf('A'),Integer.valueOf(27)),
                entry(Character.valueOf('B'),Integer.valueOf(28)),
                entry(Character.valueOf('C'),Integer.valueOf(29)),
                entry(Character.valueOf('D'),Integer.valueOf(30)),
                entry(Character.valueOf('E'),Integer.valueOf(31)),
                entry(Character.valueOf('F'),Integer.valueOf(32)),
                entry(Character.valueOf('G'),Integer.valueOf(33)),
                entry(Character.valueOf('H'),Integer.valueOf(34)),
                entry(Character.valueOf('I'),Integer.valueOf(35)),
                entry(Character.valueOf('J'),Integer.valueOf(36)),
                entry(Character.valueOf('K'),Integer.valueOf(37)),
                entry(Character.valueOf('L'),Integer.valueOf(38)),
                entry(Character.valueOf('M'),Integer.valueOf(39)),
                entry(Character.valueOf('N'),Integer.valueOf(40)),
                entry(Character.valueOf('O'),Integer.valueOf(41)),
                entry(Character.valueOf('P'),Integer.valueOf(42)),
                entry(Character.valueOf('Q'),Integer.valueOf(43)),
                entry(Character.valueOf('R'),Integer.valueOf(44)),
                entry(Character.valueOf('S'),Integer.valueOf(45)),
                entry(Character.valueOf('T'),Integer.valueOf(46)),
                entry(Character.valueOf('U'),Integer.valueOf(47)),
                entry(Character.valueOf('V'),Integer.valueOf(48)),
                entry(Character.valueOf('W'),Integer.valueOf(49)),
                entry(Character.valueOf('X'),Integer.valueOf(50)),
                entry(Character.valueOf('Y'),Integer.valueOf(51)),
                entry(Character.valueOf('Z'),Integer.valueOf(52))
        );

        InputStream inputStream = Day3.class.getResourceAsStream("/input/day3_input.txt");
        try (BufferedReader br
                     = new BufferedReader(new InputStreamReader(inputStream))) {

            int total = 0;

            String line;
            List<List<String>> groups = new ArrayList<>();
            List<String> newGroup = new ArrayList<>();
            while ((line = br.readLine()) != null) {
                System.out.println("line = " + line);
                
                newGroup.add(line);
                if( newGroup.size() == 3 ){
                    groups.add(newGroup);
                    newGroup = new ArrayList<>();
                }

            }
            for( List<String> group : groups ) {
                String x = group.get(0);
                String y = group.get(1);
                String z = group.get(2);

                //Find common
                char[] xChars = x.toCharArray();
                char[] yChars = y.toCharArray();
                char[] zChars = z.toCharArray();
                char match = ' ';
                boolean matchFound = false;
                for( char xTemp : xChars ) {
                    if( !matchFound ) {
                        for( char yTemp : yChars ) {
                            if(!matchFound){
                                for( char zTemp : zChars){
                                    if(!matchFound){
                                        if( xTemp == yTemp && yTemp == zTemp) {
                                            System.out.println("MATCH zTemp = " + zTemp);
                                            match = xTemp;
                                            matchFound = true;
                                        }
                                    }
                                }

                            }
                        }
                    }
                }

                System.out.println("match = " + match);
                int value = values.get(Character.valueOf(match));
                System.out.println("value = " + value);

                total = total + value;

            }
            System.out.println("groups.size() = " + groups.size());
            /*
            String x = line.substring(0,line.length()/2);
                String y = line.substring(line.length()/2);
                System.out.println("x = " + x);
                System.out.println("y = " + y);

                //Find common
                char[] xChars = x.toCharArray();
                char[] yChars = y.toCharArray();
                char match = ' ';
                boolean matchFound = false;
                for( char xTemp : xChars ) {
                    if( !matchFound ) {
                        for( char yTemp : yChars ) {
                            if(!matchFound){
                                if( xTemp == yTemp ) {
                                    System.out.println("MATCH yTemp = " + yTemp);
                                    match = xTemp;
                                    matchFound = true;
                                }
                            }
                        }
                    }
                }

                System.out.println("match = " + match);



                int value = values.get(Character.valueOf(match));
                System.out.println("value = " + value);

                total = total + value;
             */

            System.out.println("total = " + total);

        }
    }

}

/*
 System.out.println("line = " + line);
                String x = line.substring(0,line.length()/2);
                String y = line.substring(line.length()/2);
                System.out.println("x = " + x);
                System.out.println("y = " + y);

                //Find common
                char[] xChars = x.toCharArray();
                char[] yChars = y.toCharArray();
                char match = ' ';
                boolean matchFound = false;
                for( char xTemp : xChars ) {
                    if( !matchFound ) {
                        for( char yTemp : yChars ) {
                            if(!matchFound){
                                if( xTemp == yTemp ) {
                                    System.out.println("MATCH yTemp = " + yTemp);
                                    match = xTemp;
                                    matchFound = true;
                                }
                            }
                        }
                    }
                }

                System.out.println("match = " + match);

                Map<Character, Integer> values = Map.ofEntries(
                entry(Character.valueOf('a'),Integer.valueOf(1)),
                        entry(Character.valueOf('b'),Integer.valueOf(2)),
                        entry(Character.valueOf('c'),Integer.valueOf(3)),
                        entry(Character.valueOf('d'),Integer.valueOf(4)),
                        entry(Character.valueOf('e'),Integer.valueOf(5)),
                        entry(Character.valueOf('f'),Integer.valueOf(6)),
                        entry(Character.valueOf('g'),Integer.valueOf(7)),
                        entry(Character.valueOf('h'),Integer.valueOf(8)),
                        entry(Character.valueOf('i'),Integer.valueOf(9)),
                        entry(Character.valueOf('j'),Integer.valueOf(10)),
                        entry(Character.valueOf('k'),Integer.valueOf(11)),
                        entry(Character.valueOf('l'),Integer.valueOf(12)),
                        entry(Character.valueOf('m'),Integer.valueOf(13)),
                        entry(Character.valueOf('n'),Integer.valueOf(14)),
                        entry(Character.valueOf('o'),Integer.valueOf(15)),
                        entry(Character.valueOf('p'),Integer.valueOf(16)),
                        entry(Character.valueOf('q'),Integer.valueOf(17)),
                        entry(Character.valueOf('r'),Integer.valueOf(18)),
                        entry(Character.valueOf('s'),Integer.valueOf(19)),
                        entry(Character.valueOf('t'),Integer.valueOf(20)),
                        entry(Character.valueOf('u'),Integer.valueOf(21)),
                        entry(Character.valueOf('v'),Integer.valueOf(22)),
                        entry(Character.valueOf('w'),Integer.valueOf(23)),
                        entry(Character.valueOf('x'),Integer.valueOf(24)),
                        entry(Character.valueOf('y'),Integer.valueOf(25)),
                        entry(Character.valueOf('z'),Integer.valueOf(26)),
                        entry(Character.valueOf('A'),Integer.valueOf(27)),
                        entry(Character.valueOf('B'),Integer.valueOf(28)),
                        entry(Character.valueOf('C'),Integer.valueOf(29)),
                        entry(Character.valueOf('D'),Integer.valueOf(30)),
                        entry(Character.valueOf('E'),Integer.valueOf(31)),
                        entry(Character.valueOf('F'),Integer.valueOf(32)),
                        entry(Character.valueOf('G'),Integer.valueOf(33)),
                        entry(Character.valueOf('H'),Integer.valueOf(34)),
                        entry(Character.valueOf('I'),Integer.valueOf(35)),
                        entry(Character.valueOf('J'),Integer.valueOf(36)),
                        entry(Character.valueOf('K'),Integer.valueOf(37)),
                        entry(Character.valueOf('L'),Integer.valueOf(38)),
                        entry(Character.valueOf('M'),Integer.valueOf(39)),
                        entry(Character.valueOf('N'),Integer.valueOf(40)),
                        entry(Character.valueOf('O'),Integer.valueOf(41)),
                        entry(Character.valueOf('P'),Integer.valueOf(42)),
                        entry(Character.valueOf('Q'),Integer.valueOf(43)),
                        entry(Character.valueOf('R'),Integer.valueOf(44)),
                        entry(Character.valueOf('S'),Integer.valueOf(45)),
                        entry(Character.valueOf('T'),Integer.valueOf(46)),
                        entry(Character.valueOf('U'),Integer.valueOf(47)),
                        entry(Character.valueOf('V'),Integer.valueOf(48)),
                        entry(Character.valueOf('W'),Integer.valueOf(49)),
                        entry(Character.valueOf('X'),Integer.valueOf(50)),
                        entry(Character.valueOf('Y'),Integer.valueOf(51)),
                        entry(Character.valueOf('Z'),Integer.valueOf(52))
                );

                int value = values.get(Character.valueOf(match));
                System.out.println("value = " + value);

                total = total + value;

            }

            System.out.println("total = " + total);

        }
 */
