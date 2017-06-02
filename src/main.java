import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Created by Anthony on 5/22/2017.
 */
public class main {
    static public void main(String args[]) {
        input();
    }

    static void input(){
        double[][] a;
        double[] x;
        double[] y;
        int n = 0;
        try {
            File f = new File("input.txt");
            Scanner s = new Scanner(f);
            x = read(s.nextLine());
            y = read(s.nextLine());
            n = x.length;
            a = new double[n][n];
            dd(a,x,y);
            System.out.println(arrayToString(a,x));
            poly p = new poly(a,x);
            System.out.println(p.nested());
            System.out.println(p.interpolating());
            System.out.println(p.simply());

            s.close();
        } catch (FileNotFoundException e) {
            System.err.println("Caught Exception: " + e.getMessage());
        }


    }

    static double[] read(String s) {
        String[] a = s.split(" ");
        double[] result = new double[a.length];
        for (int i = 0 ; i < result.length; i++)
            result[i] = Double.parseDouble(a[i]);
        return result;
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
        String next = "f[]";
        String result = String.format("%-17s","x");
        result += String.format("%-17s",next);
        if ( n > 15){
            for (int i = 1; i < n; i++) {
                next = next.substring(0, 2) + i + next.substring(3, next.length()) + ",]";
                result += String.format("%-17s", next);
                next = "f[]";
            }
        } else {
            for (int i = 1; i < n; i++) {
                next = next.substring(0, 2) + "," + next.substring(2, next.length());
                result += String.format("%-17s", next);
            }
        }
        result += "\n";
        for (int i = 0; i < n; i+=1) {
            result += String.format("%-17f",x[i]);
            for (int j = 0; j < n - i; j+=1) {
                result += String.format("%-17f",a[i][j]);
            }
            result += "\n";
        }
        return result;
    }
}
