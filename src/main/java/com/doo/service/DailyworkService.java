package com.doo.service;

import com.doo.dao.DailyScoreMapper;
import com.doo.dao.DailyScoreMapperEx;
import com.doo.dao.StudentMapper;
import com.doo.pojo.DailyScore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;

@Service
public class DailyworkService {

    @Autowired
    private DailyScoreMapper dailyScoreMapper;
    @Autowired
    private DailyScoreMapperEx dailyScoreMapperEx;

    public List<DailyScore> getDailyWorksByStuId(HashMap map){
        List<DailyScore> dailyScores = dailyScoreMapperEx.selectByStuId(map);

        return dailyScores;
    }

    @Transactional
    public int addDailyScore(DailyScore dailyScore){
        return dailyScoreMapper.insertSelective(dailyScore);
    }

    public List<DailyScore> getReviseDailyWorks(String date){
        return dailyScoreMapperEx.selectReviseDailyWorks(date);
    }

    @Transactional
    public int updateReviseByStudentId(DailyScore dailyScore){
        return dailyScoreMapperEx.updateByStuId(dailyScore);
    }

    public int getAllStarsByStuId(Integer stuId){
        Integer res = dailyScoreMapperEx.getAllStarsByStuId(stuId);
        if (res == null){
            return 0;
        }
        return res;
    }
    public int getAllReviseByStuId(Integer stuId){
        Integer res = dailyScoreMapperEx.getAllReviseByStuId(stuId);
        if (res == null){
            return 0;
        }
        return res;
    }
}
