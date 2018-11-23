/**
 * 
 */
package cn.com.rockySun.eve.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 
 * @author Administrator
 * 把@RestController替换为@Controller注解
 * @RestController注解表示返回的内容都是HTTP Content不会被模版引擎处理的
 */
@Controller
public class StudentAction {
	/**
	 * 
	 * @Description	: freemarker模板用例
	 * @Author		: 
	 * @CreateTime	: 2018年11月23日 下午3:44:40
	 * @param modelAndView
	 * @return
	 */
	@RequestMapping("/students")
	public String students(Model m){
        List<String> userList = new ArrayList<String>();
        userList.add("金亮");
        userList.add("姚老哥");
        userList.add("土豪梦");
        m.addAttribute("userList", userList);
        return "student/studentList";
	}
}
