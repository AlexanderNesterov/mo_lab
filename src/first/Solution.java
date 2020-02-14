package first;

public class Solution {
    public static void main(String[] args) {
        Solution.dividingByHalf(0, 1, 0.2, 0.2);
        Solution.goldenSection(0, 1, 0.2);
    }

    public static double function(double x) {
        return x * x * x * (x * x - 1);
    }

    public static void dividingByHalf(double a, double b, double beta, double eps) {
        while (Math.abs(b - a) >= eps) {
            double left = (b + a - beta) / 2;
            double right = (b + a + beta) / 2;

            double functionLeft = function(left);
            double functionRight = function(right);

            if (functionLeft == functionRight) {
                a = left;
                b = right;
            } else if (functionLeft > functionRight) {
                a = left;
            } else {
                b = right;
            }
        }

        System.out.println("Dividing by half: ");
        System.out.println("Point: " + ((b + a) / 2) + " Function: " + function((b + a) / 2));
    }

    public static void goldenSection(double a, double b, double eps) {
        while (Math.abs(b - a) >= eps) {
            double alphaOne = (Math.sqrt(5) - 1) / 2;
            double alphaTwo = (3 - Math.sqrt(5)) / 2;

            double left = a + alphaTwo * (b - a);
            double right = a + alphaOne * (b - a);

            double functionLeft = function(left);
            double functionRight = function(right);

            if (functionLeft == functionRight) {
                b = right;
                a = left;
            } else if (functionLeft > functionRight) {
                a = left;
            } else {
                b = right;
            }
        }

        System.out.println("Golden section: ");
        System.out.println("Point: " + ((b + a) / 2) + " Function: " + function((b + a) / 2));
    }

    public static double parabola() {
        return 2.0;
    }
}
