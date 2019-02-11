package com.doo.service;

import com.doo.dao.ExamMapper;
import com.doo.dao.ExamScoreMapper;
import com.doo.dao.StudentMapper;
import com.doo.pojo.Exam;
import com.doo.pojo.ExamScore;
import com.doo.pojo.ExamScoreEX;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExamService {
    @Autowired
    private ExamScoreMapper examScoreMapper;

    public List<ExamScoreEX> getAllExamByStuId(int stuId){
        List<ExamScoreEX> examScores = examScoreMapper.selectByStuId(stuId);
        return examScores;
    }
}
