<!-- hotfix: KaTeX -->
<!-- https://github.com/yzane/vscode-markdown-pdf/issues/21/ -->
<script type="text/javascript" src="http://cdn.mathjax.org/mathjax/latest/MathJax.js?config=TeX-AMS-MML_HTMLorMML"></script>
<script type="text/x-mathjax-config">MathJax.Hub.Config({ tex2jax: { inlineMath: [['$', '$']] }, messageStyle: 'none' });</script>

# [Homework 2](https://github.com/hendraanggrian/IIT-CS430/blob/assets/assignments/hw2.pdf)

## Problem 1

> Show in detail that the running time to sort in increasing order
  using `QUICKSORT` is $T(n^2)$ when the array A contains distinct elements and
  is initially sorted in decreasing order.

| Algorithm | Best | Average | Worst |
| --- | --- | --- | --- |
| Quicksort | $\Omega(n \log n)$ | $\Theta(n \log n)$ | $O(n^2)$ |

Quicksort picks a random pivot and divides the collection accordingly. For the
most optimal performance, these divisions should be sized proportionally.
Ideally, the pivot should be the median of a sorted collection, as opposed to
the smallest or the largest. **Therefore, the running time of quicksort on a
sorted array depends on how the pivot is selected**.

| Case | Pivot |
| --- | --- |
| Best | $\bf A[n/2]$ |
| Worst | $\bf A[1] \textsf{ or } A[n]$ |
| Average | Random |

## Problem 2

> Stack depth for `quicksortTailRecursive` - The `quicksortTailRecursive`
  algorithm of Section 7.1 contains two recursive calls to itself. After the
  call to `partition`, the left sub array is recursively sorted and then the
  right subarray is recursively sorted. The second recursive call
  in `quicksortTailRecursive` is not really necessary; it can be avoided by
  using an iterative control structure. Good compilers provide this technique,
  called tail recursion. Consider the following version of quick sort, which
  simulates tail recursion.
>
> ```java
> public static void quicksortTailRecursive(int[] A, int p, int r) {
>   while (p < r) {
>     int q = partition(A, p, r);
>     quicksortTailRecursive(A, p, q - 1);
>     p = q + 1;
>   }
> }
> ```

[View full code](https://github.com/hendraanggrian/IIT-CS430/blob/main/playground/app/src/main/java/com/example/sort/Quicksorts.java)
/ [test](https://github.com/hendraanggrian/IIT-CS430/blob/main/playground/app/src/test/java/com/example/sort/QuicksortsTest.java)

### Subproblem 2A

> Argue that `quicksortTailRecursive(A, 1, A.length)` correctly sorts the
  array $A$.

Consider a regular `quicksort` algorithm below. It traverses with 2 recursive
calls that are stopped by `if` control flow, `quicksortTailRecursive` however
uses `while` loop and single recursive call.

```java
public static void quicksort(int[] A, int p, int r) {
  if (p < r) {
    int q = partition(A, p, r);
    quicksort(A, p, q - 1);
    quicksort(A, q + 1, r);
  }
}
```

By comparison, `quicksortTailRecursive` has the same iteration process and
parameter values. **Therefore, `quicksortTailRecursive` correctly sorts the
array**.

### Subproblem 2B


> Compilers usually execute recursive procedures by using a **stack** that
  contains pertinent information, including the parameter values, for each
  recursive call. The information for the most recent call is at the top of the
  stack, and the information for the initial call is at the bottom. When a
  procedure is invoked, its information is **pushed** onto the stack; when it
  terminates, its information is **popped**. Since we assume that array
  parameters are represented by pointers, the information for each procedure
  call on the stack requires $O(1)$ stack space. The **stack depth** is the
  maximum amount of stack space used at any time during a computation. Describe
  a scenario in which the stack depth of `quicksortTailRecursive` is $\Theta(n)$
  on an $n$-element input array.

**The stack depth is $\bf \Theta(n)$ when the array is already sorted and the
pivot is $\bf A[1]$ or $\bf A[n]$**. In such case, one of the partition will be
full while the other is empty. Therefore, $n$ iteration is required before the
function terminates.

### Subproblem 2C

> Modify the code for `quicksortTailRecursiveModified` so that the worst-case
  stack depth is $\Theta(\log n)$. Maintain the $O(n \log n)$ expected running
  time of the algorithm.

```java
public static void quicksortTailRecursiveModified(int[] A, int p, int r) {
  while (p < r) {
    int q = partition(A, p, r);
    if (q < p + (r - p) / 2) {
      quicksortTailRecursiveModified(A, p, q - 1);
      p = q + 1;
    } else {
      quicksortTailRecursiveModified(A, q + 1, r);
      r = q - 1;
    }
  }
}
```

Like `quicksort`, `quicksortTailRecursiveModified` breaks apart the array into
2 smaller parts. **Like any divide and conquer, the
operation $\bf O(n \log n)$**.

## Problem 3

> Hoare Partition Correctness - The version of `partition` given in chapter 7 is
  not the original partitioning algorithm. Here is the original partition
  algorithm, which is due to *T. Hoare*:
>
> ```java
> public static int hoarePartition(int[] A, int p, int r) {
>   int x = A[p];
>   int i = p - 1;
>   int j = r + 1;
>
>   while (true) {
>     do {
>       j--;
>     } while (A[j] < x);
>     do {
>       i++;
>     } while (A[i] > x);
>     if (i < j) {
>       swap(A, i, j);
>     } else {
>       return j;
>     }
>   }
> }
> ```

[View full code](https://github.com/hendraanggrian/IIT-CS430/blob/main/playground/app/src/main/java/com/example/HoarePartitions.java)
/ [test](https://github.com/hendraanggrian/IIT-CS430/blob/main/playground/app/src/test/java/com/example/HoarePartitionsTest.java)

### Subproblem 3A

> Demonstrate the operation of `hoarePartition` on the
  array $A=\{13,19,9,5,12,8,7,4,11,2,6,21\}$, showing the values of the array
  and auxiliary values after each iteration of the while-loop.

| # | $j$ | $i$ | $A$ | Note |
| ---: | --- | --- | --- | --- |
| 0 | $12$ | $0$ | $\{13, 19, 9, 5, 12, 8, 7, 4, 11, 2, 6, 21\}$ | $x = A[1] = 13$. |
| 1 | $11$ | $1$ | $\{\mathbf{6}, 19, 9, 5, 12, 8, 7, 4, 11, 2, \mathbf{13}, 21\}$ | |
| 2 | $10$ | $2$ | $\{6, \mathbf{2}, 9, 5, 12, 8, 7, 4, 11, \mathbf{19}, 13, 21\}$ | |
| 3 | $9$ | $10$ | $\{6, 2, 9, 5, 12, 8, 7, 4, 11, 19, 13, 21\}$ | **Not swapping, returns $\bf j$.** |

> The next three questions ask you to give a careful argument that the
  procedure `hoarePartition` is correct. Prove the following:

### Subproblem 3B

> The indices $i$ and $j$ are such that we never access an element of $A$
  outside the subarray $A[p \ldots r]$.

Look at the variable assignments `i = p - 1` and `j = r + 1`, also the
statements `j--` and `i++` in do-while loops. They explain that $i$ and $j$ are
set from $p$ and $r$ respectively and only move closer gradually until they
intersect. **Therefore $\bf p \le i < j \le r$
or $\bf A[p \ldots i \ldots j \ldots r]$**.

### Subproblem 3C

> When `hoarePartition` terminates, it returns a value $j$ such
  that $p \le j < r$.

The function terminates when $i$ and $j$ intersect, making sure that they are
always in the range of $p$ and $r$. **Therefore $\bf p \le j < r$ is
true.**

### Subproblem 3D

> Every element of $A[p \ldots j]$ is less than or equal to every element
  of $A[j+1 \ldots r]$ when `hoarePartition` terminates.

During termination, the function returns $j$, the index of the pivot.
**Therefore $\bf A[p \ldots j] \le x \le A[j+1 \ldots r]$**.

## Problem 4

> What are the minimum and maximum numbers of elements in a heap of height $h$?

| Data Structure | # of Nodes | Notes |
| --- | --- | --- |
| Almost Complete Binary Tree | $2^0 + 2^1 + 2^2 + \ldots + 2^{h-1} + l = \bf 2^h$ | Where $l$ is the number of nodes in the last level. |
| Complete Binary Tree | $2^0 + 2^1 + 2^2 + \ldots + 2^h = \bf 2^{h+1}-1$ | |

A heap data structure is almost complete to a complete binary tree. **For a
minimum number of elements, the tree should be almost complete with the last
level having just 1 node**. Oppositely, the tree should be complete for a maximum
number of nodes.

## Problem 5

> The code for `maxHeapify` is quite efficient in terms of constant factors,
  except possibly for the recursive call in line 10, which might cause some
  compilers to produce inefficient code. Write an efficient `maxHeapify` that
  uses an iterative control construct (a loop) instead of recursion.

```java
public static void maxHeapify(int[] A, int i) {
  while (true) {
    int l = left(i);
    int r = right(i);
    int max;
    if (l <= A.length && A[l] > A[i]) {
      max = l;
    } else {
      max = i;
    }
    if (r <= A.length && A[r] > A[max]) {
      max = r;
    }
    if (max == i) {
      break;
    }
    swap(A, i, max);
    i = max;
  }
}
```

[View full code](https://github.com/hendraanggrian/IIT-CS430/blob/main/playground/app/src/main/java/com/example/MaxHeapifies.java)
/ [test](https://github.com/hendraanggrian/IIT-CS430/blob/main/playground/app/src/test/java/com/example/MaxHeapifiesTest.java)

## Problem 6

> What is the smallest possible depth of a leaf in a decision tree for a
  comparison sort? Explain why in detail.

For the lowest possible stack depth in any sorting algorithm, the input should
already be sorted. With no sorting process, any comparison sorting algorithm
still needs to perform comparisons. **Therefore $\bf n-1$, because every
comparison checks 2 variables**.
