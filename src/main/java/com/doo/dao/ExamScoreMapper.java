package com.doo.dao;

import com.doo.pojo.ExamScore;
import com.doo.pojo.ExamScoreEX;

import java.util.List;

public interface ExamScoreMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ExamScore record);

    int insertSelective(ExamScore record);

    ExamScore selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ExamScore record);

    int updateByPrimaryKey(ExamScore record);

    List<ExamScoreEX> selectByStuId(int stuId);
}