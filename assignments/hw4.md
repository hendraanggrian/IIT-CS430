<!-- hotfix: KaTeX -->
<!-- https://github.com/yzane/vscode-markdown-pdf/issues/21/ -->
<script type="text/javascript" src="http://cdn.mathjax.org/mathjax/latest/MathJax.js?config=TeX-AMS-MML_HTMLorMML"></script>
<script type="text/x-mathjax-config">MathJax.Hub.Config({ tex2jax: { inlineMath: [['$', '$']] }, messageStyle: 'none' });</script>

# [Homework 4](https://github.com/hendraanggrian/IIT-CS430/blob/assets/assignments/hw4.pdf)

## Problem 1

> Sally is hosting an Internet auction to sell at most $n$ widgets and maximize
  her income from the sale. She receives $m$ bids, each of the form "I want
  exactly $k_i$ widgets for $d_i$ dollars," for $i = 1,2,\ldots,m$. (you can
  assume each $k_i$ and each $d_i$ are integers) Characterize her optimization
  problem similar to a problem we covered in class. Explain the algorithm to
  solve her problem and prove it is optimal. How does the solution change if
  each bidder is willing to accept partial lots (an integer amount less
  than $k_i$ widgets)?

### Using [greedy algorithm](https://github.com/hendraanggrian/IIT-CS430/blob/main/greedy_algorithm.md)

This is a **brute-force** approach using simple logic:

- Create a new sorted mapping, fill it with profit-to-widgets ratio.
- Iterate from the highest ratio, only take entry if there is remaining widget.

Time complexity is $O(m^3)$ due to new collection, reversing, and iterating.

```java
public static int maxProfitGreedy(int[] X, int[] Y, int n, int m) {
  if (m <= 0 || n <= 0) {
    return 0;
  }
  Multimap<Double, Integer> multimap = TreeMultimap.create();
  for (int i = 0; i < m; i++) {
    multimap.put((double) Y[i] / X[i], i);
  }
  int remaining = n;
  int profit = 0;
  List<Integer> indices = new ArrayList<>(multimap.values());
  Collections.reverse(indices);
  for (int i : indices) {
    if (remaining - X[i] < 0) {
      continue;
    }
    remaining -= X[i];
    profit += Y[i];
  }
  return profit;
}
```

[View full code](https://github.com/hendraanggrian/IIT-CS430/blob/main/playground/app/src/main/java/com/example/SallyWidgetAuctions.java)
/ [test](https://github.com/hendraanggrian/IIT-CS430/blob/main/playground/app/src/test/java/com/example/SallyWidgetAuctionsTest.java)

### Using [knapsack problem](https://github.com/hendraanggrian/IIT-CS430/blob/main/backpack_problem.md)

Using **recursive method**:

- Declare base case, stop recursion when widget is empty.
- If the last bid's widget demand is larger than what is available, then current
  iteration cannot be use. Backtrack from last bid.
- Otherwise compare current iteration and backtrack from last bid, only take the
  best one.

Time complexity is $O(2^m)$ because the function is branching into two nodes
each recursion level.

```java
public static int maxProfitKnapsack(int[] X, int[] Y, int n, int m) {
  if (m <= 0 || n <= 0) {
    return 0;
  }
  int previous = maxProfitKnapsack(X, Y, n, m - 1);
  if (X[m - 1] > n) {
    return previous;
  }
  int current = Y[m - 1] + maxProfitKnapsack(X, Y, n - X[m - 1], m - 1);
  return Math.max(previous, current);
}
```

## Problem 2

> We consider the problem of placing towers along a straight road, so that every
  building on the road receives cellular service. Assume that a building
  receives cellular service if it is within one mile of a tower.

### Subproblem 2A

> Devise an algorithm that uses the minimum number of towers possible to provide
  cell service to $d$ buildings located at positions $x_1,x_2,\ldots,x_d$ from
  the start of the road.

#### Using [greedy algorithm](https://github.com/hendraanggrian/IIT-CS430/blob/main/greedy_algorithm.md)

Because the towers are placed in a straight road, we can use data structure
**array**:

- Create array $Y$ with size $d$ which will be the output of the function. This
  is because in the worst case where houses are far apart, each house requires
  an antenna.
- Sort array $X$ so that $X_1 \le X_2 \le \ldots \le X_d$.
- To maximize coverage, put the first antenna on the end of the first house,
  instead of the start.
- Iterate the rest of array $X$, only put next antenna if the next house is not
  covered by the last antenna.
- Remove possible null values in array $Y$ before returning it.

Time complexity is $O(d^2)$ due to sorting.

```java
public static int[] minLocations(int[] X, int d) {
  if (d == 0) {
    return new int[0];
  } else if (d == 1) {
    return new int[]{X[0] + COVERAGE};
  }
  Arrays.sort(X);
  int[] Y = new int[d];
  Y[0] = X[0] + COVERAGE;
  int j = 0;
  for (int i = 1; i < d; i++) {
    if (X[i] > Y[j] + COVERAGE) {
      Y[++j] = X[i] + COVERAGE;
    }
  }
  return Arrays.copyOfRange(Y, 0, j + 1);
}
```

[View full code](https://github.com/hendraanggrian/IIT-CS430/blob/main/playground/app/src/main/java/com/example/CellTowerPlacements.java)
/ [test](https://github.com/hendraanggrian/IIT-CS430/blob/main/playground/app/src/test/java/com/example/CellTowerPlacementsTest.java)

### Subproblem 2B

> Prove that the algorithm you devised produces an optimal solution, that is,
  that it uses the fewest towers possible to provide cellular service to all
  buildings.

#### Using [greedy stays ahead](https://github.com/hendraanggrian/IIT-CS430/blob/main/greedy_stays_ahead.md)

As explained in section *Exchange Arguments*, one way to know if a greedy
algorithm is optimal is to compare solutions. Reversing the move from the end,
the test still yield the same result, so it must be optimal.

```java
public static int[] minLocationsReversed(int[] X, int d) {
  if (d == 0) {
    return new int[0];
  } else if (d == 1) {
    return new int[]{X[0] - COVERAGE};
  }
  Arrays.sort(X);
  int[] Y = new int[d];
  Y[d - 1] = X[d - 1] - COVERAGE;
  int j = d - 1;
  for (int i = d - 2; i >= 0; i--) {
    if (X[i] < Y[j] - COVERAGE) {
      Y[--j] = X[i] - COVERAGE;
    }
  }
  return Arrays.copyOfRange(Y, j, d);
}
```

## Problem 3

> An array cost[] consists of integers. $cost_i$ is the cost to climb from
  step $i$. Once $cost_i$ is paid, you can choose 1 or 2 steps to take forward.
  Initially, you can start from either step 0 or step 1. Please design an
  algorithm to return the minimal cost to arrive at the top of the stairs, which
  is step $n$.
>
> For example:
>
> ```
> cost[]=(1,100,1,1,1,90,1,1,80,1)
> The minimal cost is 6.
> Solution:
> Start from step 0;
> Pay 1, and take 2 steps, reaches step2;
> Pay 1, and take 2 steps, reaches step4;
> Pay 1, and take 2 steps, reaches step6;
> Pay 1, and take 1 step, reaches step7;
> Pay 1, and take 2 steps, reaches step9;
> Pay 1, and take 1 step, reaches the top.
> Total cost: 6

#### Using [dynamic programming](https://github.com/hendraanggrian/IIT-CS430/blob/main/dynamic_programming.md)

- Return default values if $cost$ length is less than 3.
- Create an array $dp$ that stores possible cost up until that index point in
  increasing order.
- Add initial 2 elements from $cost$, because we can choose up to 2 steps.
- Starting from the 3rd and so on, combine $cost_i$ with the lowest of the
  previous 2.
- Return the lowest of the last 2 elements.

```java
public static int minCost(int[] cost, int n) {
  if (n == 0) {
    return 0;
  } else if (n == 1) {
    return cost[0];
  } else if (n == 2) {
    return cost[1];
  }
  int[] dp = new int[n];
  dp[0] = cost[0];
  dp[1] = cost[1];
  for (int i = 2; i < n; i++) {
    dp[i] = cost[i] + Math.min(dp[i - 1], dp[i - 2]);
  }
  return Math.min(dp[n - 1], dp[n - 2]);
}
```

Time complexity is $O(n)$.

[View full code](https://github.com/hendraanggrian/IIT-CS430/blob/main/playground/app/src/main/java/com/example/ClimbingStairs.java)
/ [test](https://github.com/hendraanggrian/IIT-CS430/blob/main/playground/app/src/test/java/com/example/ClimbingStairsTest.java)
