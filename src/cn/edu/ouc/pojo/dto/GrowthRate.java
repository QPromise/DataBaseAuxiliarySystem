package cn.edu.ouc.pojo.dto;

public class GrowthRate {

	// 1.ID
	private Integer id;
	// 2.指标名称
	private String designation;
	// 3.下限
	private double lower;
	// 4.上限
	private double upper;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public double getLower() {
		return lower;
	}

	public void setLower(double lower) {
		this.lower = lower;
	}

	public double getUpper() {
		return upper;
	}

	public void setUpper(double upper) {
		this.upper = upper;
	}

}
