<!-- hotfix: KaTeX -->
<!-- https://github.com/yzane/vscode-markdown-pdf/issues/21/ -->
<script type="text/javascript" src="http://cdn.mathjax.org/mathjax/latest/MathJax.js?config=TeX-AMS-MML_HTMLorMML"></script>
<script type="text/x-mathjax-config">MathJax.Hub.Config({ tex2jax: { inlineMath: [['$', '$']] }, messageStyle: 'none' });</script>

# [Final exam](https://github.com/hendraanggrian/IIT-CS430/blob/assets/assignments/exam2.pdf)

## Problem 1

> The knapsack problem is described as a combinatorial optimization. Given a set
  of $n$ items with a weight and a value, we are going to find a collection to
  maximize the total value of the times we choose under or equal to the weight
  limit. Argue that the knapsack problem is an `NP` problem.

### Using [knapsack problem](https://github.com/hendraanggrian/IIT-CS430/blob/main/backpack_problem.md)

Given arrays of values $v$ and weights $w$ both with size $n$, fill the backpack
under the weight $W$. While filling the recursion or dynamic programming table,
we can expect the time complexity to be $O(n.W)$. To confirm whether or not this
approach is NP, we need to make sure the result increase linearly as the inputs
progress equally.

However, there is a difference of input type as $n$ represents the size of an
array while $W$ is a primitive integer, thus could be more appropriately
represented with $j = \log(W)$, which also translates to $W = j^2$. **The growth
of $\bf j$ exponentially increase the calculation time, therefore the solution
is not polynomial**.

## Problem 2

> An elevator runs between floor $1$ and floor $n$. At floor $i$, there is an
  integer $k_i$. If you press button `UP` at floor $i$, the elevator will go up
  by $k_i$ levels; if you press button `DOWN`, the elevator will go down
  by $k_i$ levels. But the elevator can not go higher than $n$ or lower
  than $1$. If a person is at floor $a$ and wants to go to floor $b$, how many
  times does he have to press the button at least? Design an algorithm to return
  the time or $-1$ when he can not reach $b$. (pseudo code is required)

### Using BFS

- Trigger a queue traversion with floor $a$ as the start point, keep track of
  visited item in unordered set.
- Because we are counting for how many button presses, this information must
  also be included in the queue. My test case implements modification
  using `Pair`, a non-standard Java implementation.
- If pressing `UP` won't exceed floor $n$, queue this possibility with
  incremented button press count. Consequently, also queue `DOWN` button if
  pressing it won't fall behind $1$.
- At some point when current floor of the traversion hit $b$, return the
  memorized button click count.
- Return $-1$ if queue is depleted and has not reached destined floor.

```java
public static int howManyButtons(int[] k, int a, int b, int n) {
  if (a == b) {
    return 0;
  }
  Queue<Pair<Integer, Integer>> queue = new LinkedList<>();
  queue.offer(new Pair<>(a, 0));
  Set<Integer> visited = new HashSet<>();
  while (!queue.isEmpty()) {
    Pair<Integer, Integer> current = queue.poll();
    int currentFloor = current.getKey();
    int currentPresses = current.getValue();
    if (currentFloor == b) {
      return currentPresses;
    }
    visited.add(currentFloor);
    int move = k[currentFloor];
    if (currentFloor >= move && currentFloor - move < n &&
      !visited.contains(currentFloor - move)) {
      queue.offer(new Pair<>(currentFloor - move, currentPresses + 1));
    }
    if (currentFloor + move < n && currentFloor + move > 1 &&
      !visited.contains(currentFloor + move)) {
      queue.offer(new Pair<>(currentFloor + move, currentPresses + 1));
    }
  }
  return UNREACHABLE;
}
```

[View full code](https://github.com/hendraanggrian/IIT-CS430/blob/main/playground/app/src/main/java/com/example/ElevatorFloors.java)
/ [test](https://github.com/hendraanggrian/IIT-CS430/blob/main/playground/app/src/test/java/com/example/ElevatorFloorsTest.java)

## Problem 3

> A car sets off from $S$ and drives to $D$ as shown in the figure. Gas stations
  have been set at some intersections (asterisks). The car can only run along
  sides and a full tank runs K sides. The driver has to pay the following fees:
>
> - To refill a full tank of gas, pay $\$A$.
> - If the car goes back (either $x$ coordinate or $y$ coordinate decreases when
    driving one more side), pay $\$B$.
> - The driver can set up some extra gas stations, but has to pay $\$C$ for each
    setup. Neither $S$ or $D$ allows a gas station setup.
>
> We assume that the car sets off with a full tank of gas for free. Design an
  algorithm to return a path from $S$ to $D$ that spends minimal money. (pseudo
  code required)
>
> ![Figure 1](https://github.com/hendraanggrian/IIT-CS430/raw/assets/assignments/exam2_fig1.jpg)

#### Using DFS

- Set a baseline where if $S$ meets $D$ or current $S$ coordinate is out of
  range, return the memorized spent value of this recursion tree.
- If given a choice to refill whenever we found a gas station, always
  refill for $\$A$ because we don't know how far is the adjacent station.
- If tank is empty, theorize going back to previous road for $\$B$ and building
  gas station for $\$C$ (also refilling for $\$A$). Pick the most efficient path
  of all traversed paths.
- Each recursion leaf is usually returning $0$ spent, a sign that this
  permutation doesn't reach $S \to D$.
- Return the highest decision tree has successfully reached the destination.

There are several flaws of this fast approach:

- As illustrated in the figure, currently $D$ cannot be positioned in upper-left
  side of $S$.
- The function make no prediction for when to best refill the tank, opting to
  refill each opportunity.

```java
public static int getSpent(
  char[][] maze, int sx, int sy, int dx, int dy, int maxTank, int tank, int spent
) {
  if (sx == dx && sy == dy) {
    return spent;
  } else if (sx + 1 > dx || sy + 1 > dy) {
    return spent;
  }
  if (maze[sx][sy] == STATION && tank < maxTank) {
    spent += A;
    tank = maxTank;
  }
  if (tank == 0) {
    int min = getSpent(maze, sx, sy + 1, dx, dy, maxTank, maxTank, spent + C + A);
    min = Math.min(min, getSpent(maze, sx + 1, sy, dx, dy, maxTank, tank, spent + B));
    min = Math.min(min, getSpent(maze, sx, sy + 1, dx, dy, maxTank, maxTank, spent + C + A));
    min = Math.min(min, getSpent(maze, sx, sy + 1, dx, dy, maxTank, tank, spent + B));
    return min;
  }
  return Math.max(
    getSpent(maze, sx + 1, sy, dx, dy, maxTank, tank - 1, spent),
    getSpent(maze, sx, sy + 1, dx, dy, maxTank, tank - 1, spent)
  );
}
```

[View full code](https://github.com/hendraanggrian/IIT-CS430/blob/main/playground/app/src/main/java/com/example/maze/GasStationMazes.java)
/ [test](https://github.com/hendraanggrian/IIT-CS430/blob/main/playground/app/src/test/java/com/example/maze/GasStationMazesTest.java)

## Problem 4

> A digraph $G$ is called a dominance-directed graph if for any pair of distinct
  vertices $u$ and $v$ of $G$, either $u \to v$ or $v \to u$, but not both (here
  the notation $u \to v$ means there is an edge from $u$ to $v$). Below is an
  example of a dominance-directed graph. $\{A,B,C,D,E\}$ are five sports teams.
  Teams play each other exactly once, with no ties allowed. $A \to B$ means
  team $A$ beats team $B$.
>
> In a dominance-directed graph, we define the power of a vertex as being the
  total number of 1-step and 2-step connections to other vertices. For example,
  the power of $A$ is $4$. Using the adjacency matrix and its square, calculate
  the power of each vertex and rank each team according to their vertex power.
  (show your work)
>
> ![Figure 1](https://github.com/hendraanggrian/IIT-CS430/raw/assets/assignments/exam2_fig2.jpg)

Graph H can be better represented with adjacency matrix.

| | $\bf A$ | $\bf B$ | $\bf C$ | $\bf D$ | $\bf E$ |
| --- | --- | --- | --- | --- | --- |
| $\bf A$ | | &check; | &check; | &check; ||
| $\bf B$ | | | | &check; | &check; |
| $\bf C$ | | &check; | | &check; | &check; |
| $\bf D$ | | | | | |
| $\bf E$ | &check; | | | &check; | |

Using formula number of 1-step and 2-step connections to other vertices, it
can be inferred that power of each vertex $P = H + H^2$

$$
\begin{array}{rcl}
  P &=& H + H^2 \\
  P &=& \begin{vmatrix}
    0 & 1 & 1 & 1 & 0 \\
    0 & 0 & 0 & 1 & 1 \\
    0 & 1 & 0 & 1 & 1 \\
    0 & 0 & 0 & 0 & 0 \\
    1 & 0 & 0 & 1 & 0
  \end{vmatrix} + \begin{vmatrix}
    0 & 1 & 1 & 1 & 0 \\
    0 & 0 & 0 & 1 & 1 \\
    0 & 1 & 0 & 1 & 1 \\
    0 & 0 & 0 & 0 & 0 \\
    1 & 0 & 0 & 1 & 0
  \end{vmatrix}^2 \\
  P &=& \begin{vmatrix}
    0 & 1 & 1 & 1 & 0 \\
    0 & 0 & 0 & 1 & 1 \\
    0 & 1 & 0 & 1 & 1 \\
    0 & 0 & 0 & 0 & 0 \\
    1 & 0 & 0 & 1 & 0
  \end{vmatrix} + \begin{vmatrix}
    0 & 1 & 0 & 2 & 2 \\
    1 & 0 & 0 & 1 & 0 \\
    1 & 0 & 0 & 2 & 1 \\
    0 & 0 & 0 & 0 & 0 \\
    0 & 1 & 1 & 1 & 0
  \end{vmatrix} \\
  P &=& \begin{vmatrix}
    0 & 2 & 1 & 3 & 2 \\
    1 & 0 & 0 & 2 & 1 \\
    1 & 1 & 0 & 3 & 2 \\
    0 & 0 & 0 & 0 & 0 \\
    1 & 1 & 1 & 2 & 0
  \end{vmatrix}
\end{array}
$$

**Drawing back our adjacency matrix table $\bf P$, we can summarize each path of
vertexes where the highest sum is the highest rank.**

| | $\bf A$ | $\bf B$ | $\bf C$ | $\bf D$ | $\bf E$ | Sum | Rank |
| --- | --- | --- | --- | --- | --- | ---: | ---: |
| $\bf A$ | $0$ | $2$ | $1$ | $3$ | $2$ | 8 | 1 |
| $\bf B$ | $1$ | $0$ | $0$ | $2$ | $1$ | 4 | 4 |
| $\bf C$ | $1$ | $1$ | $0$ | $3$ | $2$ | 7 | 2 |
| $\bf D$ | $0$ | $0$ | $0$ | $0$ | $0$ | 0 | 5 |
| $\bf E$ | $1$ | $1$ | $1$ | $2$ | $0$ | 5 | 3 |

## Problem 5

> There are two types of professional wrestlers: **good guys** and **bad guys**.
  Between any pair of professional wrestlers, there may or may not be a rivalry.
  Suppose we have $n$ professional wrestlers and we have a list of $r$ pairs of
  wrestlers for which there are rivalries. Give an $O(n+r)$-time algorithm that
  determines whether it is possible to designate some of the wrestlers as good
  guys and the remainder as bad guys such that each rivalry is between a good
  guy and a bad guy. If it is possible to perform such a designation, your
  algorithm should produce it. (pseudo code required)

Represent the problem as graph $G=(V,E)$ where $V=\{A,B,\ldots,V_n\}$ are the
wrestlers and $E=\{(A,B),(B,C),\ldots,E_r\}$ are the rivalry edges.

- Remove vertexes that are not weighted by rivalry. The vertexes are
  collected in set that guarantees distinct value, $V$ will then remove
  elements that are not in this collection.
- Split graphs into sub-graphs if not all vertexes are connected.
- Run BFS that mark vertex with type **good guys** for even path length or
  **bad guys** for odd length, using counter as even/odd decider.
- Return true if any of the separated graph edges contain the same type,
  which means a rivalry is already established.

```
setType(v, type): set good/bad guys to vertex
getType(v): get good/bad guys from vertex
connectedGraphs(G): return array of connected graphs
  that are disjointed from one another, if all
  vertexes are connected, return array of only self

possibleRivalry(G, n, r):
  weightedVertexes = Set()
  for edge in G.E
    weightedVertexes.add(edge.from)
    weightedVertexes.add(edge.to)
  G.V.retainAll(weightedVertexes)

  for innerGraph in connectedGraphs(G)
    bfs(innerGraph.V[0], 0)
    for edge in innerGraph.E
      if getType(edge.from) == getType(edge.to)
        return false
    return true
  throw error // guaranteed never to reach this
    line because connectedGraphs returns
    non-null & non-empty

bfs(root, counter):
  queue = Queue()
  queue.push(root)
  visited = Set()
  while Q.length > 0
    v = queue.poll()
    if (counter++ % 2 == 0) {
      setType(v, "good guys")
    } else {
      setType(v, "bad guys")
    }
    for u in v.neighbors
      if !visited.contains(u)
        visited.add(u)
        queue.push(u)
```
