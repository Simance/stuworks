package com.doo.controller;

import com.doo.dto.DailyScoreDTO;
import com.doo.dto.ReviseDTO;
import com.doo.pojo.DailyScore;
import com.doo.pojo.Student;
import com.doo.service.DailyworkService;
import com.doo.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class DailyWorkController {

    @Autowired
    private DailyworkService dailyworkService;
    @Autowired
    private StudentService studentService;

    @RequestMapping(value = "/work")
    public String getStudents(Model model){

        List<Student> students = studentService.getAllStudents();
        List<DailyScoreDTO> dailyScoreDTOS = new ArrayList<>();
        for (Student s: students
             ) {
            int stuId = s.getId();
            List<DailyScore> dailyScores = dailyworkService.getDailyWorksByStuId(stuId);
            DailyScoreDTO dailyScoreDTO = new DailyScoreDTO();
            dailyScoreDTO.setId(s.getId());
            dailyScoreDTO.setName(s.getName());
            dailyScoreDTO.setDailyScores(dailyScores);
            dailyScoreDTOS.add(dailyScoreDTO);

            int classworkNum = 0;
            int extraworkNum = 0;
            int writingapecNum = 0;
            int reviseNum = 0;
            for (DailyScore score: dailyScores
                 ) {
                int classwork = score.getClasswork();
                classworkNum += classwork;
                int extrawork = score.getExtrawork();
                extraworkNum += extrawork;
                int writingapec = score.getWritingapec();
                writingapecNum += writingapec;
                int revise = score.getNeedRevise();
                reviseNum += revise;
            }
            dailyScoreDTO.setClassworkNum(classworkNum);
            dailyScoreDTO.setExtraworkNum(extraworkNum);
            dailyScoreDTO.setWritingapecNum(writingapecNum);
            dailyScoreDTO.setReviseNum(reviseNum);

            int workNum = classworkNum + extraworkNum + writingapecNum;
            dailyScoreDTO.setWorkNum(workNum);
        }



        model.addAttribute("students", dailyScoreDTOS);
        return  "hello";
    }

    @RequestMapping(value = "/adddailyscore")
    public String addDailyScore(){
        List<Student> students = studentService.getAllStudents();
        for (Student s: students
             ) {
            DailyScore dailyScore = new DailyScore();
            dailyScore.setStuId(s.getId());
            dailyScore.setClasswork((byte)1);
            dailyScore.setExtrawork((byte)1);
            dailyScore.setWritingapec((byte)1);
            dailyScore.setCreateTime(new Date());
            int res = dailyworkService.addDailyScore(dailyScore);
        }
        return null;
    }

    @RequestMapping(value = "/revise")
    public String revise(Model model){
        //get all students who need revise
        List<DailyScore> dailyScores = dailyworkService.getReviseDailyWorks();

        List<ReviseDTO> reviseDTOS = new ArrayList<>();
        for (DailyScore s: dailyScores
             ) {
            Student student = studentService.getReviseStu(s.getStuId());
            ReviseDTO reviseDTO = new ReviseDTO();
            reviseDTO.setId(student.getId());
            reviseDTO.setName(student.getName());
            reviseDTO.setRevised(s.getRevised());
            reviseDTO.setCreateTime(s.getCreateTime());

            reviseDTOS.add(reviseDTO);
        }

        model.addAttribute("revise", reviseDTOS);

        return "revise";
    }
}
