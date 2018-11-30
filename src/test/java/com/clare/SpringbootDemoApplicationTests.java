package com.clare;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.clare.domain.Student;
import com.clare.mapper.StudentMapper;

@RunWith(SpringRunner.class)
@SpringBootTest
@WebAppConfiguration
@MapperScan("com.clare.mapper")
public class SpringbootDemoApplicationTests {

	@Autowired
	private StudentMapper studentMapper;

	@Test
	public void testDb() {
		Student stu = new Student();
		stu.setAge(22);
		stu.setSex(false);
		stu.setStuName("泡咕");
		stu.setStuGrade("二年级");
		stu.setStuClass("1班");
		studentMapper.insert(stu);
		Assert.assertEquals("泡咕", stu.getStuName());
	}

}
