package com.syy.modulebase.utils;

public class AbCircle {
    public AbPoint point;
    public double r;

    public AbCircle() {
    }

    public AbCircle(AbPoint point, double r) {
        this.point = point;
        this.r = r;
    }

    public String toString() {
        return "(" + this.point.x + "," + this.point.y + "),r=" + this.r;
    }
}
