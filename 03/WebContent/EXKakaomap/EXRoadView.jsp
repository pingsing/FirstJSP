<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String lat = request.getParameter("lat");
String lon = request.getParameter("lon");

if (lon == null) {
	lat = "37.47";
	lon = "127.00";
}
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>로드뷰 도로를 이용하여 로드뷰 생성하기</title>
    <style>
    .map_wrap {overflow:hidden;height:330px}
</style>
</head>
<body>
<div class="map_wrap">
    <div id="mapWrapper" style="width:50%;height:300px;float:left">
        <div id="map" style="width:100%;height:100%"></div> <!-- 지도를 표시할 div 입니다 -->
    </div>
    <div id="rvWrapper" style="width:50%;height:300px;float:left">
        <div id="roadview" style="width:100%;height:100%"></div> <!-- 로드뷰를 표시할 div 입니다 -->
    </div>
</div>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=a36a99a67a1a2d566d6fbb93171d83f7"></script>
<script>
var mapWrapper = document.getElementById('mapWrapper'); //지도를 감싸고 있는 DIV태그

var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
    mapCenter = new kakao.maps.LatLng(<%=lat %> , <%=lon %>), // 지도의 가운데 좌표
    mapOption = {
        center: mapCenter, // 지도의 중심좌표
        level: 3 // 지도의 확대 레벨
    };

// 지도를 표시할 div와  지도 옵션으로  지도를 생성합니다
var map = new kakao.maps.Map(mapContainer, mapOption);
map.addOverlayMapTypeId(kakao.maps.MapTypeId.ROADVIEW); //지도 위에 로드뷰 도로 올리기

var rvContainer = document.getElementById('roadview'); //로드뷰를 표시할 div
var rv = new kakao.maps.Roadview(rvContainer); //로드뷰 객체
var rvClient = new kakao.maps.RoadviewClient(); //좌표로부터 로드뷰 파노ID를 가져올 로드뷰 helper객체

toggleRoadview(mapCenter);

// 마커 이미지를 생성합니다.
var markImage = new kakao.maps.MarkerImage(
    'https://t1.daumcdn.net/localimg/localimages/07/2018/pc/roadview_minimap_wk_2018.png',
    new kakao.maps.Size(26, 46),
    {
        // 스프라이트 이미지를 사용합니다.
        // 스프라이트 이미지 전체의 크기를 지정하고
        spriteSize: new kakao.maps.Size(1666, 168),
        // 사용하고 싶은 영역의 좌상단 좌표를 입력합니다.
        // background-position으로 지정하는 값이며 부호는 반대입니다.
        spriteOrigin: new kakao.maps.Point(705, 114),
        offset: new kakao.maps.Point(13, 46)
    }
);

// 드래그가 가능한 마커를 생성합니다.
var rvMarker = new kakao.maps.Marker({
    image : markImage,
    position: mapCenter,
    draggable: true,
    map: map
});

//마커에 dragend 이벤트를 할당합니다
kakao.maps.event.addListener(rvMarker, 'dragend', function(mouseEvent) {
    var position = rvMarker.getPosition(); //현재 마커가 놓인 자리의 좌표
    toggleRoadview(position); //로드뷰를 토글합니다
});

//지도에 클릭 이벤트를 할당합니다
kakao.maps.event.addListener(map, 'click', function(mouseEvent){
    
    // 현재 클릭한 부분의 좌표를 리턴 
    var position = mouseEvent.latLng; 

    rvMarker.setPosition(position);
    toggleRoadview(position); //로드뷰를 토글합니다
});

//로드뷰 toggle함수
function toggleRoadview(position){

    //전달받은 좌표(position)에 가까운 로드뷰의 panoId를 추출하여 로드뷰를 띄웁니다
    rvClient.getNearestPanoId(position, 50, function(panoId) {
        if (panoId === null) {
            rvContainer.style.display = 'none'; //로드뷰를 넣은 컨테이너를 숨깁니다
            mapWrapper.style.width = '100%';
            map.relayout();
        } else {
            mapWrapper.style.width = '50%';
            map.relayout(); //지도를 감싸고 있는 영역이 변경됨에 따라, 지도를 재배열합니다
            rvContainer.style.display = 'block'; //로드뷰를 넣은 컨테이너를 보이게합니다
            rv.setPanoId(panoId, position); //panoId를 통한 로드뷰 실행
            rv.relayout(); //로드뷰를 감싸고 있는 영역이 변경됨에 따라, 로드뷰를 재배열합니다
        }
    });
}

let url = "https://api.odcloud.kr/api/15076613/v1/uddi:df640ad3-b85f-4f9a-83d8-b33f5a70410f?page=1&perPage=10&serviceKey=UoIayEruqDUj1qdJFn5SZm9l2tCEaj6dBve5CVqhdsKVZNUkXC65%2BCb0vkl8KJaqmVxDKrkNVufyUaY%2BYtlyuA%3D%3D";

$.getJSON(url, function(data) {
	for (let i = 0; i<data.currentCount; i++) {
		// 마커가 표시될 위치입니다 
		var markerPosition  = new kakao.maps.LatLng(data.data[i].위도, data.data[i].경도); 

		// 마커를 생성합니다
		var marker = new kakao.maps.Marker({
			map: map,
			position: markerPosition
		});
		
		// 인포윈도우로 장소에 대한 설명을 표시합니다
	    var infowindow = new kakao.maps.InfoWindow({
	        content: '<div style="width:150px;text-align:center;padding:6px 0;">' + data.data[i].공원명 + '</div>'
	    });
	    infowindow.open(map, marker);

		// 마커가 지도 위에 표시되도록 설정합니다
	    map.setCenter(mapCenter);
		
		
	}
});
</script>
</body>
</html>