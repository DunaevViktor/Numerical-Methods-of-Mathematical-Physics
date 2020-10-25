package sample;
import javax.xml.bind.SchemaOutputResolver;

public class Main {

    public static double U(double x) {
        return Math.pow(x, 2);
    }

    public static double M(double t) {
        return 25 * Math.pow(t, 2);
    }

    public static double f(double x, double t) {
        return x * t;
    }

    public static void main(String[] args) {

        double h = 0.05;
        double tau = 0.01;
        double a = 5;
        double gamma = (a * tau) / h;
        double sigma = 1 / 2 + h / (10 * tau);
        double a1 = 0;
        double b1 = 1;
        int N1 = (int) ((b1 - a1) / h);
        int N2 = (int) ((b1 - a1) / tau);
        double t[] = new double[N2 + 1];
        for (int i = 0; i <= N2; i++) {
            t[i] = i * tau;
        }
        double x[] = new double[N1 + 1];
        for (int i = 0; i <= N1; i++) {
            x[i] = i * h;
        }
        double A[][] = new double[N2 + 1][N1 + 1];
        for (int i = 0; i <= N1; i++) {
            A[0][i] = U(x[i]);
        }
        for (int j = 0; j <= N2; j++) {
            A[j][0] = M(t[j]);
        }
        for (int j = 0; j < N2; j++) {
            for (int i = 1; i <= N1; i++) {
                A[j + 1][i] = (((tau) / (1 + sigma * gamma)) * f(x[i], t[j]) + (1 - (1 - sigma) * gamma) / (1 + sigma * gamma)) * A[j][i] + ((sigma * gamma) / (1 + sigma * gamma)) * A[j + 1][i - 1] + (((1 - sigma) * gamma) / (1 + sigma * gamma)) * A[j][i - 1];
            }
        }
        System.out.println("Решение:");
        for (int i = 0; i <= N2; i++) {
            for (int j = 0; j <= N1; j++) {
                System.out.format("%.4f", A[i][j]);
                System.out.print(" ");
            }
            System.out.println();
        }

        double h2 = h / 2;
        double tau2 = tau / 2;
        double gamma2 = (a * tau2) / h2;
        double sigma2 = 1 / 2 + h2 / (10 * tau2);
        int N21 = (int) ((b1 - a1) / h2);
        int N22 = (int) ((b1 - a1) / tau2);
        double t2[] = new double[N22 + 1];
        for (int i = 0; i <= N22; i++) {
            t2[i] = i * tau2;
        }
        double x2[] = new double[N21 + 1];
        for (int i = 0; i <= N21; i++) {
            x2[i] = i * h2;
        }
        double A2[][] = new double[N22 + 1][N21 + 1];
        for (int i = 0; i <= N21; i++) {
            A2[0][i] = U(x2[i]);
        }
        for (int j = 0; j <= N22; j++) {
            A2[j][0] = M(t2[j]);
        }
        for (int j = 0; j < N22; j++) {
            for (int i = 1; i <= N21; i++) {
                A2[j + 1][i] = (((tau2) / (1 + sigma2 * gamma2)) * f(x2[i], t2[j]) + (1 - (1 - sigma2) * gamma2) / (1 + sigma2 * gamma2)) * A2[j][i] + ((sigma2 * gamma2) / (1 + sigma2 * gamma2)) * A2[j + 1][i - 1] + (((1 - sigma2) * gamma2) / (1 + sigma2 * gamma2)) * A2[j][i - 1];
            }
        }
        System.out.println("Решение:");
        for (int i = 0; i <= N22; i+=2) {
            for (int j = 0; j <= N21; j+=2) {
                System.out.format("%.4f", A2[i][j]);
                //System.out.print(A[i][j]+ " ");
                System.out.print(" ");
            }
            System.out.println();
        }

        System.out.println();
        System.out.println("Невязка:");
        System.out.println();
        int mnojI = (int) (h / h2);
        int mnojJ = (int) (tau / tau2);
        for (int i = 0; i <= N2; i ++) {
            for (int j = 0; j <= N1; j++) {
                System.out.format("%.4f", Math.abs(A2[i * mnojI][j * mnojJ] - A[i][j]));
                System.out.print(" ");
            }
            System.out.println();
        }


    }

}
