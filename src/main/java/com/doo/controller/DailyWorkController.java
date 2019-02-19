package com.doo.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.doo.dto.DailyScoreDTO;
import com.doo.dto.ReviseDTO;
import com.doo.pojo.Class;
import com.doo.pojo.DailyScore;
import com.doo.pojo.DailyScoreReq;
import com.doo.pojo.Student;
import com.doo.service.DailyworkService;
import com.doo.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
public class DailyWorkController {

    @Autowired
    private DailyworkService dailyworkService;
    @Autowired
    private StudentService studentService;
    //作业统计
    @RequestMapping(value = "/work")
    public String getDailyScore(Integer classId, String time,Integer sort, Integer desc, Model model, HttpSession session){

        if (classId != null){
            session.setAttribute("classId", classId);
        }else{
            classId = (Integer) session.getAttribute("classId");
        }

        if (time != null){

        }

        List<Student> students = studentService.getStudentsByClass(classId);
        List<DailyScoreDTO> dailyScoreDTOS = new ArrayList<>();
        for (Student s: students
             ) {
            if (time == null){
                Date date = new Date();
                DateFormat dateFormat = new SimpleDateFormat("yyyy-MM");
                time = dateFormat.format(date);
            }
            int stuId = s.getId();
            HashMap<String,Object> map = new HashMap();
            map.put("stuId",stuId);
            map.put("date",time);
            List<DailyScore> dailyScores = dailyworkService.getDailyWorksByStuId(map);

            DailyScoreDTO dailyScoreDTO = new DailyScoreDTO();
            dailyScoreDTO.setId(s.getStuId());
            dailyScoreDTO.setName(s.getName());


            int classworkNum = 0;
            int extraworkNum = 0;
            int writingapecNum = 0;

            for (DailyScore score: dailyScores
                 ) {

                int classwork = score.getClasswork();
                classworkNum += classwork;
                int extrawork = score.getExtrawork();
                extraworkNum += extrawork;
                int writingapec = score.getWritingapec();
                writingapecNum += writingapec;


            }
            dailyScoreDTO.setClassworkNum(classworkNum);
            dailyScoreDTO.setExtraworkNum(extraworkNum);
            dailyScoreDTO.setWritingapecNum(writingapecNum);



            int monthWorkNum = classworkNum + extraworkNum + writingapecNum;
            dailyScoreDTO.setMonthWorkNum(monthWorkNum);

            int workNum = dailyworkService.getAllStarsByStuId(stuId);
            int reviseNum = dailyworkService.getAllReviseByStuId(stuId);
            dailyScoreDTO.setWorkNum(workNum);
            dailyScoreDTO.setReviseNum(reviseNum);

            int dailyScoreSize = dailyScores.size();
            if (dailyScoreSize > 5){
                dailyScores = dailyScores.subList(dailyScoreSize-6,dailyScoreSize-1);
            }
            dailyScoreDTO.setDailyScores(dailyScores);

            dailyScoreDTOS.add(dailyScoreDTO);
        }

        if (sort != null && desc != null){
            if (sort == 1 && desc ==0){
                for(int i =0; i<dailyScoreDTOS.size(); i++) {
                    for (int j = 0; j < dailyScoreDTOS.size() - 1 - i; j++)

                        if (dailyScoreDTOS.get(j).getDailyScores().get(0).getStuId() < dailyScoreDTOS.get(j+1).getDailyScores().get(0).getStuId()) {
                            DailyScoreDTO temp = dailyScoreDTOS.get(j);
                            dailyScoreDTOS.set(j, dailyScoreDTOS.get(j+1));
                            dailyScoreDTOS.set(j+1, temp);

                        }
                }
            }else if (sort == 1 && desc == 1){
                for(int i =0; i<dailyScoreDTOS.size(); i++) {
                    for (int j = 0; j < dailyScoreDTOS.size() - 1 - i; j++)

                        if (dailyScoreDTOS.get(j).getDailyScores().get(0).getStuId() > dailyScoreDTOS.get(j+1).getDailyScores().get(0).getStuId()) {
                            DailyScoreDTO temp = dailyScoreDTOS.get(j);
                            dailyScoreDTOS.set(j, dailyScoreDTOS.get(j+1));
                            dailyScoreDTOS.set(j+1, temp);

                        }
                }
            }else if (sort == 2 && desc ==1){
                for(int i =0; i<dailyScoreDTOS.size(); i++) {
                    for (int j = 0; j < dailyScoreDTOS.size() - 1 - i; j++)

                        if (dailyScoreDTOS.get(j).getClassworkNum() < dailyScoreDTOS.get(j+1).getClassworkNum()) {
                            DailyScoreDTO temp = dailyScoreDTOS.get(j);
                            dailyScoreDTOS.set(j, dailyScoreDTOS.get(j+1));
                            dailyScoreDTOS.set(j+1, temp);

                        }
                }
            }else if (sort == 2 && desc == 0){
                for(int i =0; i<dailyScoreDTOS.size(); i++) {
                    for (int j = 0; j < dailyScoreDTOS.size() - 1 - i; j++)

                        if (dailyScoreDTOS.get(j).getClassworkNum() > dailyScoreDTOS.get(j+1).getClassworkNum()) {
                            DailyScoreDTO temp = dailyScoreDTOS.get(j);
                            dailyScoreDTOS.set(j, dailyScoreDTOS.get(j+1));
                            dailyScoreDTOS.set(j+1, temp);

                        }
                }
            }else if (sort == 3 && desc ==1){
                for(int i =0; i<dailyScoreDTOS.size(); i++) {
                    for (int j = 0; j < dailyScoreDTOS.size() - 1 - i; j++)

                        if (dailyScoreDTOS.get(j).getExtraworkNum() < dailyScoreDTOS.get(j+1).getExtraworkNum()) {
                            DailyScoreDTO temp = dailyScoreDTOS.get(j);
                            dailyScoreDTOS.set(j, dailyScoreDTOS.get(j+1));
                            dailyScoreDTOS.set(j+1, temp);

                        }
                }
            }else if (sort == 3 && desc == 0){
                for(int i =0; i<dailyScoreDTOS.size(); i++) {
                    for (int j = 0; j < dailyScoreDTOS.size() - 1 - i; j++)

                        if (dailyScoreDTOS.get(j).getExtraworkNum() > dailyScoreDTOS.get(j+1).getExtraworkNum()) {
                            DailyScoreDTO temp = dailyScoreDTOS.get(j);
                            dailyScoreDTOS.set(j, dailyScoreDTOS.get(j+1));
                            dailyScoreDTOS.set(j+1, temp);

                        }
                }
            }else if (sort == 4 && desc ==1){
                for(int i =0; i<dailyScoreDTOS.size(); i++) {
                    for (int j = 0; j < dailyScoreDTOS.size() - 1 - i; j++)

                        if (dailyScoreDTOS.get(j).getWritingapecNum() < dailyScoreDTOS.get(j+1).getWritingapecNum()) {
                            DailyScoreDTO temp = dailyScoreDTOS.get(j);
                            dailyScoreDTOS.set(j, dailyScoreDTOS.get(j+1));
                            dailyScoreDTOS.set(j+1, temp);

                        }
                }
            }else if (sort == 4 && desc == 0){
                for(int i =0; i<dailyScoreDTOS.size(); i++) {
                    for (int j = 0; j < dailyScoreDTOS.size() - 1 - i; j++)

                        if (dailyScoreDTOS.get(j).getWritingapecNum() > dailyScoreDTOS.get(j+1).getWritingapecNum()) {
                            DailyScoreDTO temp = dailyScoreDTOS.get(j);
                            dailyScoreDTOS.set(j, dailyScoreDTOS.get(j+1));
                            dailyScoreDTOS.set(j+1, temp);

                        }
                }
            }else if (sort == 5 && desc ==1){
                for(int i =0; i<dailyScoreDTOS.size(); i++) {
                    for (int j = 0; j < dailyScoreDTOS.size() - 1 - i; j++)

                        if (dailyScoreDTOS.get(j).getMonthWorkNum() < dailyScoreDTOS.get(j+1).getMonthWorkNum()) {
                            DailyScoreDTO temp = dailyScoreDTOS.get(j);
                            dailyScoreDTOS.set(j, dailyScoreDTOS.get(j+1));
                            dailyScoreDTOS.set(j+1, temp);

                        }
                }
            }else if (sort == 5 && desc == 0){
                for(int i =0; i<dailyScoreDTOS.size(); i++) {
                    for (int j = 0; j < dailyScoreDTOS.size() - 1 - i; j++)

                        if (dailyScoreDTOS.get(j).getMonthWorkNum() > dailyScoreDTOS.get(j+1).getMonthWorkNum()) {
                            DailyScoreDTO temp = dailyScoreDTOS.get(j);
                            dailyScoreDTOS.set(j, dailyScoreDTOS.get(j+1));
                            dailyScoreDTOS.set(j+1, temp);

                        }
                }
            }else if (sort == 6 && desc ==1){
                for(int i =0; i<dailyScoreDTOS.size(); i++) {
                    for (int j = 0; j < dailyScoreDTOS.size() - 1 - i; j++)

                        if (dailyScoreDTOS.get(j).getWorkNum() < dailyScoreDTOS.get(j+1).getWorkNum()) {
                            DailyScoreDTO temp = dailyScoreDTOS.get(j);
                            dailyScoreDTOS.set(j, dailyScoreDTOS.get(j+1));
                            dailyScoreDTOS.set(j+1, temp);

                        }
                }
            }else if (sort == 6 && desc == 0){
                for(int i =0; i<dailyScoreDTOS.size(); i++) {
                    for (int j = 0; j < dailyScoreDTOS.size() - 1 - i; j++)

                        if (dailyScoreDTOS.get(j).getWorkNum() > dailyScoreDTOS.get(j+1).getWorkNum()) {
                            DailyScoreDTO temp = dailyScoreDTOS.get(j);
                            dailyScoreDTOS.set(j, dailyScoreDTOS.get(j+1));
                            dailyScoreDTOS.set(j+1, temp);

                        }
                }
            }else if (sort == 7 && desc ==1){
                for(int i =0; i<dailyScoreDTOS.size(); i++) {
                    for (int j = 0; j < dailyScoreDTOS.size() - 1 - i; j++)

                        if (dailyScoreDTOS.get(j).getReviseNum() < dailyScoreDTOS.get(j+1).getReviseNum()) {
                            DailyScoreDTO temp = dailyScoreDTOS.get(j);
                            dailyScoreDTOS.set(j, dailyScoreDTOS.get(j+1));
                            dailyScoreDTOS.set(j+1, temp);

                        }
                }
            }else if (sort == 7 && desc == 0){
                for(int i =0; i<dailyScoreDTOS.size(); i++) {
                    for (int j = 0; j < dailyScoreDTOS.size() - 1 - i; j++)

                        if (dailyScoreDTOS.get(j).getReviseNum() > dailyScoreDTOS.get(j+1).getReviseNum()) {
                            DailyScoreDTO temp = dailyScoreDTOS.get(j);
                            dailyScoreDTOS.set(j, dailyScoreDTOS.get(j+1));
                            dailyScoreDTOS.set(j+1, temp);

                        }
                }
            }

        }

        if (desc != null && desc == 1){
            desc = 0;
        }else {
            desc = 1;
        }
        model.addAttribute("desc", desc);
        model.addAttribute("sort", sort);
        model.addAttribute("students", dailyScoreDTOS);
        return  "dailyscore";
    }

    //添加作业情况
    @RequestMapping(value = "/adddailyscore")
    public String addDailyScore(Model model, HttpSession session){

        Integer classId = (Integer) session.getAttribute("classId");
        List<Student> students = studentService.getStudentsByClass(classId);
        model.addAttribute("students",students);
        return "adddailyscore";
    }

    @RequestMapping(value = "adddailyscorecheck")
    public String addDailyScoreCheck(DailyScoreReq dailyScores){
        for (DailyScore d : dailyScores.getDailyScores()
             ) {
            if (d.getNeedRevise() == 1){
                d.setRevised((byte) 0);
            }
            d.setCreateTime(new Date());
            dailyworkService.addDailyScore(d);
        }
        return  "redirect:/work";
    }

    //订正作业名单
    @RequestMapping(value = "/revise")
    public String revise(Model model, HttpSession session){
        //get all students who need revise
        Integer classId = (Integer)session.getAttribute("classId");

        DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        Calendar cal=Calendar.getInstance();
        String date = dateFormat.format(cal.getTime());

        List<DailyScore> dailyScores = dailyworkService.getReviseDailyWorks(date);

        List<ReviseDTO> reviseDTOS = new ArrayList<>();
        for (DailyScore s: dailyScores
             ) {
            Student student = studentService.getReviseStu(s.getStuId());
            ReviseDTO reviseDTO = new ReviseDTO();
            reviseDTO.setId(student.getId());
            reviseDTO.setStuId(student.getStuId());
            reviseDTO.setName(student.getName());
            reviseDTO.setClassId(student.getClassId());

            reviseDTO.setNeedRevise(s.getNeedRevise());
            reviseDTO.setRevised(s.getRevised());
            reviseDTO.setCreateTime(s.getCreateTime());

            reviseDTOS.add(reviseDTO);
        }

        List<Class> classes = studentService.getAllClass();


        model.addAttribute("revise", reviseDTOS);
        model.addAttribute("classes", classes);
        return "revise";
    }

    //修改订正作业状态
    @RequestMapping(value = "/revised")
    @ResponseBody
    public String revised(@RequestBody String jsonData){
        JSONObject jsonObject = JSON.parseObject(jsonData);
        Integer stuId = jsonObject.getInteger("stuId");

        DailyScore dailyScore = new DailyScore();
        dailyScore.setStuId(stuId);
        dailyScore.setRevised((byte)1);

        dailyworkService.updateReviseByStudentId(dailyScore);
        String result = "success";
        return JSON.toJSONString(result);

    }

}
