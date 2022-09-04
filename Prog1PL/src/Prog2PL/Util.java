package Prog2PL;

import java.util.List;

public class Util {

    public static int gcd(int a, int b) {
        if (a == 0) return b;
        if (b == 0) return a;

        while (a != b) {
            if (a > b)
                a -= b;
            else
                b -= a;
        }
        return a;
    }

    public static int gcdFromList(List<Integer> list) {
        return list.stream().reduce(0, (total, element) -> gcd(total, element));
    }
}
