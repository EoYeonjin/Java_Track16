  	//빈값 체크
  	function checkValue(obj, msg){
  		var result = false;
  		if(obj.value == ""){
  			alert(msg);
  			obj.focus();
  			result = true;
  		}
  		return result;
  	}
  	
  	//글자수 체크
  	function checkLength(obj, min, max, msg){
  		var result = false;
  		var len = obj.value.length;
  		if(len < min || len > max){
  			alert(msg+len);
  			obj.focus();
  			result = true;
  		}
  		return result;
  	}

  	//빈값과 글자수 체크
  	function checkValueLength(obj, min, max, msg1, msg2){
  		var result = false;
		
		if(obj.value==""){
			alert(msg1);
			obj.focus();
			return true;
		}
		
  		var len = obj.value.length;
  		if(len < min || len > max){
  			alert(msg2+len);
  			obj.focus();
  			result = true;
  		}
  		return result;
  	}

	//비밀번호 일치 여부 확인
  	function checkPassword(obj1, obj2){
  		var result = false;
  		if(obj1.value != obj2.value){
  			alert("비밀번호가 같지 않습니다");
  			obj2.focus();
  			result = true;
  		}
  		return result;
  	}