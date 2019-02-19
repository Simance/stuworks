package com.doo.service;

import com.doo.dao.ClassMapper;
import com.doo.dao.ClassMapperEx;
import com.doo.dao.StudentMapper;
import com.doo.dao.StudentMapperEx;
import com.doo.pojo.Class;
import com.doo.pojo.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class StudentService {
    @Autowired
    private StudentMapper studentMapper;
    @Autowired
    private StudentMapperEx studentMapperEx;
    @Autowired
    private ClassMapper classMapper;
    @Autowired
    private ClassMapperEx classMapperEx;

    public void addStudent(Student student){
        studentMapper.insertSelective(student);
    }

    public List<Student> getStudentsByClass(Integer classId){
        List<Student> students = studentMapperEx.selectStudentsByClass(classId);

        return students;
    }

    public Student getReviseStu(int stuId){
        return studentMapper.selectByPrimaryKey(stuId);
    }

    @Transactional
    public void addClass(Class c){
        classMapper.insertSelective(c);
    }

    public List<Class> getAllClass(){
       return classMapperEx.selectAllClasses();
    }

    public Class getClassById(Integer classId){
        return classMapper.selectByPrimaryKey(classId);
    }
}
