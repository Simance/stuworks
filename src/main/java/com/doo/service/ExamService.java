package com.doo.service;

import com.doo.dao.ExamMapper;
import com.doo.dao.ExamScoreMapper;
import com.doo.dao.ExamScoreMapperEx;
import com.doo.dao.StudentMapper;
import com.doo.pojo.Exam;
import com.doo.pojo.ExamScore;
import com.doo.pojo.ExamScoreEX;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ExamService {
    @Autowired
    private ExamScoreMapper examScoreMapper;
    @Autowired
    private ExamScoreMapperEx examScoreMapperEx;
    @Autowired
    private ExamMapper examMapper;

    public List<ExamScoreEX> getAllExamByStuId(int stuId){
        List<ExamScoreEX> examScores = examScoreMapperEx.selectByStuId(stuId);
        return examScores;
    }

    @Transactional
    public int addExam(Exam exam){
        int id = examMapper.insertSelective(exam);
        return id;
    }

    @Transactional
    public void addScore(ExamScore e){
        examScoreMapper.insertSelective(e);
    }
}
