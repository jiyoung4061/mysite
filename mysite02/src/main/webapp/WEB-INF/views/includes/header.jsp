<%@page import="jdk.internal.misc.FileSystemOption"%>
<%@page import="com.markany.mysite.vo.UserVo"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%
	// JSP에는 session이 이미 처리되어있어서 아래 코드 삽입 안해도됨!
	//HttpSession session = request.getSession(false);
	UserVo authUser = (UserVo)session.getAttribute("authUser");
%>
<div id="header">
	<h1>MySite</h1>
	<ul>
	<%
		if(null == authUser){
	%>
		<li><a href="<%=request.getContextPath()%>/user?a=loginform">로그인</a><li>
		<li><a href="<%=request.getContextPath()%>/user?a=joinform">회원가입</a><li>
	<%
		} else {
			System.out.println("name>>"+authUser);
	%> 
		<li><a href="<%=request.getContextPath()%>/user?a=updateform">회원정보수정</a><li>
		<li><a href="<%=request.getContextPath()%>/user?a=logout">로그아웃</a><li>
		<li><%=authUser.getName()%>님 안녕하세요 ^^;</li>
	<%
		}
	%>
	</ul>
</div>