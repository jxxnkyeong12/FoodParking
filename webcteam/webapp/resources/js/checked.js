/**
 * 
 */
 
 //입력항목 입력여부 확인
function emptyCheck(){
	var ok = true;
	$('.chk').each(function(){
		if( $(this).val()=='' ){
			var item = $(this).attr('placeholder');
			item = item ? item : $(this).attr('title');
			alert(item + ' 입력하세요!');
			$(this).focus();
			ok = false;
			return ok;
		}	
	});
	return ok;
}

 
 
 
 var b_num_chk = {
	tag_status : function( tag ) {
		var name = tag.attr('name');
			 if ( name == 'b_num') return this.b_num_status( tag.val() );
			 else if ( name == 'b_name') return this.b_name_status( tag.val() );
			 else if ( name == 'store_name') return this.store_name_status( tag.val() );
			 else if ( name == 'b_phone') return this.b_phone_status( tag.val() );
			 else if (name == 'store_addr') return this.store_addr_status(tag.val());
			
			 else if (name == 'file') return this.file_status(tag.val());
			 else if (name == 'file2') return this.file2_status(tag.val());
		},
	
	
	//파일 있냐 없냐만
	file_status: function(file){
		if(file == "") return this.common.empty;
		else return this.b_name.valid;
	},

    //파일 있냐 없냐만
	file2_status: function(file2){
		if(file2 == "") return this.common.empty;
		else return this.b_name.valid;
	},


	//가게명 유효성 검사
	store_name_status: function(store_name) {
		if(store_name =="") return this.common.empty;
		else return this.b_name.valid;
	},
	
	// 사업자 중복확인 상태를 판단할 함수 추가
	b_num_check: function(usable) {
		//존재하는 경우 : 1 (true -> 이미 사용중), 아니면 0 (false-> 사용가능) 
		if( usable )      return this.b_num.unusable;
		else 			    return this.b_num.usable;
		
	},
	
	//주소 확인
	store_addr_status : function(store_addr) {
		if(store_addr =="") return this.common.empty;
		else return this.store_addr.valid;
	},

		
   //전화번호 
    b_phone_status : function(b_phone){
	if(b_phone =="") return this.common.empty;
	else return this.phone.valid;
	},
	
	//이름 확인하려고
	b_name_status: function(b_name){
		var reg = /^[가-힣]{2,}$/;
		if(b_name =="") return this.common.empty;
		else if (b_name.match(this.space)) return this.common.space;
		else if(!reg.test(b_name))  return this.b_name.noen;
		else if ( b_name.length < 2 )   return this.common.min;
		else if (b_name.length  > 7)  return this.common.max;
		else   return this.b_name.valid;
	},
	
	
	
	
	//사업자등록번호 확인하려고
	b_num_status : function(id){
		var reg = /([0-9]{3})-?([0-9]{2})-?([0-9]{5})/;
		if(id =="") return this.common.empty;
		else if (id.match(this.space)) return this.common.space;
		else if(!reg.test(id))  return this.b_num.invalid;
		else if ( id.length < 5 )   return this.common.min;
	   else if ( id.length < 12 )  return this.common.max;
	    else 	if(id.length = 12)			return this.b_num.valid;
	},
	
	
	
	//주소에 사용하려고
	store_addr : {
	    invalid : {code: 'invalid', desc: '주소를 입력해주세요'},
		valid : {code: 'valid', desc : '1231231321'}
	},
	
	
	//사업자번호에만 적용될 값을 선언
	b_num : {
		usable: {code: 'valid', desc: '입점신청 가능한 사업자번호입니다'},
		unusable: {code: 'invalid', desc: '이미 입점한 사업자번호입니다'},
		invalid : {code: 'invalid', desc: '숫자 10자를 바르게 입력하세요'},
		valid : { code: 'valid', desc : '확인버튼을 눌러주세요' }
	},
	
	
	
	//이름 적합한지 사용될 값
	b_name : {
	   noen : { code : 'invalid', desc : '한글로만 입력하세요' },
	   empty: { code : 'invalid', desc : '대표자명을 입력하세요' },
	   min : {code: 'invalid', desc: '한글 2글자 이상 입력하세요'},
	   valid : { code: 'valid', desc : '111' }
	},
	
	phone : {
		invalid : {code: 'invalid', desc: '숫자를 바르게 입력하세요'},
		 valid : { code: 'valid', desc : '111' }
	},
	
	
	
	
		space: /\s/g,
	
	//공통사용 상태값
	common: {
		empty: { code : 'invalid', desc : '입력하세요' },
		space : {code : 'invalid', desc : '공백없이 입력하세요'},
		min: { code: 'invalid', desc : '5자이상 입력하세요'},
		max: { code: 'invalid', desc : '10자이내 입력하세요'}
	}
	
	
}