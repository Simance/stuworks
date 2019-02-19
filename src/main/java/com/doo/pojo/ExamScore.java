package com.doo.pojo;

public class ExamScore {
    private Integer id;

    private Integer examId;

    private Integer stuId;

    private Double score;

    public ExamScore(Integer id, Integer examId, Integer stuId, Double score) {
        this.id = id;
        this.examId = examId;
        this.stuId = stuId;
        this.score = score;
    }

    public ExamScore() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getExamId() {
        return examId;
    }

    public void setExamId(Integer examId) {
        this.examId = examId;
    }

    public Integer getStuId() {
        return stuId;
    }

    public void setStuId(Integer stuId) {
        this.stuId = stuId;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }
}