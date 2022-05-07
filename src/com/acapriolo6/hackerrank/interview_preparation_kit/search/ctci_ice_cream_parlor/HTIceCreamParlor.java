package com.acapriolo6.hackerrank.interview_preparation_kit.search.ctci_ice_cream_parlor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

class Result {

    /*
     * Complete the 'whatFlavors' function below.
     *
     * The function accepts following parameters:
     *  1. INTEGER_ARRAY cost
     *  2. INTEGER money
     */

    public static void whatFlavors(List<Integer> costs, int money) {
        // Write your code here
        Map<Integer, List<String>> elements = new HashMap<>();
        for (int i = 0; i < costs.size(); i++) {
            Integer cost = costs.get(i);
            if (money > cost) {
                List<String> indexes = elements.getOrDefault(cost, new LinkedList<>());
                indexes.add(cost + "_" + i);
                elements.put(cost, indexes);
            }
        }
        for (int i = 0; i < costs.size(); i++) {
            Integer cost = costs.get(i);
            if (money > cost) {
                if (elements.containsKey(money - cost)) {
                    List<String> indexes = elements.get(money - cost);
                    int j = 0;
                    for (j = 0; j < indexes.size(); j++) {
                        String[] s = indexes.get(j).split("_");
                        if (!s[1].equalsIgnoreCase("" + i)){
                            System.out.println((i +1) + " " + (Integer.parseInt(s[1])+1));
                            return;
                        }
                    }
                }
            }
        }
    }

}

public class HTIceCreamParlor {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        int t = Integer.parseInt(bufferedReader.readLine().trim());

        IntStream.range(0, t).forEach(tItr -> {
            try {
                int money = Integer.parseInt(bufferedReader.readLine().trim());

                int n = Integer.parseInt(bufferedReader.readLine().trim());

                List<Integer> cost = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                        .map(Integer::parseInt)
                        .collect(toList());

                Result.whatFlavors(cost, money);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        bufferedReader.close();
    }
}

