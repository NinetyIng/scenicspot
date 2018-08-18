package com.ythd.ower.wx.prototype;


import com.ythd.ower.wx.model.*;

public class WXPReceivedMessageParser {
	private static final String HEAD_CDATA = "<![CDATA[";
	private static final int HEAD_CDATA_LEN = 9;
	private static final String TAIL_CDATA = "]]>";

	private static final String HEAD_TO_USER_NAME = "<ToUserName>";
	private static final int HEAD_TO_USER_NAME_LEN = 12;
	private static final String TAIL_TO_USER_NAME = "</ToUserName>";

	private static final String HEAD_FROM_USER_NAME = "<FromUserName>";
	private static final int HEAD_FROM_USER_NAME_LEN = 14;
	private static final String TAIL_FROM_USER_NAME = "</FromUserName>";

	private static final String HEAD_CREATE_TIME = "<CreateTime>";
	private static final int HEAD_CREATE_TIME_LEN = 12;
	private static final String TAIL_CREATE_TIME = "</CreateTime>";

	private static final String HEAD_MESSAGE_TYPE = "<MsgType>";
	private static final int HEAD_MESSAGE_TYPE_LEN = 9;
	private static final String TAIL_MESSAGE_TYPE = "</MsgType>";

	// received message general constants
	private static final String HEAD_MESSAGE_ID = "<MsgId>";
	private static int HEAD_MESSAGE_ID_LEN = 7;
	private static final String TAIL_MESSAGE_ID = "</MsgId>";

	// other received message constants
	private static final String HEAD_CONTENT = "<Content>";
	private static final int HEAD_CONTENT_LEN = 9;
	private static final String TAIL_CONTENT = "</Content>";

	private static final String HEAD_PICTURE_URL = "<PicUrl>";
	private static final int HEAD_PICTURE_URL_LEN = 8;
	private static final String TAIL_PICTURE_URL = "</PicUrl>";

	private static final String HEAD_LOCATION_X = "<Location_X>";
	private static final int HEAD_LOCATION_X_LEN = 12;
	private static final String TAIL_LOCATION_X = "</Location_X>";
	private static final String HEAD_LOCATION_Y = "<Location_Y>";
	private static final int HEAD_LOCATION_Y_LEN = 12;
	private static final String TAIL_LOCATION_Y = "</Location_Y>";
	private static final String HEAD_SCALE = "<Scale>";
	private static final int HEAD_SCALE_LEN = 7;
	private static final String TAIL_SCALE = "</Scale>";
	private static final String HEAD_LABEL = "<Label>";
	private static final int HEAD_LABEL_LEN = 7;
	private static final String TAIL_LABEL = "</Label>";

	private static final String HEAD_TITLE = "<Title>";
	private static final int HEAD_TITLE_LEN = 7;
	
	private static final String TAIL_TITLE = "</Title>";
	private static final String HEAD_DESCRIPTION = "<Description>";
	private static final int HEAD_DESCRIPTION_LEN = 13;
	private static final String TAIL_DESCRIPTION = "</Description>";
	private static final String HEAD_URL = "<Url>";
	private static final int HEAD_URL_LEN = 5;
	private static final String TAIL_URL = "</Url>";

	private static final String HEAD_EVENT = "<Event>";
	private static final int HEAD_EVENT_LEN = 7;
	private static final String TAIL_EVENT = "</Event>";
	private static final String HEAD_EVENT_KEY = "<EventKey>";
	private static final int HEAD_EVENT_KEY_LEN = 7;
	private static final String TAIL_EVENT_KEY = "</EventKey>";

	// /////////////////////////////////////////////////////////////////////////
	/**
	 * 消息解析,解析基础参数
	 * @param receivedMessage
	 * @return
	 */
	private WXPReceivedMessage parseWXPReceivedMessage(String receivedMessage) {
		WXPReceivedMessage message = new WXPReceivedMessage();

		String toUserName = parseMessageToUserName(receivedMessage);
		String fromUserName = parseMessageFromUserName(receivedMessage);
		long messageId = parseMessageId(receivedMessage);
		long createTime = parseMessageCreateTime(receivedMessage);
		String messageType = parseWXPReceivedMessageType(receivedMessage);

		message.setToUserId(toUserName);
		message.setFromUserId(fromUserName);
		message.setMessageId(messageId);
		message.setCreateTime(createTime);
		message.setMessageType(messageType);
		return message;
	}
	/**
	 * 解析文本消息
	 * @param receivedMessage
	 * @return
	 */
	public WXPReceivedTextMessage parseWXPReceivedTextMessage(
			String receivedMessage) {
		WXPReceivedTextMessage textMessage = new WXPReceivedTextMessage();
		WXPReceivedMessage message = parseWXPReceivedMessage(receivedMessage);
		String messageContent = parseTextMessageContent(receivedMessage);

		textMessage.setToUserId(message.getToUserId());
		textMessage.setFromUserId(message.getFromUserId());
		textMessage.setMessageId(message.getMessageId());
		textMessage.setCreateTime(message.getCreateTime());
		textMessage.setMessageType(message.getMessageType());
		textMessage.setContent(messageContent);
		return textMessage;
	}

	/**
	 * 解析图片消息
	 * @param receivedMessage
	 * @return
	 */
	public WXPReceivedImageMessage parseWXPReceivedImageMessage(
			String receivedMessage) {
		WXPReceivedImageMessage imageMessage = new WXPReceivedImageMessage();
		WXPReceivedMessage message = parseWXPReceivedMessage(receivedMessage);
		String imageURL = parseImageMessagePictureURL(receivedMessage);

		imageMessage.setToUserId(message.getToUserId());
		imageMessage.setFromUserId(message.getFromUserId());
		imageMessage.setMessageId(message.getMessageId());
		imageMessage.setCreateTime(message.getCreateTime());
		imageMessage.setMessageType(message.getMessageType());
		imageMessage.setPictureURL(imageURL);
		return imageMessage;
	}

	/**
	 * 解析位置消息
	 * @param receivedMessage
	 * @return
	 */
	public WXPReceivedLocationMessage parseWXPReceivedLocatioinMessage(
			String receivedMessage) {
		WXPReceivedLocationMessage locationMessage = new WXPReceivedLocationMessage();
		WXPReceivedMessage message = parseWXPReceivedMessage(receivedMessage);
		double latitude = parseLocationMessageLocX(receivedMessage);
		double longitude = parseLocationMessageLocY(receivedMessage);
		int scale = parseLocationMessageScale(receivedMessage);
		String label = parseLocationMessageLabel(receivedMessage);

		locationMessage.setToUserId(message.getToUserId());
		locationMessage.setFromUserId(message.getFromUserId());
		locationMessage.setMessageId(message.getMessageId());
		locationMessage.setCreateTime(message.getCreateTime());
		locationMessage.setMessageType(message.getMessageType());
		locationMessage.setLatitude(latitude);
		locationMessage.setLongitude(longitude);
		locationMessage.setScale(scale);
		locationMessage.setLabel(label);
		return locationMessage;
	}

	/**
	 * 解析链接消息
	 * @param receivedMessage
	 * @return
	 */
	public WXPReceivedLinkMessage parseWXPReceivedLinkMessage(
			String receivedMessage) {
		WXPReceivedLinkMessage linkMessage = new WXPReceivedLinkMessage();
		WXPReceivedMessage message = parseWXPReceivedMessage(receivedMessage);
		String title = parseLinkMessageTitle(receivedMessage);
		String description = parseLinkMessageDescription(receivedMessage);
		String URL = parseLinkMessageURL(receivedMessage);

		linkMessage.setToUserId(message.getFromUserId());
		linkMessage.setFromUserId(message.getFromUserId());
		linkMessage.setMessageId(message.getMessageId());
		linkMessage.setCreateTime(message.getCreateTime());
		linkMessage.setMessageType(message.getMessageType());
		linkMessage.setTitle(title);
		linkMessage.setDescription(description);
		linkMessage.setLinkURL(URL);
		return linkMessage;
	}

	/**
	 * 解析事件消息
	 * @param receivedMessage
	 * @return
	 */
	public WXPReceivedEventMessage parseWXPReceivedEventMessage(
			String receivedMessage) {
		WXPReceivedEventMessage eventMessage = new WXPReceivedEventMessage();
		WXPReceivedMessage message = parseWXPReceivedMessage(receivedMessage);
		String event = parseEventMessageEvent(receivedMessage);
		String eventKey = parseEventMessageEventKey(receivedMessage);

		eventMessage.setToUserId(message.getToUserId());
		eventMessage.setFromUserId(message.getFromUserId());
		eventMessage.setMessageId(message.getMessageId());
		eventMessage.setCreateTime(message.getCreateTime());
		eventMessage.setMessageType(message.getMessageType());
		eventMessage.setEvent(event);
		eventMessage.setEventKey(eventKey);
		return eventMessage;
	}
	/**
	 * 解析消息类型
	 * @param receivedMessage
	 * @return
	 */
	public String parseWXPReceivedMessageType(String receivedMessage) {
		int start = receivedMessage.indexOf(HEAD_MESSAGE_TYPE);
		int end = receivedMessage.indexOf(TAIL_MESSAGE_TYPE);
		String messageTypeCDATA = receivedMessage.substring(start
				+ HEAD_MESSAGE_TYPE_LEN, end);
		return parseCDATAStr(messageTypeCDATA);
	}

	/**
	 * 截取字符串,得到节点值
	 * @param content
	 * @param head
	 * @param tail
	 * @param headLength
	 * @return
	 */
	private String strSlice(String content, String head, String tail,
			int headLength) {
		int start = content.indexOf(head);
		int end = content.indexOf(tail);
		return content.substring(start + headLength, end);
	}

	/**
	 * CDATA部分解析
	 * @param strCDATA
	 * @return
	 */
	private String parseCDATAStr(String strCDATA) {
		String CDATA = strSlice(strCDATA, HEAD_CDATA, TAIL_CDATA,
				HEAD_CDATA_LEN);
		return CDATA;
	}
	/**
	 * toUserName 结束账号
	 * @param receivedMessage
	 * @return
	 */
	private String parseMessageToUserName(String receivedMessage) {
		String messageToUserName = strSlice(receivedMessage, HEAD_TO_USER_NAME,
				TAIL_TO_USER_NAME, HEAD_TO_USER_NAME_LEN);
		return parseCDATAStr(messageToUserName);
	}
	/**
	 * 发送账号
	 * @param receivedMessage
	 * @return
	 */
	private String parseMessageFromUserName(String receivedMessage) {
		String messageFromUserName = strSlice(receivedMessage,
				HEAD_FROM_USER_NAME, TAIL_FROM_USER_NAME,
				HEAD_FROM_USER_NAME_LEN);
		return parseCDATAStr(messageFromUserName);
	}

	/**
	 * 消息编号
	 * @param receivedMessage
	 * @return
	 */
	private long parseMessageId(String receivedMessage) {
		String messageId="123";
		try{
			messageId = strSlice(receivedMessage, HEAD_MESSAGE_ID,
					TAIL_MESSAGE_ID, HEAD_MESSAGE_ID_LEN);
		}catch(Exception e){
			
		}
		
		return Long.parseLong(messageId);
	}

	/**
	 * 发送时间
	 * @param receivedMessage
	 * @return
	 */
	private long parseMessageCreateTime(String receivedMessage) {
		String messageCreateTime = strSlice(receivedMessage, HEAD_CREATE_TIME,
				TAIL_CREATE_TIME, HEAD_CREATE_TIME_LEN);
		return Long.parseLong(messageCreateTime);
	}

	/**
	 * 文本消息,消息内容
	 * @param receivedMessage
	 * @return
	 */
	private String parseTextMessageContent(String receivedMessage) {
		String messageContent = strSlice(receivedMessage, HEAD_CONTENT,
				TAIL_CONTENT, HEAD_CONTENT_LEN);
		return parseCDATAStr(messageContent);
	}

	/**
	 * 图片消息,图片链接地址
	 * @param receivedMessage
	 * @return
	 */
	private String parseImageMessagePictureURL(String receivedMessage) {
		String pictureURL = strSlice(receivedMessage, HEAD_PICTURE_URL,
				TAIL_PICTURE_URL, HEAD_PICTURE_URL_LEN);
		return parseCDATAStr(pictureURL);
	}

	/**
	 * 位置消息,纬度
	 * @param receivedMessage
	 * @return
	 */
	private double parseLocationMessageLocX(String receivedMessage) {
		String locationX = strSlice(receivedMessage, HEAD_LOCATION_X,
				TAIL_LOCATION_X, HEAD_LOCATION_X_LEN);
		return Double.parseDouble(locationX);
	}

	/**
	 * 位置消息,经度
	 * @param receivedMessage
	 * @return
	 */
	private double parseLocationMessageLocY(String receivedMessage) {
		String locationY = strSlice(receivedMessage, HEAD_LOCATION_Y,
				TAIL_LOCATION_Y, HEAD_LOCATION_Y_LEN);
		return Double.parseDouble(locationY);
	}

	/**
	 * 位置消息,地图缩放大小
	 * @param receivedMessage
	 * @return
	 */
	private int parseLocationMessageScale(String receivedMessage) {
		String scale = strSlice(receivedMessage, HEAD_SCALE, TAIL_SCALE,
				HEAD_SCALE_LEN);
		return Integer.parseInt(scale);
	}

	/**
	 * 位置消息,地理位置信息
	 * @param receivedMessage
	 * @return
	 */
	private String parseLocationMessageLabel(String receivedMessage) {
		String label = strSlice(receivedMessage, HEAD_LABEL, TAIL_LABEL,
				HEAD_LABEL_LEN);
		return parseCDATAStr(label);
	}

	/**
	 * 链接消息,消息标题
	 * @param receivedMessage
	 * @return
	 */
	private String parseLinkMessageTitle(String receivedMessage) {
		String title = strSlice(receivedMessage, TAIL_TITLE, HEAD_TITLE,
				HEAD_TITLE_LEN);
		return parseCDATAStr(title);
	}

	/**
	 * 链接消息,消息描述
	 * @param receivedMessage
	 * @return
	 */
	private String parseLinkMessageDescription(String receivedMessage) {
		String description = strSlice(receivedMessage, HEAD_DESCRIPTION,
				TAIL_DESCRIPTION, HEAD_DESCRIPTION_LEN);
		return parseCDATAStr(description);
	}

	/**
	 * 链接消息,消息链接地址
	 * @param receivedMessage
	 * @return
	 */
	private String parseLinkMessageURL(String receivedMessage) {
		String URL = strSlice(receivedMessage, HEAD_URL, TAIL_URL, HEAD_URL_LEN);
		return parseCDATAStr(URL);
	}

	/**
	 * 事件类型，subscribe(订阅)、unsubscribe(取消订阅)、CLICK(自定义菜单点击事件)
	 * @param receivedMessage
	 * @return
	 */
	private String parseEventMessageEvent(String receivedMessage) {
		String event = strSlice(receivedMessage, HEAD_EVENT, TAIL_EVENT,
				HEAD_EVENT_LEN);
		return parseCDATAStr(event);
	}

	/**
	 * 事件KEY值，与自定义菜单接口中KEY值对应
	 * @param receivedMessage
	 * @return
	 */
	private String parseEventMessageEventKey(String receivedMessage) {
		String eventKey = strSlice(receivedMessage, HEAD_EVENT_KEY,
				TAIL_EVENT_KEY, HEAD_EVENT_KEY_LEN);
		return parseCDATAStr(eventKey);
	}
}
