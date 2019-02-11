package com.doo.dto;

import com.doo.pojo.Exam;
import com.doo.pojo.ExamScore;
import com.doo.pojo.ExamScoreEX;
import com.doo.pojo.Student;

import java.util.List;

public class ExamDTO extends ExamScore {

    private String stuName;
    private List<ExamScoreEX> examScores;
    private Double averScore;

    public String getStuName() {
        return stuName;
    }

    public void setStuName(String stuName) {
        this.stuName = stuName;
    }

    public List<ExamScoreEX> getExamScores() {
        return examScores;
    }

    public void setExamScores(List<ExamScoreEX> examScores) {
        this.examScores = examScores;
    }

    public Double getAverScore() {
        return averScore;
    }

    public void setAverScore(Double averScore) {
        this.averScore = averScore;
    }
}
