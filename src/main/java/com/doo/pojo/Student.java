package com.doo.pojo;

public class Student {
    private Integer id;

    private String name;

    private Integer classId;

    private Integer stuId;

    public Student(Integer id, String name, Integer classId, Integer stuId) {
        this.id = id;
        this.name = name;
        this.classId = classId;
        this.stuId = stuId;
    }

    public Student() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getClassId() {
        return classId;
    }

    public void setClassId(Integer classId) {
        this.classId = classId;
    }

    public Integer getStuId() {
        return stuId;
    }

    public void setStuId(Integer stuId) {
        this.stuId = stuId;
    }
}