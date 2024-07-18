<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>chart</title>
		<script src="http://code.jquery.com/jquery-latest.min.js"></script>
		<script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
		<style>
			#main{border:3px solid black; width:70%; height: 550px; margin:20px auto;}
		</style>
		<script>
			$(function(){
				
				let ch_label;
				let ch_data; 
				
				$("#btn").click(function(){
					alert("start");
					
					// 차트 초기화 
					let chartStatus = Chart.getChart('myChart');
					if(chartStatus != undefined){
						chartStatus.destroy();
					}
					
					ch_label=[];
					ch_data=[];
					
					$.ajax({
						url:"/chart_data",
						type:"post",
						data:{"data":$("#data").val()},
						dataType:"json",
						success:function(data){
							alert("불러오기 성공");
							console.log("data : "+data);
							
							var condition = 'period'; // 조건에 따라 라벨이 달라지게 하기 위한 선언
							data.forEach(function(d){
								console.log(d.region);
								console.log(d.period);
								console.log(d.amt);
								if(d.period == null) ch_label.push(d.region);
								else ch_label.push(d.period);
								ch_data.push(d.amt);
								
								if(!d.period){
									condition = 'region';
								}
								
							});

							const ctx = document.getElementById('myChart');
							
							
						  	new Chart(ctx, {
							    type: 'bar',
							    data: {
							      labels: ch_label,
							      datasets: [{
							    	label: condition === 'period'? '기간별 대출액':'지역별 대출액',
							        data: ch_data,
							        backgroundColor: [
							            'rgba(255, 99, 132, 0.2)',
							            'rgba(255, 159, 64, 0.2)',
							            'rgba(255, 205, 86, 0.2)',
							            'rgba(75, 192, 192, 0.2)',
							            'rgba(54, 162, 235, 0.2)',
							            'rgba(153, 102, 255, 0.2)',
							            'rgba(201, 203, 207, 0.2)'
							          ],
							        borderColor: [
							            'rgb(255, 99, 132)',
							            'rgb(255, 159, 64)',
							            'rgb(255, 205, 86)',
							            'rgb(75, 192, 192)',
							            'rgb(54, 162, 235)',
							            'rgb(153, 102, 255)',
							            'rgb(201, 203, 207)'
							          ],
							        borderWidth: 1
							      }]
							    },
							    options: {
							      scales: {
							        y: {
							          beginAtZero: true
							        }
							      }
							    }
							  }); // 차트 생성
							
						},
						error:function(){
							alert("불러오기 실패");
						}
					}); // ajax
					
					
				}); // btn
			}); // jquery
		</script>
	</head>
	<body>
		<h2>차트그리기</h2>
		<label>데이터</label>
		<select name="data" id="data">
			<option value="region">지역별</option>
			<option value="period">기간별</option>
		</select>
		<button type="button" id="btn">검색</button>
		<br><br> 
		<div id="main">
			<canvas id="myChart"></canvas>
		</div>
		<script>
		  
		</script>
	</body>
</html>