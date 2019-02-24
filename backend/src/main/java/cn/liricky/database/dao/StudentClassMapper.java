package cn.liricky.database.dao;

import cn.liricky.database.model.entity.StudentClass;
import cn.liricky.database.model.entity.StudentClassExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface StudentClassMapper {
    int countByExample(StudentClassExample example);

    int deleteByExample(StudentClassExample example);

    int deleteByPrimaryKey(Integer studentClassId);

    int insert(StudentClass record);

    int insertSelective(StudentClass record);

    List<StudentClass> selectByExample(StudentClassExample example);

    StudentClass selectByPrimaryKey(Integer studentClassId);

    int updateByExampleSelective(@Param("record") StudentClass record, @Param("example") StudentClassExample example);

    int updateByExample(@Param("record") StudentClass record, @Param("example") StudentClassExample example);

    int updateByPrimaryKeySelective(StudentClass record);

    int updateByPrimaryKey(StudentClass record);
}