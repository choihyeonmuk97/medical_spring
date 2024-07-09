<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>회원가입완료</title>
		<style>
			table,th,td{border:1px solid black; border-collapse: collapse; text-align: center;}
			th,td{width: 200px; height: 40px;}
		</style>
	</head>
	<body>
		<h2>회원정보 확인</h2>
		<form action="stuUpdate" method="post" name="frm">
			<input type="hidden" name="stuNo" value="${students.stuNo}">
			<input type="hidden" name="name" value="${students.name}">
			<input type="hidden" name="kor" value="${students.kor}">
			<input type="hidden" name="eng" value="${students.eng}">
			<input type="hidden" name="math" value="${students.math}">
			<input type="hidden" name="gender" value="${students.gender}">
			<input type="hidden" name="hobby" value="${students.hobby}">
			<table>
				<tr>
					<th>제목</th>
					<th>내용</th>
				</tr>
				<tr>
					<td>학번</td>
					<td>${students.stuNo}</td>
				</tr>
				<tr>
					<td>이름</td>
					<td>${students.name}</td>
				</tr>
				<tr>
					<td>국어</td>
					<td>${students.kor}</td>
				</tr>
				<tr>
					<td>영어</td>
					<td>${students.eng}</td>
				</tr>
				<tr>
					<td>수학</td>
					<td>${students.math}</td>
				</tr>
				<tr>
					<td>합계</td>
					<td>${students.total}</td>
				</tr>
				<tr>
					<td>평균</td>
					<td>${students.avg}</td>
				</tr>
				<tr>
					<td>성별</td>
					<td>${students.gender}</td>
				</tr>
				<tr>
					<td>취미</td>
					<td>${students.hobby}</td>
				</tr>
			</table>
			<input type="submit" value="수정">
		</form>
	</body>
</html>