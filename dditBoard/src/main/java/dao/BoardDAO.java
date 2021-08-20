package dao;

//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import util.JDBCUtil;
import vo.BoardLHVO;
import vo.BoardVO;
import vo.MemberVO;

public class BoardDAO implements BoardImplement{
	private BoardDAO() {}
	private static BoardDAO instance;
	
	public static BoardDAO getBoardDAO() {
		if (instance == null) {
			instance = new BoardDAO();
		}
		return instance;
	}
	
	private JDBCUtil util = JDBCUtil.getInstance();

	@Override
	public int registerBoard(BoardVO boardVO) {
		StringBuilder sql = new StringBuilder();
		sql.append("INSERT INTO BOARD(");
		sql.append("	BOARD_TITLE,");
		sql.append("	BOARD_CONTENT,");
		sql.append("	BOARD_ANON,");
		sql.append("	MEM_ID");
		sql.append(") VALUES (");
		sql.append("	?,");
		sql.append("	?,");
		sql.append("	?,");
		sql.append("	?");
		sql.append(")");
		
		List<Object> list = new ArrayList<Object>();
		list.add(boardVO.getBoardTitle());
		list.add(boardVO.getBoardContent());
		list.add(boardVO.getBoardAnon());
		list.add(boardVO.getMemId());
		
		return util.update(sql.toString(), list);
	}
	
	@Override
	public MemberVO checkBoardAuthor(BoardVO boardVO) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT M.MEM_NM ");
		sql.append("  FROM MEMBER M, BOARD B ");
		sql.append(" WHERE M.MEM_ID = B.MEM_ID ");
		sql.append("   AND B.BOARD_IDX = ?");
		
		List<Object> list = new ArrayList<Object>();
		list.add(boardVO.getBoardIdx());
		
		MemberVO memberVO = new MemberVO(util.selectOne(sql.toString(),list).get("MEM_NM")+"");
		
		return memberVO;
	}

	@Override
	public int modifyBoard(BoardVO boardVO) {
		StringBuilder sql = new StringBuilder();
		sql.append("UPDATE BOARD");
		sql.append("   SET BOARD_TITLE = ?,");
		sql.append("   	   BOARD_CONTENT = ?,");
		sql.append("   	   BOARD_ANON = ?  ");
		sql.append(" WHERE MEM_ID = ?");
		sql.append("   AND BOARD_IDX = ?");
		
		List<Object> list = new ArrayList<Object>();
		list.add(boardVO.getBoardTitle());
		list.add(boardVO.getBoardContent());
		list.add(boardVO.getBoardAnon());
		list.add(boardVO.getMemId());
		list.add(boardVO.getBoardIdx());
		
		return util.update(sql.toString(), list);
	}

	@Override
	public int deleteCommentInBoard(BoardVO boardVO) {
		StringBuilder sql = new StringBuilder();
		sql.append("DELETE FROM COMMENT WHERE BOARD_IDX = ?");
		
		List<Object> list = new ArrayList<Object>();
		list.add(boardVO.getBoardIdx());
		
		return util.update(sql.toString(), list);
	}
	
	@Override
	public int deleteBoardLHInBoard(BoardVO boardVO) {
		StringBuilder sql = new StringBuilder();
		sql.append("DELETE FROM BOARD_LH WHERE BOARD_IDX = ?");
		
		List<Object> list = new ArrayList<Object>();
		list.add(boardVO.getBoardIdx());
		
		return util.update(sql.toString(), list);
	}
	
	@Override
	public boolean deleteBoard(BoardVO boardVO) {
		StringBuilder sql = new StringBuilder();
		sql.append("DELETE FROM BOARD WHERE BOARD_IDX = ?");
		
		List<Object> list = new ArrayList<Object>();
		list.add(boardVO.getBoardIdx());
		
		int result = util.update(sql.toString(), list);
		
		if(result > 0) {
			return true;
		}
		return false;
	}

	@Override
	public List<BoardVO> printOutBoard(int startRow, int endRow) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT B.BOARD_IDX,");
		sql.append("	   B.BOARD_TITLE,");
		sql.append("	   B.BOARD_CONTENT,");
		sql.append("	   B.BOARD_DATE,");
		sql.append("	   B.BOARD_ANON,");
		sql.append("   B.MEM_ID,");
		sql.append("	   COUNT(C.BOARD_IDX)");
		sql.append("  FROM BOARD B");
		sql.append("  LEFT OUTER JOIN COMMENT C ON B.BOARD_IDX = C.BOARD_IDX ");
		sql.append(" GROUP BY B.BOARD_IDX,");
		sql.append("	   B.BOARD_TITLE,");
		sql.append("	   B.BOARD_CONTENT,");
		sql.append("	   B.BOARD_DATE,");
		sql.append("	   B.BOARD_ANON,");
		sql.append("   B.MEM_ID");
		sql.append(" ORDER BY B.BOARD_DATE DESC");
		sql.append(" LIMIT ?, ?");
		
		List<Object> list = new ArrayList<>();
		list.add(startRow);
		list.add(endRow);
		List<Map<String, Object>> map = util.selectList(sql.toString(), list);
		List<BoardVO> boardList = new ArrayList<>();
		
		for (int i = 0; i < map.size(); i++) {
			BoardVO boardVO = new BoardVO();
			boardVO.setBoardIdx(Integer.parseInt(map.get(i).get("BOARD_IDX").toString()));
			boardVO.setBoardTitle((map.get(i).get("BOARD_TITLE").toString()));
			boardVO.setBoardContent(map.get(i).get("BOARD_CONTENT").toString());
			boardVO.setBoardDate(map.get(i).get("BOARD_DATE").toString());
			boardVO.setBoardAnon(map.get(i).get("BOARD_ANON").toString());
			boardVO.setMemId(map.get(i).get("MEM_ID").toString());
			boardVO.setComCnt(Integer.parseInt(map.get(i).get("COUNT(C.BOARD_IDX)").toString()));
			boardList.add(boardVO);
		}
		
		return boardList;

	}

	@Override
	public int anoymousBoard(BoardVO boardVO) {
		StringBuilder sql = new StringBuilder();
		sql.append("UPDATE BOARD ");
		sql.append("   SET BOARD_ANON = 'y'");
		sql.append(" WHERE BOARD_IDX = ?");
		
		List<Object> list = new ArrayList<Object>();
		list.add(boardVO.getBoardIdx());
		
		return util.update(sql.toString(), list);
	}
	
	@Override
	public int realNameBoard(BoardVO boardVO) {
		StringBuilder sql = new StringBuilder();
		sql.append("UPDATE BOARD ");
		sql.append("   SET BOARD_ANON = 'n'");
		sql.append(" WHERE BOARD_IDX = ?");
		
		List<Object> list = new ArrayList<Object>();
		list.add(boardVO.getBoardIdx());
		
		return util.update(sql.toString(), list);
	}
	
	@Override
	public BoardLHVO checkBoardLH(BoardLHVO boardLHVO) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT LH");
		sql.append("  FROM BOARD_LH");
		sql.append(" WHERE BOARD_IDX = ?");
		
		List<Object> list = new ArrayList<Object>();
		list.add(boardLHVO.getBoardIdx());
		
		String boardLikeNum = util.selectOne(sql.toString(),list).get("LH").toString();
		boardLHVO.setLh(boardLikeNum);
		
		return boardLHVO;
	}

	@Override
	public BoardLHVO checkLikeHateOnBoard(BoardLHVO boardLhVO) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT LH");
		sql.append("  FROM BOARD_LH");
		sql.append(" WHERE MEM_ID = ?");
		sql.append("   AND BOARD_IDX = ?");
		
		List<Object> list = new ArrayList<Object>();
		list.add(boardLhVO.getMemId());
		list.add(boardLhVO.getBoardIdx());
		
		String boardLikeNum = util.selectOne(sql.toString(),list).get("LH").toString();
		boardLhVO.setLh(boardLikeNum);
		
		return boardLhVO;
	}
	
	@Override
	public int likeBoard(BoardVO boardVO) {
		StringBuilder sql = new StringBuilder();
		sql.append(" UPDATE BOARD ");
		sql.append("   SET BOARD_LIKE = BOARD_LIKE+1");
		sql.append(" WHERE BOARD_IDX = ?");
		
		List<Object> list = new ArrayList<Object>();
		list.add(boardVO.getBoardIdx());
		
		return util.update(sql.toString(), list);
	}
	
	@Override
	public int cancelLikeBoard(BoardVO boardVO) {
		StringBuilder sql = new StringBuilder();
		sql.append(" UPDATE BOARD ");
		sql.append("   SET BOARD_LIKE = BOARD_LIKE-1");
		sql.append(" WHERE BOARD_IDX = ?");
		
		List<Object> list = new ArrayList<Object>();
		list.add(boardVO.getBoardIdx());
		
		return util.update(sql.toString(), list);
	}

	@Override
	public int hateBoard(BoardVO boardVO) {
		StringBuilder sql = new StringBuilder();
		sql.append("  UPDATE BOARD ");
		sql.append("   SET BOARD_HATE = BOARD_HATE+1");
		sql.append(" WHERE BOARD_IDX = ?");
		
		List<Object> list = new ArrayList<Object>();
		list.add(boardVO.getBoardIdx());
		
		return util.update(sql.toString(), list);
	}

	@Override
	public int cancelHateBoard(BoardVO boardVO) {
		StringBuilder sql = new StringBuilder();
		sql.append("UPDATE BOARD ");
		sql.append("   SET BOARD_HATE = BOARD_HATE-1");
		sql.append(" WHERE BOARD_IDX = ?");
		
		List<Object> list = new ArrayList<Object>();
		list.add(boardVO.getBoardIdx());
		
		return util.update(sql.toString(), list);
	}
	
	@Override
	public int makeLHtoNBoardLH(BoardLHVO boardLhVO) {
		StringBuilder sql = new StringBuilder();
		sql.append("UPDATE BOARD_LH");
		sql.append("    SET LH = ?");
		sql.append("  WHERE MEM_ID =?");
		sql.append("    AND BOARD_IDX = ?");
		
		List<Object> list = new ArrayList<Object>();		
		list.add(boardLhVO.getLh());
		list.add(boardLhVO.getMemId());
		list.add(boardLhVO.getBoardIdx());
		
		return util.update(sql.toString(), list);
	}

	@Override
	public BoardVO printCountLikeBoard(BoardVO boardVO) {
		StringBuilder sql = new StringBuilder();
		sql.append(" SELECT BOARD_LIKE ");
		sql.append("   FROM BOARD");
		sql.append("  WHERE BOARD_IDX = ?");
		
		List<Object> list = new ArrayList<Object>();
		list.add(boardVO.getBoardIdx());
		
		int boardLikeNum = Integer.parseInt(util.selectOne(sql.toString(),list).get("BOARD_LIKE").toString());
		boardVO.setBoardLike(boardLikeNum);
		
		return boardVO;
	}

	@Override
	public BoardVO printCountHateBoard(BoardVO boardVO) {
		StringBuilder sql = new StringBuilder();
		sql.append(" SELECT BOARD_HATE ");
		sql.append("   FROM BOARD");
		sql.append("  WHERE BOARD_IDX = ?");
		
		List<Object> list = new ArrayList<Object>();
		list.add(boardVO.getBoardIdx());
		
		int boardLikeNum = Integer.parseInt(util.selectOne(sql.toString(),list).get("BOARD_HATE").toString());
		boardVO.setBoardHate(boardLikeNum);
		
		return boardVO;
	}
	
	public int countBoard() {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT COUNT(*)");
		sql.append("  FROM BOARD");
		
		Map<String, Object> map = util.selectOne(sql.toString());
		
		int cnt = Integer.parseInt(map.get("COUNT(*)") + "");
		
		return cnt;
	}
}
