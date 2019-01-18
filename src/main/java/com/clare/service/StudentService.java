package com.clare.service;

import com.clare.domain.Student;
import com.clare.mapper.StudentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@CacheConfig(cacheNames = "students")
@Service
public class StudentService {

    @Autowired
    private StudentMapper studentMapper;

    @CacheEvict(key ="'student-' + #p0",allEntries=true)
    public int deleteByPrimaryKey(Integer id) {
        return studentMapper.deleteByPrimaryKey(id);
    }

    public int insert(Student record) {
        return studentMapper.insert(record);
    }

    public int insertSelective(Student record) {
        return studentMapper.insertSelective(record);
    }

    @Cacheable(key ="'student-' + #p0")
    public Student selectByPrimaryKey(Integer id) {
        return studentMapper.selectByPrimaryKey(id);
    }

    @CachePut(key = "'student-' + #p0.id")
    public Student updateByPrimaryKeySelective(Student record) {
        studentMapper.updateByPrimaryKeySelective(record);
        return studentMapper.selectByPrimaryKey(record.getId());
    }

    @CachePut(key = "'student-' + #p0.id")
    public Student updateByPrimaryKey(Student record) {
        studentMapper.updateByPrimaryKey(record);
        return studentMapper.selectByPrimaryKey(record.getId());
    }

    public List<Student> findAll() {
        return studentMapper.findAll();
    }
}
