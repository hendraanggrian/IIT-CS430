package com.example.graph;

import static java.lang.Integer.MIN_VALUE;

public class Graph {
  private static final Edge[] EMPTY_EDGES = new Edge[0];

  public char[] vertexes;
  public Edge[] edges;

  private Graph(char[] vertexes, Edge... edges) {
    this.vertexes = vertexes;
    this.edges = edges;
  }

  public static Graph undirectedGraphOf(char[] vertexes) {
    return undirectedGraphOf(vertexes, EMPTY_EDGES);
  }

  public static Graph undirectedGraphOf(char[] vertexes, Edge... edges) {
    Edge[] bidirectionalEdges = new Edge[edges.length * 2];
    for (int i = 0; i < edges.length; i++) {
      Edge edge = edges[i];
      bidirectionalEdges[i * 2] = edge;
      bidirectionalEdges[i * 2 + 1] = new Edge(edge.end, edge.start, edge.weight);
    }
    return new Graph(vertexes, bidirectionalEdges);
  }

  public static Graph directedGraphOf(char[] vertexes) {
    return directedGraphOf(vertexes, EMPTY_EDGES);
  }

  public static Graph directedGraphOf(char[] vertexes, Edge... edges) {
    return new Graph(vertexes, edges);
  }

  public static class Edge {
    char start;
    char end;
    int weight;

    public Edge(char start, char end) {
      this(start, end, MIN_VALUE);
    }

    public Edge(char start, char end, int weight) {
      this.start = start;
      this.end = end;
      this.weight = weight;
    }
  }
}
