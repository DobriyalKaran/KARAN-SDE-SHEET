/*
Problem Description

Given a number A, find if it is COLORFUL number or not.

If number A is a COLORFUL number return 1 else, return 0.

What is a COLORFUL Number:

A number can be broken into different consecutive sequence of digits.
The number 3245 can be broken into sequences like 3, 2, 4, 5, 32, 24, 45, 324, 245 and 3245.
This number is a COLORFUL number, since the product of every consecutive sequence of
digits is different.


Problem Constraints
1 <= A <= 2 * 10^9

A = 23
o/p = 1

Explanation 1:
Possible Sub-sequences: [2, 3, 23] where
2 -> 2
3 -> 3
23 -> 6  (product of digits)
This number is a COLORFUL number since product of every digit of a sub-sequence are different.
 */

import java.util.*;
public class Main {
    public static void main(String[] args) {
        int A = 23;
        System.out.println(colorful(A));
    }
    public static int colorful(int A) {
        HashSet < Integer > hashSet = new HashSet < > ();
        ArrayList < Integer > numbers = new ArrayList < > ();
        while (A != 0) {
            int num = A % 10;
            numbers.add(num);
            A /= 10;
        }
        Collections.reverse(numbers);
        int n = numbers.size();
        for (int i = 0; i < n; i++) {
            int prod = 1;
            for (int j = i; j < n; j++) {
                // prod stores the product of every digit in the range [i..j]
                prod *= numbers.get(j);
                // check if the product is unique
                if (hashSet.contains(prod))
                    return 0;
                hashSet.add(prod);
            }
        }
        return 1;
    }
}