package com.doo.controller;

import com.doo.pojo.Student;
import com.doo.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class StudentController {

    @Autowired
    private StudentService studentService;

    @RequestMapping(value = "/addstudent")
    public String addStudent(){
        return "addstudent";
    }

    @RequestMapping(value ="/addstudentcheck")
    public String addStudentCheck(String studentName){
        String[] students = studentName.split(",");
        for (String s : students
             ) {
            Student student = new Student();
            student.setName(s);
            studentService.addStudent(student);
        }
        return "hello";
    }
}

