package cn.com.rockySun.eve.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import cn.com.rockySun.eve.dao.IDebugInfoDao;
import cn.com.rockySun.eve.entity.DebugInfo;

@Service
public class DebugInfoService {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	private IDebugInfoDao debugInfoDao;
	
	public List<DebugInfo> queryList(){
		String sql = "SELECT * FROM DEBUGINFO";
		return (List) jdbcTemplate.query(sql, new RowMapper(){
			public DebugInfo mapRow(ResultSet resultSet , int rowNum) throws SQLException {
				DebugInfo debugInfo = new DebugInfo();
				debugInfo.setOptDateTime(resultSet.getDate("OPTDATETIME"));
				debugInfo.setObjectName(resultSet.getString("OBJECTNAME"));;
				debugInfo.setPlanId(resultSet.getString("PLAN_ID"));
				debugInfo.setOrgCode(resultSet.getString("ORG_CODE"));
				debugInfo.setErrorMsg(resultSet.getString("ERRORMSG"));
				return debugInfo;
			}
		});
	}
	
	public List<DebugInfo> queryListByMybatis(){	
		return debugInfoDao.queryListByMybatis();
	}
}
