<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="<%=request.getContextPath()%>/assets/css/user.css" rel="stylesheet" type="text/css">
<title>Insert title here</title>
</head>
<body>
<div id="container">
		<jsp:include page="/WEB-INF/views/includes/header.jsp"/>
		<div id="content">
			<div id="user">

				<form id="join-form" name="joinForm" method="post" action="<%=request.getContextPath()%>/user?a=join">
					<input type='hidden' name='a' value='update'/> 
					<label class="block-label" for="name">이름</label>
					<input id="name" name="name" type="text" value="<% //vo.getName() %>">

					<h4>이메일</h4>
					
					<label class="block-label">패스워드</label>
					<input name="password" type="password" value="">
					
					<fieldset>
						<legend>성별</legend>
						여자일 경우 보여줌
						<label>여</label> <input type="radio" name="gender" value="female" checked="checked">
						<label>남</label> <input type="radio" name="gender" value="male">
						나ㅏㅁ자일 경우 보여줌
						<label>여</label> <input type="radio" name="gender" value="female" checked="checked">
						<label>남</label> <input type="radio" name="gender" value="male" checked="checked">
					</fieldset>
					
					<fieldset>
						<legend>약관동의</legend>
						<input id="agree-prov" type="checkbox" name="agreeProv" value="y">
						<label>서비스 약관에 동의합니다.</label>
					</fieldset>
					
					<input type="submit" value="가입하기">
					
				</form>
			</div>
		</div>
		<jsp:include page="/WEB-INF/views/includes/navigation.jsp"/>
		<jsp:include page="/WEB-INF/views/includes/footer.jsp"/>
	</div>
</body>
</html>