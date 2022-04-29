package ru.kpfu.itis.kashapova.java;

import ru.kpfu.itis.kashapova.java.histogram.GrGis;

import java.util.List;

public class Functional {
    public Double[] dataset;
    public Function function;
    protected List<Integer> ni = null;
    protected List<Double> xi = null;
    protected double x;
    protected double d;

    public Functional(Double[] dataset) {
        this.dataset = dataset;
        this.function = new Function(this.dataset);
    }

    public void start() {
        System.out.println("Количество элементов в выборке: " + function.getLengthDataset());
    }

    protected void writeMin(){
        System.out.println("Min: " + function.getMin());
    }

    protected void writeMax(){
        System.out.println("Max: " + function.getMax());
    }

    protected void writeRangeCount(){
        System.out.println("Количество диапазонов: " + function.getRangeCount());
    }

    protected void writeRangeLength(){
        System.out.println("Длина диапазона: " + function.getRangeLength());
    }

    protected void writeNi(){
        ni = function.getNi();
        System.out.println("Столбец n_i: " + ni);
    }

    protected void writeXi(){
        xi = function.getXi();
        System.out.println("Столбец x_i: " + xi);
    }

    protected void writeX(){
        try {
            x = function.getX(ni,xi);
            System.out.println("Выборочное среднее: " + x);
        } catch (NullPointerException ex){
            System.out.println("Вызовете сначала функцию Functional.getNi()/Functional.getXi()");
        }
    }

    protected void writeD(){
        try {
            d = function.getD(ni, xi, x);
            System.out.println("Выборочная дисперсия: " + Math.pow(d,2));
            System.out.println("Выборочное стандартное отклонение: " + d);
        } catch (NullPointerException ex){
            System.out.println("Вызовете сначала функцию Functional.getNi()/Functional.getXi()/Functional.getX()");
        }
    }

    protected void writeA3(){
        try {
            double a3 = function.getA3(ni, xi, x, d);
            System.out.println("Ассиметрия: " + a3);
        } catch (NullPointerException ex){
            System.out.println("Вызовете сначала функцию Functional.getNi()/Functional.getXi()/Functional.getX()/Functional.getD()");
        }
    }

    protected void writeE4(){
        try {
            double e4 = function.getE4(ni, xi, x, d);
            System.out.println("Экцесс: " + e4);
        } catch (NullPointerException ex){
            System.out.println("Вызовете сначала функцию Functional.getNi()/Functional.getXi()/Functional.getX()/Functional.getD()");
        }
    }

    protected void writeMo(){
        try {
            double mO = function.getMo(ni);
            System.out.println("Мода: " + mO);
        } catch (NullPointerException ex){
            System.out.println("Вызовете сначала функцию Functional.getNi()");
        }
    }

    protected void writeAll(){
        writeMin();
        writeMax();
        writeRangeCount();
        writeRangeLength();
        writeNi();
        writeXi();
        writeX();
        writeD();
        writeA3();
        writeE4();
        writeMo();
    }

    protected void drawHistogram(){
        GrGis.draw(ni, function.getMin(), function.getRangeCount(), function.getRangeLength());
    }

    public Double[] getDataset() {
        return dataset;
    }

    public void setDataset(Double[] dataset) {
        this.dataset = dataset;
        this.function = new Function(this.dataset);
    }

    public Function getFunction() {
        return function;
    }
}
