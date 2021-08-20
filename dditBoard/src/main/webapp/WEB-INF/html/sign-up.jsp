<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>DDIT 회원가입</title>
  <link rel="stylesheet" href="../css/sign-up.css">
  <script src="https://code.jquery.com/jquery-3.6.0.js" integrity="sha256-H+K7U5CnXl1h5ywQfKtSj8PCmoN9aaq30gDh27Xc0jk=" crossorigin="anonymous"></script>
  <script>
  	$(document).ready(function(){
  		$('#user-id').on('keyup', function(){
  			$.ajax({
  				type: 'post',
  				url: '/idCheck',
  				data: {
  					'userid' : $('#user-id').val()
  				},
  				success :
  					function(data) {
  						if(data == 1) {
  							$('.user-id-wranning').text('이미 사용중인 아이디 입니다.')
  							$('.user-id-wranning').css('color', 'red')
  						} else if(data == 0) {
  							$('.user-id-wranning').text('사용 가능한 아이디 입니다.')
  							$('.user-id-wranning').css('color', 'black')
  						} else if(data == 2) {
  							$('.user-id-wranning').text('형식에 맞게 다시 입력해 주세요.')
  							$('.user-id-wranning').css('color', 'red')
  						} else if(data == 3) {
  							$('.user-id-wranning').text('6~20자 사이로 영어, 숫자만 가능하며 특수문자는 _(언더바)만 가능합니다.')
  							$('.user-id-wranning').css('color', 'black')
  						}
	  				}
	  			})
	  		})
	  	$('.send-lisence').on('click', function(){
	  		console.log(12314)
	  		$.ajax({
	  			type: 'post',
	  			url: '/sendEmail',
	  			data: {
	  				'usermail' : $('#user-email').val()
	  			},
	  			success:
	  					alert('메일 전송')
	  		})
	  	})
	  	$('.check-lisence').on('click', function() {
	  		$.ajax({
	  			type: 'post',
	  			url: '/emailCheck',
	  			data: {
	  				'lisence' : $('.lisence').val(),
	  				'userEmail' : $('#user-email').val()
	  			},
	  			success:
	  				function(data) {
	  					if(data === "1") {
		  					alert('인증 성공')	  						
	  					} else {
	  						alert('인증 실패 다시 진행해 주세요')
	  						history.back()
	  					}
	  				}
	  		})
	  	})
  	})
  </script>
  <script src="../js/sign-up.js" defer></script>
</head>
<body>
<div class="body__container">
  <form class="sign-up-frm" name="signUpFrm" action="/member?cmd=signUpPro" method="post">
    <h1>회원가입</h1>
    <label for="user-id">아이디</label>
    <input id="user-id" class="frm-input" type="text" name="signUpId" placeholder="6~20자 사이" autocomplete="off">
    <span class="user-id-wranning">6~20자 사이로 영어, 숫자만 가능하며 특수문자는 _(언더바)만 가능합니다.</span>
    
    <label for="user-pw">비밀번호</label>
    <input id="user-pw" class="frm-input" type="password" name="signUpPw" placeholder="8~20자 사이" autocomplete="off">
    <span class="user-pw-wranning">영어 대소문자, 숫자, 특수문자를 사용하여 8~20자 사이로 입력해 주세요</span>
    
    <label for="user-pw-check">비밀번호 확인</label>
    <input id="user-pw-check" class="frm-input" type="password" placeholder="8~20자 사이" autocomplete="off" disabled>
    <span class="user-pw-ck-wranning">비밀번호를 다시 입력해 주세요.</span>

    <label for="user-name">이름</label>
    <input id="user-name" class="frm-input" type="text" name="signUpName" placeholder="이름" autocomplete="off">

	<label for="user-regno">주민번호 앞자리</label>
	<input id="user-regno" class="frm-input" type="text" name="signUpRegno" placeholder="주민번호 앞자리" autocomplete="off">

    <label for="user-email">이메일</label>
    <div class="email-wrapper">
      <input id="user-email" class="frm-input" type="email" name="signUpEmail" placeholder="이메일" autocomplete="off">
      <button class="email-btn send-lisence" type="button">인증번호 전송</button>
    </div>
    <div class="email-wrapper">
      <input type="text" class="frm-input lisence" placeholder="인증번호" autocomplete="off">
      <button class="email-btn check-lisence" type="button">인증번호 확인</button>
    </div>

    <button class="frm-submit-btn" type="button" disabled>회원가입</button>
  </form>
  <footer>
    <p>bigRoot, zeroJin, naZero, twoSton, twoPerson</p>
  </footer>
</div>
</body>
</html>