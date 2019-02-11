package com.doo.service;

import com.doo.dao.DailyScoreMapper;
import com.doo.dao.StudentMapper;
import com.doo.pojo.DailyScore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DailyworkService {

    @Autowired
    private DailyScoreMapper dailyScoreMapper;


    public List<DailyScore> getDailyWorksByStuId(int stuId){
        List<DailyScore> dailyScores = dailyScoreMapper.selectByStuId(stuId);

        return dailyScores;
    }

    @Transactional
    public int addDailyScore(DailyScore dailyScore){
        return dailyScoreMapper.insert(dailyScore);
    }

    public List<DailyScore> getReviseDailyWorks(){
        return dailyScoreMapper.selectReviseDailyWorks();
    }
}
