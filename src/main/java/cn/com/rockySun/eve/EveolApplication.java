package cn.com.rockySun.eve;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("cn.com.rockySun.eve.dao")
public class EveolApplication {

	public static void main(String[] args) {
		SpringApplication.run(EveolApplication.class, args);
	}
}
