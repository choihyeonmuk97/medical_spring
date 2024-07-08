<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>member</title>
	</head>
	<body>
		<h2>member</h2>
		<form action="doMember" name="frm" method="post">
		<label>id</label>
		<input type="text" name="id"><br>
		<label>pw</label>
		<input type="text" name="pw"><br>
		<label>name</label>
		<input type="text" name="name"><br>
		<label>phone</label>
		<input type="text" name="phone"><br>
		<label>gender</label><br>
		<input type="radio" name="gender" value="male" id="male">
		<label for="male">남자</label>
		<input type="radio" name="gender" value="female" id="female">
		<label for="female">여자</label>
		<br>
		<label>취미</label><br>
		<input type="checkbox" name="hobby" value="game" id="game">
		<label for = "game">게임</label>
		<input type="checkbox" name="hobby" value="run" id="run">
		<label for = "run">조깅</label>
		<input type="checkbox" name="hobby" value="book" id="book">
		<label for = "book">독서</label>
		<input type="checkbox" name="hobby" value="golf" id="golf">
		<label for = "golf">골프</label>
		<input type="checkbox" name="hobby" value="swim" id="swim">
		<label for = "swim">수영</label>
		<br>
		<input type="submit" value="전송">
		</form>
	</body>
</html>