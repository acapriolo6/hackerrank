package com.acapriolo6.hackerrank.interview_preparation_kit.greedy_algorithms.reverse_shuffle_merge;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

class Result {

    /*
     * Complete the 'reverseShuffleMerge' function below.
     *
     * The function is expected to return a STRING.
     * The function accepts STRING s as parameter.
     */

    public static String reverseShuffleMerge(String s) {
        // Write your code here
        return reverseShuffleMergeRec(s);
    }

    private static String reverseShuffleMergeRec(String s) {
        if (s.length() == 1) {
            return s;
        }
        //I know that as the merge is combining of the reverse and a shuffle so I'm looking for a string that is half size of s.
        Map<Character, Integer> count = new HashMap<>();
        for (Character c : s.toCharArray()) {
            count.put(c, count.getOrDefault(c, 0) + 1);
        }
        for (Character c : count.keySet()) {
            count.put(c, count.getOrDefault(c, 0) / 2);
        }
        StringBuilder result = new StringBuilder();
        for (int i = s.length() - 1; i >= 0; i--) {
            char c = s.charAt(i);
            Integer n = count.get(c);
            if (n > 0) {
                Character smallest = count.entrySet().stream()
                        .filter(it -> it.getValue() > 0)
                        .map(Map.Entry::getKey)
                        .sorted()
                        .findFirst().orElse('z');
                if (smallest == c && i > s.length() / 2 && ((n - 1) / 2) > 1 ) {
                    result.append(c);
                    count.put(c, n - 1);
                }else if (i <= s.length() / 2) {
                    result.append(c);
                    count.put(c, n - 1);
                }
            }
        }
        return result.toString();
    }

}

public class ReverseShuffleMerge {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("OUTPUT_PATH"));

        String s = bufferedReader.readLine();

        String result = Result.reverseShuffleMerge(s);

        bufferedWriter.write(result);
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}

