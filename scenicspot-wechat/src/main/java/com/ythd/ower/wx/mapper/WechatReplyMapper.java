package com.ythd.ower.wx.mapper;

import com.ythd.ower.common.dto.PageData;

import com.ythd.ower.data.dao.EADao;

public interface WechatReplyMapper extends EADao {

	PageData findByKw(PageData data);
}