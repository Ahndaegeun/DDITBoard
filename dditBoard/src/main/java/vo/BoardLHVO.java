package vo;

public class BoardLHVO {
	private String memId;
	private int boardIdx;
	private String lh;
	
	public BoardLHVO() {
	}

	public BoardLHVO(String memId, int boardIdx, String lh) {
		super();
		this.memId = memId;
		this.boardIdx = boardIdx;
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

	public String getLh() {
		return lh;
	}

	public void setLh(String lh) {
		this.lh = lh;
	}

	@Override
	public String toString() {
		return "BoardLHVO [memId=" + memId + ", boardIdx=" + boardIdx + ", lh=" + lh + "]";
	}
	
	
	
}
