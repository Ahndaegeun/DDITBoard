package vo;

public class CommentLHVO {
	private String memId;
	private int boardIdx;
	private int commIdx;
	private String lh;
	
	public CommentLHVO() {
	}

	public CommentLHVO(String memId, int boardIdx, int commIdx, String lh) {
		this.memId = memId;
		this.boardIdx = boardIdx;
		this.commIdx = commIdx;
		this.lh = lh;
	}

	public String getMemId() {
		return memId;
	}

	public void setMemId(String memId) {
		this.memId = memId;
	}

	public int getBoardIdx() {
		return boardIdx;
	}

	public void setBoardIdx(int boardIdx) {
		this.boardIdx = boardIdx;
	}

	public int getCommIdx() {
		return commIdx;
	}

	public void setCommIdx(int commIdx) {
		this.commIdx = commIdx;
	}

	public String getLh() {
		return lh;
	}

	public void setLh(String lh) {
		this.lh = lh;
	}

	@Override
	public String toString() {
		return "CommentLHVO [memId=" + memId + ", boardIdx=" + boardIdx + ", commIdx=" + commIdx + ", lh=" + lh + "]";
	}
	
	
	
	
}
