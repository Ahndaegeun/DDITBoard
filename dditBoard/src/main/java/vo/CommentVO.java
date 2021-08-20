package vo;

public class CommentVO {
	private int commIdx;
	private String commContent;
	private String commDate;
	private String commAnon;
	private int commLike;
	private int commHate;
	private String commState;
	private String memId;
	private int boardIdx;
	
	public CommentVO() {
	}
	public CommentVO(int commIdx, String commContent, String commDate, String commAnon, int commLike, int commHate,
			String commState, String memId, int boardIdx) {
		this.commIdx = commIdx;
		this.commContent = commContent;
		this.commDate = commDate;
		this.commAnon = commAnon;
		this.commLike = commLike;
		this.commHate = commHate;
		this.commState = commState;
		this.memId = memId;
		this.boardIdx = boardIdx;
	}
	public int getCommIdx() {
		return commIdx;
	}
	public void setCommIdx(int commIdx) {
		this.commIdx = commIdx;
	}
	public String getCommContent() {
		return commContent;
	}
	public void setCommContent(String commContent) {
		this.commContent = commContent;
	}
	public String getCommDate() {
		return commDate;
	}
	public void setCommDate(String commDate) {
		this.commDate = commDate;
	}
	public String getCommAnon() {
		return commAnon;
	}
	public void setCommAnon(String commAnon) {
		this.commAnon = commAnon;
	}
	public int getCommLike() {
		return commLike;
	}
	public void setCommLike(int commLike) {
		this.commLike = commLike;
	}
	public int getCommHate() {
		return commHate;
	}
	public void setCommHate(int commHate) {
		this.commHate = commHate;
	}
	public String getCommState() {
		return commState;
	}
	public void setCommState(String commState) {
		this.commState = commState;
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
	@Override
	public String toString() {
		return "CommentVO [commIdx=" + commIdx + ", commContent=" + commContent + ", commDate=" + commDate
				+ ", commAnon=" + commAnon + ", commLike=" + commLike + ", commHate=" + commHate + ", commState="
				+ commState + ", memId=" + memId + ", boardIdx=" + boardIdx + "]";
	}
	
	
	
	
	
}
