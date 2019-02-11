package com.doo.dao;

import com.doo.pojo.DailyScore;
import redis.clients.jedis.BinaryClient;

import java.util.List;

public interface DailyScoreMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(DailyScore record);

    int insertSelective(DailyScore record);

    DailyScore selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(DailyScore record);

    int updateByPrimaryKey(DailyScore record);

    List<DailyScore> selectByStuId(Integer stuId);

    List<DailyScore> selectReviseDailyWorks();
}