package com.doo.controller;

import com.doo.dto.ExamDTO;
import com.doo.pojo.ExamScoreEX;
import com.doo.pojo.Student;
import com.doo.service.ExamService;
import com.doo.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class ExamController {

    @Autowired
    private StudentService studentService;
    @Autowired
    private ExamService examService;

    @RequestMapping(value = "/exam")
    public String exam(Model model){

        List<Student> students = studentService.getAllStudents();
        List<ExamDTO> examDTOS = new ArrayList<>();
        for (Student s: students
             ) {
            //获取考试名称
            List<ExamScoreEX> examScores = examService.getAllExamByStuId(s.getId());
            ExamDTO examDTO = new ExamDTO();
            examDTO.setExamScores(examScores);

            Double examScoreSum = 0.00;
            for (ExamScoreEX e: examScores
                 ) {
                examScoreSum += e.getScore();
            }
            Double averscore = examScoreSum / examScores.size();
            examDTO.setAverScore(averscore);
            examDTO.setStuName(s.getName());

            examDTOS.add(examDTO);
        }
        model.addAttribute("examscores", examDTOS);
        return "exam";
    }
}
