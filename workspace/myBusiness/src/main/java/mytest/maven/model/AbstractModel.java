package mytest.maven.model;

import java.sql.Timestamp;

public class AbstractModel {
	private Long id;
	private Timestamp createdDate;

	public long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Timestamp getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Timestamp createdDate) {
		this.createdDate = createdDate;
	}

}
