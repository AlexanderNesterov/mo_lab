package first;

public class Solution {
    private static final double[] COEFFS = {1, 0, (double) 2/3, 0, -3, -5};
    private static final double a = 0.0001;
    private static final double b = 5;
    private static final double u = 0.05;
    private static final double eps = 0.001;

    public static void main(String[] args) {
        System.out.println(checkInterval(a, b));
        Solution.dividingByHalf(a, b, 0.0001, eps);
        Solution.goldenSection(a, b, eps);
        Solution.parabola(0.0001, 5, 0.001);
        Solution.newton(u, eps);
    }

    public static double function(double x) {
        return COEFFS[0] * Math.pow(x, 5) + COEFFS[1] * Math.pow(x, 4) +
                COEFFS[2] * Math.pow(x, 3) + COEFFS[3] * Math.pow(x, 2) + COEFFS[4] * x + COEFFS[5];
    }

    public static double diffFirst(double x) {
        return 5 * COEFFS[0] * Math.pow(x, 4) + 4 * COEFFS[1] * Math.pow(x, 3) +
                3 * COEFFS[2] * Math.pow(x, 2) + 2 * COEFFS[3] * x + COEFFS[4];
    }

    public static double diffSecond(double x) {
        return 20 * COEFFS[0] * Math.pow(x, 3) + 12 * COEFFS[1] * Math.pow(x, 2) +
                6 * COEFFS[2] * x + 2 * COEFFS[3];
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
        System.out.println();
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
        System.out.println();
    }

    public static void parabola(double u1, double u3, double eps) {
        double u2 = (u3 + u1) / 2;
        int i = 0;
        double u2Check = u2;

        double deltaPlus = function(u1) - function(u2);
        double deltaMinus = function(u3) - function(u2);

        double w = u2 + ((((Math.pow((u3 - u2), 2)) * deltaMinus) - ((Math.pow((u2 - u1), 2)) * deltaPlus))
                / (2 * (((u3 - u2) * deltaMinus) + ((u2 - u1) * deltaPlus))));

        while (Math.abs(u2Check - w) >= eps) {
            i++;

            deltaMinus = function(u1) - function(u2);
            deltaPlus = function(u3) - function(u2);

            if ((deltaPlus + deltaMinus) == 0) {
                throw new ArithmeticException();
            }

            w = u2 + ((((Math.pow((u3 - u2), 2)) * deltaMinus) - ((Math.pow((u2 - u1), 2)) * deltaPlus))
                    / (2 * (((u3 - u2) * deltaMinus) + ((u2 - u1) * deltaPlus))));

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

        System.out.println("Parabola: ");
        System.out.println("Point: " + u2 + " Function: " + function(u2));
        System.out.println("Number of iterations: " + i);
    }

    private static void newton(double x, double eps) {
        int i = 0;

        while (Math.abs(diffFirst(x)) > eps) {
            i++;
/*            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println(diffSecond(x));*/
            if (diffSecond(x) == 0) {
                x += 0.1;
                continue;
            }

            x = x - (diffFirst(x) / diffSecond(x));
        }

        System.out.println("Newton: ");
        System.out.println("Point: " + x + " Function: " + function(x));
        System.out.println("Number of iterations: " + i);
    }

    private static boolean checkInterval(double a, double b) {
        double step = (b - a) / 50000;

        for (double i = a; i < b; i += step) {
//            System.out.println(diffSecond(i));
            if (diffSecond(i) < 0) {
                return false;
            }
        }

        return true;
    }
}
