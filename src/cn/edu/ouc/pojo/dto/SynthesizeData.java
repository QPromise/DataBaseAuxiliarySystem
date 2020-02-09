package cn.edu.ouc.pojo.dto;

import java.io.Serializable;

/**
 * 综合数据指标
 * 
 * @author 高杨
 * @date 2018年1月30日 下午2:44:17
 */
public class SynthesizeData implements Serializable {

	private static final long serialVersionUID = 1L;
	private Integer id;
	private String year;
	private double value;

	public SynthesizeData() {
		super();
	}

	public SynthesizeData(String year, double value) {
		super();
		this.year = year;
		this.value = value;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public double getValue() {
		return value;
	}

	public void setValue(double value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return "SynthesizeData [id=" + id + ", year=" + year + ", value=" + value + "]";
	}
	
	

}
