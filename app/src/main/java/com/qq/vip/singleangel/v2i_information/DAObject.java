package com.qq.vip.singleangel.v2i_information;

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
    private long delay;

    public DAObject(){
        this.no = 0;
        this.id = 0;
        this.index = 0;
        this.sst = 0;
        this.srt = 0;
        this.cst = 0;
        this.crt = 0;
        this.end = 0;
        this.delay = 0;
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

    public long getDelay() {
        return delay;
    }

    /**
     * 计算时延，除去处理时间
     */
    public void setDelay() {
        if (srt != 0 && cst != 0 && crt != 0 && sst != 0){
            this.delay = srt - cst + (crt - sst);
        }
    }

}
