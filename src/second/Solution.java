package second;

import first.UserInput;

import java.util.Arrays;

public class Solution {
    private static double eps = 0.01;
    private static double alpha = 1;
    private final static double[] u00 = new double[]{UserInput.getDouble("FIRST COORDINATE"),
            UserInput.getDouble("SECOND COORDINATE")};

    public static void main(String[] args) {
        eps = UserInput.getDouble("EPS");
        alpha = UserInput.getDouble("ALPHA");

        System.out.println("Method of half divided interval");
        step();

        System.out.println();
        System.out.println("GD");
        gd();

        System.out.println();
        System.out.println("Newton");
        newton();
    }

    private static double f(double[] u) {
        return -(u[0] * u[0]) - (u[1] * u[1]) + 7 * u[0] * u[1];
    }

    private static double fU1(double[] u) {
        return -2 * u[0] + 7 * u[1];
    }

    private static double fU2(double[] u) {
        return -2 * u[1] + 7 * u[0];
    }

    private static double moduleGrad(double[] grad) {
        return Math.sqrt(grad[0] * grad[0] + grad[1] * grad[1]);
    }

    private static double[] grad(double[] u) {
        return new double[]{fU1(u), fU2(u)};
    }

    private static double help(double alpha) {
        double[] grad = grad(u00);
        return f(new double[]{(u00[0] - alpha * grad[0]), (u00[1] - alpha * grad[1])});
    }

    public static double[] bisection(double a, double b, double x) {
        double delta = 0.0001;
        double eps = 0.001;
        int n = 0;

        while (Math.abs(b - a) >= eps) {
            n++;
            double u1 = (b + a - delta) / 2;
            double u2 = (b + a + delta) / 2;

            double functionU1 = help(u1);
            double functionU2 = help(u2);

            if (functionU1 == functionU2) {
                a = u1;
                b = u2;
            } else if (functionU1 > functionU2) {
                a = u1;
            } else {
                b = u2;
            }
        }

        return new double[]{(b + a) / 2, help((b + a) / 2), n};
    }

    private static void step() {
        double funcAlpha = alpha;
        double[] u0 = Arrays.copyOf(u00, u00.length);
        int k = 0;

        while (moduleGrad(grad(u0)) >= eps) {
            k++;
            double[] grad = grad(u0);
            double[] u1 = {(u0[0] - funcAlpha * grad[0]), (u0[1] - funcAlpha * grad[1])};

            if (f(u1) < f(u0)) {
                u0 = u1;
            } else {
                funcAlpha /= 2;
            }
        }

        System.out.println("f: " + f(u0));
        System.out.println("u0: " + Arrays.toString(u0));
        System.out.println("k: " + k);
    }

    private static void gd() {
        int k = 0;
        int n = 0;
        double funcAlpha = alpha;
        double[] u0 = Arrays.copyOf(u00, u00.length);

        while (moduleGrad(grad(u0)) >= eps && funcAlpha != 0) {
            k++;
            double[] grad = grad(u0);
            u0[0] -= funcAlpha * grad[0];
            u0[1] -= funcAlpha * grad[1];

            double[] results = bisection(-100, 100, 1);
            funcAlpha = results[0];
            n = (int) results[2];

            k += n;
        }

        System.out.println("f: " + f(u0));
        System.out.println("u0: " + Arrays.toString(u0));
        System.out.println("k: " + k);
    }

    private static void newton() {
        int k = 0;
        double[] u0 = Arrays.copyOf(u00, u00.length);

        while (moduleGrad(grad(u0)) >= eps && k < 4) {
            k++;

            double[] duobles = {grad(u0)[0] * ((double) 2 / 45) + grad(u0)[1] * ((double) 7 / 45)
                    , grad(u0)[0] * ((double) 7 / 45) + grad(u0)[1] * ((double) 2 / 45)};
            u0[0] -= duobles[0];
            u0[1] -= duobles[1];

        }

        System.out.println("f: " + f(u0));
        System.out.println("u0: " + Arrays.toString(u0));
        System.out.println("k: " + k);
    }
}
