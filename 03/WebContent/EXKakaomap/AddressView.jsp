<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>주소로 장소 표시하기</title>
</head>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<body>
<p style="margin-top:-12px">
    <em class="link">
        <a href="javascript:void(0);" onclick="window.open('http://fiy.daum.net/fiy/map/CsGeneral.daum', '_blank', 'width=981, height=650')">
            혹시 주소 결과가 잘못 나오는 경우에는 여기에 제보해주세요.
        </a>
    </em>
</p>
<div id="map" style="width:100%;height:350px;"></div>

<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=a36a99a67a1a2d566d6fbb93171d83f7&libraries=services"></script>
<script>
var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
    mapOption = {
        center: new kakao.maps.LatLng(33.450701, 126.570667), // 지도의 중심좌표
        level: 3 // 지도의 확대 레벨
    };  

// 지도를 생성합니다    
var map = new kakao.maps.Map(mapContainer, mapOption); 

// 주소-좌표 변환 객체를 생성합니다
var geocoder = new kakao.maps.services.Geocoder();

let url = "https://api.odcloud.kr/api/15051494/v1/uddi:65a2cf4b-158c-4c5f-8475-c50f28b24ead?page=1&perPage=10&serviceKey=UoIayEruqDUj1qdJFn5SZm9l2tCEaj6dBve5CVqhdsKVZNUkXC65%2BCb0vkl8KJaqmVxDKrkNVufyUaY%2BYtlyuA%3D%3D";

$.getJSON(url, function(data){
	for(let i = 0; i<data.currentCount; i++) {
		
		let txt = "<tr>" +
		"	<td>" + data.data[i].연번 + '</td>' +
		"	<td>" + data.data[i].약국명칭 + '</td>' +
		"	<td>" + data.data[i].지번주소 + '</td>' +
		"	<td>" + data.data[i].전화번호 + '</td>' +
		"</tr>";
		$("table").append(txt);
		
		// 주소로 좌표를 검색합니다
		geocoder.addressSearch(data.data[i].지번주소, function(result, status) {

		     // 정상적으로 검색이 완료됐으면 
		     if (status === kakao.maps.services.Status.OK) {

		        var coords = new kakao.maps.LatLng(result[0].y, result[0].x);

		        // 결과값으로 받은 위치를 마커로 표시합니다
		        var marker = new kakao.maps.Marker({
		            map: map,
		            position: coords
		        });

		        // 인포윈도우로 장소에 대한 설명을 표시합니다
		        var infowindow = new kakao.maps.InfoWindow({
		            content: '<div style="width:150px;text-align:center;padding:6px 0;">' + data.data[i].약국명칭 + '</div>'
		        });
		        infowindow.open(map, marker);

		        // 지도의 중심을 결과값으로 받은 위치로 이동시킵니다
		        map.setCenter(coords);
		    } 
		});
	}
});
</script>

<table border= "1">
	<tr>
		<td>번호</td>
		<td>약국명칭</td>
		<td>소재지</td>
		<td>전화번호</td>
	</tr>
</table>
</body>
</html>