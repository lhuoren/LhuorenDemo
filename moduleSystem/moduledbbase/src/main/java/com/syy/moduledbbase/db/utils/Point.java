package com.syy.moduledbbase.db.utils;

public class Point {
    public double x;
    public double y;
    public double z;

    public Point() {

    }

    public void setPoint(double var1, double var3){
        this.x = var1;
        this.y = var3;
    }

    public void setPoint(double var1, double var3, double var5){
        this.x = var1;
        this.y = var3;
        this.z = var5;
    }

    public Point(double var1, double var3) {
        this.x = var1;
        this.y = var3;
    }

    public Point(double var1, double var3, double var5) {
        this.x = var1;
        this.y = var3;
        this.z = var5;
    }


    public String toString() {
        return "Point{x=" + this.x + ", y=" + this.y + ", z=" + this.z + '}';
    }
}
