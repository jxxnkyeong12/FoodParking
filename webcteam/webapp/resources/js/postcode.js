/**
 * DAUM 주소 API 반환값을 addresses 데이터 저장용으로 반환한다.
 * @param {object} data
 */
function getAddress(data) {
    var address = {
        'zipcode' : '',
        'state' : '',
        'city' : '',
        'onlyStreet' : '',
        'street' : '',
        'streetFull' : '',
    };
    if(typeof data.zonecode === 'string') {
        address['zipcode'] = data.zonecode;
    }
    if(typeof data.sido === 'string') {
        address['state'] = data.sido;
    }
    if(typeof data.sigungu === 'string') {
        address['city'] = data.sigungu;
    }

    if(typeof data.sido === 'string' && typeof data.sigungu  === 'string' && typeof data.roadAddress  === 'string') {
        var tmp = data.roadAddress.replace(data.sido, "");
        tmp = tmp.replace(data.sigungu, "");
        address['onlyStreet'] = tmp.trim();
    }
    
    address['street'] = address['onlyStreet']; 
    if(typeof data.buildingName === 'string' && data.buildingName != '') {
        address['street'] = address['street'] + " (" + data.buildingName + ")";
    }
    
    address['streetFull'] = address['state'] + " " + address['city'] + " " + address['street'];

    return address;

}
