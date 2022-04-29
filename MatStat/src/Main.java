import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static double min = Double.MAX_VALUE;
    private static double max = Double.MIN_VALUE;


    public static void main(String[] args) {
        List<Double> list = new ArrayList<>();
        Scanner sc = new Scanner(System.in);


        System.out.print("Количество элементов в выборке: ");
//        n = sc.nextInt();
        int n = 30;


        int k = (int) getRangeCount(n);
        System.out.println("Количество диапазонов: " + k);



//        for (int i = 0; i < n; i++) {
//            System.out.print("Элемент " +  i + ": ");
//            double num = sc.nextDouble();
//            if (num>max) max = num;
//            if (num<min) min = num;
//            list.add(num);
//        }




        list.add(20.0);
        list.add(21.0);
        list.add(25.0);
        list.add(26.0);
        list.add(26.0);
        list.add(30.0);
        list.add(30.0);
        list.add(30.0);
        list.add(32.0);
        list.add(32.0);
        list.add(33.0);
        list.add(33.0);
        list.add(34.0);
        list.add(35.0);
        list.add(35.0);
        list.add(36.0);
        list.add(37.0);
        list.add(38.0);
        list.add(38.0);
        list.add(38.0);
        list.add(40.0);
        list.add(41.0);
        list.add(42.0);
        list.add(43.0);
        list.add(43.0);
        list.add(43.0);
        list.add(43.0);
        list.add(46.0);
        list.add(48.0);
        list.add(49.0);

        min = 20.0; max = 49.0;







        System.out.println(list);
        System.out.println("Min: " + min);
        System.out.println("Max: " + max);


        int h = (int) getRangeLength(max, min, k);
        System.out.println("Длина диапазона: " + h);


        List<Integer> ni = getNi(list, k, h, (int) min);
        System.out.println("Столбец ni: " + ni);


        List<Double> xi = getXi(k, h, (int) min);
        System.out.println("Столбец xi: " + xi);


        double x = getX(ni, xi, n, k);
        System.out.println("Выборочное среднее: " + x);


        double d = getD(ni, xi, n, k, x);
        System.out.println("Выборочная дисперсия: " + Math.pow(d,2));
        System.out.println("Выборочное стандартное отклонение: " + d);


        double a3 = getA3(ni, xi, n, k, x, d);
        System.out.println("Ассиметрия: " + a3);


        double e4 = getE4(ni, xi, n, k, x, d);
        System.out.println("Экцесс: " + e4);


        double mO = getMo(ni, h, k, min);
        System.out.println("Мода: " + mO);


    }


    public static long getRangeCount(int n){
        return Math.round(1 + 3.322 * Math.log10(n));
    }

    public static long getRangeLength(double max, double min, int k){
        return Math.round((max - min)/k);
    }

    public static List<Integer> getNi(List<Double> list, int k, int h, int min){
        List<Integer> ni = new ArrayList<>();
        for (int i = 0; i < k; i++) {
            int c1 = min + h * i;
            int c2 = min + h * (i + 1);
            ni.add(getNiCount(list, c1, c2));
        }
        return ni;
    }
    public static int getNiCount(List<Double> list, int c1, int c2){
        int count = 0;
        for (Double num : list) {
            if (num>= c1 && num <c2){
                count++;
            }
        }
        return count;
    }


    public static List<Double> getXi(int k, int h, int min){
        List<Double> xi = new ArrayList<>();
        for (int i = 0; i < k; i++) {
            double c1 = min + h * i;
            double c2 = min + h * (i + 1);
            double x = c1+ ((c2-c1)/2);
            xi.add( x);
        }
        return xi;
    }


    public static double getX (List<Integer> ni, List<Double> xi, int n, int k){
        double x = 0.0;

        for (int i = 0; i < k; i++) {
            x = ni.get(i) * xi.get(i) + x;
        }
        return x/n;
    }


    public static double getD (List<Integer> ni, List<Double> xi, int n, int k, double x){
        double d = 0.0;

        for (int i = 0; i < k; i++) {
            d = (ni.get(i) * Math.pow((xi.get(i) - x), 2)) + d;
        }
        return Math.sqrt(d/n);

    }


    public static double getA3 (List<Integer> ni, List<Double> xi, int n, int k, double x, double d){
        double a3 = 0.0;

        for (int i = 0; i < k; i++) {
            a3 = (ni.get(i) * Math.pow((xi.get(i) - x), 3)) + a3;
        }
        return (a3/n)/Math.pow(d,3);
    }


    public static double getE4 (List<Integer> ni, List<Double> xi, int n, int k, double x, double d){
        double e4 = 0.0;

        for (int i = 0; i < k; i++) {
            e4 = (ni.get(i) * Math.pow((xi.get(i) - x), 4)) + e4;
        }
        return ((e4/n)/Math.pow(d,4)) - 3;
    }

    public static double getMo (List<Integer> ni, int h, int k, double min){
        int maxNi = 0;
        int maxNiIndex = -1;
        for (int i = 0; i <k; i++) {
            if (ni.get(i) > maxNi) {
                maxNi = ni.get(i);
                maxNiIndex = i;
            }
        }
        int x0 = (int) (min + h * maxNiIndex);
        double nM = ni.get(maxNiIndex);
        double nMPrevious = ni.get(maxNiIndex - 1);
        double nMNext = ni.get(maxNiIndex + 1);

        return x0 + ((nM - nMPrevious) / ((nM- nMPrevious) + (nM-nMNext)) * h);


    }
}



