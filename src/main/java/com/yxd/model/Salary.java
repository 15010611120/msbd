package com.yxd.model;
/**
 * 工资发放记录
 * @author Xiaodong
 *
 */
public class Salary {

	private String basicSalary;//基本
	private String accumulationFund;//公积金
	private String medicalInsurance;//医保
	private String salaryTime;
	private String subsidy;//补助
	private String note;//其他
	private String company;//公司
	private String personal;//人员
	
	public String getBasicSalary() {
		return basicSalary;
	}
	public void setBasicSalary(String basicSalary) {
		this.basicSalary = basicSalary;
	}
	public String getAccumulationFund() {
		return accumulationFund;
	}
	public void setAccumulationFund(String accumulationFund) {
		this.accumulationFund = accumulationFund;
	}
	public String getMedicalInsurance() {
		return medicalInsurance;
	}
	public void setMedicalInsurance(String medicalInsurance) {
		this.medicalInsurance = medicalInsurance;
	}
	public String getSubsidy() {
		return subsidy;
	}
	public void setSubsidy(String subsidy) {
		this.subsidy = subsidy;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public String getPersonal() {
		return personal;
	}
	public void setPersonal(String personal) {
		this.personal = personal;
	}
	public String getSalaryTime() {
		return salaryTime;
	}
	public void setSalaryTime(String salaryTime) {
		this.salaryTime = salaryTime;
	}
	
	
}
