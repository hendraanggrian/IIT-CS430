<!-- hotfix: KaTeX -->
<!-- https://github.com/yzane/vscode-markdown-pdf/issues/21/ -->
<script type="text/javascript" src="http://cdn.mathjax.org/mathjax/latest/MathJax.js?config=TeX-AMS-MML_HTMLorMML"></script>
<script type="text/x-mathjax-config">MathJax.Hub.Config({ tex2jax: { inlineMath: [['$', '$']] }, messageStyle: 'none' });</script>

# Mid exam

## Theory problem 1

> Onstant in algorithm analysis, we assume that parandter pasing during
  procedure calls takes constant time, even if $N$-element array is being
  passed. This assumption is valid in most systems because a pointer to the
  array is passed. Not the array itself. This problem examines the implications
  of three parameter-passing strategies:
>
> - An array is passed by pointer. Time = $\Theta(1)$
> - An array is passed by copying. Time = $\Theta(N)$, where $N$ is the size of
    the array.
> - An array is passed by copying only the subrange that might be accessed by
    the called procedure. Time = $\Theta(g - p > + 1)$ if the
    subarray $A[p \ldots g]$ is passed
>
> Consider the recursive binary search algorithm for finding a number in a
  sorted array. Give recurrences for the worst-case running times of binary
  search when arrays are passed using each of the three methods above, and give
  good upper bounds on the solutions of the recurrences. Let $N$ be the size of
  the original problem and $n$ be the size of a subproblem.

## Theory problem 2

> (missing text). Given the following binary searen tree, in which (missing
  text) are shown as circles and external nodes (leaves; nil pointers) are shown
  as small boxes. The external path length (for leaves left to right)
  is $3 + 3 + 2 + 2 + 4 + 4 + 3 = 21$.

### Subproblem 2A

> Is there a binary search tree on the same set of
  letters, $\{D, G, N, Q, S, T\}$, that has a lower external path length? Either
  give such a tree and how it is formed from the original BST or prove it does
  not exist.

### Subproblem 2B

> Can the nodes in the original BST be colored red and black so as to form a
  proper red-black tree?

### Subproblem 2C

> What might you conclude about a relationship between external path length and
  red-black trees?

## Theory problem 3

> Which of the following sorting algorithms is unstable? Use an example (missing
  text)

## Theory problem 4

> We will conduct $n$ operations on a data structure. If $i$ is a power of 2,
  the actual cost of operation $i$ is $i$, otherwise the actual cost is $1$.
  Analyze the amortized cost by accounting method.

## Practice problem 1

> Each of $n$ keys is an array may have one of the values red, white or blue.
  Give a linear time algorithm for rearranging the keys so that all the reds
  come before all the whites and all the whites come before all the blues. The
  only operations permitted on the keys are:
>
> - Examination of a key to find out what color it is.
> - A swap (interchange of positions) of two keys specified by their indices.

## Practice problem 2

> Design an algorithm to return ke $k$-th largest element from array $A$ by
  adopting `HEAPSORT`. Analyze the complexity of your design.

## Practice problem 3

> The following is an instance of a BST with 9 nodes.

### Subproblem 3A

> If the keys of nodes are $10, 12, 15, 19, 20, 23, 30, 25, 27$, please fill
  them in the circles to form a BST.

### Subproblem 3B

> Design an algorithm $SumLR(T, L, R)$ to return the sum of all keys in $T$, a
  BST between $L$ and $R$. For example, in $T$, if $L=5$ and $R=19$, the
  algorithm should return $56 (10+12+15+19=56)$.
