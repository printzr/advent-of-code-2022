package day13;

import common.AdventOfCodeBase;
import common.Part;

import java.util.*;
import java.util.stream.Collectors;

public class Day13 extends AdventOfCodeBase {

    public Day13(String inputFilename, Part part) {
        super(inputFilename, part);
    }

    @Override
    public String run() {
        if( isPart1() ) {
            return part1();
        }
        return "";
    }

    private String part1() {
        List<Integer> indicesWithRightOrder = new ArrayList<>();

        //Parse Input
        List<Object> packets = new ArrayList<>();
        for( String line : lines ) {
            if( line.trim().isEmpty() ) {
                continue;
            }
            stringPosition = 0;
            packets.add(buildList(line));
        }

        //Compare Pairs
        int index = 1;
        for( int i = 0; i<packets.size(); i = i+2 ){
            Object left = packets.get(i);
            Object right = packets.get(i+1);
            if( compareObjects(left,right)) {
                System.out.println("Add index = " + index);
                indicesWithRightOrder.add(index);
            }
            index++;
        }

        int result = indicesWithRightOrder.stream().reduce(0,Integer::sum);
        return String.valueOf(result);
    }

    private boolean compareObjects(Object left, Object right) {
        System.out.println("compare::left:"+left+"   right:"+right);
        if( isInteger(left) && isInteger(right)) {
            System.out.println("Value Check:"+(parseInt(left) <= parseInt(right)));
            return parseInt(left) <= parseInt(right);
//            return Character.getNumericValue((char)left) <= Character.getNumericValue((char)right);
        } else if (!isInteger(left) && !isInteger(right)) {
            List<Object> leftList = (List<Object>)left;
            List<Object> rightList = (List<Object>)right;
            if(leftList.isEmpty() && !rightList.isEmpty()) {
                System.out.println("LeftList Empty:TRUE");
                return true;
            }
//            if( leftList.size() > rightList.size() ){
//                System.out.println("LeftList more items:FALSE");
//                return false;
//            }
            if( allInt(leftList) && allInt(rightList)){
                for( int i = 0; i<leftList.size(); i++ ) {
                    int leftVal = parseInt(leftList.get(i));
                    if( i == rightList.size()) {
                        //right ran out of items
                        return true;
                    }
                    int rightVal = parseInt(rightList.get(i));
                    if( leftVal < rightVal ){
                        System.out.println("ListCompare:TRUE");
                        return true;
                    } else if (leftVal > rightVal) {
                        System.out.println("ListCompare:FALSE");
                        return false;
                    } else {
                        System.out.println("ListCompare:equal");
                    }
                }
            }

            boolean compareResult = true;
            for( int i = 0; i<leftList.size(); i++) {
                if( i == rightList.size()) {
                    //right out of items
                    return true;
                }
                compareResult = compareObjects(leftList.get(i),rightList.get(i));
                if( !compareResult) {
                    System.out.println("break");
                    break;
                }
            }
            System.out.println("ListCompare full...compareResult:"+compareResult);
            return compareResult;
        } else if( isInteger(left) && !isInteger(right)){
            return compareObjects(List.of(left),right);
        } else if( !isInteger(left) && isInteger(right)) {
            return compareObjects(left, List.of(right));
        }
        throw new RuntimeException("unexpected scenario");
    }

    private int parseInt(Object obj) {
        if( obj instanceof Integer) {
            return (int)obj;
        } else if( obj instanceof Character){
            return Character.getNumericValue((char)obj);
        } else {
            throw new RuntimeException("unexpected int value");
        }
    }

    private boolean allInt(List<Object> objList) {
        for( Object obj : objList ){
            if(!isInteger(obj)) {
                return false;
            }
        }
        return true;
    }

    private boolean isInteger(Object obj) {
        return obj instanceof Integer || obj instanceof Character;
    }

    static int stringPosition = 0; // the position in the String

    Object buildList(String nestedList) {
        List<Object> list = new ArrayList<>();

        while (stringPosition < nestedList.length()) {
            int cPosition = stringPosition;
            char c = nestedList.charAt(stringPosition++);

            if (c == '[') // add a sub-list via a recursive call
                list.add(buildList(nestedList));
            else if (c == ']') // stop building the list
                break;
            else if (c == ',') {} // do nothing
            else {
                if( (cPosition+1)<nestedList.length()) {
                    char nextC = nestedList.charAt(cPosition+1);
                    if( Character.isDigit(nextC)) {
//                        System.out.println("c = " + c);
//                        System.out.println("nextC = " + nextC);
//                        System.out.println("c+nextC = " + c+nextC);
//                        System.out.println("int c+nextC = " + Integer.valueOf(""+c+nextC));
                        list.add(Integer.valueOf(""+c+nextC));
                    } else {
                        list.add(Character.getNumericValue(c));
                    }
                } else {
                    list.add(c);
                }

            }
        
        }
        return list;
    }

}

