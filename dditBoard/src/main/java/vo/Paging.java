package vo;

public class Paging {
	private int page = 1;
	private int loadedPage;
	private int totalCount;
	private int beginPage;
	private int endPage;
	private int loadedRow;
	private int totalRow;
	
	public Paging() {}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getLoadedPage() {
		return loadedPage;
	}

	public void setLoadedPage(int loadedPage) {
		this.loadedPage = loadedPage;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public int getBeginPage() {
		return beginPage;
	}

	public void setBeginPage(int beginPage) {
		this.beginPage = beginPage;
	}

	public int getEndPage() {
		return endPage;
	}

	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}

	public int getLoadedRow() {
		return loadedRow;
	}

	public void setLoadedRow(int loadedRow) {
		this.loadedRow = loadedRow;
	}

	public int getTotalRow() {
		return totalRow;
	}

	public void setTotalRow(int totalRow) {
		this.totalRow = totalRow;
	}
	
	

}
