<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>EMAIL</title>
		<script src="http://code.jquery.com/jquery-latest.min.js"></script>
		<script >
			$(function(){
				$("#eBtn").click(function(){
					
					$.ajax({
						url:'/email_send',
						type:'post',
						data:{'name':$("#name").val(),'email':$("#email").val()},
						dataType:'text',
						success:function(data){
							alert('전송 성공');
							console.log(data);
						},
						error:function(){
							alert('전송 실패')
						}
					
					}); // ajax
				}); // click
			}); // jquery
		</script>
	</head>
	<body>
		<h2>메일 보내기</h2>
		<label>이름</label>
		<input type="text" name="name" id="name"><br>
		<label>이메일 주소</label>
		<input type="text" name="email" id="email"><br>
		<button type="button" id="eBtn">전송</button>
	</body>
</html>