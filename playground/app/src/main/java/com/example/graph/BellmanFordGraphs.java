package com.example.graph;

import java.util.*;

import static java.lang.Integer.MAX_VALUE;

public class BellmanFordGraphs {
  private static final List<Integer> EMPTY_WEIGHTS = new ArrayList<>();

  private BellmanFordGraphs() {
  }

  public static Collection<Integer> getShortestPaths(Graph G, char s) {
    if (G.vertexes.length <= 1) {
      return EMPTY_WEIGHTS;
    }

    int round = 0;
    Map<Character, Integer> weights = new TreeMap<>();
    for (char v : G.vertexes) {
      weights.put(v, MAX_VALUE);
    }
    weights.put(s, 0);
    printRound(++round, weights);

    for (char v : G.vertexes) {
      for (Graph.Edge edge : G.edges) {
        if (canRelax(weights, edge)) {
          weights.put(edge.end, weights.get(edge.start) + edge.weight);
          printRound(++round, weights);
        }
      }
    }

    for (int i = 0; i < G.vertexes.length; i++) {
      if (canRelax(weights, G.edges[i])) {
        throw new IllegalStateException("Negative cycle found");
      }
    }

    return weights.values();
  }

  private static boolean canRelax(Map<Character, Integer> weights, Graph.Edge edge) {
    return weights.get(edge.start) != MAX_VALUE &&
      weights.get(edge.start) + edge.weight < weights.get(edge.end);
  }

  private static void printRound(int round, Map<Character, Integer> weights) {
    StringBuilder sb = new StringBuilder(round + ". ");
    for (int weight : weights.values()) {
      sb.append(weight == MAX_VALUE ? "∞ " : (weight + " "));
    }
    System.out.println(sb.substring(0, sb.length() - 1));
  }
}
