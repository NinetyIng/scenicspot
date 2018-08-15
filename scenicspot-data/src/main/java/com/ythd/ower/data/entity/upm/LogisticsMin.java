package com.ythd.ower.data.entity.upm;

import java.util.List;

public class LogisticsMin {
	
	private  String logisticsName;
	
	private String createTime;
	
	private String logisticsId;
	
	private String bsId;
	
	
	private List<LogisticsFee> logisticsFees;


	public String getLogisticsName() {
		return logisticsName;
	}


	public void setLogisticsName(String logisticsName) {
		this.logisticsName = logisticsName;
	}


	public String getBsId() {
		return bsId;
	}


	public void setBsId(String bsId) {
		this.bsId = bsId;
	}


	public String getCreateTime() {
		return createTime;
	}


	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}


	public String getLogisticsId() {
		return logisticsId;
	}


	public void setLogisticsId(String logisticsId) {
		this.logisticsId = logisticsId;
	}


	public List<LogisticsFee> getLogisticsFees() {
		return logisticsFees;
	}


	public void setLogisticsFees(List<LogisticsFee> logisticsFees) {
		this.logisticsFees = logisticsFees;
	}
	
	
	

}
