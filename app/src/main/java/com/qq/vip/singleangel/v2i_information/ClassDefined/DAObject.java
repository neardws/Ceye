package com.qq.vip.singleangel.v2i_information.ClassDefined;

/**
 * Created by singl on 2018/4/13.
 */

public class DAObject {
    private int no;
    private int id;
    private int index;
    private long sst;
    private long srt;
    private long cst;
    private long crt;
    private int end;
    private int success;
    private long singleDelay;
    private long doubleDelay;

    public DAObject(){
        this.no = 0;
        this.id = 0;
        this.index = 0;
        this.success = 0;
        this.sst = 0;
        this.srt = 0;
        this.cst = 0;
        this.crt = 0;
        this.end = 0;
        this.singleDelay = 0;
        this.doubleDelay = 0;
    }

    public int getSuccess() {
        return success;
    }

    public void setSuccess(int success) {
        this.success = success;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public int getNo() {
        return no;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public int getIndex() {
        return index;
    }

    public void setSrt(long srt) {
        this.srt = srt;
    }

    public long getSrt() {
        return srt;
    }

    public void setSst(long sst) {
        this.sst = sst;
    }

    public long getSst() {
        return sst;
    }

    public long getCst() {
        return cst;
    }

    public void setCst(long cst) {
        this.cst = cst;
    }

    public long getCrt() {
        return crt;
    }

    public void setCrt(long crt) {
        this.crt = crt;
    }

    public int isEnd() {
        return end;
    }

    public void setEnd(int end) {
        this.end = end;
    }

    public void setSingleDelay(long singleDelay) {
        this.singleDelay = singleDelay;
    }

    public long getSingleDelay() {
        return singleDelay;
    }

    public void setDoubleDelay(long doubleDelay) {
        this.doubleDelay = doubleDelay;
    }

    public long getDoubleDelay() {
        return doubleDelay;
    }
}
