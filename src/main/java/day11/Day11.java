package day11;

import common.AdventOfCodeBase;
import common.Part;
import day7.Day7;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.*;
import java.util.stream.Collectors;

public class Day11 extends AdventOfCodeBase {

    public Day11(String inputFilename, Part part) {
        super(inputFilename, part);
    }

    private static final String SQUARE_OPERATOR = "square";

    @Override
    public String run() {
        List<Monkey> monkeys = parseMonkeys();
        int rounds = isPart1() ? 20 : 10000;

        for( int i = 0; i < rounds; i++ ) {
            for( Monkey monkey : monkeys ) {
                monkey.turn(monkeys);
            }

            //Debug
            if( i == 0 || i == 19 || i == 999 || i == 1999 || i == 2999 || i == 3999 ) {
                for( Monkey m : monkeys ) {
                    System.out.println(m);
                }
            }
        }

        //Sort ascending on inspectionCount
        Collections.sort(monkeys, Comparator.comparingInt(x-> x.inspectionCount));

        //Debug
        for( Monkey m : monkeys ) {
            System.out.println(m);
        }
        //125721*123617
        //15541252857
        //15541252857
        BigInteger monkeyBusiness = BigInteger.valueOf(Long.valueOf(monkeys.get(monkeys.size()-1).inspectionCount)).multiply(BigInteger.valueOf(Long.valueOf(monkeys.get(monkeys.size()-2).inspectionCount))) ;
        return monkeyBusiness.toString();
    }

    private List<Monkey> parseMonkeys() {
        List<Monkey> monkeys = new ArrayList<>();
        Monkey monkeyBuilder = new Monkey();
        int row = 1;
        int monkeyId = 0;

        for (String line : lines) {
            //System.out.println("line = " + line);

            if( line.trim().equals("") ){
                continue;
            }
            if( line.startsWith("Monkey")) {
                //skip
            } else if(line.trim().startsWith("Starting")) {
                String[] parts = line.trim().split(" ");
                for( int i = 2; i<parts.length; i++) {
                    monkeyBuilder.items.add(BigInteger.valueOf(Long.valueOf(parts[i].replace(",", ""))));
                }
            } else if( line.trim().startsWith("Operation")) {
                String operation = line.substring(19);
                if( "old * old".equals(operation)) {
                    monkeyBuilder.operationOperator = SQUARE_OPERATOR;
                } else {
                    String[] parts = operation.split(" ");
                    monkeyBuilder.operationOperator = parts[1];
                    monkeyBuilder.operationValue = parts[2];
                }
            } else if (line.trim().startsWith("Test")) {
                String divisibleBy = line.substring(21);
                monkeyBuilder.testValue = Integer.valueOf(divisibleBy);
            } else if (line.trim().startsWith("If true")) {
                String ifTrue = line.substring(29);
                monkeyBuilder.trueMonkey = Integer.valueOf(ifTrue);
            } else if( line.trim().startsWith("If false")) {
                String ifFalse = line.substring(30);
                monkeyBuilder.falseMonkey = Integer.valueOf(ifFalse);
            }
            if( row > 1 && row % 6 == 0 ) {
                monkeyBuilder.monkeyId = monkeyId;
//                System.out.println("row = " + row);
//                System.out.println("monkeyBuilder = " + monkeyBuilder);
                monkeys.add(monkeyBuilder);
                monkeyBuilder = new Monkey();
                monkeyId++;
            }
            row++;
        }
        return monkeys;
    }

    private class Monkey {
        public int monkeyId;
        public List<BigInteger> items;
        public String operationOperator;
        public String operationValue;
        public int testValue;
        public int trueMonkey;
        public int falseMonkey;
        public int inspectionCount;

        public Monkey() {
            this.items = new ArrayList<>();
            this.inspectionCount = 0;
        }


        public void turn(List<Monkey> monkeys) {
            for( BigInteger item : items ) {
                //Inspect item
                BigInteger worryValue = applyWorryOperation(item);
                inspectionCount++;
                if( isPart1() ) {
                    //Divide by 3
                    worryValue = worryValue.divide(BigInteger.valueOf(Long.valueOf(3)));
                } else {
                    int reduceBy = 1;
                    for( Monkey m : monkeys) {
                        reduceBy = reduceBy*m.testValue;
                    }
                    worryValue = worryValue.divideAndRemainder(BigInteger.valueOf(Long.valueOf(reduceBy)))[1];
                }

                //Test item
                if( worryValue.divideAndRemainder(BigInteger.valueOf(Long.valueOf(testValue)))[1].intValue() == 0 ) {
                    monkeys.get(trueMonkey).catchItem(worryValue);
                } else {
                    monkeys.get(falseMonkey).catchItem(worryValue);
                }
            }
            items = new ArrayList<>();
        }

        public void catchItem(BigInteger item) {
            items.add(item);
        }

        private BigInteger applyWorryOperation(BigInteger item) {
            if( SQUARE_OPERATOR.equals(operationOperator)) {
                return item.multiply(item);
            } else if("*".equals(operationOperator)) {
                return item.multiply(BigInteger.valueOf(Long.valueOf(operationValue)));
            } else if("+".equals(operationOperator)) {
                return item.add(BigInteger.valueOf(Long.valueOf(operationValue)));
            } else {
                throw new RuntimeException("unexpected operator");
            }
        }

        @Override
        public String toString() {
            return "Monkey{" +
                    "monkeyId="+monkeyId+ '\'' +
                    "inspectionCount="+inspectionCount+ '\'' +
                    "items=" + items.stream().map(x-> x.toString()).collect(Collectors.joining(",")) +
                    ", operationOperator='" + operationOperator + '\'' +
                    ", operationValue='" + operationValue + '\'' +
                    ", testValue=" + testValue +
                    ", trueMonkey=" + trueMonkey +
                    ", falseMonkey=" + falseMonkey +
                    '}';
        }
    }

}

