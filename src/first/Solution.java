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
        int i = 0;

        while (Math.abs(b - a) >= eps) {
            i++;
            double u1 = (b + a - beta) / 2;
            double u2 = (b + a + beta) / 2;

            double functionU1 = function(u1);
            double functionU2 = function(u2);

            if (functionU1 == functionU2) {
                a = u1;
                b = u2;
            } else if (functionU1 > functionU2) {
                a = u1;
            } else {
                b = u2;
            }
        }

        System.out.println("Dividing by half: ");
        System.out.println("Point: " + ((b + a) / 2) + " Function: " + function((b + a) / 2));
        System.out.println("Number of iterations: " + i);
    }

    public static void goldenSection(double a, double b, double eps) {
        int i = 0;

        double alpha = (Math.sqrt(5) - 1) / 2;
        double alphaOne = (3 - Math.sqrt(5)) / 2;

        double u1 = a + alphaOne * (b - a);
        double u2 = a + alpha * (b - a);

        double functionU1 = function(u1);
        double functionU2 = function(u2);

        while (Math.abs(b - a) >= eps) {
            i++;

            if (functionU1 == functionU2) {
                b = u2;
                a = u1;
                u1 = a + alphaOne * (b - a);
                u2 = a + alpha * (b - a);
                functionU1 = function(u1);
                functionU2 = function(u2);
            } else if (functionU1 > functionU2) {
                a = u1;
                u1 = u2;
                functionU1 = functionU2;
                u2 = a + alpha * (b - a);
                functionU2 = function(u2);
            } else {
                b = u2;
                u2 = u1;
                functionU2 = functionU1;
                u1 = a + alphaOne * (b - a);
                functionU1 = function(u1);
            }
        }

        i++;
        System.out.println("Golden section: ");
        System.out.println("Point: " + ((b + a) / 2) + " Function: " + function((b + a) / 2));
        System.out.println("Number of iterations: " + i);
    }

    public static void parabola(double u1, double u3, double eps) {
        double u2 = (u3 + u1) / 2;
        int i = 0;
        double u2Check = u2;

        double deltaPlus = function(u1) - function(u2);
        double deltaMinus = function(u3) - function(u2);

        double w = (((Math.pow((u3 - u2), 2)) * deltaMinus) - ((Math.pow((u2 - u1), 2)) * deltaPlus))
                / (2 * ((u3 - u2) * deltaMinus + (u2 - u1) * deltaPlus));

        while (Math.abs(u2Check - w) >= eps) {
            i++;

            deltaPlus = function(u1) - function(u2);
            deltaMinus = function(u3) - function(u2);

            w = (((Math.pow((u3 - u2), 2)) * deltaMinus) - ((Math.pow((u2 - u1), 2)) * deltaPlus))
                    / (2 * ((u3 - u2) * deltaMinus + (u2 - u1) * deltaPlus));

            u2Check = u2;

            if (w < u2) {
                if (function(w) < function(u2)) {
                    u3 = u2;
                    u2 = w;
                } else if (function(w) > function(u2)) {
                    u1 = w;
                } else {
                    if (function(u1) > function(u2)) {
                        u3 = u2;
                        u2 = w;
                    } else if (function(u2) > function(u3)) {
                        u1 = w;
                    }
                }
            } else if (w > u2) {
                if (function(w) < function(u2)) {
                    u1 = u2;
                    u2 = w;
                } else if (function(w) > function(u2)) {
                    u3 = w;
                } else {
                    if (function(u3) > function(u2)) {
                        u1 = u2;
                        u2 = w;
                    } else if (function(u1) > function(u2)) {
                        u3 = w;
                    }
                }
            } else {
                if (function(u2 - 0.01) < function(u2)) {
                    u2 -= 0.01;
                } else {
                    u2 += 0.01;
                }
            }
        }

        System.out.println("x: " + u2);
        System.out.println("f(x): " + function(u2));
        System.out.println("Number of iterations: " + i);
    }
}
