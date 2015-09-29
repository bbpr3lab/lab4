package com.company;

import java.io.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Main {

    static class NameComparator implements Comparator<Beer> {

        @Override
        public int compare(Beer o1, Beer o2) {
            return o1.getName().compareTo(o2.getName());
        }
    }

    static class StyleComparator implements Comparator<Beer> {

        @Override
        public int compare(Beer o1, Beer o2) {
            return o1.getStyle().compareTo(o2.getStyle());
        }
    }

    static class StrengthComparator implements Comparator<Beer> {

        @Override
        public int compare(Beer o1, Beer o2) {
            return o1.getStrength().compareTo(o2.getStrength());
        }
    }

    static class Beer implements Serializable, Comparable<String> {
        String name;
        String style;
        BigDecimal strength;

        Beer(String name, String style, BigDecimal strength) {
            this.name = name;
            this.style = style;
            this.strength = strength;
        }

        public String getName() {
            return name;
        }

        public String getStyle() {
            return style;
        }

        public BigDecimal getStrength() {
            return strength;
        }

        @Override
        public String toString() {
            return "Beer{" +
                    "name='" + name + '\'' +
                    ", style='" + style + '\'' +
                    ", strength=" + strength +
                    '}';
        }


        @Override
        public int compareTo(String o) {
            return name.compareTo(o);
        }
    }

    static Comparator<Beer> beerComparatorFactory(String str) {
        switch (str) {
            case "name": return new NameComparator();
            case "style": return new StyleComparator();
            case "strength": return new StrengthComparator();
            default: throw new IllegalArgumentException("no such comparator");
        }
    }

    static List<Beer> beers = new ArrayList<>();

    static BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));

    static void listBeers(Comparator<Beer> cmp) {
        Collections.sort(beers, cmp);
        beers.forEach(System.out::println);
    }

    static String prompt() throws IOException {
        System.out.print(">");
        return stdin.readLine();
    }

    static void deleteBeer(String name) {
        Collections.sort(beers, new NameComparator());
        int i = Collections.binarySearch(beers, name);
        if (i >= 0)
            beers.remove(i);
    }

    static void loadBeers(String filename) throws IOException, ClassNotFoundException {
        try (
                FileInputStream fileInputStream = new FileInputStream(filename);
                ObjectInputStream in = new ObjectInputStream(fileInputStream);
                ) {
            beers = (List<Beer>) in.readObject();
        }
    }

    static void saveBeers(String filename) throws IOException {
        try (
                FileOutputStream fileOutputStream = new FileOutputStream(filename);
                ObjectOutputStream outputStream = new ObjectOutputStream(fileOutputStream);
                ) {
            outputStream.writeObject(beers);
        }
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        String input;
        boolean running = true;
        while ( running && (input = prompt()) != null) {

            String[] argz = input.split(" ");
            switch (argz[0]) {
                case "exit":
                    running = false;
                    break;
                case "add":
                    beers.add(new Beer(argz[1], argz[2], new BigDecimal(argz[3])));
                    break;
                case "list":
                    listBeers(beerComparatorFactory(argz.length < 2 ? "name" : argz[1]));
                    break;
                case "load":
                    loadBeers(argz[1]);
                    break;
                case "save":
                    saveBeers(argz[1]);
                    break;
                case "delete":
                    deleteBeer(argz[1]);
                    break;
            }
        }
    }
}
