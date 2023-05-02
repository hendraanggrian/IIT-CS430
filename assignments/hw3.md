<!-- hotfix: KaTeX -->
<!-- https://github.com/yzane/vscode-markdown-pdf/issues/21/ -->
<script type="text/javascript" src="http://cdn.mathjax.org/mathjax/latest/MathJax.js?config=TeX-AMS-MML_HTMLorMML"></script>
<script type="text/x-mathjax-config">MathJax.Hub.Config({ tex2jax: { inlineMath: [['$', '$']] }, messageStyle: 'none' });</script>

# [Homework 3](https://github.com/hendraanggrian/IIT-CS430/blob/assets/assignments/hw3.pdf)

## Problem 1

> What is the largest possible number of internal (key) nodes in a red-black
  tree with black-height $k$ (measured from root)? What is the smallest possible
  number?

- **The largest internal nodes is $\bf 2^{2k} - 1$**, it is a path of half black
  and half red nodes.
- **The smallest is $\bf 2^k - 1$ nodes**, it is a path of all black nodes.

## Problem 2

> A team of biologists keeps information about DNA structures in a balanced
  binary search tree (i.e. AVL, red/black, etc) using as key the specific weight
  (an integer) of the structure. The biologists routinely ask questions of the
  type: "Are there any structures in the tree with specific weight between $a$
  and $b$ (inclusive)?" and they hope to get an answer as soon as possible.
  Design an efficient algorithm that, given integers $a$ and $b$, returns true
  if there exists a key $x$ in the tree such that $a \le x \le b$, and false if
  no such key exists in the tree. Describe your algorithm in pseudocode. What is
  the time complexity of your algorithm?

```java
public static boolean isInRange(Tree T, int a, int b) {
  if (a > b) {
    throw new IllegalArgumentException("Invalid input range");
  }
  if (T == null) {
    return false;
  }
  if (T.value < a) {
    return isInRange(T.right, a, b);
  } else if (T.value > b) {
    return isInRange(T.left, a, b);
  }
  return true;
}
```

[View full code](https://github.com/hendraanggrian/IIT-CS430/blob/main/playground/app/src/main/java/com/example/tree/DnaTrees.java)
/ [test](https://github.com/hendraanggrian/IIT-CS430/blob/main/playground/app/src/test/java/com/example/tree/DnaTreesTest.java)

Searching in balanced binary tree works by halving each traversal, **therefore
the time complexity is $\bf O(\log n)$**. However, this estimation doesn't
include the comparison cost.

## Problem 3

> Suppose that we can augment a red-black tree of $n$ nodes with an additional
  attribute at each node, the size, which is the number of (internal) nodes in
  the subtree rooted at $x$. That is, `size(x)` and it is the number of internal
  nodes in the subtree rooted at $x$. Also, the size at a node $x$ can be
  computed using only the information stored in $x$, `left(x)`, and `right(x)`,
  and that we can maintain “size” at all nodes during insertion and deletion
  in $O(\log n)$ time. In particular, we can maintain size so that `insert(x)`
  and `delete(x)` are supported in $O(\log n)$ time.
>
> Write a new function on a
  red-black tree, `countGreater(D, x)`, which returns the number of elements
  larger than $x$ in the red-black tree $D$. Your function should work even
  if $x$ is not in the red-black tree. Show your function runs in $O(\log n)$
  time.

```
# given functions
size(x): Number of internal nodes in the subtree rooted at x
left(x): left size of node x
right(x): right size of node x

countGreater(D, x):
  if (D.left == null) and (D.right == null)
    return D.value > x
  if D.value <= x
    return count(D.right, x)
  else
    return size(right(x) + 1 + count(D.left, x))
```

`countGreater(D, x)` is a recursive function with only 2 if statements:

- **In the base case** where $D$ do not have children, only consider self data.
- **In the recursive case**, traverse the node until we find the greatest node
  that satisfies `D.value <= x`.

**The function should run on $O(\log n)$**, because each tree traversion has
the maximum of 2 children.

## Problem 4

> $x$ is an integer. All nodes in a BST $T$ are primes. Design an algorithm to
  check $x$'s primality. If $x$ is a prime and its key exists in the BTS
  already, return $1$; if $x$ is a prime but its key does not exist in the BTS,
  insert it into the BST; if $x$ is not a prime, return $0$.

```
# modifiy given functions
insert(x) → insert(T, x): add node with x value to tree T

# custom function
contains(T, x): return true if node with x value is already in tree T

isPrime(x):
  if x < 2
    return false
  for i in range(2, int(sqrt(x)) + 1)
    if x % i == 0
     return false
  return false

hasPrime(T, x):
  if not isPrime(x)
    return 0
  if contains(T, x)
    return 1
  insert(T, x)
  return 1
```

Function `hasPrime(T, x)` has simple following logic:

- Return $1$ if $x$ is a prime number, $0$ otherwise.
- Add the node with value $x$ if it doesn't yet exist in tree $T$.

### Why change given functions?

I just don't see how it is possible to `insert(x)` to tree $T$ without
calling `insert(T, x)`.
