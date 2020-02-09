package cn.edu.ouc.pojo.vo;

import java.io.Serializable;

/**
 * 用于数据矫正的实体类
 * @author AN
 *
 */
public class ReviseDataVO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	//1.id
	private Integer id;
	//2.指标名称
	private String designation;
	//3.指标值
	private Double value;
	//4.上次修改的值
	private Double reviseValue;
	//5.是否排除计算
	private Integer isExclude;
	//6.年份
	private String year;
	//7.状态
	private Integer satate;
	//8.指标id
	private Integer indexId;
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
	public Double getValue() {
		return value;
	}
	public void setValue(Double value) {
		this.value = value;
	}
	public Double getReviseValue() {
		return reviseValue;
	}
	public void setReviseValue(Double reviseValue) {
		this.reviseValue = reviseValue;
	}
	public Integer getIsExclude() {
		return isExclude;
	}
	public void setIsExclude(Integer isExclude) {
		this.isExclude = isExclude;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	
	public Integer getSatate() {
		return satate;
	}
	public void setSatate(Integer satate) {
		this.satate = satate;
	}
	public Integer getIndexId() {
		return indexId;
	}
	public void setIndexId(Integer indexId) {
		this.indexId = indexId;
	}
	@Override
	public String toString() {
		return "ReviseDataVO [id=" + id + ", designation=" + designation + ", value=" + value + ", reviseValue="
				+ reviseValue + ", isExclude=" + isExclude + ", year=" + year + ", satate=" + satate + ", indexId="
				+ indexId + "]";
	}
	
}
