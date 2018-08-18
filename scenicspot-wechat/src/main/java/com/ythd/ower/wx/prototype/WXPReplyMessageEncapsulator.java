package com.ythd.ower.wx.prototype;
import com.ythd.ower.wx.model.*;

import java.util.List;


/**
 * 回复消息封装
 * @author WL
 *
 */
public class WXPReplyMessageEncapsulator {
	// message general constants
	private static final String HEAD_XML = "<xml>";
	private static final String TAIL_XML = "</xml>";

	private static final String HEAD_CDATA = "<![CDATA[";
	private static final String TAIL_CDATA = "]]>";

	private static final String HEAD_TO_USER_NAME = "<ToUserName>";
	private static final String TAIL_TO_USER_NAME = "</ToUserName>";

	private static final String HEAD_FROM_USER_NAME = "<FromUserName>";
	private static final String TAIL_FROM_USER_NAME = "</FromUserName>";

	private static final String HEAD_CREATE_TIME = "<CreateTime>";
	private static final String TAIL_CREATE_TIME = "</CreateTime>";

	private static final String HEAD_MESSAGE_TYPE = "<MsgType>";
	private static final String TAIL_MESSAGE_TYPE = "</MsgType>";

	// reply message general constants
	private static final String HEAD_FUNCTION_FLAG = "<FuncFlag>";
	private static final String TAIL_FUNCTION_FLAG = "</FuncFlag>";

	// other reply message constants
	private static final String HEAD_CONTENT = "<Content>";
	private static final String TAIL_CONTENT = "</Content>";

	private static final String HEAD_TITLE = "<Title>";
	private static final String TAIL_TITLE = "</Title>";
	private static final String HEAD_DESCRIPTION = "<Description>";
	private static final String TAIL_DESCRIPTION = "</Description>";
	private static final String HEAD_MUSIC_URL = "<MusicUrl>";
	private static final String TAIL_MUSIC_URL = "</MusicUrl>";
	private static final String HEAD_HQ_MUSIC_URL = "<HQMusicUrl>";
	private static final String TAIL_HQ_MUSIC_URL = "</HQMusicUrl>";

	private static final String HEAD_ARTICLE_COUNT = "<ArticleCount>";
	private static final String TAIL_ARTICLE_COUNT = "</ArticleCount>";
	private static final String HEAD_ARTICLES = "<Articles>";
	private static final String TAIL_ARTICLES = "</Articles>";
	private static final String HEAD_ITEM = "<Item>";
	private static final String TAIL_ITEM = "</Item>";
	private static final String HEAD_PICTURE_URL = "<PicUrl>";
	private static final String TAIL_PICTURE_URL = "</PicUrl>";
	private static final String HEAD_ARTICLE_URL = "<Url>";
	private static final String TAIL_ARTICLE_URL = "</Url>";

	/**
	 * 回复文本消息
	 * @param textMessage
	 * @return
	 */
	public String encapsulateReplyTextMessage(WXPReplyTextMessage textMessage) {
		StringBuffer messageContent = new StringBuffer();
		String generalContent = encapsulateReplyMessageGeneralContent(textMessage);
		String textMessageContent = encapsulateReplyTextMessageContent(textMessage
				.getContent());

		messageContent.append(generalContent);
		messageContent.append(textMessageContent);
		return encapsulateReplyMessage(messageContent.toString());
	}

	/**
	 * 回复音乐消息
	 * @param musicMessage
	 * @return
	 */
	public String encapsulateReplyMusicMessage(WXPReplyMusicMessage musicMessage) {
		StringBuffer messageContent = new StringBuffer();
		String generalContent = encapsulateReplyMessageGeneralContent(musicMessage);
		String title = encapsulateReplyMessageTitle(musicMessage.getTitle());
		String description = encapsulateReplyMessageDescription(musicMessage
				.getDescription());
		String musicURL = encapsulateReplyMusicMessageMusicURL(musicMessage
				.getMusicURL());
		String HQMusicURL = encapsulateReplyMusicMessageHQMusicURL(musicMessage
				.getHQMusicURL());

		messageContent.append(generalContent);
		messageContent.append(title);
		messageContent.append(description);
		messageContent.append(musicURL);
		messageContent.append(HQMusicURL);
		return encapsulateReplyMessage(messageContent.toString());
	}

	/**
	 * 回复图文消息
	 * @param newsMessage
	 * @return
	 */
	public String encapsulateReplyNewsMessage(WXPReplyNewsMessage newsMessage) {
		StringBuffer messageContent = new StringBuffer();
		String generalContent = encapsulateReplyMessageGeneralContent(newsMessage);
		String articleCount = encapsulateReplyNewsMessageArticleCount(newsMessage
				.getArticleAmount());
		String articles = encapsulateReplyNewsMessageActicles(newsMessage.getArticles());
		
		messageContent.append(generalContent);
		messageContent.append(articleCount);
		messageContent.append(articles);
		return encapsulateReplyMessage(messageContent.toString());
	}

		/**
		 * 生成新闻回复
		 * @return
		 */
		public String getNewsXml(WXPReplyNewsMessage message){
			String responseStr="";
			responseStr = "<xml>";
			responseStr += "<ToUserName><![CDATA[" + message.getToUserId()
					+ "]]></ToUserName>";
			responseStr += "<FromUserName><![CDATA[" + message.getFromUserId()
					+ "]]></FromUserName>";
			responseStr += "<CreateTime>" + System.currentTimeMillis()
					+ "</CreateTime>";
			responseStr += "<MsgType><![CDATA[news]]></MsgType>";
			responseStr += "<ArticleCount>"+message.getArticles().size()+"</ArticleCount>";
			responseStr += "<Articles>";
			String items ="";
			for(int i=0;i<message.getArticles().size();i++){
				items +="<item>";
				items += "<Title><![CDATA["+message.getArticles().get(i).getTitle()+"]]></Title>";
				items += "<Description><![CDATA["+message.getArticles().get(i).getDesciprtion()+"]]></Description>";
				items += "<PicUrl><![CDATA["+message.getArticles().get(i).getPictureURL()+"]]></PicUrl>";
				items += "<Url><![CDATA["+message.getArticles().get(i).getArticleURL()+"]]></Url>";
				items += "</item>";
			}
			responseStr += items;
			responseStr += "</Articles>";
			responseStr += "</xml>";
			return responseStr;
		}
	
		/**
		 * 生成新闻回复
		 * @return
		 */
		public String getTextXml(WXPReplyTextMessage message){
			String responseStr="";
			responseStr = "<xml>";
			responseStr += "<ToUserName><![CDATA[" + message.getToUserId()+ "]]></ToUserName>";
			responseStr += "<FromUserName><![CDATA[" + message.getFromUserId()+ "]]></FromUserName>";
			responseStr += "<CreateTime>" + System.currentTimeMillis()+ "</CreateTime>";
			responseStr += "<MsgType><![CDATA[text]]></MsgType>";
			responseStr += "<Content><![CDATA["+message.getContent()+"]]></Content>";
			responseStr += "</xml>";
			return responseStr;
		}
	
	/**
	 * 封装基础消息部分
	 * @param message
	 * @return
	 */
	private String encapsulateReplyMessageGeneralContent(WXPReplyMessage message) {
		StringBuffer generalContent = new StringBuffer();
		String toUserName = encapsulateReplyMessageToUserName(message
				.getToUserId());
		String fromUserName = encapsulateReplyMessageFromUserName(message
				.getFromUserId());
		String createTime = encapsulateReplyMessageCreateTime(message
				.getCreateTime());
		String messageType = encapsulateReplyMessageMsgType(message
				.getMessageType());
		String functionFlag = encapsulateReplyMessageFunctionFlag(message
				.getFuncFlag());

		generalContent.append(toUserName);
		generalContent.append(fromUserName);
		generalContent.append(createTime);
		generalContent.append(messageType);
		generalContent.append(functionFlag);
		return generalContent.toString();
	}

	/**
	 * 封装xml节点,内容,头标记,结束标记
	 * @param content
	 * @param head
	 * @param tail
	 * @return
	 */
	private String strAppend(String content, String head, String tail) {
		StringBuffer strBuffer = new StringBuffer();
		strBuffer.append(head);
		strBuffer.append(content);
		strBuffer.append(tail);
		return strBuffer.toString();
	}
	/**
	 * 封装带CDATA部分的消息节点
	 * @param CDATA
	 * @return
	 */
	private String encapsulateReplyMessageCDATA(String CDATA) {
		return strAppend(CDATA, HEAD_CDATA, TAIL_CDATA);
	}
	/**
	 * XML外标记
	 * @param messageContent
	 * @return
	 */
	private String encapsulateReplyMessage(String messageContent) {
		return strAppend(messageContent, HEAD_XML, TAIL_XML);
	}
	/**
	 * 接收账户,用户账户
	 * @param toUserName
	 * @return
	 */
	private String encapsulateReplyMessageToUserName(String toUserName) {
		String toUserNameContent = encapsulateReplyMessageCDATA(toUserName);
		return strAppend(toUserNameContent, HEAD_TO_USER_NAME,
				TAIL_TO_USER_NAME);
	}
	/**
	 * 发送账户,公众账号
	 * @param fromUserName
	 * @return
	 */
	private String encapsulateReplyMessageFromUserName(String fromUserName) {
		String fromUserNameContent = encapsulateReplyMessageCDATA(fromUserName);
		return strAppend(fromUserNameContent, HEAD_FROM_USER_NAME,
				TAIL_FROM_USER_NAME);
	}
	/**
	 * 消息创建时间
	 * @param createTime
	 * @return
	 */
	private String encapsulateReplyMessageCreateTime(long createTime) {
		String strCreateTime = Long.toString(createTime);
		return strAppend(strCreateTime, HEAD_CREATE_TIME, TAIL_CREATE_TIME);
	}
	/**
	 * 消息类型
	 * @param messageType
	 * @return
	 */
	private String encapsulateReplyMessageMsgType(String messageType) {
		String messageTypeContent = encapsulateReplyMessageCDATA(messageType);
		return strAppend(messageTypeContent, HEAD_MESSAGE_TYPE,
				TAIL_MESSAGE_TYPE);
	}
	/**
	 * 星标刚收到的消息。
	 * @param funcFlag
	 * @return
	 */
	private String encapsulateReplyMessageFunctionFlag(int funcFlag) {
		String strFuncFlag = Integer.toString(funcFlag);
		return strAppend(strFuncFlag, HEAD_FUNCTION_FLAG, TAIL_FUNCTION_FLAG);
	}
	/**
	 * 回复文本内容
	 * @param content
	 * @return
	 */
	private String encapsulateReplyTextMessageContent(String content) {
		String contentBody = encapsulateReplyMessageCDATA(content);
		return strAppend(contentBody, HEAD_CONTENT, TAIL_CONTENT);
	}
	/**
	 * 图文消息标题
	 * @param title
	 * @return
	 */
	private String encapsulateReplyMessageTitle(String title) {
		String titleContent = encapsulateReplyMessageCDATA(title);
		return strAppend(titleContent, HEAD_TITLE, TAIL_TITLE);
	}
	/**
	 * 图文消息描述
	 * @param description
	 * @return
	 */
	private String encapsulateReplyMessageDescription(String description) {
		String descriptionContent = encapsulateReplyMessageCDATA(description);
		return strAppend(descriptionContent, HEAD_DESCRIPTION, TAIL_DESCRIPTION);
	}
	/**
	 * 音乐链接
	 * @param musicURL
	 * @return
	 */
	private String encapsulateReplyMusicMessageMusicURL(String musicURL) {
		String musicURLContent = encapsulateReplyMessageCDATA(musicURL);
		return strAppend(musicURLContent, HEAD_MUSIC_URL, TAIL_MUSIC_URL);
	}
	/**
	 * 高品质音乐链接
	 * @param HQMusicURL
	 * @return
	 */
	private String encapsulateReplyMusicMessageHQMusicURL(String HQMusicURL) {
		String HQMusicURLContent = encapsulateReplyMessageCDATA(HQMusicURL);
		return strAppend(HQMusicURLContent, HEAD_HQ_MUSIC_URL,
				TAIL_HQ_MUSIC_URL);
	}
	/**
	 * 多图为消息总数
	 * @param articleCount
	 * @return
	 */
	private String encapsulateReplyNewsMessageArticleCount(int articleCount) {
		String strArticleCount = Integer.toString(articleCount);
		return strAppend(strArticleCount, HEAD_ARTICLE_COUNT,
				TAIL_ARTICLE_COUNT);
	}
	/**
	 * 图文消息主题
	 * @param articles
	 * @return
	 */
	private String encapsulateReplyNewsMessageActicles(List<WXPArticle> articles) {
		StringBuffer articlesContent = new StringBuffer();
		for(WXPArticle article: articles) {
			String articleContent = encapsulateReplyNewsMessageArticle(article);
			articlesContent.append(articleContent);
		}
		return strAppend(articlesContent.toString(), HEAD_ARTICLES, TAIL_ARTICLES);
	}
	/**
	 * 单条图文消息生成
	 * @param article
	 * @return
	 */
	private String encapsulateReplyNewsMessageArticle(WXPArticle article) {
		StringBuffer articleContent = new StringBuffer();
		String title = encapsulateReplyMessageTitle(article.getTitle());
		String description = encapsulateReplyMessageDescription(article.getDesciprtion());
		String pictureURL = encapsulateReplyNewsMessagePictureURL(article.getPictureURL());
		String articleURL = encapsulateReplyNewsMessageArticleURL(article.getArticleURL());
		
		articleContent.append(title);
		articleContent.append(description);
		articleContent.append(pictureURL);
		articleContent.append(articleURL);
		return strAppend(articleContent.toString(), HEAD_ITEM, TAIL_ITEM);
	}
	/**
	 * 图文消息图片链接
	 * @param pictureURL
	 * @return
	 */
	private String encapsulateReplyNewsMessagePictureURL(String pictureURL) {
		String pictureURLContent = encapsulateReplyMessageCDATA(pictureURL);
		return strAppend(pictureURLContent, HEAD_PICTURE_URL, TAIL_PICTURE_URL);
	}
	/**
	 * 点击图文消息跳转链接,自动添加拨号破解后缀
	 * @param articleURL
	 * @return
	 */
	private String encapsulateReplyNewsMessageArticleURL(String articleURL) {
		String articleURLContent = encapsulateReplyMessageCDATA(articleURL);
		return strAppend(articleURLContent, HEAD_ARTICLE_URL, TAIL_ARTICLE_URL);
	}

}