package dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import util.JDBCUtil;
import vo.BoardVO;
import vo.CommentLHVO;
import vo.CommentVO;

public class CommentDAO implements CommentInterface {
	
	public CommentDAO() {
	}

	private static CommentDAO instance;

	public static CommentDAO getInstance() {
		if (instance == null) {
			instance = new CommentDAO();
		}
		return instance;
	}

	JDBCUtil jdbc = JDBCUtil.getInstance();
	
	@Override
	public boolean insertComment(CommentVO vo) {
		StringBuilder sql = new StringBuilder();
		sql.append("INSERT INTO COMMENT (");
		sql.append("	COMM_IDX,");
		sql.append("	COMM_CONTENT,");
		sql.append("	COMM_DATE,");
		sql.append("	COMM_ANON,");
		sql.append("	MEM_ID,");
		sql.append("	BOARD_IDX");
		sql.append(") VALUES (");
		sql.append("	?,");
		sql.append("	?,");
		sql.append("	NOW(),");
		sql.append("	?,");
		sql.append("	?,");
		sql.append("	?)");

		List<Object> list = new ArrayList<Object>();
		list.add(vo.getCommIdx());
		list.add(vo.getCommContent());
		list.add(vo.getCommAnon());
		list.add(vo.getMemId());
		list.add(vo.getBoardIdx());

		int result = jdbc.update(sql.toString(), list);

		if (result > 0) {
			return true;
		}
		return false;
	}
	

	@Override
	public boolean deleteComment(CommentVO vo) {
		StringBuilder sql = new StringBuilder();
		sql.append("UPDATE COMMENT");
		sql.append("   SET COMM_STATE = 'N', COMM_CONTENT = '삭제된 댓글 입니다.'");
		sql.append(" WHERE COMM_IDX = ?");
		sql.append("   AND BOARD_IDX = ?");

		List<Object> list = new ArrayList<>();
		list.add(vo.getCommIdx());
		list.add(vo.getBoardIdx());

		int result = jdbc.update(sql.toString(), list);

		if (result > 0) {
			return true;
		}
		return false;
	}
	
	public Map<Integer, Integer> getCommentCount() {
		Map<Integer, Integer> map = new HashMap<>();
		
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT BOARD_IDX,");
		sql.append("	   COUNT(*)");
		sql.append("  FROM COMMENT");
		sql.append(" GROUP BY BOARD_IDX");
		
		List<Map<String, Object>> result = jdbc.selectList(sql.toString());
		for(Map<String, Object> r : result) {
			int idx = Integer.parseInt(r.get("BOARD_IDX") + "");
			int cnt = Integer.parseInt(r.get("COUNT(*)") + "");
			map.put(idx, cnt);
		}
		
		return map;
	}
	
	
	@Override
	public List<CommentVO> getCommentList(int idx) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT COMM_IDX, ");
		sql.append("	   COMM_CONTENT, ");
		sql.append("       COMM_DATE,");
		sql.append("       COMM_ANON,");
		sql.append("       COMM_LIKE,");
		sql.append("       COMM_HATE,");
		sql.append("       COMM_STATE,");
		sql.append("       BOARD_IDX,");
		sql.append("       MEM_ID");
		sql.append(" FROM  COMMENT ");
		sql.append("WHERE BOARD_IDX = ?");

		List<Object> list = new ArrayList<>();
		list.add(idx);
		
		List<Map<String, Object>> result = jdbc.selectList(sql.toString(), list);
		List<CommentVO> commentList = new ArrayList<CommentVO>();
		
		if (result.size() == 0) {
			return null;
		}
		
		for (int i = 0; i < result.size(); i++) {
			CommentVO comment = new CommentVO();
			comment.setCommIdx(Integer.parseInt((result.get(i).get("COMM_IDX") + "")));
			comment.setCommContent((String) result.get(i).get("COMM_CONTENT"));
			comment.setCommDate(result.get(i).get("COMM_DATE") + "");
			comment.setCommAnon((String) result.get(i).get("COMM_ANON"));
			comment.setCommLike(Integer.parseInt(result.get(i).get("COMM_LIKE") + ""));
			comment.setCommHate(Integer.parseInt(result.get(i).get("COMM_HATE") + ""));
			comment.setMemId((String) result.get(i).get("MEM_ID"));
			comment.setBoardIdx(Integer.parseInt(result.get(i).get("BOARD_IDX") + ""));
			comment.setCommState(result.get(i).get("COMM_STATE") + "");
			commentList.add(comment);
		}

		return commentList;
	}

	
	@Override
	public boolean clickCommentLike(CommentVO vo) {
		StringBuilder sql = new StringBuilder();
		sql.append("UPDATE COMMENT ");
		sql.append("   SET COMM_LIKE ");
		sql.append("  	   = COMM_LIKE +1");
		sql.append(" WHERE COMM_IDX ");
		sql.append("       = ? ");

		List<Object> list = new ArrayList<>();
		list.add(vo.getCommIdx());

		int result = jdbc.update(sql.toString(), list);

		if (result > 0) {
			return true;
		}
		return false;
	}

	
	@Override
	public boolean clickCommentHate(CommentVO vo) {
		StringBuilder sql = new StringBuilder();
		sql.append("UPDATE COMMENT ");
		sql.append("   SET COMM_HATE ");
		sql.append("  	   = COMM_HATE +1");
		sql.append(" WHERE COMM_IDX ");
		sql.append("       = ? ");

		List<Object> list = new ArrayList<>();
		list.add(vo.getCommIdx());

		int result = jdbc.update(sql.toString(), list);

		if (result > 0) {
			return true;
		}
		return false;
	}

	
	@Override
	public List<CommentVO> showTotalCommentLike(CommentVO vo) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT COMM_LIKE");
		sql.append("  FROM COMMENT ");
		sql.append(" WHERE COMM_IDX ");
		sql.append("    	=?");

		List<Object> list = new ArrayList<>();
		list.add(vo.getCommIdx());

		List<Map<String, Object>> result = jdbc.selectList(sql.toString(), list);
		List<CommentVO> commentLikeList = new ArrayList<>();

		if (result.size() == 0) {
			return null;
		}

		for (int i = 0; i < result.size(); i++) {
			CommentVO commLike = new CommentVO();
			commLike.setCommLike(Integer.parseInt(result.get(i).get("COMM_LIKE") + ""));
			commentLikeList.add(commLike);
		}
		return commentLikeList;
	}

	
	@Override
	public List<CommentVO> showTotalCommentHate(CommentVO vo) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT COMM_HATE");
		sql.append("  FROM COMMENT ");
		sql.append(" WHERE COMM_IDX ");
		sql.append("    	=?");

		List<Object> list = new ArrayList<>();
		list.add(vo.getCommIdx());

		List<Map<String, Object>> result = jdbc.selectList(sql.toString(), list);
		List<CommentVO> commentHateList = new ArrayList<>();

		if (result.size() == 0) {
			return null;
		}

		for (int i = 0; i < result.size(); i++) {
			CommentVO commHate = new CommentVO();
			commHate.setCommHate(Integer.parseInt(result.get(i).get("COMM_HATE") + ""));
			commentHateList.add(commHate);
		}
		return commentHateList;
	}
	
	public boolean deleteComLH(CommentLHVO vo) {
		StringBuilder sql = new StringBuilder();
		sql.append("DELETE FROM COMM_LH");
		sql.append(" WHERE BOARD_IDX = ?");
		sql.append("   AND COMM_IDX = ?");
		
		List<Object> list = new ArrayList<>();
		list.add(vo.getBoardIdx());
		list.add(vo.getCommIdx());
		
		int result = jdbc.update(sql.toString(), list);
		
		if(result > 0) {
			return true;
		}
		return false;
	}
	
	public boolean deleteComLHAll(BoardVO vo) {
		StringBuilder sql = new StringBuilder();
		sql.append("DELETE FROM COMM_LH");
		sql.append(" WHERE BOARD_IDX = ?");
		
		List<Object> list = new ArrayList<>();
		list.add(vo.getBoardIdx());
		
		int result = jdbc.update(sql.toString(), list);
		
		if(result > 0) {
			return true;
		}
		return false;
	}
	
	public boolean deleteBoardComment(BoardVO vo) {
		StringBuilder sql = new StringBuilder();
		sql.append("DELETE FROM COMMENT");
		sql.append(" WHERE BOARD_IDX = ?");
		
		List<Object> list = new ArrayList<>();
		list.add(vo.getBoardIdx());
		int result = jdbc.update(sql.toString(), list);
		
		if(result > 0) {
			return true;
		}
		return false;
	}
}






