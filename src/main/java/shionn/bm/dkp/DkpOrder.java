package shionn.bm.dkp;

public enum DkpOrder {
	name("name ASC"), dkp("dkp DESC"), clazz("class ASC, dkp DESC");

	private String sql;

	private DkpOrder(String sql) {
		this.sql = sql;
	}

	public String getSql() {
		return sql;
	}

}
