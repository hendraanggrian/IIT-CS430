<!-- hotfix: KaTeX -->
<!-- https://github.com/yzane/vscode-markdown-pdf/issues/21/ -->
<script type="text/javascript" src="http://cdn.mathjax.org/mathjax/latest/MathJax.js?config=TeX-AMS-MML_HTMLorMML"></script>
<script type="text/x-mathjax-config">MathJax.Hub.Config({ tex2jax: { inlineMath: [['$', '$']] }, messageStyle: 'none' });</script>

# [Homework 1](https://github.com/hendraanggrian/IIT-CS430/blob/assets/assignments/hw1.pdf)

## Problem 1

### Subproblem 1A

> Use pseudocode to specify a brute-force algorithm that determines when given
  as input a sequence of $n$ positive integers whether there are two distinct
  terms of the sequence that have as sum a third term. The algorithm should loop
  through all triples of terms of the sequence, checking whether the sum of the
  first two terms equals the third.

In a brute-force approach, simply create 3 pointers that gradually move forward
covering all posibilities.

```java
public static boolean hasTupleBruteForce(int[] A) {
  for (int i = 0; i < A.length - 2; i++) {
    for (int j = i + 1; j < A.length - 1; j++) {
      for (int k = j + 1; k < A.length; k++) {
        // because the input is unsorted, manually find the highest value and compare the rest
        int max = Math.max(A[i], Math.max(A[j], A[k]));
        if (A[i] + A[j] + A[k] - max == max) {
          return true;
        }
      }
    }
  }
  return false;
}
```

[View full code](https://github.com/hendraanggrian/IIT-CS430/blob/main/playground/app/src/main/java/com/example/FindATuples.java)
/ [test](https://github.com/hendraanggrian/IIT-CS430/blob/main/playground/app/src/test/java/com/example/FindATuplesTest.java)

### Subproblem 1B

> Give a big-O estimate for the complexity of the brute-force algorithm part
  (a).

**The big-O estimate is $\bf O(n^3)$**, because we're searching for permutations
of 3 in $n$.

### Subproblem 1C

> Devise a more efficient algorithm for solving the problem that first sorts the
  input sequence and then checks for each pair of terms whether their sum is in
  the sequence.

A more efficient solution is to sort the input first, allowing the comparison to
be executed in 1 pass.

```java
public static boolean hasTupleSorted(int[] A) {
  Arrays.sort(A);
  for (int i = 0; i < A.length - 2; i++) {
    if (A[i] + A[i + 1] == A[i + 2]) {
      return true;
    }
  }
  return false;
}
```

### Subproblem 1D

> Give a big-O estimate for the complexity of this algorithm. Is it more
  efficient than the brute-force algorithm?

**The big-O estimate is $\bf O(n^2 \log n)$**, because function `Array.sort`
operates at $O(n \log n)$.

## Problem 2

> Prove, by induction on $k$, that level $k$ of a binary tree has less than or
  equal to $2^k$ nodes (root level has $k = 0$).

$$
\begin{array}{rcl}
  T(k+1) &\le& 2T(k) \quad \textsf{to increment the tree height, max of
    2 leaf nodes can be added} \\
   &\le& 2 . 2^k \quad \ \ \ \textsf{from the assumption} \\
   &\le& 2^{k+1} \quad \ \ \ \textsf{reducing $k-1$} \\
  \bf T(k) &\le& \bf 2^{k} \qquad \ \ \textsf{\textbf{therefore true}, the
    equation is balanced}
\end{array}
$$

## Problem 3

> Use definition of big O to prove or disprove.

### Subproblem 3A

> Is $2^{n+1} = O(2^n)$?

$$
\begin{array}{rcl}
  2^{n+1} &=& O(2^n) \\
  2^n . 2^1 &\le& c2^n \qquad \textsf{divide all by $2^n$} \\
  \bf 2^1 &\le& \bf c \qquad\quad \textsf{\textbf{therefore true},
    because $c > 0$}
\end{array}
$$

### Subproblem 3B

> Is $2^{2n} = O(2^n)$?

$$
\begin{array}{rcl}
  2^{2n} &=& O(2^n) \\
  2^n . 2^n &\le& c2^n \qquad \textsf{divide all by $2^n$} \\
  \bf 2^n &\le& \bf c \qquad\quad \textsf{\textbf{therefore false}, because $c$
    and $n$ is unbounded}
\end{array}
$$

## Problem 4

> The following routine takes as input a list of $n$ numbers, and returns the
  first value of $i$ for which $L[i] < L[i-1]$, or $n$ if no such number exists.
>
> ```c
> int firstDecrease(int * L, int n) {
>   for (int i = 2; i <= n && L[i] >= L[i - 1]; i++) {
>     return i;
>   }
> }
> ```

### Subproblem 4A

> What is the big-O runtime for the routine, measured as a function of its
  return value $i$?

| Line | Cost |
| --- | --- |
| `int firstDecrease(int * L, int n) {` | |
| &emsp;`for (int i = 2; i <= n && L[i] >= L[i - 1]; i++) {` | $a$ |
| &emsp;&emsp;`return i;` | $b$ |
| &emsp;`}` | |
| `}` | |

$$
\begin{array}{rcl}
  \bf T(i) &=& a . i + b \\
  &=& \bf O(i)
\end{array}
$$

### Subproblem 4B

> If the numbers are chosen independently at random, then the probability
  that `firstDecrease(L) returns i` is $(i-1)/i!$, except for the special case
  of $i = n+1$ for which the probability is $1/n!$ Use this fact to write an
  expression for the expected value returned by the algorithm. (Your answer can
  be expressed as a sum, it does not have to be solved in closed form. Do not
  use O-notation.)

#### Given

$$
\begin{array}{rcl}
  Pr(i) &=& (i-1) / i! \\
  Pr(n+1) &=& 1 / n \\
\end{array}
$$

#### Expectation

$$
\begin{array}{rcl}
  \bf E(i) &=& \Sigma i . Pr(i) \quad \textsf{where $i$ is from $2$ to $n+1$}\\
  &=& \Sigma i . Pr(i) + (n+1) . Pr(n+1) \\
  &=& 2*(2-1)/2! + 3*(3-1)/3! + \ldots n*(n-1)/n! + n*(n+1)/n! \\
  &=& \bf (2*1)/2! + (3*2)/3! + \ldots n*(n-1)/n! + n*(n+1)/n!
\end{array}
$$

### Subproblem 4C

> What is the big-O average case running time of the routine? Hint: Simplify
  the previous summation until you see a common taylor series.

| Theory | Equation | Where |
| --- | --- | --- |
| Taylor series | $\displaystyle \sum_{n=0}^{\infty} \frac{f^{(n)}(a)}{n!} (x-a)^n$ | $n!$ is factorial of $n$.<br>$a$ is real or complex number.<br>$f^{(n)}(a)$ is $n$-th derivative of $f$ evaluated at the point $a$. |

$$
\begin{array}{rcl}
  \bf f(x) &=& f(a) + f'(a)(x-a) + \frac{f''(a)}{2!}(x-a)^3 + \ldots +
    \frac{f^{(3)}(a)}{n!}(x-a)^n + \ldots \\
  &=& f(0)\frac{x^0}{0!} + f'(0)\frac{x^1}{1!} + f''(0)\frac{x^2}{2!} +
    \ldots \\
  &=& \bf e^x
\end{array}
$$

With $x=1$, we get our $E(i)$. **Hence $\bf E(i) = O(e)$**.

## Problem 5

> Consider the following program and recursive function.
>
> ```c
> #include <iostream.h>
> void Z(int[], int, int);
> void swap(int&, int&);
>
> void main() {
>   int A[3] = {1, 2, 3};
>   int n = 3;
>   Z(A, n, 0);
> }
>
> void swap(int &x, int &y) {
>   int temp;
>   temp = x;
>   x = y;
>   y = temp;
> }
>
> void Z(int A[], int n, int k) {
>   if (k == n - 1) {
>     for (int i = 0; i < n; i++) {
>       cout << A[i] << " ";
>     }
>     cout << endl;
>   } else {
>     for (int i = k; i < n; i++) {
>       swap(A[i], A[k]);
>       Z(A, n, k + 1);
>       swap(A[i], A[k]);
>     }
>   }
> }
> ```

[View full code](https://github.com/hendraanggrian/IIT-CS430/blob/main/playground/app/src/main/java/com/example/Permutations.java)
/ [test](https://github.com/hendraanggrian/IIT-CS430/blob/main/playground/app/src/test/java/com/example/PermutationsTest.java)

### Subproblem 5A

> Demonstrate the execution, show the output, and explain what the program
  accomplishes.

`Z` is a recursive function with 2 pointers with conditions:

- **In the base case**, print the result of this recursion leaf node when the
  left pointer reaches the right.
- **In the recursive case**, iterate every possibility of combination by
  temporarily swapping values by the index of the array.

Function `main` starts the recursive reaction, printing all combinations of $A$,
each has a length of $n$. The result:

```
1 2 3
1 3 2
2 1 3
2 3 1
3 2 1
3 1 2
```

Based on such characteristics and results, **I believe this is a function to
print permutations of an array of integers**.

### Subproblem 5B

> Give a recurrence equation describing the worst-case behavior of the program.

| Line | Relation |
| --- | --- |
| `void Z(int A[], int n, int k) {` | $T(n)$ |
| &emsp;`if (k == n - 1) {`| |
| &emsp;&emsp;`for (int i = 0; i < n; i++) {` | $\bf n$ |
| &emsp;&emsp;&emsp;`cout << A[i] << " ";` | $O(1)$ |
| &emsp;&emsp;`}` | |
| &emsp;&emsp;`cout << endl;` | $O(1)$ |
| &emsp;`} else {` | |
| &emsp;&emsp;`for (int i = k; i < n; i++) {` | $\bf n$ |
| &emsp;&emsp;&emsp;`swap(A[i], A[k]);` | $O(1)$ |
| &emsp;&emsp;&emsp;`Z(A, n, k + 1);` | $T(n-1)$ |
| &emsp;&emsp;&emsp;`swap(A[i], A[k]);` | $O(1)$ |
| &emsp;&emsp;`}` | |
| &emsp;`}` | |
| `}` | |

| Case | Equation |
| --- | --- |
| Base | $\begin{array}{rcl} \bf T(1) &=& n . O(1) + O(1) \\ &=& \bf n \end{array}$ |
| Recursive | $\begin{array}{rcl} \bf T(n) &=& n . (O(1) + T(n-1) + O(1)) \\ &=& \bf n . T(n-1) \end{array}$ |

### Subproblem 5C

> Solve the recurrence equation.

$$
\begin{array}{rcl}
  T(n) &=& n . T(n-1) \\
  &=& 2n + n . T(n-1) \\
  &=& 2n + n[2(n-1) + (n-1) . T(n-2)] \\
  &=& 2n + n . [2(n-1) + (n-1) . [2(n-2) + (n-2) . T(n-3)]] \\
  &=& 2n + 2 . n(n-1) + 2 . n(n-1)(n-2) \ldots 2.n!
\end{array}
$$

$n$ terms, and each is $\le 2.n!$.

$$
\begin{array}{rcl}
  \bf T(n) &=& n . 2n! \\
  &=& \bf O(n+1!) \textsf{ or } O(n!)
\end{array}
$$

## Problem 6

> Let $A[1 \ldots n]$ be an array of $n$ distinct numbers. If $i < j$
  and $A[i] > A[j]$, then the pair $\{i,j\}$ is called an inversion of $A$.

### Subproblem 6A

> List the five inversions of the array $\{2,3,8,6,1\}$.

We start the 2 pointers on the right:

| # | Pair | $i < j$ | $A[i] > A[j]$ |
| ---: | --- | --- | --- |
| 1 | $\bf \{4, 5\}$ | $4 < 5$ | $6 > 1$ |
| 2 | $\bf \{3, 5\}$ | $3 < 5$ | $8 > 1$ |
| 3 | $\bf \{2, 5\}$ | $2 < 5$ | $3 > 1$ |
| 4 | $\bf \{1, 5\}$ | $1 < 5$ | $2 > 1$ |
| 5 | $\bf \{3, 4\}$ | $3 < 4$ | $8 > 6$ |

### Subproblem 6B

> What array with elements from the set $\{1,2,\ldots,n\}$ has the most
  inversions? How many does it have?

Using the previous example of starting from the right, arrays with the most
inversions are in reversed order.

$$
\begin{array}{rcl}
  \displaystyle \bf \sum_{k=1}^{n-1}k &=& (n-1) + (n-2) + \ldots + 2 + 1 \\
  &=& n(n-1) / 2 \\
  &=& \bf O(n^2) \\
\end{array}
$$

### Subproblem 6C

> What is the relationship between the running time of *insertion sort* and the
  number of inversions in the input array? Justify your answer.

| # | Swaps |
| ---: | --- |
| 1 | $\bf \{8, 6\}$ |
| 2 | $\bf \{2, 1\}$ |
| 3 | $\bf \{3, 1\}$ |
| 4 | $\bf \{6, 1\}$ |
| 5 | $\bf \{8, 1\}$ |

### Subproblem 6D

> Give an algorithm that determines the number of inversions in any permutation
  on $n$ elements in $\Theta(n \log n)$ worst-case time. (Hint: Modify *merge
  sort*.)

```java
public static int countInversions(int[] A, int p, int r) {
  if (p >= r) {
    return 0;
  }
  int mid = (p + r) / 2;
  int left = countInversions(A, p, mid);
  int right = countInversions(A, mid + 1, r);
  return left + right + mergeSortAndCountInversions(A, p, mid, r);
}

static int mergeSortAndCountInversions(int[] A, int p, int q, int r) {
  // split into sub-arrays
  int[] left = new int[q - p + 1];
  int[] right = new int[r - q];
  for (int i = 0; i < left.length; ++i) {
    left[i] = A[p + i];
  }
  for (int j = 0; j < right.length; ++j) {
    right[j] = A[q + 1 + j];
  }
  // merge sub-arrays
  int i = 0;
  int j = 0;
  int k = p;
  int inversions = 0;
  while (i < left.length && j < right.length) {
    if (left[i] <= right[j]) {
      A[k] = left[i];
      i++;
    } else {
      inversions += left.length - i + 1;
      A[k] = right[j];
      j++;
    }
    k++;
  }
  return inversions;
}
```

[View full code](https://github.com/hendraanggrian/IIT-CS430/blob/main/playground/app/src/main/java/com/example/Inversions.java)
/ [test](https://github.com/hendraanggrian/IIT-CS430/blob/main/playground/app/src/test/java/com/example/InversionsTest.java)

## Problem 7

> Give big-O bounds for $T(n)$ in each of the following recurrences.
  (Substitution or Recursion Tree)

### Subproblem 7A

> $T(n) = T(n-1) + n$

By expansion:

$$
\begin{array}{rcl}
  T(1) &=& 1 \qquad\qquad \ \text{is the base case} \\
  T(2) &=& T(1) + 2 \quad \text{start of the recursive case} \\
  T(3) &=& T(2) + 3 \quad \ldots
\end{array}
$$

As is the case previously, **the big-O is $\bf O(n^2)$**.

### Subproblem 7B

> $T(n) = T(n/4) + T(n/2) + n^2$

Using Recursion Tree:

- $n^2 = (n/4)^2 + (n/2)^2$
  - $(\frac{n}{4})^2 = (\frac{n}{4} / 4)^2 + (\frac{n}{4} / 2)^2$
    - $(\frac{n}{16})^2 = \ldots$
    - $(\frac{n}{8})^2 = \ldots$
  - $(\frac{n}{2})^2 = (\frac{n}{2} / 4)^2 + (\frac{n}{2} / 2)^2$
    - $(\frac{n}{8})^2 = \ldots$
    - $(\frac{n}{4})^2 = \ldots$

Calculating by level:

$$
\begin{array}{rcl}
  1n^2 &=& (1.\frac{5}{16})n^2 \\
  \frac{5}{16}n^2 &=& (\frac{5.5}{16.16})n^2 \\
  \frac{25}{256}n^2 &=& \ldots
\end{array}
$$

Dropping the lower term, **the big-O is $\bf O(n^2)$**.
