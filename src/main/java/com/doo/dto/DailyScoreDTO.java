package com.doo.dto;

import com.doo.pojo.DailyScore;
import com.doo.pojo.Student;

import java.util.List;

public class DailyScoreDTO extends Student {

    private List<DailyScore> dailyScores;
    private int classworkNum;
    private int extraworkNum;
    private int writingapecNum;
    private int reviseNum;
    private int workNum;

    public List<DailyScore> getDailyScores() {
        return dailyScores;
    }

    public void setDailyScores(List<DailyScore> dailyScores) {
        this.dailyScores = dailyScores;
    }

    public int getClassworkNum() {
        return classworkNum;
    }

    public void setClassworkNum(int classworkNum) {
        this.classworkNum = classworkNum;
    }

    public int getExtraworkNum() {
        return extraworkNum;
    }

    public void setExtraworkNum(int extraworkNum) {
        this.extraworkNum = extraworkNum;
    }

    public int getWritingapecNum() {
        return writingapecNum;
    }

    public void setWritingapecNum(int writingapecNum) {
        this.writingapecNum = writingapecNum;
    }

    public int getReviseNum() {
        return reviseNum;
    }

    public void setReviseNum(int reviseNum) {
        this.reviseNum = reviseNum;
    }

    public int getWorkNum() {
        return workNum;
    }

    public void setWorkNum(int workNum) {
        this.workNum = workNum;
    }
}
