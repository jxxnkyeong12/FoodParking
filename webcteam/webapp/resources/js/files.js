/**
 * 
 */
 
 $(function(){

//파일선택 첨부하기
	$('.attach-file').change(function(){
		var filename = '';
		for(var idx=0; idx<this.files.length; idx++){
    		filename += (filename=='' ? '' : ',') + this.files[idx].name
    	}

		var attached = this.files[0];
		//console.log(attached )
		//선택한 파일이 있는 경우에만 처리
		if( attached ){
			$('.file-name').text( filename );		//선택한 파일명 보이게
			$('.delete-file').css('display', 'inline'); //삭제버튼 보이게
			//이미지 보여질 태그가 있는 경우에 처리
			if( $('#preview').length > 0 ){
				//선택한 파일이 이미지파일인 경우 보여지게
				if( isImage(attached.name) ){
					//이미지태그를 만들어 처리
					$('#preview').html( '<img>' );
					//선택할 파일정보를 읽어서 이미지태그의 src로 지정
					var reader = new FileReader();
					reader.onload = function( e ){
						$('#preview img').attr('src', e.target.result );
					}
					reader.readAsDataURL( attached );
				}else{
					$('#preview').html('');					
				}
			}
		}else{
			$('.delete-file').css('display', 'none');
			$('#preview').html('');		
			
		}
	});
	
	
	$('.delete-file').click(function(){
		$('.file-name').text(''); //선택한 파일명 없애기
		$('.attach-file').val(''); //실제 첨부된 파일정보 삭제
		$(this).css('display', 'none'); //삭제버튼 안보이게
		$('#preview').html(''); //보여지고 있는 이미지태그 없애기
		$('.file-name').text('파일 업로드 ');		
	});


$('.attach-file2').change(function(){
		var filename = '';
		for(var idx=0; idx<this.files.length; idx++){
    		filename += (filename=='' ? '' : ',') + this.files[idx].name
    	}

		var attached = this.files[0];
		//console.log(attached )
		//선택한 파일이 있는 경우에만 처리
		if( attached ){
			$('.file-name2').text( filename );		//선택한 파일명 보이게
			$('.delete-file2').css('display', 'inline'); //삭제버튼 보이게
			//이미지 보여질 태그가 있는 경우에 처리
			if( $('#preview').length > 0 ){
				//선택한 파일이 이미지파일인 경우 보여지게
				if( isImage(attached.name) ){
					//이미지태그를 만들어 처리
					$('#preview').html( '<img>' );
					//선택할 파일정보를 읽어서 이미지태그의 src로 지정
					var reader = new FileReader();
					reader.onload = function( e ){
						$('#preview img').attr('src', e.target.result );
					}
					reader.readAsDataURL( attached );
				}else{
					$('#preview').html('');					
				}
			}
		}else{
			$('.delete-file2').css('display', 'none');
			$('#preview').html('');		
			
		}
	});
	
	
	$('.delete-file2').click(function(){
		$('.file-name2').text(''); //선택한 파일명 없애기
		$('.attach-file2').val(''); //실제 첨부된 파일정보 삭제
		$(this).css('display', 'none'); //삭제버튼 안보이게
		$('#preview').html(''); //보여지고 있는 이미지태그 없애기
		$('.file-name2').text('파일 업로드 ');		
	});

	
});


