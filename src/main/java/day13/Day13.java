package day13;

import common.AdventOfCodeBase;
import common.Part;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Day13 extends AdventOfCodeBase {

    public Day13(String inputFilename, Part part) {
        super(inputFilename, part);
    }

    @Override
    public String run() {
        int answer = 0;
        List<Packet> packets = new ArrayList<>();
        int index = 1;
        for (int i = 0; i < lines.size(); i++) {
            Packet packet1 = new Packet(lines.get(i));
            packets.add(packet1);
            i++;
            Packet packet2 = new Packet(lines.get(i));
            packets.add(packet2);
            i++;

            if (isPart1()) {
                answer += (packet1.compareTo(packet2) > 0) ? index : 0;
                index++;
            }
        }
        if (isPart2()) {
            answer = 1;
            packets.add(new Packet("[[2]]"));
            packets.add(new Packet("[[6]]"));
            Collections.sort(packets);
            Collections.reverse(packets);
            int divider1Index = 0;
            int divider2Index = 2;
            for (int i = 0; i < packets.size(); i++) {
                if (packets.get(i).str.equals("[[2]]")) {
                    divider1Index = i + 1;
                } else if (packets.get(i).str.equals("[[6]]")) {
                    divider2Index = i + 1;
                }
            }
            answer = divider2Index * divider1Index;
        }
        return "" + answer;
    }

    class Packet implements Comparable<Packet> {
        List<Packet> children;
        int val;
        boolean integer = true;
        String str;

        public Packet(String packet) {
            str = packet;
            children = new ArrayList<>();
            if (packet.equals("[]")) {
                val = -1;
            }
            if (!packet.startsWith("[")) {
                val = Integer.parseInt(packet);
            } else {
                packet = packet.substring(1, packet.length() - 1);
                int level = 0;
                String tmp = "";
                for (char c : packet.toCharArray()) {
                    if (c == ',' && level == 0) {
                        children.add(new Packet(tmp));
                        tmp = "";
                    } else {
                        level += (c == '[') ? 1 : (c == ']') ? -1 : 0;
                        tmp += c;
                    }
                }
                if (!tmp.equals("")) {
                    children.add(new Packet(tmp));
                }
                integer = false;
            }
        }

        public int compareTo(Packet other) {
            if (integer && other.integer) {
                return other.val - val;
            }
            if (!integer && !other.integer) {
                for (int i = 0; i < Math.min(children.size(), other.children.size()); i++) {
                    int val = children.get(i).compareTo(other.children.get(i));
                    if (val != 0) {
                        return val;
                    }
                }
                return other.children.size() - children.size();
            }
            Packet lst1 = integer ? new Packet("[" + val + "]") : this;
            Packet lst2 = other.integer ? new Packet("[" + other.val + "]") : other;
            return lst1.compareTo(lst2);
        }
    }

}

