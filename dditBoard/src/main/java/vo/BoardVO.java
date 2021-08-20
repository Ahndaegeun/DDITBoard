package vo;

public class BoardVO {
	private int boardIdx;
	private String boardTitle;
	private String boardContent;
	private String boardDate;
	private String boardAnon;
	private int boardLike;
	private int boardHate;
	private String memId;
	private int comCnt;
	
	public BoardVO() {
	}
	public BoardVO(int boardIdx, String boardTitle, String boardContent, String boardDate, String boardAnon,
			int boardLike, int boardHate, String memId) {
		this.boardIdx = boardIdx;
		this.boardTitle = boardTitle;
		this.boardContent = boardContent;
		this.boardDate = boardDate;
		this.boardAnon = boardAnon;
		this.boardLike = boardLike;
		this.boardHate = boardHate;
		this.memId = memId;
	}
	public int getBoardIdx() {
		return boardIdx;
	}
	public void setBoardIdx(int boardIdx) {
		this.boardIdx = boardIdx;
	}
	public String getBoardTitle() {
		return boardTitle;
	}
	public void setBoardTitle(String boardTitle) {
		this.boardTitle = boardTitle;
	}
	public String getBoardContent() {
		return boardContent;
	}
	public void setBoardContent(String boardContent) {
		this.boardContent = boardContent;
	}
	public String getBoardDate() {
		return boardDate;
	}
	public void setBoardDate(String boardDate) {
		this.boardDate = boardDate;
	}
	public String getBoardAnon() {
		return boardAnon;
	}
	public void setBoardAnon(String boardAnon) {
		this.boardAnon = boardAnon;
	}
	public int getBoardLike() {
		return boardLike;
	}
	public void setBoardLike(int boardLike) {
		this.boardLike = boardLike;
	}
	public int getBoardHate() {
		return boardHate;
	}
	public void setBoardHate(int boardHate) {
		this.boardHate = boardHate;
	}
	public String getMemId() {
		return memId;
	}
	public void setMemId(String memId) {
		this.memId = memId;
	}
	public int getComCnt() {
		return comCnt;
	}
	public void setComCnt(int comCnt) {
		this.comCnt = comCnt;
	}
	@Override
	public String toString() {
		return "BoardVO [boardIdx=" + boardIdx + ", boardTitle=" + boardTitle + ", boardContent=" + boardContent
				+ ", boardDate=" + boardDate + ", boardAnon=" + boardAnon + ", boardLike=" + boardLike + ", boardHate="
				+ boardHate + ", memId=" + memId + "]";
	}
	
	
	
	
	
	
}
