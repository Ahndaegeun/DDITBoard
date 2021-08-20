<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>아이디 비밀번호 찾기</title>
  <link rel="stylesheet" href="../css/find.css">
  <script src="../js/find.js" defer></script>
</head>
<body>
<div class="body__container">
  <div class="inner">
    <h1>ID 찾기</h1>
    <div class="form-wrapper">
      <div class="btn-group">
        <button class="id-btn select-btn">아이디 찾기</button>
        <button class="pw-btn">비밀번호 찾기</button>
      </div>

      <form class="id-frm show" method="post" action="/member?cmd=searchIdPro" name="idFrm">
        <label for="user-name">이름</label>
        <input id="user-name" type="text" name="idName">
        <label for="user-regno">주민번호</label>
        <input id="user-regno" type="text" name="idRegno">
        <button class="search-id-btn" type="button">아이디 찾기</button>
      </form>
      
      <form class="pw-frm" action="/member?cmd=resetPwPro" name="pwFrm" method="post">
        <label for="pw-user-id">아이디</label>
        <input id="pw-user-id" type="text" name="pwId">
        <label for="pw-user-name">이름</label>
        <input id="pw-user-name" type="text" name="pwName">
        <label for="pw-user-regno">주민번호</label>
        <input id="pw-user-regno" type="text" name="pwRegno">
        <button class="reset-pw-btn" type="button">비밀번호 초기화</button>  
      </form>
    </div>

  </div>
</div>
</body>
</html>