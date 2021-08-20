package dao;

import java.util.List;

import vo.BoardLHVO;
import vo.BoardVO;
import vo.MemberVO;

public interface BoardImplement {
	/*
	 * o 인터페이스 완료, v 구현 완료
	 * 1. 게시판 작성 o v
	 * 2. 게시판 수정(작성한 유저만) o v
	 * 3. 게시판 삭제(작성한 유저만) o v
	 * 4. 게시판 출력 o v
	 * 5. 게시판 작성자 확인 o v
	 * 6. (제목, 내용,) 익명 여부 o
	 * 7. 게시판 좋하요, 싫어요 o
	 * 8. 게시판 좋아요, 싫어요 갯수 o
	 * 003. 사진 올리기?
	 * 001. 게시판의 매개변수 받아오는 것
	 * 002. 게시판 스크롤 페이징(프론트랑 회의)
	 */
	int registerBoard(BoardVO boardVO);
	int modifyBoard(BoardVO boardVO);
//	List<CommentVO> findCommentInBoard(BoardVO boardVO);
	int deleteCommentInBoard(BoardVO boardVO);
	int deleteBoardLHInBoard(BoardVO boardVO);
	boolean deleteBoard(BoardVO boardVO);
	List<BoardVO> printOutBoard(int startRow, int endRow);
	MemberVO checkBoardAuthor(BoardVO boardVO);
	int anoymousBoard(BoardVO boardVO);
	int realNameBoard(BoardVO boardVO);
	BoardLHVO checkBoardLH(BoardLHVO boardLhVO);
	BoardLHVO checkLikeHateOnBoard(BoardLHVO boardLhVO);
	int likeBoard(BoardVO boardVO);
	int hateBoard(BoardVO boardVO);
	int cancelLikeBoard(BoardVO boardVO);
	int cancelHateBoard(BoardVO boardVO);
	int makeLHtoNBoardLH(BoardLHVO boardLhVO);
	BoardVO printCountLikeBoard(BoardVO boardVO);
	BoardVO printCountHateBoard(BoardVO boardVO);
	
}
