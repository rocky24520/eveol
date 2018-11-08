package cn.com.rockySun.eve.entity;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DebugInfo  implements Serializable{
	private static final long serialVersionUID = -6556793741331167103L;
	private Date optDateTime;
	
	private String objectName;
	
	private String planId;
	
	private String orgCode;
	
	private String errorMsg;

	public Date getOptDateTime() {
		return optDateTime;
	}

	public void setOptDateTime(Date optDateTime) {
		this.optDateTime = optDateTime;
	}

	public String getObjectName() {
		return objectName;
	}

	public void setObjectName(String objectName) {
		this.objectName = objectName;
	}

	public String getPlanId() {
		return planId;
	}

	public void setPlanId(String planId) {
		this.planId = planId;
	}

	public String getOrgCode() {
		return orgCode;
	}

	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}

	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}
	
	@Override
	public String toString() {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return "时间：" + simpleDateFormat.format(optDateTime) + ",对象名：" + objectName + "，错误信息：" + errorMsg;
	}
	
	
}
