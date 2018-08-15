package com.ythd.ower.wx.model;

import java.util.Date;

public class WXAPIModel {
	private String jsapi_ticket;
	private String access_token;
	private String timestamp;
	private String nonceStr;
	private String signature;
	private Date createTime;
	public boolean isOverTime(){
		return false;
	}
	public String getJsapi_ticket() {
		return jsapi_ticket;
	}
	public void setJsapi_ticket(String jsapi_ticket) {
		this.jsapi_ticket = jsapi_ticket;
	}
	public String getAccess_token() {
		return access_token;
	}
	public void setAccess_token(String access_token) {
		this.access_token = access_token;
	}
	public String getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}
	public String getNonceStr() {
		return nonceStr;
	}
	public void setNonceStr(String nonceStr) {
		this.nonceStr = nonceStr;
	}
	public String getSignature() {
		return signature;
	}
	public void setSignature(String signature) {
		this.signature = signature;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
}
