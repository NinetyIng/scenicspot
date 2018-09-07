package com.ythd.ower.wx.service;
import com.ythd.ower.common.dto.PageData;
import com.ythd.ower.data.dao.EABaseService;
import com.ythd.ower.data.dao.EADao;
import com.ythd.ower.wx.mapper.WechatReplyMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WechatReplyService extends EABaseService {
	@Autowired
	private WechatReplyMapper wechatReplyMapper;
	@Override
	public EADao getDao(){
		return wechatReplyMapper;
	}
	public PageData findByKw(PageData data) {
		return null;
	}
}