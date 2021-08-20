<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  
  <!-- info -->
  <meta name="author" content="kade">
  <meta name="description" content="DDIT406호 여러분 어서오세요">
  
  <!-- og -->
  
  <meta property="og:type" content="website">
  <meta property="og:site_name" content="DDIT406">
  <meta property="og:title" content="DDIT406">
  <meta property="og:description" content="DDIT406호 여러분 어서오세요">
  <meta property="og:img" content="/images/link-photo.jpg">
  <meta property="og:url" content="http://adg0807.cafe24.com">

  <!-- twitter -->
  <meta property="twitter:card" content="summary">
  <meta property="twitter:site" content="DDIT406">
  <meta property="twitter:title" content="DDIT406">
  <meta property="twitter:description" content="DDIT406호 여러분 어서오세요">
  <meta property="twitter:img" content="/images/link-photo.jpg">
  <meta property="twitter:url" content="http://adg0807.cafe24.com">
  
  <link rel="shortcut icon" href="/images/favicon.ico">
  
  <title>DDIT 406</title>
  <link rel="stylesheet" href="css/sign-in.css">
  <script src="js/sign-in.js" defer></script>
</head>
<body>
<div class="body__container">
  <section class="main-banner">
  </section>
  <form class="login-frm" action="member?cmd=loginPro" method="post" name="loginFrm">
    <h3 class="login-sub-title point">로그인</h3>
    <input class="user-id" type="text" placeholder="ID" name="userId">
    <input class="user-pw" type="password" placeholder="PW" name="userPw">
    <div class="frm-inner-wrap">
<!--       <label class="check-box-label">아이디 기억하기<input class="re-check" type="checkbox"></label> -->
      <a class="forgot-pw" href="member?cmd=searchId">아이디, 비밀번호 찾기</a>
    </div>
    <button class="login-btn" type="button">로그인</button>
    <a class="go-to-sign-up" href="member?cmd=moveTosignUp">회원가입</a>
  </form>
</div>
</body>
</html>