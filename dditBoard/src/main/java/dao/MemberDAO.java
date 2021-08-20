package dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import vo.MemberVO;
import util.JDBCUtil;

public class MemberDAO implements MemberInterface {

	private static MemberDAO instance;

	public static MemberDAO getMemberDAO() {
		if (instance == null) {
			instance = new MemberDAO();
		}
		return instance;
	}

	JDBCUtil jdbc = JDBCUtil.getInstance();

	@Override
	public boolean join(MemberVO memberVo) {
		StringBuilder sql = new StringBuilder();
		sql.append("INSERT INTO MEMBER VALUES(");
		sql.append("	?,");
		sql.append("	?,");
		sql.append("	?,");
		sql.append("	?,");
		sql.append("	? ");
		sql.append(")     ");
		List<Object> list = new ArrayList<>();
		list.add(memberVo.getMemNm());
		list.add(memberVo.getMemRegno());
		list.add(memberVo.getMemId());
		list.add(memberVo.getMemPw());
		list.add(memberVo.getMemEmail());

		int result = jdbc.update(sql.toString(), list);

		if (result > 0) {
			return true;
		} else {
			return false;
		}

	}

	@Override
	public boolean joinCheck(MemberVO memberVo) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT 1            ");
		sql.append("  FROM MEMBER       ");
		sql.append(" WHERE MEM_NM = ?   ");
		sql.append("   AND MEM_REGNO = ?");

		List<Object> list = new ArrayList<>();
		list.add(memberVo.getMemNm());
		list.add(memberVo.getMemRegno());

		Map<String, Object> result = jdbc.selectOne(sql.toString(), list);

		if (result != null) {
			return true;
		}
		return false;
	}

	@Override
	public boolean login(MemberVO memberVo) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT 1         ");
		sql.append("  FROM MEMBER    ");
		sql.append(" WHERE MEM_ID = ?");
		sql.append("   AND MEM_PW = ?");

		List<Object> list = new ArrayList<>();
		list.add(memberVo.getMemId());
		list.add(memberVo.getMemPw());

		Map<String, Object> result = new HashMap<>();
		result = jdbc.selectOne(sql.toString(), list);

		if (result != null) {
			return true;
		}
		return false;
	}

	@Override
	public MemberVO findId(MemberVO memberVo) {
		// 이름과 생년월일로 찾는 아이디
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT MEM_ID       ");
		sql.append("  FROM MEMBER       ");
		sql.append(" WHERE MEM_NM = ?   ");
		sql.append("   AND MEM_REGNO = ?");

		List<Object> list = new ArrayList<>();
		list.add(memberVo.getMemNm());
		list.add(memberVo.getMemRegno());

		Map<String, Object> findMap = jdbc.selectOne(sql.toString(), list);

		if (findMap != null) {
			MemberVO vo = new MemberVO();
			vo.setMemId(findMap.get("MEM_ID") + "");
			return vo;
		}
		return null;
	}

	@Override
	public MemberVO selectInfo(MemberVO memberVo) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT MEM_ID,   ");
		sql.append("	   MEM_NM,   ");
		sql.append("	   MEM_REGNO,");
		sql.append("	   MEM_EMAIL ");
		sql.append("  FROM MEMBER    ");
		sql.append(" WHERE MEM_ID = ?");

		List<Object> list = new ArrayList<>();
		list.add(memberVo.getMemId());

		Map<String, Object> map = jdbc.selectOne(sql.toString(), list);

		if (map != null) {
			MemberVO vo = new MemberVO();
			vo.setMemId(map.get("MEM_ID") + "");
			vo.setMemNm(map.get("MEM_NM") + "");
			vo.setMemPw(map.get("MEM_PW") + "");
			vo.setMemRegno(map.get("MEM_REGNO") + "");
			vo.setMemEmail(map.get("MEM_EMAIL") + "");
			return vo;
		}
		return null;
	}

	@Override
	public boolean updateInfo(MemberVO memberVo) {
		StringBuilder sql = new StringBuilder();
		sql.append("UPDATE MEMBER        ");
		sql.append("   SET MEM_NM = ?,   ");
		sql.append("   	   MEM_REGNO = ?,");
		sql.append("   	   MEM_EMAIL = ? ");
		sql.append(" WHERE MEM_ID = ?    ");

		List<Object> list = new ArrayList<>();
		list.add(memberVo.getMemNm());
		list.add(memberVo.getMemRegno());
		list.add(memberVo.getMemEmail());
		list.add(memberVo.getMemId());

		int result = jdbc.update(sql.toString(), list);

		if (result > 0) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean updatePw(MemberVO memberVo) {
		StringBuilder sql = new StringBuilder();
		sql.append("UPDATE MEMBER    ");
		sql.append("   SET MEM_PW = ?");
		sql.append(" WHERE MEM_ID = ?");

		List<Object> list = new ArrayList<>();
		list.add(memberVo.getMemPw());
		list.add(memberVo.getMemId());

		int result = jdbc.update(sql.toString(), list);

		if (result > 0) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean idCheck(MemberVO memberVo) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT MEM_ID");
		sql.append("  FROM MEMBER");
		sql.append(" WHERE MEM_ID = ?");

		List<Object> list = new ArrayList<>();
		list.add(memberVo.getMemId());

		Map<String, Object> map = jdbc.selectOne(sql.toString(), list);

		if (map == null) {
			return true;
		}
		return false;
	}

	public int likeCount(MemberVO memberVo) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT A.BOA_L + B.COM_L AS C_LIKE");
		sql.append(" FROM (SELECT COUNT(*) AS BOA_L");
		sql.append("   FROM BOARD_LH");
		sql.append("   WHERE LH = 'L'");
		sql.append("   AND MEM_ID = ?) A,");
		sql.append("   (SELECT COUNT(*) AS COM_L");
		sql.append("   FROM COMM_LH");
		sql.append("   WHERE LH = 'L'");
		sql.append("   AND MEM_ID = ?) B");

		List<Object> list = new ArrayList<>();
		list.add(memberVo.getMemId());
		list.add(memberVo.getMemId());

		Map<String, Object> map = jdbc.selectOne(sql.toString(), list);

		if (map != null) {
			return Integer.parseInt(map.get("C_LIKE") + "");
		} else {
			return 0;
		}
	}

	public int hateCount(MemberVO memberVo) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT A.BOA_H + B.COM_H AS C_HATE");
		sql.append(" FROM (SELECT COUNT(*) AS BOA_H");
		sql.append("   FROM BOARD_LH");
		sql.append("   WHERE LH = 'H'");
		sql.append("   AND MEM_ID = ?) A,");
		sql.append("   (SELECT COUNT(*) AS COM_H");
		sql.append("   FROM COMM_LH");
		sql.append("   WHERE LH = 'H'");
		sql.append("   AND MEM_ID = ?) B");

		List<Object> list = new ArrayList<>();
		list.add(memberVo.getMemId());
		list.add(memberVo.getMemId());

		Map<String, Object> map = jdbc.selectOne(sql.toString(), list);

		if (map != null) {
			return Integer.parseInt(map.get("C_HATE") + "");
		} else {
			return 0;
		}
	}

	public int boardCount(MemberVO memberVo) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT COUNT(*) AS C_BOARD");
		sql.append(" FROM BOARD");
		sql.append(" WHERE MEM_ID = ?");

		List<Object> list = new ArrayList<>();
		list.add(memberVo.getMemId());

		Map<String, Object> map = jdbc.selectOne(sql.toString(), list);

		if (map != null) {
			return Integer.parseInt(map.get("C_BOARD") + "");
		} else {
			return 0;
		}
	}

	public int commentCount(MemberVO memberVo) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT COUNT(*) AS C_COMMENT");
		sql.append(" FROM COMMENT");
		sql.append(" WHERE MEM_ID = ?");

		List<Object> list = new ArrayList<>();
		list.add(memberVo.getMemId());

		Map<String, Object> map = jdbc.selectOne(sql.toString(), list);

		if (map != null) {
			return Integer.parseInt(map.get("C_COMMENT") + "");
		} else {
			return 0;
		}
	}
	
	public boolean isDdit(MemberVO vo) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT 1");
		sql.append("  FROM DDIT_LIST");
		sql.append(" WHERE NAME = ?");
		sql.append("   AND REGNO = ?");
		sql.append("   AND SIGN_CHECK = 'N'");
		
		List<Object> list = new ArrayList<>();
		list.add(vo.getMemNm());
		list.add(vo.getMemRegno());
		Map<String, Object> map = jdbc.selectOne(sql.toString(), list);
		
		if(map != null) {
			return true;
		}
		
		return false;
	}
	
	public boolean changeDditList(MemberVO vo) {
		StringBuilder sql = new StringBuilder();
		sql.append("UPDATE DDIT_LIST");
		sql.append("   SET SIGN_CHECK = 'Y'");
		sql.append(" WHERE NAME = ?");
		sql.append("   AND REGNO = ?");
		
		List<Object> list = new ArrayList<>();
		list.add(vo.getMemNm());
		list.add(vo.getMemRegno());
		
		int result = jdbc.update(sql.toString(), list);
		
		if(result > 0) {
			return true;
		}
		
		return false;
	}
	
	public String getUserName(String userId) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT MEM_NM");
		sql.append("  FROM MEMBER");
		sql.append(" WHERE MEM_ID = ?");
		
		List<Object> list = new ArrayList<>();
		list.add(userId);
		String name = jdbc.selectOne(sql.toString(), list).get("MEM_NM") + "";
		
		return name;
	}

}
