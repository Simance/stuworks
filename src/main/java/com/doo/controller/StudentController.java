package com.doo.controller;

import com.doo.pojo.Class;
import com.doo.pojo.Student;
import com.doo.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class StudentController {

    @Autowired
    private StudentService studentService;

    //班级列表
    @RequestMapping(value = "/classview")
    public String classView(Model model){
        List<Class> classes = studentService.getAllClass();
        model.addAttribute("classes",classes);
        return "classview";
    }
    //添加班级
    @RequestMapping(value = "/class")
    public String addClass(){
        return "class";
    }

    @RequestMapping(value="/classcheck")
    public String addClassCheck(@RequestParam String name){

        Class c = new Class();
        c.setName(name);

        studentService.addClass(c);
        return "redirect:/classview";
    }

    //添加学生
    @RequestMapping(value = "/addstudent")
    public String addStudent(Model model, HttpSession session){

        Integer classId = (Integer)session.getAttribute("classId");
        Class c = studentService.getClassById(classId);
        model.addAttribute("className", c.getName());
        return "addstudent";
    }

    @RequestMapping(value ="/addstudentcheck")
    public String addStudentCheck(@RequestParam("name") String name, HttpSession session){

        Integer classId = (Integer) session.getAttribute("classId");
        String[] students = name.split(",");
        for (int i = 0; i<students.length; i++){
            Student student = new Student();
            student.setClassId(classId);
            student.setStuId(i+1);
            student.setName(students[i]);
            studentService.addStudent(student);
        }
        return "redirect:/work";
    }
}

