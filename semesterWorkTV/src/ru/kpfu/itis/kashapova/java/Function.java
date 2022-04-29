package ru.kpfu.itis.kashapova.java;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Function {
    protected int m;
    protected int k;
    public static int n;
    protected Double[] dataset;

    public Function(Double[] dataset) {
        this.dataset = dataset;
        Arrays.sort(dataset);
    }

    protected int getLengthDataset(){
        return n = dataset.length;
    }

    protected double getMin(){
        return dataset[0];
    }
    protected double getMax(){
        return dataset[dataset.length - 1];
    }

    protected long getRangeCount(){
        return m = (int) Math.round(1 + (3.222 * Math.log10(getLengthDataset())));
    }

    protected long getRangeLength(){
        return k =  (int) Math.round((dataset[dataset.length - 1] - dataset[0]) / getRangeCount());
    }

    protected List<Integer> getNi(){
        List<Integer> ni = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            int c1 = (int) (getMin() + k * i);
            int c2 = (int) (getMin() + k * (i + 1));
            ni.add(getNiCount(c1, c2));
        }
        return ni;
    }

    protected int getNiCount(int c1, int c2){
        int count = 0;
        for (int i = 0; i < dataset.length; i++) {
            if (dataset[i] >= c1 && dataset[i] <c2){
                count++;
            }
        }
        return count;
    }

    protected List<Double> getXi(){
        DecimalFormat ds = new DecimalFormat("###.###");
        List<Double> xi = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            double c1 = getMin() + k * i;
            double c2 = getMin() + k * (i + 1);
            double x = c1+ ((c2-c1)/2);
            xi.add(Double.valueOf(ds.format(x)));
        }
        return xi;
    }

    protected double getX (List<Integer> ni, List<Double> xi){
        double x = 0.0;

        for (int i = 0; i < m; i++) {
            x = ni.get(i) * xi.get(i) + x;
        }
        return x/getLengthDataset();
    }

    protected double getD (List<Integer> ni, List<Double> xi, double x){
        double d = 0.0;

        for (int i = 0; i < m; i++) {
            d = (ni.get(i) * Math.pow((xi.get(i) - x), 2)) + d;
        }
        return Math.sqrt(d/getLengthDataset());

    }

    protected double getA3 (List<Integer> ni, List<Double> xi, double x, double d){
        double a3 = 0.0;

        for (int i = 0; i < m; i++) {
            a3 = (ni.get(i) * Math.pow((xi.get(i) - x), 3)) + a3;
        }
        return (a3/getLengthDataset())/Math.pow(d,3);
    }


    protected double getE4 (List<Integer> ni, List<Double> xi, double x, double d){
        double e4 = 0.0;

        for (int i = 0; i < m; i++) {
            e4 = (ni.get(i) * Math.pow((xi.get(i) - x), 4)) + e4;
        }
        return ((e4/getLengthDataset())/Math.pow(d,4)) - 3;
    }

    protected double getMo (List<Integer> ni){
        int maxNi = 0;
        int maxNiIndex = -1;
        for (int i = 0; i <m; i++) {
            if (ni.get(i) > maxNi) {
                maxNi = ni.get(i);
                maxNiIndex = i;
            }
        }
        int x0 = (int) (getMin() + k * maxNiIndex);
        double nM = ni.get(maxNiIndex);
        double nMPrevious = 0;
        try {
            nMPrevious = ni.get(maxNiIndex - 1);
        }catch (IndexOutOfBoundsException ex) {
            //
        }
        double nMNext = ni.get(maxNiIndex + 1);

        return x0 + ((nM - nMPrevious) / ((nM- nMPrevious) + (nM-nMNext)) * k);
    }
}
