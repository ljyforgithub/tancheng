package vo;

public class Level implements Comparable<Level>{
	private String level;
	private String number;
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}

	@Override
	public boolean equals(Object o) {
		if(o instanceof Level) {
			Level lev=(Level)o;
			return this.level.equals(lev.level);
		}
		return super.equals(o);
	}
	@Override
	public int hashCode() {
		return Integer.parseInt(this.level);
	}
	@Override
	public int compareTo(Level o) {
		int lev1 =Integer.parseInt(this.level);
		int lev2 = Integer.parseInt(o.getLevel());
		if(lev1<lev2) {
			return -1;
		}else if(lev1>lev2) {
			return 1;
		}else {
		return 0;
		}
	}
}
