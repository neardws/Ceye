package com.qq.vip.singleangel.v2i_information.ClassDefined;

/**
 * Created by singl on 2018/4/17.
 */

public class DASummer {
    private int no;
    private int packageName;
    private long singleDelay;
    private long doubleDelay;
    private float packageLost;

    public void setNo(int no) {
        this.no = no;
    }

    public int getNo() {
        return no;
    }

    public void setPackageName(int packageName) {
        this.packageName = packageName;
    }

    public int getPackageName() {
        return packageName;
    }

    public long getSingleDelay() {
        return singleDelay;
    }

    public void setSingleDelay(long singleDelay) {
        this.singleDelay = singleDelay;
    }

    public long getDoubleDelay() {
        return doubleDelay;
    }

    public void setDoubleDelay(long doubleDelay) {
        this.doubleDelay = doubleDelay;
    }

    public float getPackageLost() {
        return packageLost;
    }

    public void setPackageLost(float packageLost) {
        this.packageLost = packageLost;
    }
}
