<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link
	href="${pageContext.servletContext.contextPath }/assets/css/board.css"
	rel="stylesheet" type="text/css">
</head>
<body>
	<div id="container">
		<c:import url="/WEB-INF/views/includes/header.jsp" />
		<div id="content">
			<div id="board">
				<form id="search_form" action="${pageContext.request.contextPath }/board?a=search" method="post">
					<input type="text" id="kwd" name="kwd" value=""> <input
						type="submit" value="찾기">
				</form>
				<table class="tbl-ex">
					<tr>
						<th>번호</th>
						<th>제목</th>
						<th>글쓴이</th>
						<th>조회수</th>
						<th>작성일</th>
						<th>&nbsp;</th>
					</tr>
					<c:set var="count" value="${fn:length(list) }" />
					<c:forEach items='${list}' var='vo' varStatus="status">
						<tr>
							<td>${count - status.index}</td>
							<td style='text-align:left; padding-left:${(vo.depth-1)*20}px'>
								<c:if test="${vo.depth > 1 }">
									<img
										src='${pageContext.servletContext.contextPath }/assets/images/reply.png' />
								</c:if> <a
								href="${pageContext.request.contextPath }/board?a=viewform&no=${vo.getNo()}">${vo.getTitle() }</a>
							</td>
							<td>${vo.getUserName() }</td>
							<td>${vo.getHit() }</td>
							<td>${vo.getRegDate() }</td>
							<c:if test="${authUser.getNo() == vo.getUserNo() }">
								<td><a
									href="${pageContext.request.contextPath }/board?a=deleteform&no=${vo.getNo()}"
									class="del">삭제</a></td>
							</c:if>
						</tr>
					</c:forEach>
				</table>

				<!-- pager 추가 -->
				<div class="pager">
				<c:choose>
				<c:when test="${pagename eq 'search'}">
					<c:set var="action" value="search"/>
				</c:when>
				<c:otherwise>
					<c:set var="action" value="list"/>
				</c:otherwise>
				</c:choose>
					<ul>
							<li>
								<c:if test="${p != 1 }">
									<a href="${pageContext.request.contextPath }/board?a=${action }&p=${p-1}">◀</a>
								</c:if>
							</li>
							<c:forEach begin='1' end='${maxPage }' var='page' step='1'>
								<c:choose>
									<c:when test="${p eq page }">
										<li class="selected"><a href="${pageContext.request.contextPath }/board?a=${action }&p=${page}">${page }</a></li>
									</c:when>
									<c:otherwise>
										<li><a href="${pageContext.request.contextPath }/board?a=${action }&p=${page}">${page }</a></li>
									</c:otherwise>
								</c:choose>
							</c:forEach>
						
							<li>
								<c:if test="${ p < maxPage }">
									<a href="${pageContext.request.contextPath }/board?a=list&p=${p+1}">▶</a>
								</c:if>
							</li>
					</ul>
				</div>
				<!-- pager 추가 -->

				<c:if test="${not empty authUser }">
					<div class="bottom">
						<a href="${pageContext.request.contextPath }/board?a=addform"
							id="new-book">글쓰기</a>
					</div>
				</c:if>
			</div>
		</div>
		<c:import url="/WEB-INF/views/includes/navigation.jsp" />
		<c:import url="/WEB-INF/views/includes/footer.jsp" />
	</div>
</body>
</html>