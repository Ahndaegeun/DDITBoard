package vo;

public class DditListVO {
	private String name;
	private String regno;
	private String signCheck;
	
	public DditListVO() {
	}

	public DditListVO(String name, String regno, String signCheck) {
		this.name = name;
		this.regno = regno;
		this.signCheck = signCheck;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRegno() {
		return regno;
	}

	public void setRegno(String regno) {
		this.regno = regno;
	}

	public String getSignCheck() {
		return signCheck;
	}

	public void setSignCheck(String signCheck) {
		this.signCheck = signCheck;
	}

	@Override
	public String toString() {
		return "DditListVO [name=" + name + ", regno=" + regno + ", signCheck=" + signCheck + "]";
	}
	
	
}
