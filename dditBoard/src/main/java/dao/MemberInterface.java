package dao;

import vo.MemberVO;

public interface MemberInterface {
	boolean join(MemberVO memberVo);
	boolean joinCheck(MemberVO memberVo);
	boolean login(MemberVO memberVo);
	MemberVO findId(MemberVO memberVo);
	MemberVO selectInfo(MemberVO memberVo);
	boolean updateInfo(MemberVO memberVo);
	boolean updatePw(MemberVO memberVo);
	boolean idCheck(MemberVO memberVo);
	//정규화
	//이메일 인증
	
}
