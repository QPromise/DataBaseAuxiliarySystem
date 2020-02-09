package cn.edu.ouc.pojo.dto;

import java.io.Serializable;

public class IndexWeight implements Serializable {

	private static final long serialVersionUID = 1L;
	// 1.指标id
	private Integer id;
	// 2.指标名称
	private String designation;
	// 3.权重值
	private Double weightValue;
	
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
	public Double getWeightValue() {
		return weightValue;
	}
	public void setWeightValue(Double weightValue) {
		this.weightValue = weightValue;
	}
	@Override
	public String toString() {
		return "IndexWeight [indexId=" + id + ", designation=" + designation + ", weightValue=" + weightValue
				+ "]";
	}
	
	
}
