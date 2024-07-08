<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>doMember</title>
		<style>
			table,th,td{border:1px solid black; border-collapse: collapse; text-align: center;}
			th,td{width: 200px;}
		</style>
	</head>
	<body></body>
		<h2>MEMBER</h2>
		<form action="memUpdate" method="post">
		<input type="hidden" name="id" value="${id}">
		<input type="hidden" name="pw" value="${pw}">
		<input type="hidden" name="name" value="${name}">
		<input type="hidden" name="phone" value="${phone}">
		<input type="hidden" name="gender" value="${gender}">
		<input type="hidden" name="hobby" value="${hobby}">
			<table>
				<tr>
					<th>데이터</th>
					<th>값</th>
				</tr>
				<tr>
					<td>아이디(id)</td>
					<td>${id}</td>
				</tr> 
				<tr>
					<td>비밀번호(pw)</td>
					<td>${pw}</td>
				</tr> 
				<tr>
					<td>이름(name)</td>
					<td>${name}</td>
				</tr> 
				<tr>
					<td>전화번호(phone)</td>
					<td>${phone}</td>
				</tr> 
				<tr>
					<td>성별(gender)</td>
					<td>${gender}</td>
				</tr> 
				<tr>
					<td>취미(hobby)</td>
					<td>${hobby}</td>
				</tr> 
			</table>
			<input type="submit" value="수정">		
		</form>
		
</html>