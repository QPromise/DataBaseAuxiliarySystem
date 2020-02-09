package cn.edu.ouc.pojo.vo;

public class CompareVO {

	private String name;
	private Double data1;
	private Double data2;
	private Double data3;
	public CompareVO(String name, Double data1, Double data2, Double data3) {
		this.name = name;
		this.data1 = data1;
		this.data2 = data2;
		this.data3 = data3;
	}
	public CompareVO() {
		super();
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Double getData1() {
		return data1;
	}
	public void setData1(Double data1) {
		this.data1 = data1;
	}
	public Double getData2() {
		return data2;
	}
	public void setData2(Double data2) {
		this.data2 = data2;
	}
	public Double getData3() {
		return data3;
	}
	public void setData3(Double data3) {
		this.data3 = data3;
	}
	
}
