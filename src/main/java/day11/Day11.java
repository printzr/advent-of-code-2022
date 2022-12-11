package day11;

import common.AdventOfCodeBase;
import day7.Day7;

import java.math.BigInteger;
import java.util.*;
import java.util.stream.Collectors;

public class Day11 extends AdventOfCodeBase {

    public Day11(String inputFilename) {
        super(inputFilename);
    }

    private static final String SQUARE_OPERATOR = "square";

    @Override
    public String part1() {
        List<Monkey> monkeys = parseMonkeys();

        //For 20 rounds
        for( int i = 0; i < 10000; i++ ) {
            System.out.println("i = " + i);
//            System.out.println("i = " + i);
            for( Monkey monkey : monkeys ) {
                monkey.turn(monkeys);
            }

            if( i == 0 || i == 19 || i == 9999 || i == 1999 || i == 2999 || i == 3999 ) {
                for( Monkey m : monkeys ) {
                    System.out.println(m);
                }
            }
        }

        Collections.sort(monkeys, Comparator.comparingInt(x-> x.inspectionCount));

        for( Monkey m : monkeys ) {
            System.out.println(m);
        }
        Integer monkeyBusiness = monkeys.get(monkeys.size()-1).inspectionCount * monkeys.get(monkeys.size()-2).inspectionCount;
        return monkeyBusiness.toString();
    }


    @Override
    public String part2() {
        return null;
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
                //Divide by 3
                //worryValue = worryValue / 3;
                //Test item
                if( worryValue.mod(BigInteger.valueOf(testValue)).intValue() == 0 ) {
//                    System.out.println("throw "+worryValue +" to "+trueMonkey);
                    monkeys.get(trueMonkey).catchItem(worryValue);
                } else {
                    monkeys.get(falseMonkey).catchItem(worryValue);
//                    System.out.println("throw "+worryValue +" to "+falseMonkey);
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

