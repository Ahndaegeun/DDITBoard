<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<!-- 
  안대근
  유영진
  이준석
  이인환
  이나영
-->
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>DDIT 406</title>
  <link rel="stylesheet" href="../css/index.css">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
  <script src="https://code.jquery.com/jquery-3.6.0.js" integrity="sha256-H+K7U5CnXl1h5ywQfKtSj8PCmoN9aaq30gDh27Xc0jk=" crossorigin="anonymous"></script>
  <script src="../js/index.js" defer></script>
</head>
<body>

<c:if test="${empty user }">
	<script>
	alert('로그인 후 이용 가능합니다.')
	location = '../index.jsp'
	</script>
</c:if>

<div class="body__container">
  <div class="user-info size1024">
    <img class="user-info-img" src="../storage/${userImg }" alt="user-img">
    <div class="user-name">${user.memId }</div>
    <button class="user-modify-btn">내 정보 수정</button>
    <a href="/member?cmd=logout">로그아웃</a>
    <ul class="user-modify-info">
      <li class="user-id">아이디 : <span>${user.memNm }</span></li>
      <li class="user-email">이메일 : <span>${user.memEmail }</span></li>
      <li class="count-contents">내가 작성한 글 : <span>${count[0] }</span></li>
      <li class="count-comment">내가 작성한 댓글 : <span>${count[1] }</span></li>
<%--       <li class="count-like">내가 받은 좋아요 : <span>${count[2] }</span></li> --%>
<%--       <li class="count-hate">내가 받은 싫어요 : <span>${count[3] }</span></li> --%>
      <li><hr></li>
      <li class="user-img-modify">
        <form class="modify-img-frm" action="/member?cmd=updateImgPro" method="post" enctype="multipart/form-data">
          <div class="modify-title">프로필 이미지 변경</div>
          <div class="file-wrapper">
            <input class="user-img-file" type="file" name="changeImg" accept="image/*">
            <button type="button" class="user-img-btn">변경</button>
          </div>
        </form>
      </li>
      <li><hr></li>
      <li>
        <div class="change-title">비밀번호 변경</div>
        <form class="modify-pw-frm" action="/member?cmd=changePwPro" name="changeFrm" method="post">
          <input type="password" autocomplete="false" name="changePw">
          <button class="modify-pw-btn">비밀번호 변경</button>
        </form>
        <div class="pw-text">8~20자 사이 영어 대소문자, 특수문자 포함</div>
      </li>
    </ul>
  </div>
  <main>
    <ul class="board-list">
      <li class="write-contents">
        <div class="write-title">글쓰기</div>
        <div class="upload-img-wrap">
<!--           <label for="upload-ing"><i class="fa fa-file-image-o"></i></label> -->
<!--           <input id="upload-img" type="file" form="write-contents-frm"> -->
		  <label for="write-title">제목</label>
		  <input id="write-title" type="text" form="write-contents-frm" name="writeTitle">
          <label for="anonymity">익명</label>
          <input type="checkbox" id="anonymity" form="write-contents-frm" name="writeAnon">
        </div>
        <form id="write-contents-frm" action="/board?cmd=writePro" name="writeFrm" method="post">
          <textarea class="user-textarea" name="writeArea"></textarea>
          <button class="write-btn" type="button">작성</button>
        </form>
      </li>
      
<%--       <c:forEach items="${boardList }" var="contents"> --%>
<!--       <li class="card"> -->
<%--         <c:if test="${contents.boardAnon == 'Y' && user.memId != 'admin'}"> --%>
<!--       	<img class="user-img" src="../storage/anon.jpg" alt="people"> -->
<%--       	</c:if> --%>
<%--         <c:if test="${contents.boardAnon == 'N' || user.memId == 'admin'}"> --%>
<%--       	<img class="user-img" src="../storage/${contents.memId}.PNG" alt="people"> --%>
<%--       	</c:if> --%>
<!--         <div class="wrapper"> -->
<%--           <c:if test="${contents.boardAnon == 'Y' && user.memId != 'admin'}"> --%>
<!--           <div class="author"> -->
<!--           	<span>익명</span> -->
<%--           	<c:if test="${contents.memId == user.memId || user.memId == 'admin'}"> --%>
<%--           		<a href="/board?cmd=boardDelete&boardNum=${contents.boardIdx }">삭제</a> --%>
<%--           	</c:if> --%>
<!--           </div> -->
<%--           </c:if> --%>
<%--           <c:if test="${contents.boardAnon == 'N' || user.memId == 'admin'}"> --%>
<!--           <div class="author"> -->
<%--           	<span>${contents.memId }</span> --%>
<%--           	<c:if test="${contents.memId == user.memId || user.memId == 'admin'}"> --%>
<%--           		<a href="/board?cmd=boardDelete&boardNum=${contents.boardIdx }">삭제</a> --%>
<%--           	</c:if> --%>
<!--           </div> -->
<%--           </c:if> --%>
<%--           <div class="title">${contents.boardTitle }</div> --%>
<%--           <div class="write-date">${contents.boardDate }</div> --%>
<%--           <p class="content">${contents.boardContent }</p> --%>
<!--         </div> -->
<!--         <div class="card-btn"> -->
<%-- <%--           <button class="like-btn"><i class="fa fa-thumbs-o-up"></i>${contents.boardLike } 좋아요</button> --%>
<%-- <%--           <button class="hate-btn"><i class="fa fa-thumbs-o-down"></i>${contents.boardHate } 싫어요</button> --%>
<!--           <button class="comment-btn"><i class="fa fa-commenting-o"></i> -->
<%--           	<c:set var="loop_flag" value="0"/> --%>
<%--           	<c:forEach items="${commCount }" var="comCnt"> --%>
<%--           		<c:if test="${comCnt.key == contents.boardIdx }"> --%>
<%--           			<c:if test="${comCnt.value > 0 }"> --%>
<%-- 	          			<c:set var="loop_flag" value="${comCnt.value }"/>          			 --%>
<%--           			</c:if> --%>
<%--           		</c:if> --%>
<%--           	</c:forEach> --%>
<%--           	${loop_flag } --%>
<!--           </button> -->
<!--         </div> -->
        
        
        
<!--         <ul class="comment-list"> -->
<%--           <c:forEach items="${commentList }" var="comment"> --%>
<%--           	<c:if test="${comment.boardIdx == contents.boardIdx && comment.commState != 'N'}"> --%>
<!-- 	          <li class="comment"> -->
<%-- 	            <c:if test="${comment.commAnon == 'Y' && user.memId != 'admin'}"> --%>
<%-- 	            <p class="description">${comment.commContent }<span class="comment-author"> - 익명 <c:if test="${comment.memId == user.memId || user.memId == 'admin' }"><a href="/board?cmd=commentDelete&number=${comment.commIdx}&board=${comment.boardIdx}" class="com-del">삭제</a></c:if></span></p> --%>
<%-- 	            </c:if> --%>
<%-- 	            <c:if test="${comment.commAnon == 'N' || user.memId == 'admin'}"> --%>
<%-- 	            <p class="description">${comment.commContent }<span class="comment-author"> - ${comment.memId } <c:if test="${comment.memId == user.memId || user.memId == 'admin' }"><a href="/board?cmd=commentDelete&number=${comment.commIdx}&board=${comment.boardIdx}" class="com-del">삭제</a></c:if></span></p> --%>
<%-- 	            </c:if> --%>
<!-- 	            <div class="comment-menu"> -->
<%-- <%-- 	              <button class="like-btn"><i class="fa fa-thumbs-o-up"></i>${comment.commLike } 좋아요</button> --%>
<%-- <%-- 	              <button class="hate-btn"><i class="fa fa-thumbs-o-down"></i>${comment.commHate } 싫어요</button> --%>
<!-- 	            </div> -->
<!-- 	          </li> -->
<%--           	</c:if> --%>
<%--           </c:forEach> --%>
<!--           <li class="insert-box"> -->
<!--             <form class="insert-comment" action="/board?cmd=commentPro" name="commFrm" method="post"> -->
<!--               <textarea class="comment-text-area" name="commArea"></textarea> -->
<!--               <div class="btn-check-wrap"> -->
<%--               	<input type="hidden" value="${contents.boardIdx }" name="comInBoard"> --%>
<!--                 <label for="comment-anonymity">익명</label> -->
<!--                 <input id="comment-anonymity" type="checkbox" name="commAnon"> -->
<!--               </div> -->
<!--               <button class="insert-comment-btn">댓글 등록</button> -->
<!--             </form> -->
<!--           </li> -->
<!--         </ul> -->
<!--       </li> -->
<%--       </c:forEach> --%>
<!--       <li id="test"></li> -->
    </ul>
  </main>
  <div class="mobile-device-menu">
    <button class="mobile-contents-btn"><i class="fa fa-list-alt fa-3x"></i></button>
    <button class="mobile-info-btn clicked"><i class="fa fa-user fa-3x"></i></button>
  </div>
</div>
</body>
</html>