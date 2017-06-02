import java.util.Arrays;

/**
 * Created by Anthony on 6/2/2017.
 */
public class poly {
    private double[][] a;
    private double[] x;

    public poly(double[][] a, double[] x) {
        this.a = a;
        this.x = x;
    }

    public String nested() {
        String result = "NESTED:\n";
        for (int i = 0; i < a[0].length-1; i++) {
            result += a[0][i];
            result += " + ";
            if (x[i] == 0)
                result += "x";
            else {
                result += (x[i] < 0) ? "(x+" : "(x";
                result += -x[i] + ")";
            }
            result += "(";
        }
        result += a[0][a[0].length-1];
        for (int i = 0; i < a[0].length-1; i++) {
            result += ")";
        }
        return result;
    }

    public String interpolating() {
        String result = "INTERPOLATING:\n" + a[0][0] + "";
        for (int i = 1; i < a[0].length; i++) {
            result += (a[0][i] < 0) ? " - " + -a[0][i] : " + " + a[0][i];
            for (int j = 0; j < i; j++) {
                if (x[j] == 0)
                    result += "x";
                else {
                    result += (x[j] < 0) ? "(x+" : "(x";
                    result += -x[j] + ")";
                }
            }
        }
        return result;
    }

    public String simply() {
        String result = "SIMPLIFIED:\n";
        double[] n = getCoefficients(a[0]);
        result += (n[0] == 0) ? "":"" + n[0] + "x^" + (n.length - 1);
        for (int i = 0; i < n.length; i++) {
            if (n[i] != 0) {
                result += (n[i] < 0) ? " - " + -n[i] + "" : " + " + n[i] + "";
                if (n.length - i - 1 != 0)
                    result += "x^" + (n.length - i - 1);
            }
        }
        return result;
    }

    public double[] getCoefficients(double[] a) {
        double[] result = new double[1];
        double[] val = Arrays.copyOfRange(x, 0, x.length - 1);
        result[0] = a[a.length - 1];
        for (int i = val.length - 1; i >= 0; i--) {
            result = expand(val[i], result, a[i]);
        }
        return result;
    }

    public double[] expand(double x, double[] k, double m) {
        double[] result = new double[k.length + 1];
        for (int i = 0; i < k.length; i++)
            result[i] = k[i];
        for (int i = result.length - 1; i > 0; i--) {
            result[i] -= x * result[i - 1];
        }
        result[result.length - 1] += m;
        return result;
    }
}
