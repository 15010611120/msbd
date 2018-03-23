package com.yxd.model;

import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name="hkrt_ams_terminal")
public class TerModel {

	public String agentCnName;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private String id;
	private String terminalId;
	private Date createTime;
	private String manufactureFirm;
	private Short terminalBatchNo;
	private String merchantId;
	private String contractNo;
	private String agentId;
	private Short isAgentProm;
	private String status;
	private String statusCode;
	private String machineTerminalId;
	private String terminalModel;
	private Short biosVersion;
	private String signStatus;
	private Date lastSignTime;
	private Date agentBindTime;
	private String psamCardNo;
	private String terminalTraceNo;
	private String terminalBatchNos;
	private String terminalInvoiceNo;
	private String effectStatus;
	private String datakey;
	private String pinkey;
	private String mackey;
	private String tckkey;
	private String env;
	private String assembledkey;
	private String pubkey;
	private String bankid;
	private String psamid;
	private String terminalModelCode;
	private String merchantCnName;
	private String agentType;
	private String merchantPhone;
	private String firstAgentName;
	private String agentLvl;
	
	
	public String getFirstAgentName() {
		return firstAgentName;
	}
	public void setFirstAgentName(String firstAgentName) {
		this.firstAgentName = firstAgentName;
	}
	public String getAgentLvl() {
		return agentLvl;
	}
	public void setAgentLvl(String agentLvl) {
		this.agentLvl = agentLvl;
	}
	public String getTerminalModelCode() {
		return terminalModelCode;
	}
	public void setTerminalModelCode(String terminalModelCode) {
		this.terminalModelCode = terminalModelCode;
	}
	public String getDatakey() {
		return datakey;
	}
	public void setDatakey(String datakey) {
		this.datakey = datakey;
	}
	public String getMerchantCnName() {
		return merchantCnName;
	}
	public void setMerchantCnName(String merchantCnName) {
		this.merchantCnName = merchantCnName;
	}
	public String getEffectStatus() {
		return effectStatus;
	}
	public void setEffectStatus(String effectStatus) {
		this.effectStatus = effectStatus;
	}
	public String getTerminalBatchNos() {
		return terminalBatchNos;
	}
	public void setTerminalBatchNos(String terminalBatchNos) {
		this.terminalBatchNos = terminalBatchNos;
	}
	private String statusName;
	private Date lastModifiedTime;
	private String lastModifiedUser;
	private String createUser;
	private String crateUserName;
	private String lastModifiedUserName;
	private String agentShortName;
	private String isTerminalByMerchant;
	private String settleMerchantId;
	private String terminalStatus;
	private String terminalStatusCode;
	private String signStatusName;
	private String settleMerchantName;
	private Date merchantBindTime;
	private String remarks;
	private String systemTerminalId;
	
	public String getIsTerminalByMerchant() {
		return isTerminalByMerchant;
	}
	public void setIsTerminalByMerchant(String isTerminalByMerchant) {
		this.isTerminalByMerchant = isTerminalByMerchant;
	}
	
	public String getSystemTerminalId() {
		return systemTerminalId;
	}
	public void setSystemTerminalId(String systemTerminalId) {
		this.systemTerminalId = systemTerminalId;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public String getPinkey() {
		return pinkey;
	}
	public void setPinkey(String pinkey) {
		this.pinkey = pinkey;
	}
	public String getMackey() {
		return mackey;
	}
	public void setMackey(String mackey) {
		this.mackey = mackey;
	}
	public String getTckkey() {
		return tckkey;
	}
	public void setTckkey(String tckkey) {
		this.tckkey = tckkey;
	}
	public String getEnv() {
		return env;
	}
	public void setEnv(String env) {
		this.env = env;
	}
	public String getAssembledkey() {
		return assembledkey;
	}
	public void setAssembledkey(String assembledkey) {
		this.assembledkey = assembledkey;
	}
	public String getPubkey() {
		return pubkey;
	}
	public void setPubkey(String pubkey) {
		this.pubkey = pubkey;
	}
	public String getBankid() {
		return bankid;
	}
	public void setBankid(String bankid) {
		this.bankid = bankid;
	}
	public String getPsamid() {
		return psamid;
	}
	public void setPsamid(String psamid) {
		this.psamid = psamid;
	}
	public Date getMerchantBindTime() {
		return merchantBindTime;
	}
	public void setMerchantBindTime(Date merchantBindTime) {
		this.merchantBindTime = merchantBindTime;
	}
	public String getSettleMerchantName() {
		return settleMerchantName;
	}
	public void setSettleMerchantName(String settleMerchantName) {
		this.settleMerchantName = settleMerchantName;
	}
	public String getSignStatusName() {
		return signStatusName;
	}
	public void setSignStatusName(String signStatusName) {
		this.signStatusName = signStatusName;
	}
	public String getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}
	public String getTerminalStatus() {
		return terminalStatus;
	}
	public void setTerminalStatus(String terminalStatus) {
		this.terminalStatus = terminalStatus;
	}
	public String getTerminalStatusCode() {
		return terminalStatusCode;
	}
	public void setTerminalStatusCode(String terminalStatusCode) {
		this.terminalStatusCode = terminalStatusCode;
	}
	public String getSettleMerchantId() {
		return settleMerchantId;
	}
	public void setSettleMerchantId(String settleMerchantId) {
		this.settleMerchantId = settleMerchantId;
	}
	public String getAgentCnName() {
		return agentCnName;
	}
	public void setAgentCnName(String agentCnName) {
		this.agentCnName = agentCnName;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTerminalId() {
		return terminalId;
	}
	public void setTerminalId(String terminalId) {
		this.terminalId = terminalId;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public String getManufactureFirm() {
		return manufactureFirm;
	}
	public void setManufactureFirm(String manufactureFirm) {
		this.manufactureFirm = manufactureFirm;
	}
	public Short getTerminalBatchNo() {
		return terminalBatchNo;
	}
	public void setTerminalBatchNo(Short terminalBatchNo) {
		this.terminalBatchNo = terminalBatchNo;
	}
	public String getMerchantId() {
		return merchantId;
	}
	public void setMerchantId(String merchantId) {
		this.merchantId = merchantId;
	}
	public String getContractNo() {
		return contractNo;
	}
	public void setContractNo(String contractNo) {
		this.contractNo = contractNo;
	}
	public String getAgentId() {
		return agentId;
	}
	public void setAgentId(String agentId) {
		this.agentId = agentId;
	}
	public Short getIsAgentProm() {
		return isAgentProm;
	}
	public void setIsAgentProm(Short isAgentProm) {
		this.isAgentProm = isAgentProm;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getMachineTerminalId() {
		return machineTerminalId;
	}
	public void setMachineTerminalId(String machineTerminalId) {
		this.machineTerminalId = machineTerminalId;
	}
	public String getTerminalModel() {
		return terminalModel;
	}
	public void setTerminalModel(String terminalModel) {
		this.terminalModel = terminalModel;
	}
	public Short getBiosVersion() {
		return biosVersion;
	}
	public void setBiosVersion(Short biosVersion) {
		this.biosVersion = biosVersion;
	}
	public String getSignStatus() {
		return signStatus;
	}
	public void setSignStatus(String signStatus) {
		this.signStatus = signStatus;
	}
	public Date getLastSignTime() {
		return lastSignTime;
	}
	public void setLastSignTime(Date lastSignTime) {
		this.lastSignTime = lastSignTime;
	}
	public Date getAgentBindTime() {
		return agentBindTime;
	}
	public void setAgentBindTime(Date agentBindTime) {
		this.agentBindTime = agentBindTime;
	}
	public String getPsamCardNo() {
		return psamCardNo;
	}
	public void setPsamCardNo(String psamCardNo) {
		this.psamCardNo = psamCardNo;
	}
	public String getTerminalTraceNo() {
		return terminalTraceNo;
	}
	public void setTerminalTraceNo(String terminalTraceNo) {
		this.terminalTraceNo = terminalTraceNo;
	}
	public String getTerminalInvoiceNo() {
		return terminalInvoiceNo;
	}
	public void setTerminalInvoiceNo(String terminalInvoiceNo) {
		this.terminalInvoiceNo = terminalInvoiceNo;
	}
	
	public String getStatusName() {
		return statusName;
	}
	public void setStatusName(String statueName) {
		this.statusName = statueName;
	}
	public String getCreateUser() {
		return createUser;
	}
	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}
	public Date getLastModifiedTime() {
		return lastModifiedTime;
	}
	public void setLastModifiedTime(Date lastModifiedTime) {
		this.lastModifiedTime = lastModifiedTime;
	}
	public String getLastModifiedUser() {
		return lastModifiedUser;
	}
	public void setLastModifiedUser(String lastModifiedUser) {
		this.lastModifiedUser = lastModifiedUser;
	}
	public String getCrateUserName() {
		return crateUserName;
	}
	public void setCrateUserName(String crateUserName) {
		this.crateUserName = crateUserName;
	}
	public String getLastModifiedUserName() {
		return lastModifiedUserName;
	}
	public void setLastModifiedUserName(String lastModifiedUserName) {
		this.lastModifiedUserName = lastModifiedUserName;
	}
	
	public String getAgentShortName() {
		return agentShortName;
	}
	public void setAgentShortName(String agentShortName) {
		this.agentShortName = agentShortName;
	}	
	
	public String getAgentType() {
		return agentType;
	}
	public void setAgentType(String agentType) {
		this.agentType = agentType;
	}
	
	public String getMerchantPhone() {
		return merchantPhone;
	}
	public void setMerchantPhone(String merchantPhone) {
		this.merchantPhone = merchantPhone;
	}
	public TerModel() {
	}
	public TerModel(String agentCnName, String id, String terminalId,
			Date createTime, String manufactureFirm, Short terminalBatchNo,
			String merchantId, String contractNo, String agentId,
			Short isAgentProm, String status, String machineTerminalId,
			String terminalModel, Short biosVersion, String signStatus,
			Date lastSignTime, Date agentBindTime, String psamCardNo,
			String terminalTraceNo, String terminalInvoiceNo,
			String statusName, Date lastModifiedTime,
			String lastModifiedUser, String createUser, String crateUserName,
			String lastModifiedUserName,String agentShortName) {
		super();
		this.agentCnName = agentCnName;
		this.id = id;
		this.terminalId = terminalId;
		this.createTime = createTime;
		this.manufactureFirm = manufactureFirm;
		this.terminalBatchNo = terminalBatchNo;
		this.merchantId = merchantId;
		this.contractNo = contractNo;
		this.agentId = agentId;
		this.isAgentProm = isAgentProm;
		this.status = status;
		this.machineTerminalId = machineTerminalId;
		this.terminalModel = terminalModel;
		this.biosVersion = biosVersion;
		this.signStatus = signStatus;
		this.lastSignTime = lastSignTime;
		this.agentBindTime = agentBindTime;
		this.psamCardNo = psamCardNo;
		this.terminalTraceNo = terminalTraceNo;
		this.terminalInvoiceNo = terminalInvoiceNo;
		this.statusName = statusName;
		this.lastModifiedTime = lastModifiedTime;
		this.lastModifiedUser = lastModifiedUser;
		this.createUser = createUser;
		this.crateUserName = crateUserName;
		this.lastModifiedUserName = lastModifiedUserName;
		this.agentShortName = agentShortName;
	}
	
	
}
