<!-- hotfix: KaTeX -->
<!-- https://github.com/yzane/vscode-markdown-pdf/issues/21/ -->
<script type="text/javascript" src="http://cdn.mathjax.org/mathjax/latest/MathJax.js?config=TeX-AMS-MML_HTMLorMML"></script>
<script type="text/x-mathjax-config">MathJax.Hub.Config({ tex2jax: { inlineMath: [['$', '$']] }, messageStyle: 'none' });</script>

# [Homework 5](https://github.com/hendraanggrian/IIT-CS430/blob/assets/assignments/hw5.pdf)

## Problem 1

> Refer to the weighted directed graph below and adopt the Bellman-Ford to find
  shortest paths sourced frVom **vertex $\bf 0$** to all other vertices. Answer the
  following questions.
>
> ![Figure 1](https://github.com/hendraanggrian/IIT-CS430/raw/assets/assignments/hw5_fig1.jpg)

### Subproblem 1A

> Present the adjacent matrix.

| | $\bf V[0]$ | $\bf V[1]$ | $\bf V[2]$ | $\bf V[3]$ | $\bf V[4]$ | $\bf V[5]$ | $\bf V[6]$ |
| --- | --- | --- | --- | --- | --- | --- | --- |
| $\bf V[0]$ | $0$ | $6$ | $5$ | $5$ | $0$ | $0$ | $0$ |
| $\bf V[1]$ | $0$ | $0$ | $0$ | $0$ | $-1$ | $0$ | $0$ |
| $\bf V[2]$ | $0$ | $-2$ | $0$ | $0$ | $1$ | $0$ | $0$ |
| $\bf V[3]$ | $0$ | $0$ | $-2$ | $0$ | $0$ | $-1$ | $0$ |
| $\bf V[4]$ | $0$ | $0$ | $0$ | $0$ | $0$ | $0$ | $3$ |
| $\bf V[5]$ | $0$ | $0$ | $0$ | $0$ | $0$ | $0$ | $3$ |
| $\bf V[6]$ | $0$ | $0$ | $0$ | $0$ | $0$ | $0$ | $0$ |

### Subproblem 1B

> Demonstrate the procedure of shortest paths disclosure in the following table
  by adopting Bellman-Ford algorithm. Use row $1$ and $2$ as examples. You may add
  more rows when necessary.
>
> | Round | $\bf Dist^k[0]$ | $\bf Dist^k[1]$ | $\bf Dist^k[2]$ | $\bf Dist^k[3]$ | $\bf Dist^k[4]$ | $\bf Dist^k[5]$ | $\bf Dist^k[6]$ |
> | --- | --- | --- | --- | --- | --- | --- | --- |
> | **1** | $0$ | $6$ | $5$ | $5$ | $\infty$ | $\infty$ | $\infty$ |
> | **2** | $0$ | $3$ | $3$ | $5$ | $5$ | $4$ | $\infty$ |
> | **3** | | | | | | | |
> | **4** | | | | | | | |
> | **5** | | | | | | | |
> | **6** | | | | | | | |

| Round | $\bf V[0]$ | $\bf V[1]$ | $\bf V[2]$ | $\bf V[3]$ | $\bf V[4]$ | $\bf V[5]$ | $\bf V[6]$ |
| --- | --- | --- | --- | --- | --- | --- | --- |
| **1** | $0$ | $6$ | $5$ | $5$ | $\infty$ | $\infty$ | $\infty$ |
| **2** | $0$ | $3$ | $3$ | $5$ | $5$ | $4$ | $\infty$ |
| **3** | $0$ | $1$ | $3$ | $5$ | $2$ | $4$ | $7$ |
| **4** | $0$ | $1$ | $3$ | $5$ | $0$ | $4$ | $5$ |
| **5** | $0$ | $1$ | $3$ | $5$ | $0$ | $4$ | $3$ |
| **6** | $0$ | $1$ | $3$ | $5$ | $0$ | $4$ | $3$ |

- Round 1
  1. $\bf V(0)+E(0,1) = 0+6 = 6$
  2. $\bf V(0)+E(0,2) = 0+5 = 5$
  3. $\bf V(0)+E(0,3) = 0+5 = 5$
- Round 2
  1. $V(0)+E(0,1) = 0+6 = 6 \quad > \quad \mathbf{V(2)+E(2,1) = 5-2 = 3}$
  2. $V(0)+E(0,2) = 0+5 = 5 \quad > \quad \mathbf{V(3)+E(3,2) = 5-2 = 3}$
  3. $\bf V(0)+E(0,3) = 0+5 = 5$
  4. $\mathbf{V(1)+E(1,4) = 6-1 = 5} \quad < \quad V(2)+E(2,4) = 5+1 = 6$
  5. $V(3)+E(3,5) = 5-1 = 4$
- Round 3
  1. $V(0)+E(0,1) = 0+6 = 6 \quad > \quad \mathbf{V(2)+E(2,1) = 3-2 = 1}$
  2. $V(0)+E(0,2) = 0+5 = 5 \quad > \quad \mathbf{V(3)+E(3,2) = 5-2 = 3}$
  3. $\bf V(0)+E(0,3) = 0+5 = 5$
  4. $\mathbf{V(1)+E(1,4) = 3-1 = 2} \quad < \quad V(2)+E(2,4) = 3+1 = 4$
  5. $\bf V(3)+E(3,5) = 5-1 = 4$
  6. $V(4)+E(4,6) = 5+3 = 8 \quad > \quad \mathbf{V(5)+E(5,6) = 4+3 = 7}$
- And so on...

### Subproblem 1C

> Implement the function to find all shortest paths from $0$ to other vertices
  by Bellman-Ford. Your implementation should print the shortest paths.
  (Preferred Java or C++). Run your code and present the shortest paths found by
  your implementation.

#### Using [Bellman-Ford's algorithm](https://github.com/hendraanggrian/IIT-CS430/blob/main/graphs.md#bellman-fords-algorithm)

- We use `char` instead of `int` to represent vertex, because
  sometimes $V = \{A,B,C,\ldots\}$.
- Create mapping of vertex to weight as a return value of the function.
  - If the vertexes are ordered integers
    (e.g.: $1,2,3,\ldots$), `weights` can be reduced to integer array.
  - In the code, the vertexes can be any characters
    (e.g.: $A,B,C,\ldots$), `weights` has to be a hash table.
- Put $0$ on starting point and $\infty$ on everything else.
- Cycle each point and each edge to relax weight.
- Stop the process if a negative cycle is found, that is, connecting vertexes
  on a loop where all their values combined result in a negative value.
- Return the mapping values.

```java
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
```

Average time complexity of Bellman-Ford's algorithm is $O(V E)$.

[View full code](https://github.com/hendraanggrian/IIT-CS430/blob/main/playground/app/src/main/java/com/example/graph/BellmanFordGraphs.java)
/ [test](https://github.com/hendraanggrian/IIT-CS430/blob/main/playground/app/src/test/java/com/example/graph/BellmanFordGraphsTest.java)

Which generates this result.

```
1. 0 ∞ ∞ ∞ ∞ ∞ ∞
2. 0 6 ∞ ∞ ∞ ∞ ∞
3. 0 6 5 ∞ ∞ ∞ ∞
4. 0 6 5 5 ∞ ∞ ∞
5. 0 6 5 3 ∞ ∞ ∞
6. 0 3 5 3 ∞ ∞ ∞
7. 0 3 5 3 2 ∞ ∞
8. 0 3 5 3 2 2 ∞
9. 0 3 5 3 2 2 5
```

## Problem 2

> Argue that if all edge weights of an undirected graph are positive, then any
  subset of edges that connects all vertices and has minimum total weight must
  be a tree.

Here's an example of the smallest form of undirected graph, the minimum spanning
tree is $A \to B \to C = 1 + 2 = 3$.

![Undirected graph with positive weights.](https://github.com/hendraanggrian/IIT-CS430/raw/assets/minimum-spanning-tree/positives.png)

[View full diagram](https://github.com/hendraanggrian/IIT-CS430/blob/main/minimum-spanning-tree/minimum-spanning-tree.drawio)

Consider a graph where the weights are all $-1$. In this
case, minimum spanning tree is $A \to B \to C = -1 - 1 = -2$ which is more than
the total of $-3$. By definition, this cannot be a minimum spanning tree.

![Undirected graph with negative weights.](https://github.com/hendraanggrian/IIT-CS430/raw/assets/minimum-spanning-tree/negatives.png)

## Problem 3

> Given an unweighted undirected graph $G$, and a vertex pair $u$ and $v$,
  please give out a pseudo code returning `true` if $v$ is reachable from $u$.
  Otherwise return `false`. Analyze the time complexity of your algorithm.

### Using BFS

- Because this is undirected graph, each element of `G.edges` is duplicated with
  inversed start & end.
- Create a mapping of vertex to visit.
- Create a queue as a basis of BFS, using `start` as initial point.
- Iterate by spreading to each connected vertex, mark all visited vertexes.
- Return true if `end` is found, otherwise `false` if only all vertexes are
  visited.

```java
public static boolean isReachable(Graph G, char start, char end) {
  if (start == end) {
    return true;
  }
  Map<Character, Boolean> visits = new TreeMap<>();
  for (char v : G.vertexes) {
    visits.put(v, false);
  }
  Queue<Character> bfs = new LinkedList<>();
  visits.put(start, true);
  bfs.add(start);
  while (!bfs.isEmpty()) {
    start = bfs.remove();
    for (Edge edge : G.edges) {
      if (edge.start != start) {
        continue;
      }
      if (edge.end == end) {
        return true;
      }
      if (!visits.get(edge.end)) {
        visits.put(edge.end, true);
        bfs.add(edge.end);
      }
    }
  }
  return false;
}
```

[View full code](https://github.com/hendraanggrian/IIT-CS430/blob/main/playground/app/src/main/java/com/example/graph/ReachableGraphs.java)
/ [test](https://github.com/hendraanggrian/IIT-CS430/blob/main/playground/app/src/test/java/com/example/graph/ReachableGraphsTest.java)

Time complexity of this function is $O(V E)$.

## Problem 4

> Some students enroll in some events in athletics. Below shows students names
  and their enrolled events.
>
> | Name | Events |
> | --- | --- |
> | Zach Williams | $A\ B\ E$ |
> | Jennifer Hopkins | $C\ D$ |
> | Ivan Green | $C\ E\ F$ |
> | Douglas May | $D\ F\ A$ |
> | Katherine Nojwoi | $B\ F$ |
>
> But some events may be held with a time conflict. To find out if each student
  could succeed participating in all events he/she enrolls in, we refer to the
  graph $G$ below. In $G$, if two events have no time conflict, they are
  connected by an edge. Give out a pseudo code to return `true` if a student's
  enrolled events have no conflict and `false` otherwise.
>
> ![Figure 2](https://github.com/hendraanggrian/IIT-CS430/raw/assets/assignments/hw5_fig2.jpg)

### Using sliding window

But the range is always $1$.

- Only allow input with $2$ or more events.
  - If length is $0$, there is no conflict because there is no event.
  - If length is $1$, just see if that vertex exist in a graph.
- Keep the events in a list because we are looping with `Iterator`.
- For each event pair, increment both pointers if matching graph edge is found.
- If no graph edge is found, return `false` because there is no connection.
- Return `true` when all events are checked.

```java
public static boolean isConflict(Graph G, char... events) {
  if (events.length == 0) {
    return false;
  } else if (events.length == 1) {
    for (char v : G.vertexes) {
      if (events[0] == v) {
        return false;
      }
    }
    return true;
  }
  int i = 0;
  int j = 1;
  outer:
  while (j < events.length) {
    for (Edge edge : G.edges) {
      if (edge.start == events[i] && edge.end == events[j]) {
        i++;
        j++;
        continue outer;
      }
    }
    return true;
  }
  return false;
}
```

[View full code](https://github.com/hendraanggrian/IIT-CS430/blob/main/playground/app/src/main/java/com/example/graph/AthleticEvents.java)
/ [test](https://github.com/hendraanggrian/IIT-CS430/blob/main/playground/app/src/test/java/com/example/graph/AthleticEventsTest.java)

Time complexity of this function is $O(events.E)$.
