/* 회원정보의 유효성 판단처리 */

var member = {
	//키보드입력시 입력값 태그별 상태확인
	tag_status: function( tag ){
		var name = tag.attr('name');
		if( name=='pw' )  return this.pw_status(tag.val());
		else if( name=='pw_chk' ) return this.pw_chk_status( tag.val() );
		else if(name == 'email') return this.email_status(tag.val());
		else if(name == 'name') return this.name_status(tag.val());
		else if(name == 'b_num') return this.b_num_status(tag.val());
	},

	//사업자번호 상태값 확인
	b_num_status: function(b_num) {
		var reg = /([0-9]{3})-?([0-9]{2})-?([0-9]{5})/;
		if(b_num =="") return this.common.empty;
		else if (b_num.match(this.space)) return this.common.space;
		else if(!reg.test(b_num))  return this.b_num.invalid;
		else if ( b_num.length < 5 )   return this.common.min;
		else if ( b_num.length < 12 )  return this.common.max;
		else if(b_num.length = 12)         return this.b_num.valid;
	},
	
	//사업자번호에만 적용될 값을 선언
	b_num : {
		usable: {code: 'valid', desc: '입점신청 가능한 사업자번호입니다'},
		unusable: {code: 'invalid', desc: '이미 등록된 사업자번호입니다'},
		invalid : {code: 'invalid', desc: '숫자 10자를 바르게 입력하세요'},
		valid : { code: 'valid', desc : '확인버튼을 눌러주세요' }
	},

	//이메일 상태값 확인
	email_status: function(email){
		var reg = /^[a-zA-Z0-9+-\_.]+@[a-zA-Z0-9-]+\.[a-zA-Z0-9-.]+$/;		
		if( email=='' )					return this.common.empty;
		else if( reg.test(email) )		return this.email.valid;
		else							return this.email.invalid;
	},
	
	//아이디 중복확인 상태 확인
	email_check: function(usable){
		//존재하면 true :이미 사용 중, 존재하지 않으면 false : 사용 가능
		if(usable)	return this.email.unusable;
		else	return this.email.usable;
	},
	
	email: {
		unusable: {code: 'invalid', desc:'이미 사용 중인 이메일입니다.'},
		usable: {code: 'valid', desc:'사용 가능한 이메일입니다.'},
		invalid: {code: 'invalid', desc:'사용할 수 없는 이메일입니다'},
		valid: {code: 'valid', desc:'이메일 중복확인을 해주세요.'}
	},
	
	//비밀번호확인 입력값 상태확인: 비밀번호와 입력값이 동일한지
	pw_chk_status: function(pw_chk){
		if( pw_chk=='' )							return this.common.empty;
		else if( pw_chk==$('[name=pw]').val() ) 	return this.pw.equal;
		else									return this.pw.notEqual;
	},
	
	//비밀번호입력값 상태확인: 영문 대.소문자, 숫자를 모두 포함
	pw_status: function( pw ){
		var reg = /[^a-zA-Z0-9]/g, upper=/[A-Z]/g, lower=/[a-z]/g, digit=/[0-9]/g;
		if( pw=='' )						return this.common.empty;
		else if( pw.match(this.space) )		return this.common.space;
		else if( reg.test(pw) )				return this.pw.invalid;
		else if( pw.length<5 )				return this.common.min;
		else if( pw.length>10 )				return this.common.max;
		else if( !upper.test(pw) || !lower.test(pw) || !digit.test(pw) )			
											return this.pw.lack;
		else								return this.pw.valid;
	},
	
	//비밀번호확인 입력값 상태확인: 비밀번호와 입력값이 동일한지
	pw_chk_status: function(pw_chk){
		if( pw_chk=='' )								return this.common.empty;
		else if( pw_chk==$('[name=pw]').val() ) 	return this.pw.equal;
		else									return this.pw.notEqual;
	},
	
	//비밀번호관련 상태값
	pw: {
		equal: { code:'valid', desc:'비밀번호가 일치' },
		notEqual: { code:'invalid', desc:'비밀번호가 일치하지 않습니다' },
		valid: { code:'valid', desc:'사용가능한 비밀번호' },
		invalid: { code:'invalid', desc:'영문 대/소문자,숫자만 입력' },
		lack: { code:'invalid', desc:'영문 대/소문자,숫자를 모두 포함'}
	},
	
	
	//이름 입력값 상태확인
	name_status: function(name) {
		var reg = /^[가-힣]{2,5}$/;
		var data = name ;
		if(data == '') return this.common.empty;
		else if(data.match(this.space)) return this.common.space;
		else if(!reg.test(data)) return this.name.invalid;
		else if(data.length<2) return this.name.min;
		else if(data.length>5) return this.name.max;
		else return this.name.valid;
	},
	
	//이름 관련 상태값
	name: {
		min: { code: 'invalid', desc: '2자 이상 입력해주세요'},
		max: { code: 'invalid', desc: '4자 이하 입력해주세요'},
		invalid: { code: 'invalid', desc: '한글 2자~4자로 입력해주세요'},
		valid: { code: 'valid', desc: '사용 가능한 이름'}
	},
	
	space: /\s/g,
	
	//공통사용 상태값
	common: {
		empty: { code:'invalid', desc:'입력하세요' },
		space: { code:'invalid', desc:'공백없이 입력하세요' },
		min: { code:'invalid', desc:'5자이상 입력하세요' },
		max: { code:'invalid', desc:'10자이내 입력하세요' }
	}
} 