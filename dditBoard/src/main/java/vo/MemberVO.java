package vo;

public class MemberVO {
	private String memId;
	private String memNm;
	private String memRegno;
	private String memPw;
	private String memEmail;
	
	public MemberVO() {
	}
	public MemberVO(String memId, String memNm, String memRegno, String memPw, String memEmail) {
		super();
		this.memId = memId;
		this.memNm = memNm;
		this.memRegno = memRegno;
		this.memPw = memPw;
		this.memEmail = memEmail;
	}
	
	
	public MemberVO(String memId) {
		super();
		this.memId = memId;
	}
	public String getMemId() {
		return memId;
	}
	public void setMemId(String memId) {
		this.memId = memId;
	}
	public String getMemNm() {
		return memNm;
	}
	public void setMemNm(String memNm) {
		this.memNm = memNm;
	}
	public String getMemRegno() {
		return memRegno;
	}
	public void setMemRegno(String memRegno) {
		this.memRegno = memRegno;
	}
	public String getMemPw() {
		return memPw;
	}
	public void setMemPw(String memPw) {
		this.memPw = memPw;
	}
	public String getMemEmail() {
		return memEmail;
	}
	public void setMemEmail(String memEmail) {
		this.memEmail = memEmail;
	}
	@Override
	public String toString() {
		return "MemberVO [memId=" + memId + ", memNm=" + memNm + ", memRegno=" + memRegno + ", memPw=" + memPw
				+ ", memEmail=" + memEmail + "]";
	}
	
	
	
	
	
}
