package com.clare.web;

import com.clare.domain.Student;
import com.clare.mapper.StudentMapper;
import com.clare.service.StudentService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/students") // 通过这里配置使下面的映射都在/users下
public class StudentController {
	private Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	private StudentMapper studentMapper;
	@Autowired
	private StudentService studentService;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public List<Student> getStudentList() {
		// 处理"/students/"的GET请求，用来获取Student列表
		// 还可以通过@RequestParam从页面中传递参数来进行查询条件或者翻页信息的传递
		return studentService.findAll();
	}

	/**
	 *处理"/students/"的POST请求，用来创建Student
	 * // @ModelAttribute 表单绑定参数
	 * // @RequestParam 页面传参
	 * // @RequestBody json等传参
	 */
	@RequestMapping(value = "/", method = RequestMethod.POST)
	public String postStudent(@RequestBody Student stu) {
		String result = "failure";
		try {
			int insertId = studentService.insert(stu);
			if (insertId > 0) {
				result = "success";
				ObjectMapper mapper = new ObjectMapper();
				String str = mapper.writeValueAsString(stu);
				logger.info("insert student success,student={}", str);
			}
		} catch (Exception e) {
			logger.error("",e);
		}
		return result;
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public Student getStudent(@PathVariable Integer id) {
		// 处理"/students/{id}"的GET请求，用来获取url中id值的Student信息
		// url中的id可通过@PathVariable绑定到函数的参数中
		return studentService.selectByPrimaryKey(id);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public Student putUser(@PathVariable Integer id, @RequestBody Student stu) {
		// 处理"/students/{id}"的PUT请求，用来更新User信息
		Student student = null;
		try {
			stu.setId(id);
			student = studentService.updateByPrimaryKeySelective(stu);
			ObjectMapper mapper = new ObjectMapper();
			String str = mapper.writeValueAsString(stu);
			logger.info("update student success, new info {}", str);
		} catch (Exception e) {
			logger.error("", e);
		}
		return student;
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public String deleteStudent(@PathVariable Integer id) {
		// 处理"/students/{id}"的DELETE请求，用来删除Student
		studentService.deleteByPrimaryKey(id);
		logger.info("delete student id={}", id);
		return "success";
	}


}