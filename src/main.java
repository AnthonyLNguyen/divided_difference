/**
 * Created by Anthony on 5/22/2017.
 */
public class main {
    static public void main(String args[]) {
        int n = 4;
        double[][] a = new double[n][n];
        double[] x = new double[n];
        double[] y = new double[n];
        x[0] = 1;
        x[1] = 3d/2d;
        x[2] = 0;
        x[3] = 2;
        y[0] = 3;
        y[1] = 13d/4d;
        y[2] = 3;
        y[3] = 5d/3d;
        dd(a,x,y);
        System.out.println(arrayToString(a,x));
    }

    static void dd(double[][] a, double[] x, double[] y){
        int n = a.length;
        for (int i = 0; i < n; i++){
            a[i][0] = y[i];
        }
        for (int j = 1; j < n; j++){
            for (int i = 0; i < n - j; i++)
                a[i][j] = (a[i+1][j-1]-a[i][j-1])/(x[i+j]-x[i]);
        }
    }

    static String arrayToString(double[][] a, double[] x){
        int n = a.length;
        String result = String.format("%-24s","x");
        result += String.format("%-24s","f[]");
        for (int i = 1; i < n; i++) {
            result += "f[";
            for (int j = 0; j < i; j++) {
                result += ",";
            }
            result += String.format("%-20s","]");
        }
        result += "\n";
        for (int i = 0; i < n; i+=1) {
            result += String.format("%-24s",x[i]);
            for (int j = 0; j < n - i; j+=1) {
                result += String.format("%-24s",a[i][j]);
            }
            result += "\n";
        }
        return result;
    }
}
