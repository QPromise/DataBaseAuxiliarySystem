package cn.edu.ouc.pojo.vo;

public class CheckoutVO {
	// 1.指标名称
	private String designation;
	// 2.显示信息
	private String message;
	// 3.年份
	private int year;

	public CheckoutVO() {
	}

	public CheckoutVO(String designation, String message, int year) {
		this.designation = designation;
		this.message = message;
		this.year = year;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}
}
