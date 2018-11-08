package cn.com.rockySun.eve.entity;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "student")
@PropertySource("classpath:student.properties")
/*
 * spring boot（版本1.5.1.RELEASE）项目中，当准备映射自定义的配置文件属性到类中的时候，发现原本的@ConfigurationProperties注解已将location属性移除，因此导致无法正常给配置类的属性赋值
 * 既然不行了，那我们只能换一种方式：
 * 在@EnableConfigurationProperties取消激活自定义的配置类（重要）
 * 在配置类中采用@Component的方式注册为组件，然后使用@PropertySource来指定自定义的资源目录
 */
public class Student {

	private String no;
	
	private String name;
	
	private int age;
	
	private String sex;
	
	public String getNo() {
		return no;
	}
	
	public void setNo(String no) {
		this.no = no;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public int getAge() {
		return age;
	}
	
	public void setAge(int age) {
		this.age = age;
	}
	
	public String getSex() {
		return sex;
	}
	
	public void setSex(String sex) {
		this.sex = sex;
	}
}
