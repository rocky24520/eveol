package cn.com.rockySun.eve.dao;

import java.util.List;

import cn.com.rockySun.eve.entity.DebugInfo;

public interface IDebugInfoDao {

	List<DebugInfo> queryListByMybatis();
}
