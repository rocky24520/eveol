package cn.com.rockySun.eve.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.com.rockySun.eve.entity.DebugInfo;
import cn.com.rockySun.eve.service.DebugInfoService;

@RestController
public class DebugInfoController {
	
	@Autowired
	private DebugInfoService debugInfoService;

	@RequestMapping("/debugInfoList")
	public List debugInfoList(){
		return debugInfoService.queryList();
		
	}
	
	@RequestMapping("/debug2")
	public List queryListByMybatis(){
		return debugInfoService.queryListByMybatis();
		
	}
}
