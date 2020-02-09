package cn.edu.ouc.pojo.dto;

public class IndexData {

	private Integer index;
	private Double value;

	public IndexData() {
	}

	public IndexData(Integer index, Double value) {
		this.index = index;
		this.value = value;
	}

	public Integer getIndex() {
		return index;
	}

	public void setIndex(Integer index) {
		this.index = index;
	}

	public Double getValue() {
		return value;
	}

	public void setValue(Double value) {
		this.value = value;
	}
}
