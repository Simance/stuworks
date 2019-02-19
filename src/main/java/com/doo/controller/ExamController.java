package com.doo.controller;

import com.doo.dto.ExamDTO;
import com.doo.pojo.*;
import com.doo.service.ExamService;
import com.doo.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import redis.clients.jedis.BinaryClient;

import javax.servlet.http.HttpSession;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

@Controller
public class ExamController {

    @Autowired
    private StudentService studentService;
    @Autowired
    private ExamService examService;

    //添加考试
    @RequestMapping(value = "/exam")
    public String exam(Model model, HttpSession session) {
        Integer classId = (Integer) session.getAttribute("classId");
        List<Student> students = studentService.getStudentsByClass(classId);
        model.addAttribute("students",students);
        return "exam";
    }

    //添加考试
    @RequestMapping(value = "/examcheck", method = {RequestMethod.POST},produces="text/html;charset=UTF-8")
    //@ResponseBody
    /*public String examCheck(@RequestBody List<ExamScore> examScores, Model model) {
        //TODO 添加考试ID
        for (ExamScore e: examScores
             ) {
            examService.addScore(e);
        }
        String result = "success";
        return result;
    }*/
    public String examCheck(@RequestParam String name, ExamScoreReq examScoreReq, Model model) {
        System.out.println(name);
        //添加考试信息
        //useGeneratedKeys="true" keyProperty="id" 返回主键
        Exam exam = new Exam();
        exam.setName(name);
        examService.addExam(exam);
        int examId = exam.getId();
        //添加成绩
        for (ExamScore e: examScoreReq.getExamScores()
             ) {
            if (e.getStuId() != null){
                e.setExamId(examId);
                examService.addScore(e);
            }
        }
        String result = "success";

        return "redirect:/examstatis";
    }

    //显示考试情况
    @RequestMapping(value = "/examstatis")
    public String examStatis(Model model, HttpSession session){

        Integer classId = (Integer) session.getAttribute("classId");
        List<Student> students = studentService.getStudentsByClass(classId);
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
            Double averscore = 0.00;
            if (examScores.size() > 0){
                Double averscore1 = examScoreSum / examScores.size();
                DecimalFormat df = new DecimalFormat("0.00");
                averscore = Double.parseDouble(df.format(averscore1));
            }

            examDTO.setAverScore(averscore);

            examDTO.setStuName(s.getName());
            examDTO.setStuId(s.getStuId());
            examDTO.setStuTableId(s.getId());

            examDTOS.add(examDTO);
        }
        model.addAttribute("stuexamscores", examDTOS);
        return "examstatis";
    }

    //成绩折线图
    @RequestMapping(value = "/examscoreview")
    public String examScoreView(Integer stuId, Model model){

        List<ExamScoreEX> examScoreEXES = examService.getAllExamByStuId(stuId);
        List<Double> scores = new ArrayList<>();
        List<String> labels = new ArrayList<>();
        for (ExamScoreEX e: examScoreEXES
             ) {
            scores.add(e.getScore());
            labels.add("'"+e.getExamName()+"'");
        }
        model.addAttribute("scores", scores);
        model.addAttribute("labels", labels);
        return "/examscoreview";
    }
}
