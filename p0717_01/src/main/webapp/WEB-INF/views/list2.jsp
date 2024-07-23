<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>게시판</title>
	<link
		href="https://fonts.googleapis.com/css?family=Noto+Sans+KR:400,500,700,900&display=swap&subset=korean"
		rel="stylesheet">
	<link rel="stylesheet"
		href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.12.1/css/all.min.css">
	<link rel="stylesheet" href="/css/style.css">
	<link rel="stylesheet" href="/css/notice_list.css">
	<script src="https://code.jquery.com/jquery-latest.min.js"></script>
  <script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=d892befda0f3748480b5e97a2dd60e23"></script>
</head>
<body>
	<section>
		<script>
			$(function() {
				$("#sBtn").click(function() {
					alert("검색버튼 클릭")
					$.ajax({
						url : "search_data",
						type : "post",
						data : {"keyword" : $("#keyword").val()},
						dataType : "json",
						success : function(data) {
							alert("성공");
							console.log("controller data : "+ data);
							let arr = data.response.body.items.item;
	
							let htmlData = "";

							for(let i=0;i<arr.length;i++){
	              htmlData += '<tr>';
	              htmlData += '<td><span class="table-notice">'+ arr[i].galContentId +'</span></td>';
	              htmlData += '<td class="table-title">'+ arr[i].galTitle +'</td>';
	              htmlData += '<td>'+ arr[i].galPhotographyLocation +'</td>';
	              htmlData += '<td>'+ arr[i].galPhotographer +'</td>';
	              htmlData += '<td>'+ arr[i].galModifiedtime +'</td>';
	              htmlData += '<td>';
	              htmlData += '<img style="width:20%" src="'+ arr[i].galWebImageUrl +'">';
	              htmlData += '</td>';
	              htmlData += '</tr>';
	            }

							$("#tbody").html(htmlData);
						},
						error : function() {
							alert("실패");
						}
					}); // ajax
		}); // sBtn
	}); // 쿼리
		</script>

		<h1>키워드 검색</h1>
		<div class="wrapper">
			<form action="/search" name="search" method="post">
				<select name="category" id="category">
					<option value="all">키워드</option>
					<option value="title">제목</option>
					<option value="content">내용</option>
				</select>

				<div class="title">
					<input type="text" name="keyword" id="keyword" size="16">
				</div>

				<button type="button" id="sBtn">
					<i class="fas fa-search"></i>
				</button>
			</form>
		</div>

		<!-- 카카오맵 -->
		<!-- 지도를 표시할 div 입니다 -->
		<div id="mapBody">
			<div id="map" style="width: 100%; height: 350px;"></div>
		</div>
		
		
		
		<p>
			<em>지도</em>
		</p>
		<div id="clickLatlng"></div>

		<table>
			<colgroup>
				<col width="15%">
				<col width="*%">
				<col width="15%">
				<col width="15%">
				<col width="15%">
				<col width="15%">
			</colgroup>
			<!-- 제목부분 -->
			<tr>
				<th>사진번호</th>
				<th>제목</th>
				<th>위치</th>
				<th>작가</th>
				<th>촬영일</th>
				<th>이미지</th>
			</tr>
			<!-- 내용부분 -->
			<tbody id="tbody">

			</tbody>

		</table>

		<ul class="page-num">
			<li class="first"></li>
			<li class="prev"></li>
			<li class="num"><div>1</div></li>
			<li class="next"></li>
			<li class="last"></li>
		</ul>

		<a href="write_view.jsp"><div class="write">쓰기</div></a>
	</section>
	<script type="text/javascript">
		var mapContainer = document.getElementById('map'), // 지도를 표시할 div
		mapOption = {
			center : new kakao.maps.LatLng(33.450701, 126.570667), // 지도의 중심좌표
			level : 3
		// 지도의 확대 레벨
		};

		//지도를 생성합니다    
		var map = new kakao.maps.Map(mapContainer, mapOption);

		//주소-좌표 변환 객체를 생성합니다
		var geocoder = new kakao.maps.services.Geocoder();

		//주소로 좌표를 검색합니다
		geocoder.addressSearch('제주특별자치도 제주시 첨단로 242',	function(result, status) {
			// 정상적으로 검색이 완료됐으면 
			if (status === kakao.maps.services.Status.OK) {
	
				var coords = new kakao.maps.LatLng(result[0].y,
						result[0].x);
	
				// 결과값으로 받은 위치를 마커로 표시합니다
				var marker = new kakao.maps.Marker({
					map : map,
					position : coords
				});
	
				// 인포윈도우로 장소에 대한 설명을 표시합니다
				var infowindow = new kakao.maps.InfoWindow(
						{
							content : '<div style="width:150px;text-align:center;padding:6px 0;">우리회사</div>'
						});
				infowindow.open(map, marker);
	
				// 지도의 중심을 결과값으로 받은 위치로 이동시킵니다
				map.setCenter(coords);
			}
		});
	</script>


</body>
</html>