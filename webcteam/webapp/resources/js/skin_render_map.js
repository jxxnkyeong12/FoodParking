/**
 * 지도 블록 렌더링
 * @param el 타겟 블록
 */
 function moduleMapRender(el) {
    if(MAP_CONFIG.map_type == "api") {
        var lat = 37.368041;
        var lng = 127.101324;
        var tit = "";
        var id = null;
        var seq = el.attr("data-map-address-seq");   
        var src = el.find("iframe").attr("src");
        var parentEl = el.parent();

        parentEl.hasClass("skin_block") ? id = parentEl.attr("data-module-id") + "_map" : id = parentEl.attr("data-minimodule-id") + "_map";

        el.find(".resp_googlemap_inner").html('<div id="'+ id +'"></div>');        

        /*
        if(src) {                
            lat = src.split("!3d")[1].split("!")[0];
            lng = src.split("!2d")[1].split("!3d")[0];    
            mapDraw(id, lat, lng, tit)       
        }
        */

        if(seq > 0){               
            $.get('/map/' + seq , [], function (res) {                      
                lat = res.map_address.map_lat;
                lng = res.map_address.map_lng;
                tit = JSON.parse(res.map_address.map_info).map_title;
                mapDraw(id, lat, lng, tit)
            })
        }      
    }  
}

/**
 * 화면에 지도 그리기
 * @param id container tag id
 * @param id lat 경도
 * @param id lng 위도
 * @param id 마커 표시 문구
 */
 function mapDraw(id, lat, lng, tit) { 
    var marker = null;

    switch(MAP_CONFIG.map_service_type) {
        case "kakao":           
            kakaoMap.init(id, MAP_CONFIG.map_feature);
            if(tit) kakaoMap.setInfoWindow(tit); 
            marker = kakaoMap.setMarker(lat, lng, false);           
            if(tit) kakaoMap.openInfoWindow(marker);
        break
        case "naver":
            naverMap.init(id, MAP_CONFIG.map_feature);
            if(tit) naverMap.setInfoWindow(tit); 
            marker = naverMap.setMarker(lat, lng, false);
            if(tit) naverMap.openInfoWindow(marker);
        break
        case "google":
            googleMap.init(id, MAP_CONFIG.map_feature);
            if(tit) googleMap.setInfoWindow(tit); 
            marker = googleMap.setMarker(lat, lng, false);
            if(tit) googleMap.openInfoWindow(marker);
        break
    }
}