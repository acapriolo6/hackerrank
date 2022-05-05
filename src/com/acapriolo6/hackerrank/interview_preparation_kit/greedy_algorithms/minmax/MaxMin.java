package com.acapriolo6.hackerrank.interview_preparation_kit.greedy_algorithms.minmax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toList;

class Result {

    /*
     * Complete the 'maxMin' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts following parameters:
     *  1. INTEGER k
     *  2. INTEGER_ARRAY arr
     */

    public static int maxMin(int k, List<Integer> arr) {
        // Write your code here
        Collections.sort(arr);
        Point[] distances = new Point[arr.size()];
        for (int i = 0; i < arr.size() - 1; i++) {
            distances[i] = new Point(arr.get(i+1) - arr.get(i), i);
        }
        distances[arr.size() - 1] = new Point(null, arr.size() - 1);
        Arrays.sort(distances, new Comparator<Point>() {
            @Override
            public int compare(Point point, Point t1) {
                if (point.distance == null) {
                    return 1;
                }
                if (t1.distance == null) {
                    return -1;
                }
                return point.distance.compareTo(t1.distance);
            }
        });
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < k; i++) {
            if (arr.get(distances[i].index) > max) {
                max = arr.get(distances[i].index);
            }
            if (arr.get(distances[i].index) < min) {
                min = arr.get(distances[i].index);
            }
        }

        return max - min;
    }

    static class Point {
        Integer distance;
        int index;

        public Point(Integer distance, int index) {
            this.distance = distance;
            this.index = index;
        }
    }

}

public class MaxMin {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("OUTPUT_PATH_" + MaxMin.class.getName()));

        int n = Integer.parseInt(bufferedReader.readLine().trim());

        int k = Integer.parseInt(bufferedReader.readLine().trim());

        List<Integer> arr = IntStream.range(0, n).mapToObj(i -> {
                    try {
                        return bufferedReader.readLine().replaceAll("\\s+$", "");
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                })
                .map(String::trim)
                .map(Integer::parseInt)
                .collect(toList());

        int result = Result.maxMin(k, arr);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}
