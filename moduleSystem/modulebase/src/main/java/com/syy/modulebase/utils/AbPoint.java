package com.syy.modulebase.utils;

public class AbPoint {
    public double x;
    public double y;

    public AbPoint() {
    }

    public AbPoint(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public String toString() {
        return "(" + this.x + "," + this.y + ")";
    }

    public boolean equals(Object o) {
        AbPoint point = (AbPoint)o;
        return this.x == point.x && this.y == point.y;
    }

    public int hashCode() {
        return (int)(this.x * this.y) ^ 8;
    }
}
