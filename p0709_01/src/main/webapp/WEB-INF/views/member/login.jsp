<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>로그인</title>
	</head>
	<body>
		<h2>로그인</h2>
		<form action="doLogin" name="lfrm" method="post">
			<label>ID</label>
			<input type="text" name="id"><br>
			<label>PW</label>
			<input type="password" name="pw"><br>
			<label>PageNo</label>
			<input type="text" name="pno"><br>
			<input type="submit" value="로그인">
			
		</form>
	</body>
</html>