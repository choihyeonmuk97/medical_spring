<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>메인페이지</title>
	</head>
	<body>
		<h1>메인 페이지</h1>
		<c:if test="${session_id == null}">
			<h2>로그인 하세요.</h2>
			<form>
				<label>아이디</label>
				<input type="text" name="id" id="id"><br>
				<label>비밀번호</label>
				<input type="text" name="pw" id="pw"><br>
		</form>
		<br>
			<a href="https://kauth.kakao.com/oauth/authorize?response_type=code&client_id=0eaa4e7d89428e4883521e1d0ce6f078&redirect_uri=http://localhost:8181/kakao/oauth">
				<img src="/images/kakao_login_medium_narrow.png">
			</a>
		</c:if>
		<c:if test="${session_id != null}">
			<h2>${session_nickName}님 환영합니다!</h2>
			<a href="https://kauth.kakao.com/oauth/logout?client_id=0eaa4e7d89428e4883521e1d0ce6f078&logout_redirect_uri=http://localhost:8181/kakao/logout">
				로그아웃
			</a>
		</c:if>
		<br>
	</body>
</html>