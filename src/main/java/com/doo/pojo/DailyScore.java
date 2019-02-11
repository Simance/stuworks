package com.doo.pojo;

import java.util.Date;

public class DailyScore {
    private Integer id;

    private Integer stuId;

    private Byte classwork;

    private Byte extrawork;

    private Byte writingapec;

    private Byte needRevise;

    private Byte revised;

    private Date createTime;

    public DailyScore(Integer id, Integer stuId, Byte classwork, Byte extrawork, Byte writingapec, Byte needRevise, Byte revised, Date createTime) {
        this.id = id;
        this.stuId = stuId;
        this.classwork = classwork;
        this.extrawork = extrawork;
        this.writingapec = writingapec;
        this.needRevise = needRevise;
        this.revised = revised;
        this.createTime = createTime;
    }

    public DailyScore() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getStuId() {
        return stuId;
    }

    public void setStuId(Integer stuId) {
        this.stuId = stuId;
    }

    public Byte getClasswork() {
        return classwork;
    }

    public void setClasswork(Byte classwork) {
        this.classwork = classwork;
    }

    public Byte getExtrawork() {
        return extrawork;
    }

    public void setExtrawork(Byte extrawork) {
        this.extrawork = extrawork;
    }

    public Byte getWritingapec() {
        return writingapec;
    }

    public void setWritingapec(Byte writingapec) {
        this.writingapec = writingapec;
    }

    public Byte getNeedRevise() {
        return needRevise;
    }

    public void setNeedRevise(Byte needRevise) {
        this.needRevise = needRevise;
    }

    public Byte getRevised() {
        return revised;
    }

    public void setRevised(Byte revised) {
        this.revised = revised;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}