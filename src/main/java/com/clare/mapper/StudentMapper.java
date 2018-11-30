package com.clare.mapper;

import com.clare.domain.Student;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface StudentMapper {
    @Delete({
        "delete from t_student",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into t_student (stu_name, ",
        "sex, age, stu_grade, ",
        "stu_class)",
        "values (#{stuName,jdbcType=VARCHAR}, ",
        "#{sex,jdbcType=BIT}, #{age,jdbcType=INTEGER}, #{stuGrade,jdbcType=VARCHAR}, ",
        "#{stuClass,jdbcType=VARCHAR})"
    })
    int insert(Student record);

    int insertSelective(Student record);

    @Select({
        "select",
        "id, stu_name, sex, age, stu_grade, stu_class",
        "from t_student",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @ResultMap("com.clare.mapper.StudentMapper.BaseResultMap")
    Student selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Student record);

    @Update({
        "update t_student",
        "set stu_name = #{stuName,jdbcType=VARCHAR},",
          "sex = #{sex,jdbcType=BIT},",
          "age = #{age,jdbcType=INTEGER},",
          "stu_grade = #{stuGrade,jdbcType=VARCHAR},",
          "stu_class = #{stuClass,jdbcType=VARCHAR}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(Student record);
    
    List<Student> findAll();
}