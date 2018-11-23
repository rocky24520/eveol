package cn.com.rockySun.eve.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import cn.com.rockySun.eve.entity.Student;

@RestController
public class HelloController {
	
	@Value(value = "${book.author}")
	private String bookAuthor;
	
	@Value(value = "${book.name}")
	private String bookName;
	
	@Value(value = "${book.pinyin}")
	private String bookPinyin;
	
	@RequestMapping("/hello")
	public String sayHai(){
		return "Hello世界";
	}
	
	@Autowired
	private Student student;
	
	@RequestMapping(value = "/bookInfo",produces = "text/plain;charset=UTF-8")
	public String bookInfo(){
		return "《" + bookName + "（" + bookPinyin + "）》的作者是:" + bookAuthor; 
	}
	
	@RequestMapping("/student")
	public String student(){
		return "我叫" + student.getName() + ",我的学号是" + student.getNo() + "，今年已经" + student.getAge() + "岁了，当然我是" + student.getSex();
	}
	

	
}
