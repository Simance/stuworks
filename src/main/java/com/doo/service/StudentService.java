package com.doo.service;

import com.doo.dao.StudentMapper;
import com.doo.pojo.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {
    @Autowired
    private StudentMapper studentMapper;

    public void addStudent(Student student){
        studentMapper.insert(student);
    }

    public List<Student> getAllStudents(){
        List<Student> students = studentMapper.selectAll();

        return students;
    }

    public Student getReviseStu(int stuId){
        return studentMapper.selectByPrimaryKey(stuId);
    }
}
