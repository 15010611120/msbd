package com.yxd.model;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name="yxd_consumption_record")
public class ConsumptionRecord {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String consumptionName;
	private String consumptionType;
	private String consumprionTime;
	private String consumptionOP;
	private String consumpAmount;
	private String note;
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getConsumptionName() {
		return consumptionName;
	}
	public void setConsumptionName(String consumptionName) {
		this.consumptionName = consumptionName;
	}
	public String getConsumptionType() {
		return consumptionType;
	}
	public void setConsumptionType(String consumptionType) {
		this.consumptionType = consumptionType;
	}
	public String getConsumprionTime() {
		return consumprionTime;
	}
	public void setConsumprionTime(String consumprionTime) {
		this.consumprionTime = consumprionTime;
	}
	public String getConsumptionOP() {
		return consumptionOP;
	}
	public void setConsumptionOP(String consumptionOP) {
		this.consumptionOP = consumptionOP;
	}
	public String getConsumpAmount() {
		return consumpAmount;
	}
	public void setConsumpAmount(String consumpAmount) {
		this.consumpAmount = consumpAmount;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	
	

}
