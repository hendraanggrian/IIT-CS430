<!-- hotfix: KaTeX -->
<!-- https://github.com/yzane/vscode-markdown-pdf/issues/21/ -->
<script type="text/javascript" src="http://cdn.mathjax.org/mathjax/latest/MathJax.js?config=TeX-AMS-MML_HTMLorMML"></script>
<script type="text/x-mathjax-config">MathJax.Hub.Config({ tex2jax: { inlineMath: [['$', '$']] }, messageStyle: 'none' });</script>

# [Demo 1](https://student.desmos.com/?prepopulateCode=b33pqa)

## BST Right Rotation

> ### Please provide pseudo code for BST right rotation.

## Insertion and Its Complexity

> ### Rewrite the Insertion with recursion and analyze the complexity accordingly.

### Code

```java
public static void insertionSort(int[] A, int p, int r) {
  if (p < r) {
    insertionSort(A, p, r - 1);
    int temp = A[r];
    int i = r - 1;
    while (i >= p && temp < A[i]) {
      A[i + 1] = A[i];
      i--;
    }
    A[i + 1] = temp;
  }
}
```

[View full code](https://github.com/hendraanggrian/IIT-CS430/blob/main/playground/app/src/main/java/com/example/sort/InsertionSorts.java)
/ [test](https://github.com/hendraanggrian/IIT-CS430/blob/main/playground/app/src/test/java/com/example/sort/InsertionSortsTest.java)

### Recurrence

$$
\begin{array}{rcl}
  T(n) &=& T(n-1) + O(n-1) + O(1) \quad \textsf{if $n>1$} \\
  T(1) &=& O(1)
\end{array}
$$

## Prove the asymptotic bound.

> ### Prove the asymptotic bound below.
>
> ![Figure 1.](https://github.com/hendraanggrian/IIT-CS430/raw/assets/desmos/prove_the_asymptotic_bound.jpg)

## Statistic Algorithm

> ### Statistic Algorithm Design
>
> Assume that are given a "black-box" (i.e., you do not have the source code)
  procedure `median` that takes as parameters an array $A$ and subarray indices
  $p$ and $r$, and returns the value of the median element of $A[p \ldots r]$ in
  $O(n)$ time in the worst case. Give a simple, linear-time algorithm that uses
  this procedure `median` to find the $i$-th smallest element. Write the
  recurrence relation for your algorithm and show the solution is linear growth.

Given `median`, here is a linear-time algorithm `select` for finding $i$-th
smallest element in $A$. This algorithm uses the deterministic `partition`
algorithm that was modified to take an element to partition around as an input
parameter.

```
select(A, p, r, i):
  if p == r
    return A[p]
  x = median(a, p, r)
  q = partition(x)
  k = q - p + 1
  if i == k
    return A[q]
  else if i < k
    return select(A, p, q - 1, i)
    else return select(A, q + 1, r, i - k)
```

Because $x$ is the median of $A[p \ldots r]$, each of the subarrays
$A[p \ldots q - 1]$ and $A[q + 1 \ldots r]$ has at most half the number of
elements of $A$. **The recurrence for the worst-case running time of `select`
is $\bf T(n) = T(n/2) + O(n)$**. (by master method)

### Recurrence equation

$$
\begin{array}{rcl}
  \bf T(n) &=& T(n-1) + O(n-1) + O(1) \quad
    \text{use iteration} \\
  &=& [O(n-1) + O(1)] + T(n-1) \\
  &=& [O(n-1) + O(1)] + [O(n-2) + O(1)] + T(n-2) \\
  &=& [O(n-1) + O(1)] + [O(n-2) + O(1)] +
    [O(n-3) + O(1)] + T(n-3) \\
  &=& [O(n-1) + O(1)] + [O(n-2) + O(1)] +
    [O(n-3) + O(1)] + \ldots + T(1) \\
  && \text{n-1 terms total} \\
  &=& \displaystyle \sum_{i=1}^{n-1} [O(n-i) + O(1)] \\
  &=& \bf O(n^2)
\end{array}
$$

## R-B BST

> ### R-B BST
>
> Draw the complete binary search tree of height 3 on the
  keys $\{1,2,\ldots,15\}$. Add the NIL leaves and color the nodes in three
  different ways such that the black-heights of the resulting red-black trees
  are $2,3,4$.

![Figure 2.](https://github.com/hendraanggrian/IIT-CS430/raw/assets/desmos/r-b_bst.jpg)
