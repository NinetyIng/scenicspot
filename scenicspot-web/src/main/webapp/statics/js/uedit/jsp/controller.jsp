<%@ page language="java" contentType="text/html; charset=UTF-8"
	import="com.baidu.ueditor.ActionEnter"
    pageEncoding="UTF-8"%>
<%@ page import="org.json.JSONObject" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%

    request.setCharacterEncoding( "utf-8" );
	response.setHeader("Content-Type" , "text/html");
	
	String rootPath = application.getRealPath( "/" );
	//out.write( new ActionEnter( request, rootPath ).exec());
	JSONObject jsonObject = new JSONObject();
	response.getWriter().write(new ActionEnter( request, rootPath ).exec());
%>