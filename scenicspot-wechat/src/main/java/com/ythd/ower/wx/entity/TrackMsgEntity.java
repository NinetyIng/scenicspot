package com.ythd.ower.wx.entity;

import java.util.List;
import lombok.Data;

@Data
public class TrackMsgEntity {
	
	public String ebusinessID;
	
	public String success;
	
	public String logisticCode;
	
	public String state;
	
	public String shipperCode;
	
	public String shipperName;

	public List<TrackEntity> traces;
}
