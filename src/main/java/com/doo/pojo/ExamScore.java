package com.doo.pojo;

public class ExamScore {
    private Integer id;

    private Integer scoreId;

    private Integer stuId;

    private Double score;

    public ExamScore(Integer id, Integer stuId, Double score) {
        this.id = id;
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

    public Integer getScoreId() {
        return scoreId;
    }

    public void setScoreId(Integer scoreId) {
        this.scoreId = scoreId;
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