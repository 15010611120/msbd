package com.yxd.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Part;
import javax.mail.Session;
import javax.mail.Store;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.yxd.model.CreditCardBill;

/**
 * javaMail邮件解析
 * @author Xiaodong
 *
 */
@Controller
public class ParserMailController {
	
	protected static Logger logger4J = Logger.getLogger(ParserMailController.class.getSimpleName());
	
	List<CreditCardBill> cardBillList= new ArrayList<CreditCardBill>(); 

	private String isChangeBill;
	
	@RequestMapping("/getLoginMailJump")
	@ResponseBody
	public ModelAndView springBootLoginjump(HttpSession session){
		ModelAndView mv = new ModelAndView("/pages/order/cardBill");
		return mv;
	}
	/**
	  * 模拟邮箱登陆
	  * @param userName
	  * @param pwd
	  * @return
	  * @throws Exception
	  */
	 @ResponseBody
	 @RequestMapping(value = "/parsingCreditCardBills")
	 public ModelAndView parsingCreditCardBills(HttpServletRequest request,String usersName,String pwds)throws Exception {
		 logger4J.debug("Entering parsingCreditCardBills.service() >>>>>>>>>>>>>>>>>>>>>start<<<<<<<<<<<<<<<<<<<<<<");
		 List<CreditCardBill> cardBillLists= new ArrayList<CreditCardBill>(); 
		 Properties props=new Properties();
		 String host = checkMailHost(usersName);
		 long dat = System.currentTimeMillis();
		 if("".equals(host)){
//			 message.setCode("01");
//			 message.setMsg("暂不支持该邮箱");
			 ModelAndView mv = new ModelAndView("/pages/order/cardBill","list",cardBillLists);
			 return mv; 
		 }
		 //设置邮件接收协议为pop3 
		 props.setProperty("mail.store.protocol", "pop3");  
		 props.setProperty("mail.pop3.host", host); 
		 
		 Session session = Session.getInstance(props);  
		 Store store = session.getStore("pop3");  
		 try {
			// 解密
//			pwd = AesUtil.decryptStr(pwd);
			//登陆邮箱
			store.connect(host, usersName, pwds);
		} catch (MessagingException e) {
			String error = e.toString();
			String erro = "auth error";
			String erro1 = "pop3 nosupport";
			String erro2 = "Unable to log on";
			String erro3 = "Connect failed";
			String erro4 = "ERR.LOGIN.PASSERR";
			String erro5 = "ÄúÃ»ÓÐÈ¨ÏÞÊ¹ÓÃpop3¹¦ÄÜ";
			int a = error.indexOf(erro);
			int b = error.indexOf(erro1);
			int c = error.indexOf(erro2);
			int d = error.indexOf(erro3);
			int ee = error.indexOf(erro4);
			int f = error.indexOf(erro5);
			if(a>=0 || ee>=0){
//				message.setCode("01");
//				message.setMsg("用户名或密码错误");
				logger4J.info("Entering parsingCreditCardBills.service()******************用户名或密码错误**************");
//				message.setObj(cardBillList);
				store.close();
				ModelAndView mv = new ModelAndView("/pages/order/cardBill","list",cardBillLists);
				return mv; 
			}else if(b>=0){
//				message.setCode("01");
//				message.setMsg("请开启新浪邮箱的pop3服务");
				logger4J.info("Entering parsingCreditCardBills.service()***************开启邮箱的pop3服务******************");
//				message.setObj(cardBillList);
				store.close();
				ModelAndView mv = new ModelAndView("/pages/order/cardBill","list",cardBillLists);
				return mv; 
			}else if(c>=0){
//				message.setCode("01");
//				message.setMsg("无法登陆！！！请检查用户名和密码");
//				message.setObj(cardBillList);
				store.close();
				logger4J.info("Entering parsingCreditCardBills.service()************************无法登陆******************");
				ModelAndView mv = new ModelAndView("/pages/order/cardBill","list",cardBillLists);
				return mv; 
			}else if(d>=0){
//				message.setCode("01");
//				message.setMsg("无法连接请检查用户名地址");
//				message.setObj(cardBillList);
				store.close();
				logger4J.info("Entering parsingCreditCardBills.service()************************无法连接请检查用户名地址******************");
				ModelAndView mv = new ModelAndView("/pages/order/cardBill","list",cardBillLists);
				return mv; 
			}else if(f>=0){
//				message.setCode("01");
//				message.setMsg("请开启163邮箱的pop3服务");
//				message.setObj(cardBillList);
				store.close();
				logger4J.info("Entering parsingCreditCardBills.service()************************请开启163邮箱的pop3服务******************");
				ModelAndView mv = new ModelAndView("/pages/order/cardBill","list",cardBillLists);
				return mv; 
			}
			e.printStackTrace();
		}
		 Folder folder = store.getFolder("inbox");  
		 //设置邮件可读可写  
		 folder.open(Folder.READ_WRITE);  
		 int s = folder.getMessageCount();
		 //设置邮件读取条数
		 Message[] messages ;
		 if(s>100){
			 messages = folder.getMessages(s-50, s);
		 }else if(s<=100&&s>=50){
			 messages = folder.getMessages(s-50, s);
		 }else{
			 messages = folder.getMessages();  
		 }
	//	 Message[] messages = search(folder); 
		 for (int i = 0; i < messages.length; i++) {  
			 try {
				 long b = System.currentTimeMillis();
				 long interval = (b - dat)/1000;
				 if(interval>30){//超时30秒
//					 message.setCode("01");
//					 message.setMsg("读取邮件失败");
					 cardBillList.clear();
					 isChangeBill=null;
					 folder.close(true);  
					 store.close();
					 ModelAndView mv = new ModelAndView("/pages/order/cardBill","list",cardBillLists);
					 return mv; 
				 }
				//解析发件时间
				 Date currentTime = (Date) messages[i].getSentDate();
				 Date dayNow=new Date();
				 Calendar calendar = Calendar.getInstance();    
				 calendar.setTime(dayNow);    
				 calendar.add(Calendar.MONTH, -1);//当前时间前去一个月，即一个月前的时间    
				 Date day = calendar.getTime();//获取当前时间前去一个月，即一个月前的时间  
				 if(currentTime.getTime()<=dayNow.getTime() && currentTime.getTime()>day.getTime()){
					 //处理账单日
					 SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
					 String dateString = formatter.format(currentTime);
					 Date beginDate = formatter.parse(dateString); 
				     Calendar date = Calendar.getInstance();
					 date.setTime(beginDate);
					 date.set(Calendar.DATE, date.get(Calendar.DATE) - 1);
					 dateString = formatter.format(date.getTime());
					 //解析邮件主题  
					 String subject = messages[i].getSubject();
					 //解析发件人地址  
					 String address = messages[i].getFrom()[0].toString();
					 int adr = address.indexOf("<");
					 if(adr>=0){//邮箱地址是否存在尖括号<>
						 String ads =address.substring(address.indexOf("<")+1,address.indexOf(">"));
						 cardBillList=bankMailboxAddress(ads,dateString,messages[i],subject);
						 //获取到的账单数据
						 if(cardBillList.size()>0 && cardBillList.get(0).getBill()!=null 
								 && !"".equals(cardBillList.get(0).getBill())){
							 cardBillLists.add(cardBillList.get(0));
						 }
						 cardBillList.clear();
					 }else{
						 cardBillList=bankMailboxAddress(address,dateString,messages[i],subject);
						 if(cardBillList.size()>0 && cardBillList.get(0).getBill()!=null 
								 && !"".equals(cardBillList.get(0).getBill())){
							 cardBillLists.add(cardBillList.get(0));
						 }
						 cardBillList.clear();
					 } 
				 }
			} catch (Exception e) {
				logger4J.info("Entering parsingBills()******************邮箱中存在问题邮件******************");
				e.printStackTrace();
//				message.setCode("01");
//				message.setMsg("邮箱中存在问题邮件");
				ModelAndView mv = new ModelAndView("/pages/order/cardBill","list",cardBillLists);
				return mv; 
			}
			 
		 }
		 if(cardBillLists.size()==0 && isChangeBill!=null){
//			 message.setCode("01");
//			 message.setMsg(isChangeBill+"发生更新，暂不可导入");
		 }else if(cardBillLists.size()==0){
			 /*message.setCode("01");
			 message.setMsg("本月暂无账单");
			 message.setIsSuccess(true);*/
		 }else{
//			 message.setCode("00");
//			 message.setMsg("解析账单成功");
//			 message.setIsSuccess(true);
		 }
		 isChangeBill=null;
		 folder.close(true);  
		 store.close();
//		 message.setObj(cardBillLists);
		 request.setAttribute("userNames", usersName);
		 request.setAttribute("pwds", pwds);
		 logger4J.debug("Entering parsingCreditCardBills.service() >>>>>>>>>>>>>>>>>>>>>end<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
		 ModelAndView mv = new ModelAndView("/pages/order/cardBill","list",cardBillLists);
		 return mv; 
	}
	 
	 /**
	  * 解析综合数据  
	  * @param part
	  * @param dateString
	  * @param bankName
	  * @throws Exception
	  */
	 private List<CreditCardBill> getAllMultipart(Part part,String dateString,String bankName) throws Exception{  
		 CreditCardBill cardBill = new CreditCardBill();
		 String contentType = part.getContentType();  
		 int index = contentType.indexOf("name");  
		 boolean conName = false;  
		 if(index!=-1){  
			 conName=true;  
		 }  
		 //判断part类型  
		 if (part.isMimeType("text/html") && ! conName) {  
			 String acont = part.getContent().toString().trim();
			 acont = replaceBlank(acont);
			 cardBill = getParsingBills(bankName,acont,dateString);
			 cardBillList.add(cardBill);
		 }else if (part.isMimeType("multipart/*")) {
			 Multipart multipart = (Multipart) part.getContent(); 
//			 String acont = part.getContent().toString().trim();
//			 System.out.println(acont);
			 int counts = multipart.getCount();  
			 for (int i = 0; i < counts; i++) {
				 //递归获取数据 
				 getAllMultipart(multipart.getBodyPart(i),dateString,bankName);  
			 }  
	  	 }
		 return cardBillList; 
	 }
	 
	 /**
	  * 处理:为指定银行邮箱地址
	  * @param ads
	  * @param dateString
	  * @param messages
	  * @return
	  * @throws Exception
	  */
	 public List<CreditCardBill> bankMailboxAddress(String ads,String dateString,Part messages,String subject) throws Exception{
		 String bankName ="";
		 if(subject.indexOf("招商银行信用卡电子账单")>=0 && ads!=null && !"".equals(ads) && ads.equals("ccsvc@message.cmbchina.com")){// ccsvc@message.cmbchina.com 招商银行
			 bankName = "招商银行";
		 }else if(subject.indexOf("交通银行信用卡电子账单")>=0 && ads!=null && !"".equals(ads) && ads.equals("PCCC@bocomcc.com")){// PCCC@bocomcc.com 交通银行信用卡账单邮箱地址
			 bankName = "交通银行";
		 }else if(subject.indexOf("中国工商银行客户对账单")>=0 && ads!=null && !"".equals(ads) && ads.equals("webmaster@icbc.com.cn")){// webmaster<webmaster@icbc.com.cn> 工商银行信用卡账单邮箱地址
			 bankName = "中国工商银行";
		 }else if(subject.indexOf("中信银行信用卡电子账单")>=0 && ads!=null && !"".equals(ads) && ads.equals("citiccard@citiccard.com")){//  <citiccard@citiccard.com> 中信银行信用卡账单邮箱地址
			 bankName = "中信银行";
	     }else if(subject.indexOf("中国农业银行金穗信用卡电子对账单")>=0 && ads!=null && !"".equals(ads) && ads.equals("e-statement@creditcard.abchina.com")){//  e-statement@creditcard.abchina.com 中国农业银行信用卡账单邮箱地址
			 bankName = "中国农业银行";
	     }else if(subject.indexOf("民生信用卡")>=0 && subject.indexOf("电子对账单")>=0 && subject.indexOf("民生信用卡")>=0 && subject.indexOf("电子对账单")>=0 && ads!=null && !"".equals(ads) && ads.equals("master@creditcard.cmbc.com.cn")){//  master@creditcard.cmbc.com.cn 中国民生银行信用卡账单邮箱地址
			 bankName = "中国民生银行";
	     }else if(subject.indexOf("中国建设银行信用卡电子账单")>=0 && ads!=null && !"".equals(ads) && ads.equals("service@vip.ccb.com")){//  <service@vip.ccb.com> 建设银行信用卡账单邮箱地址
			 bankName = "中国建设银行";
	     }else if(subject.indexOf("浦发银行-信用卡电子账单")>=0 && ads!=null && !"".equals(ads) && ads.equals("estmtservice@eb.spdbccc.com.cn")){//  estmtservice<estmtservice@eb.spdbccc.com.cn>
			 bankName = "浦发银行";
	     }else if(subject.indexOf("浦发银行-信用卡电子账单")>=0 && ads!=null && !"".equals(ads) && ads.equals("浦发银行信用卡中心")){//浦发银行信用卡中心
			 bankName = "浦发银行";
	     }
		 
		 
		//测试邮箱地址
			if(ads!=null && !"".equals(ads) && ads.equals("17610583197@163.com")){//  <citiccard@citiccard.com> 中信银行信用卡账单邮箱地址
				 bankName = "中国民生银行";
		    }else if(ads!=null && !"".equals(ads) && ads.equals("1753711102@qq.com")){//  <citiccard@citiccard.com> 中信银行信用卡账单邮箱地址
				 bankName = "中国农业银行";
		    }else if(ads!=null && !"".equals(ads) && ads.equals("yangxiaodong@hkrt.cn")){//  <citiccard@citiccard.com> 中信银行信用卡账单邮箱地址
				 bankName = "交通银行";
		    }else 
		    	if(ads!=null && !"".equals(ads) && ads.equals("1099025603@qq.com")){//  <citiccard@citiccard.com> 中信银行信用卡账单邮箱地址
				 bankName = "中信银行";
		    }else if(ads!=null && !"".equals(ads) && ads.equals("2463377815@qq.com")){//  <citiccard@citiccard.com> 中信银行信用卡账单邮箱地址
				 bankName = "中国工商银行";
		    }
		 
		 if (!"".equals(bankName)){
//			 yxd
			 logger4J.debug("Entering parsingBills()***************开始解析"+bankName+"信用卡账单******************");
			 cardBillList = getAllMultipart(messages,dateString,bankName);
		 }
		return cardBillList;
		 
	 }
	 
	 /**
	  * 准备解析对应银行账单
	  * @param bankName
	  * @param acont
	  * @param dateString
	  * @return
	  */
	 public CreditCardBill getParsingBills(String bankName,String acont,String dateString){
		 CreditCardBill cardBill = new CreditCardBill();
		 if("招商银行".equals(bankName)){
			 cardBill = parsingCMBBills(acont,dateString,bankName);
		 }else if("交通银行".equals(bankName)){
			 cardBill = parsingBCMBillsHtml(acont,dateString,bankName);
		 }else if("中国工商银行".equals(bankName)){
			 cardBill = parsingICBCBillsHtml(acont,dateString,bankName);
		 }else if("中信银行".equals(bankName)){
			 cardBill = parsingCITICBillsHtml(acont,dateString,bankName);
		 }else if("中国农业银行".equals(bankName)){
			 cardBill = parsingABCBillsHtml(acont,dateString,bankName);
		 }else if("中国民生银行".equals(bankName)){
			 cardBill = parsingCMBCBillsHtml(acont,dateString,bankName);
		 }else if("浦发银行".equals(bankName)){
			 cardBill = parsingSPDBBillsHtml(acont,dateString,bankName);
		 }else if("中国建设银行".equals(bankName)){
			 cardBill = parsingCCBBillsHtml(acont,dateString,bankName);
		 }
//		 yxd
		 logger4J.debug("Entering parsingBills()***************解析"+bankName+"信用卡账单结束******************");
		return cardBill;
		 
	 }
	 /**
	  * 解析招商银行账单数据
	  * @param acont 邮件文本内容
	  * @param billDay 发件时间
	  * @param bankName 银行名称
	  */
	 public CreditCardBill parsingCMBBills(String acont,String billDay,String bankName){
		 CreditCardBill cardBill= new CreditCardBill();
		 String repayDay = "";//还款日
		 String bill="";//已出账单
		 String minRepay="";//最低还款
		 String quota=""; //额度
		 try {
			 /*repayDay =acont.substring(acont.indexOf("/")-2,acont.indexOf("/")+3);
			 repayDay = repayDay.replaceAll("/", "-");*/
			 
			 int u=acont.indexOf("<font")+5;//1
			 int uu1=acont.indexOf("<font",u+1)+5;//2
			 int uu2=acont.indexOf("<font",uu1+1)+5;//3
			 int uu =acont.indexOf("/",uu2)+3;//5
			 //还款日
			 repayDay =acont.substring(uu-5,uu);
	 		 repayDay = repayDay.replaceAll("/", "-");
			 //还款日去掉“-” 不是纯数字表示账单样式发生变化
			 if(repayDay.replaceAll("-", "").matches("[0-9]+")==false){
	 			isChangeBill = isBillChange(3,acont,bankName);
				return cardBill;
		 	 }
			 if(repayDay.length()==5){
				 repayDay = AnnualTreatment(repayDay,billDay);
			 }
			 //已出账单--数据
			 int aindex = acont.indexOf(".",acont.indexOf("￥"))+3;//第一个￥后面的.的位置
			 bill =acont.substring(acont.indexOf("￥")+1,aindex);
			 bill = bill.replaceAll("[,]", "");
			 if (bill.replaceAll("(?:-|\\.)", "").matches("[0-9]+")==false) {
				 isChangeBill = isBillChange(1,acont,bankName);
				 return cardBill;
			 }
			 //最低还款--数据
			 int a1=acont.indexOf("￥",acont.indexOf("￥")+1);
			 int a2=acont.indexOf(".",acont.indexOf("￥",acont.indexOf("￥")+1))+3;
			 minRepay = acont.substring(a1+1,a2);
			 minRepay = minRepay.replaceAll("[,]", "").replaceAll(",", "").replaceAll(",", "");
			 if (minRepay.replaceAll("[,]", "").replaceAll("[.]", "").matches("[0-9]+")==false) {
				 isChangeBill = isBillChange(2,acont,bankName);
				 return cardBill;
			 }

			 cardBill.setBankName(bankName);
			 cardBill.setBill(bill);
			 cardBill.setBillDay(billDay);
			 cardBill.setRepayDay(repayDay);
			 cardBill.setMinRepay(minRepay);
			 cardBill.setBankCardNo("");
			 cardBill.setQuota(quota);
		} catch (Exception e) {
			e.printStackTrace();
			if(isChangeBill==null){
	 			isChangeBill=bankName+"账单";
			}else{
				isChangeBill=isChangeBill+"and"+bankName+"账单";
			}
			logger4J.info("Entering parsingCMBBills()******************解析招商银行账单错误***"+acont+"***************");
		}
		return cardBill;
	 }
	 
	 /**
	  * 解析浦发银行账单数据(text字符串)
	  * @param acont 邮件文本内容
	  * @param billDay 发件时间
	  * @param bankName 银行名称
	  */
	 public CreditCardBill parsingSPDBBills(String acont,String billDay,String bankName){
		 CreditCardBill cardBill= new CreditCardBill();
		 String repayDay = "";//还款日
		 String bill="";//已出账单
		 String minRepay="";//最低还款
		 String quota = "";//额度
		 try {
			 //还款日
			 repayDay = acont.substring(acont.lastIndexOf("：")+1,acont.lastIndexOf("|")-7);
			 repayDay = repayDay.replaceAll("/", "-");
			 if(repayDay.replaceAll("-", "").matches("[0-9]+")==false){
	 			isChangeBill = isBillChange(3,acont,bankName);
				return cardBill;
			 }
			 
			 //应还款额
			 String sub_acont = acont.substring(acont.indexOf("￥"));
			 bill = sub_acont.substring(sub_acont.indexOf("￥")+1,sub_acont.indexOf(".")+3);
			 bill = bill.replaceAll(",", "");
			 if (bill.replaceAll("(?:-|\\.)", "").matches("[0-9]+")==false) {
				 isChangeBill = isBillChange(1,acont,bankName);
				 return cardBill;
			 }
			 cardBill.setBankName(bankName);
			 cardBill.setBill(bill);
			 cardBill.setBillDay(billDay);
			 cardBill.setRepayDay(repayDay);
			 cardBill.setMinRepay(minRepay);
			 cardBill.setBankCardNo("");
			 cardBill.setQuota(quota);
		} catch (Exception e) {
			e.printStackTrace();
			if(isChangeBill==null){
	 			isChangeBill=bankName+"账单";
			}else{
				isChangeBill=isChangeBill+"and"+bankName+"账单";
			}
			logger4J.info("Entering parsingCMBBills()******************解析浦发银行账单错误***"+acont+"***************");
		}
		return cardBill;
	 }
	 /**
	  * 解析浦发银行账单数据(html字符串)
	  * @param acont 邮件文本内容
	  * @param billDay 发件时间
	  * @param bankName 银行名称
	  */
	 public CreditCardBill parsingSPDBBillsHtml(String acont,String billDay,String bankName){
		 CreditCardBill cardBill= new CreditCardBill();
		 String repayDay = "";//还款日
		 String bill="";//已出账单
		 String minRepay="";//最低还款
		 String quota = "";//额度
		 try {
			 //还款日
			 int ide = acont.indexOf(">到期还款日：");
			 int ide2 = acont.indexOf("</td>", ide);
			 repayDay = acont.substring(ide+7,ide2);
			 repayDay = repayDay.replaceAll("/", "-");
			 if(repayDay.replaceAll("-", "").matches("[0-9]+")==false){
				 isChangeBill = isBillChange(3,acont,bankName);
				 return cardBill;
			 }
			 
			 //应还款额
			 int index = acont.indexOf("￥", acont.indexOf("本期应还款总额：</td>"));
			 int index2 = acont.indexOf("</td>", index);
			 bill = acont.substring(index+1,index2);
			 bill = bill.replaceAll(",", "");
			 if (bill.replaceAll("(?:-|\\.)", "").matches("[0-9]+")==false) {
				 isChangeBill = isBillChange(1,acont,bankName);
				 return cardBill;
			 }
			 cardBill.setBankName(bankName);
			 cardBill.setBill(bill);
			 cardBill.setBillDay(billDay);
			 cardBill.setRepayDay(repayDay);
			 cardBill.setMinRepay(minRepay);
			 cardBill.setBankCardNo("");
			 cardBill.setQuota(quota);
		 } catch (Exception e) {
			 e.printStackTrace();
			 if(isChangeBill==null){
				 isChangeBill=bankName+"账单";
			 }else{
				 isChangeBill=isChangeBill+"and"+bankName+"账单";
			 }
			 logger4J.info("Entering parsingCMBBills()******************解析浦发银行账单错误***"+acont+"***************");
		 }
		 return cardBill;
	 }
	 
	 
	 /**
	  * 解析建设银行账单数据（text字符串）
	  * @param acont 邮件文本内容
	  * @param billDay 发件时间
	  * @param bankName 银行名称
	  */
	 public CreditCardBill parsingCCBBills(String acont,String billDay,String bankName){
		 CreditCardBill cardBill= new CreditCardBill();
		 String repayDay = "";//还款日
		 String bill="";//已出账单
		 String minRepay="";//最低还款
		 String quota = "";//额度
		 try {
			 //还款日
			 repayDay = acont.substring(acont.indexOf("请于")+2,acont.indexOf("日前还款"));
			 repayDay = repayDay.replace("年", "-");
			 repayDay = repayDay.replace("月", "-");
			 if(repayDay.replaceAll("-", "").matches("[0-9]+")==false){
	 			isChangeBill = isBillChange(3,acont,bankName);
				return cardBill;
			 }
			 
			 //应还款额
			 acont = acont.substring(acont.indexOf("CNY"),acont.indexOf("USD"));
			 String[] acontArray = acont.split("\\|");
			 bill = acontArray[4];
			 bill = bill.replaceAll(",", "");
			 if (bill.replaceAll("(?:-|\\.)", "").matches("[0-9]+")==false) {
				 isChangeBill = isBillChange(1,acont,bankName);
				 return cardBill;
			 }

			 cardBill.setBankName(bankName);
			 cardBill.setBill(bill);
			 cardBill.setBillDay(billDay);
			 cardBill.setRepayDay(repayDay);
			 cardBill.setMinRepay(minRepay);
			 cardBill.setBankCardNo("");
			 cardBill.setQuota(quota);
		} catch (Exception e) {
			e.printStackTrace();
			if(isChangeBill==null){
	 			isChangeBill=bankName+"账单";
			}else{
				isChangeBill=isChangeBill+"and"+bankName+"账单";
			}
			logger4J.info("Entering parsingCMBBills()******************解析中国建设银行账单错误***"+acont+"***************");
		}
		return cardBill;
	 }
	 /**
	  * 解析建设银行账单数据（html字符串）
	  * @param acont 邮件文本内容
	  * @param billDay 发件时间
	  * @param bankName 银行名称
	  */
	 public CreditCardBill parsingCCBBillsHtml(String acont,String billDay,String bankName){
		 CreditCardBill cardBill= new CreditCardBill();
		 String repayDay = "";//还款日
		 String bill="";//已出账单
		 String minRepay="";//最低还款
		 String quota = "";//额度
		 try {
			 //还款日
			 repayDay = acont.substring(acont.indexOf("日</font></b>前还款")-10,acont.indexOf("日</font></b>前还款"));
			 repayDay = repayDay.replace("年", "-");
			 repayDay = repayDay.replace("月", "-");
			 if(repayDay.replaceAll("-", "").matches("[0-9]+")==false){
				 isChangeBill = isBillChange(3,acont,bankName);
				 return cardBill;
			 }
			 
			 //应还款额
			 int index = acont.indexOf("<b>", acont.indexOf("CNY"));
			 int index2 = acont.indexOf("</b>", index);
			 bill = acont.substring(index+3,index2);
			 bill = bill.replaceAll(",", "");
			 if (bill.replaceAll("(?:-|\\.)", "").matches("[0-9]+")==false) {
				 isChangeBill = isBillChange(1,acont,bankName);
				 return cardBill;
			 }
			 
			 cardBill.setBankName(bankName);
			 cardBill.setBill(bill);
			 cardBill.setBillDay(billDay);
			 cardBill.setRepayDay(repayDay);
			 cardBill.setMinRepay(minRepay);
			 cardBill.setBankCardNo("");
			 cardBill.setQuota(quota);
		 } catch (Exception e) {
			 e.printStackTrace();
			 if(isChangeBill==null){
				 isChangeBill=bankName+"账单";
			 }else{
				 isChangeBill=isChangeBill+"and"+bankName+"账单";
			 }
			 logger4J.info("Entering parsingCMBBills()******************解析中国建设银行账单错误***"+acont+"***************");
		 }
		 return cardBill;
	 }
	 
	 /**
	  * 解析交通银行账单数据
	  * @param acont 邮件文本内容
	  * @param billDay 发件时间
	  * @param bankName 银行名称
	  */
	 public CreditCardBill parsingBCMBills(String acont,String billDay,String bankName){
	 	 CreditCardBill cardBill= new CreditCardBill();
	 	 String repayDay = "";//还款日
	 	 String bill="";//已出账单
	 	 String minRepay="";//最低还款
	 	 String cardNo="";//银行卡号
	 	 try {
	 		 //银行卡号
	 		 cardNo = acont.substring(acont.indexOf("交通银行银联标准卡号:")+11,acont.indexOf("账单周期"));
	 		 cardNo = cardNo.substring(cardNo.length()-4,cardNo.length()); 
	 		 if (cardNo.matches("[0-9]+")==false || cardNo.length()!=4) {
				 isChangeBill = isBillChange(0,acont,bankName);
				 return cardBill;
			 }
	 		 //还款日
	 		 repayDay =acont.substring(acont.indexOf("到期还款日")+5,acont.indexOf("到期还款日")+15);
	 		 repayDay = repayDay.replaceAll("/", "-");
	 		 //还款日去掉“-” 不是纯数字表示账单样式发生变化
	 		 if(repayDay.replaceAll("-", "").matches("[0-9]+")==false){
	 			isChangeBill = isBillChange(3,acont,bankName);
				return cardBill;
	 		 }
	 		 if(repayDay.length()==5){
				 repayDay = AnnualTreatment(repayDay,billDay);
			 }
	 		 //已出账单--数据
	 		 int aindex = acont.indexOf(".",acont.indexOf("￥"))+3;//第一个￥后面的.的位置
	 		 bill =acont.substring(acont.indexOf("￥")+1,aindex);
	 		 bill = bill.replaceAll("[,]", "");
	 		 if (bill.replaceAll("(?:-|\\.)", "").matches("[0-9]+")==false) {
				 isChangeBill = isBillChange(1,acont,bankName);
				 return cardBill;
			 }
	 		 
	 		 //最低还款--数据
	 		 int a1=acont.indexOf("￥",acont.indexOf("￥")+1);
	 		 int a2=acont.indexOf(".",acont.indexOf("￥",acont.indexOf("￥")+1))+3;
	 		 minRepay = acont.substring(a1+1,a2);
	 		 minRepay = minRepay.replaceAll("[,]", "");
	 		 if (minRepay.replaceAll("[.]", "").matches("[0-9]+")==false) {
				 isChangeBill = isBillChange(2,acont,bankName);
				 return cardBill;
			 }

	 		 cardBill.setBankName(bankName);
	 		 cardBill.setBill(bill);
	 		 cardBill.setBillDay(billDay);
	 		 cardBill.setRepayDay(repayDay);
	 		 cardBill.setMinRepay(minRepay);
	 		 cardBill.setBankCardNo(cardNo);
	 	} catch (Exception e) {
	 		e.printStackTrace();
	 		if(isChangeBill==null){
	 			isChangeBill=bankName+"账单";
			}else{
				isChangeBill=isChangeBill+"and"+bankName+"账单";
			}
	 		logger4J.info("Entering parsingBCMBills()******************解析交通银行账单错误***"+acont+"***************");
	 	}
	 	return cardBill;
	 }
	 
	 /**
	  * 解析交通银行账单数据
	  * @param acont 邮件文本内容
	  * @param billDay 发件时间
	  * @param bankName 银行名称
	  */
	 public CreditCardBill parsingBCMBillsHtml(String acont,String billDay,String bankName){
	 	 CreditCardBill cardBill= new CreditCardBill();
	 	 String repayDay = "";//还款日
	 	 String bill="";//已出账单
	 	 String minRepay="";//最低还款
	 	 String cardNo="";//银行卡号
	 	 String quota = "";//额度
	 	 try {
	 		 //银行卡号
//			 int ii=acont.indexOf("最低还款额");
	 		 int ii=acont.indexOf("信用额度");
			 int ii1=acont.indexOf("</td>",ii+1)+5;
//			 int ii2=acont.indexOf("</</td>",ii1);
			 acont = acont.substring(acont.indexOf("卡号:"), ii1);
			 int i=acont.indexOf("卡号:");
			 int i1 = acont.indexOf("</p>",i);
			 cardNo = acont.substring(i+3,i1);
			 cardNo = cardNo.substring(cardNo.length()-4,cardNo.length());
	 		 if (cardNo.matches("[0-9]+")==false || cardNo.length()!=4) {
				 isChangeBill = isBillChange(0,acont,bankName);
				 return cardBill;
			 }
	 		 //还款日
	 		 int t= acont.indexOf("到期还款日");
			 int t1 = acont.indexOf("</td>",t);
			 repayDay =acont.substring(t1-10,t1);
	 		 repayDay = repayDay.replaceAll("/", "-");
	 		 //还款日去掉“-” 不是纯数字表示账单样式发生变化
	 		 if(repayDay.replaceAll("-", "").matches("[0-9]+")==false){
	 			isChangeBill = isBillChange(3,acont,bankName);
				return cardBill;
	 		 }
	 		 if(repayDay.length()==5){
				 repayDay = AnnualTreatment(repayDay,billDay);
			 }
	 		 //已出账单--数据
	 		 int b= acont.indexOf("本期应还款额");
			 int b1 = acont.indexOf("</td>",b);
			 int b2 = acont.indexOf("￥",b);
			 bill =acont.substring(b2+1,b1).trim();
	 		 bill = bill.replaceAll("[,]", "");
	 		 if (bill.replaceAll("(?:-|\\.)", "").matches("[0-9]+")==false) {
				 isChangeBill = isBillChange(1,acont,bankName);
				 return cardBill;
			 }
	 		 
	 		 //最低还款--数据
	 		 int m= acont.indexOf("最低还款额");
			 int m1 = acont.indexOf("</td>",m);
			 int m2 = acont.indexOf("￥",m)+1;
			 minRepay = acont.substring(m2,m1).trim();
	 		 minRepay = minRepay.replaceAll("[,]", "");
	 		 if (minRepay.replaceAll("[.]", "").matches("[0-9]+")==false) {
				 isChangeBill = isBillChange(2,acont,bankName);
				 return cardBill;
			 }
	 		 
	 		 //信用额度
	 		 int ed= acont.indexOf("信用额度");
			 int ed1 = acont.indexOf("</td>",ed);
			 int ed2 = acont.indexOf("￥",ed)+1;
			 quota = acont.substring(ed2,ed1).trim();
			 quota = quota.replaceAll("[,]", "");
			 if (quota.replaceAll("[.]", "").matches("[0-9]+")==false) {
				 isChangeBill = isBillChange(4,acont,bankName);
				 return cardBill;
			 }
	 		 cardBill.setBankName(bankName);
	 		 cardBill.setBill(bill);
	 		 cardBill.setBillDay(billDay);
	 		 cardBill.setRepayDay(repayDay);
	 		 cardBill.setMinRepay(minRepay);
	 		 cardBill.setBankCardNo(cardNo);
	 		 cardBill.setQuota(quota);
	 	} catch (Exception e) {
	 		e.printStackTrace();
	 		if(isChangeBill==null){
	 			isChangeBill=bankName+"账单";
			}else{
				isChangeBill=isChangeBill+"and"+bankName+"账单";
			}
	 		logger4J.info("Entering parsingBCMBills()******************解析交通银行账单错误***"+acont+"***************");
	 	}
	 	return cardBill;
	 }
	 
	 /**
	  * 解析工商银行账单数据
	  * @param acont 邮件文本内容
	  * @param billDay 发件时间
	  * @param bankName 银行名称
	  */
	 public CreditCardBill parsingICBCBills(String acont,String billDay,String bankName){
	 	 CreditCardBill cardBill= new CreditCardBill();
	 	 String repayDay = "";//还款日
	 	 String bill="";//已出账单
	 	 String minRepay="";//最低还款
	 	 String cardNo="";//银行卡号
	 	 try {
	 		 //银行卡号
	 		 cardNo = acont.substring(acont.indexOf("信用额度")+4,acont.indexOf("信用额度")+8);
	 		 if (cardNo.matches("[0-9]+")==false || cardNo.length()!=4) {
				 isChangeBill = isBillChange(0,acont,bankName);
				 return cardBill;
			 }
			 //还款日
			 repayDay =acont.substring(acont.indexOf("贷记卡到期还款日")+8,acont.indexOf("贷记卡到期还款日")+16);
//			 处理还款日日期格式问题
			 int reDay = repayDay.indexOf("年");
			 if(reDay>=0){
				 Date date = new SimpleDateFormat("yyyy年MM月dd").parse(repayDay); 
				 repayDay = new SimpleDateFormat("yyyy-MM-dd").format(date);
			 }
			 repayDay = repayDay.replaceAll("/", "-");
			 //还款日去掉“-” 不是纯数字表示账单样式发生变化
			 if(repayDay.replaceAll("-", "").matches("[0-9]+")==false){
	 			isChangeBill = isBillChange(3,acont,bankName);
				return cardBill;
		 	 }
	 		 if(repayDay.length()==5){
				 repayDay = AnnualTreatment(repayDay,billDay);
			 }
	 		 
			 //已出账单--数据
			 bill =acont.substring(acont.indexOf("人民币")+3,acont.indexOf("/RMB"));
			 bill = bill.replaceAll("[,]", "");
			 if (bill.replaceAll("(?:-|\\.)", "").matches("[0-9]+")==false) {
				 isChangeBill = isBillChange(1,acont,bankName);
				 return cardBill;
			 }
			 //最低还款--数据
			 int a1 = acont.indexOf("/RMB")+4;
			 int a2=acont.indexOf("/RMB",acont.indexOf("/RMB")+1);
			 minRepay = acont.substring(a1,a2);
			 minRepay = minRepay.replaceAll("[,]", "");
			 if (minRepay.replaceAll("[.]", "").matches("[0-9]+")==false) {
				 isChangeBill = isBillChange(2,acont,bankName);
				 return cardBill;
			 }

	 		 cardBill.setBankName(bankName);
	 		 cardBill.setBill(bill);
	 		 cardBill.setBillDay(billDay);
	 		 cardBill.setRepayDay(repayDay);
	 		 cardBill.setMinRepay(minRepay);
	 		 cardBill.setBankCardNo(cardNo);
	 	} catch (Exception e) {
	 		e.printStackTrace();
	 		if(isChangeBill==null){
	 			isChangeBill=bankName+"账单";
			}else{
				isChangeBill=isChangeBill+"and"+bankName+"账单";
			}
	 		logger4J.info("Entering parsingICBCBills()******************解析工商银行账单错误***"+acont+"***************");
	 	}
	 	return cardBill;
	 }
	 
	 /**
	  * 解析工商银行账单数据
	  * @param acont 邮件文本内容
	  * @param billDay 发件时间
	  * @param bankName 银行名称
	  */
	 public CreditCardBill parsingICBCBillsHtml(String acont,String billDay,String bankName){
	 	 CreditCardBill cardBill= new CreditCardBill();
	 	 String repayDay = "";//还款日
	 	 String bill="";//已出账单
	 	 String minRepay="";//最低还款
	 	 String cardNo="";//银行卡号
	 	 String quota = "";//额度
	 	 try {
	 		 int ac = acont.indexOf("对账单生成日");
			 int acR1 = acont.indexOf("/RMB",ac);
			 int acR2 = acont.indexOf("/RMB",acR1+1)+4;
			 acont = acont.substring(ac, acR2);
			 int r = acont.indexOf("对账单生成日");
			 int r1 = acont.indexOf("</td>", r);
			 int c =acont.indexOf("(牡丹贷记卡)");
	 		 //银行卡号
			 cardNo = acont.substring(c-4, c);
	 		 if (cardNo.matches("[0-9]+")==false || cardNo.length()!=4) {
				 isChangeBill = isBillChange(0,acont,bankName);
				 return cardBill;
			 }
			 //还款日
	 		 repayDay = acont.substring(r1-11,r1);
//			 处理还款日日期格式问题
			 int reDay = repayDay.indexOf("年");
			 if(reDay>=0){
				 Date date = new SimpleDateFormat("yyyy年MM月dd").parse(repayDay); 
				 repayDay = new SimpleDateFormat("yyyy-MM-dd").format(date);
			 }
			 repayDay = repayDay.replaceAll("/", "-");
			 //还款日去掉“-” 不是纯数字表示账单样式发生变化
			 if(repayDay.replaceAll("-", "").matches("[0-9]+")==false){
	 			isChangeBill = isBillChange(3,acont,bankName);
				return cardBill;
		 	 }
	 		 if(repayDay.length()==5){
				 repayDay = AnnualTreatment(repayDay,billDay);
			 }
	 		 
			 //已出账单--数据
	 		 int b = acont.indexOf("人民币");
			 int rmb1 = acont.indexOf("/RMB",b);
			 int rmb2 = acont.indexOf("/RMB",rmb1+1);
			 int rmb3 = acont.indexOf("/RMB",rmb2+1);
			 int btd1 = acont.indexOf(">",b);
			 int btd2 = acont.indexOf(">",btd1+1);
			 bill =acont.substring(btd2+1,rmb1);
			 bill = bill.replaceAll("[,]", "");
			 if (bill.replaceAll("(?:-|\\.)", "").matches("[0-9]+")==false) {
				 isChangeBill = isBillChange(1,acont,bankName);
				 return cardBill;
			 }
			 //最低还款--数据
			 int m1=acont.indexOf(">",rmb1);
			 int m2=acont.indexOf(">",m1+1);
			 minRepay = acont.substring(m2+1,rmb2);
			 minRepay = minRepay.replaceAll("[,]", "");
			 if (minRepay.replaceAll("[.]", "").matches("[0-9]+")==false) {
				 isChangeBill = isBillChange(2,acont,bankName);
				 return cardBill;
			 }

//			 信用额度
			 int ze1=acont.indexOf(">",rmb2);
			 int ze2=acont.indexOf(">",ze1+1);
			 quota = acont.substring(ze2+1,rmb3);
			 quota = quota.replaceAll("[,]", "");
			 if (quota.replaceAll("[.]", "").matches("[0-9]+")==false) {
				 isChangeBill = isBillChange(4,acont,bankName);
				 return cardBill;
			 }
	 		 cardBill.setBankName(bankName);
	 		 cardBill.setBill(bill);
	 		 cardBill.setBillDay(billDay);
	 		 cardBill.setRepayDay(repayDay);
	 		 cardBill.setMinRepay(minRepay);
	 		 cardBill.setBankCardNo(cardNo);
	 		 cardBill.setQuota(quota);
	 	} catch (Exception e) {
	 		e.printStackTrace();
	 		if(isChangeBill==null){
	 			isChangeBill=bankName+"账单";
			}else{
				isChangeBill=isChangeBill+"and"+bankName+"账单";
			}
	 		logger4J.info("Entering parsingICBCBills()******************解析工商银行账单错误***"+acont+"***************");
	 	}
	 	return cardBill;
	 }
	 
	 /**
	  * 解析中信银行账单数据
	  * @param acont 邮件文本内容
	  * @param billDay 发件时间
	  * @param bankName 银行名称
	  */
	 public CreditCardBill parsingCITICBills(String acont,String billDay,String bankName){
	 	 CreditCardBill cardBill= new CreditCardBill();
	 	 String repayDay = "";//还款日
	 	 String bill="";//已出账单
	 	 String minRepay="";//最低还款
	 	 String cardNo="";//银行卡号
	 	 try {
	 		 int u=acont.indexOf("RMB")+3;//1
			 int u1=acont.indexOf("RMB",u+1)+3;//2
			 int u2=acont.indexOf("RMB",u1+1)+3;//3
			 int u3=acont.indexOf("RMB",u2+1)+3;//4
			 int u4=acont.indexOf("RMB",u3+1);//5
			 //卡号（后四位）
			 cardNo = acont.substring(acont.indexOf("最低还款额")+20,u4);
			 if (cardNo.matches("[0-9]+")==false || cardNo.length()!=4) {
				 isChangeBill = isBillChange(0,acont,bankName);
				 return cardBill;
			 }
			 
			 //还款日
			 repayDay =acont.substring(acont.indexOf("到期还款日：")+6,acont.indexOf("RMB"));
			 int reDay = repayDay.indexOf("年");
			 if(reDay>=0){
				 Date date = new SimpleDateFormat("yyyy年MM月dd").parse(repayDay); 
				 repayDay = new SimpleDateFormat("yyyy-MM-dd").format(date);
			 }
			 repayDay = repayDay.replaceAll("/", "-");
			 //还款日去掉“-” 不是纯数字表示账单样式发生变化
			 if(repayDay.replaceAll("-", "").matches("[0-9]+")==false){
		 		isChangeBill = isBillChange(3,acont,bankName);
				return cardBill;
		 	 }
			 
			 //已出账单--数据
			 bill =acont.substring(u,u1-3);
			 bill = bill.replaceAll("[,]", "");
			 if (bill.replaceAll("(?:-|\\.)", "").matches("[0-9]+")==false) {
				 isChangeBill = isBillChange(1,acont,bankName);
				 return cardBill;
			 }
			 
			 //最低还款--数据
			 minRepay = acont.substring(u1,u2-7);
			 minRepay = minRepay.replaceAll("[,]", "");
			 if (minRepay.replaceAll("[.]", "").matches("[0-9]+")==false) {
				 isChangeBill = isBillChange(2,acont,bankName);
				 return cardBill;
			 }

	 		 cardBill.setBankName(bankName);
	 		 cardBill.setBill(bill);
	 		 cardBill.setBillDay(billDay);
	 		 cardBill.setRepayDay(repayDay);
	 		 cardBill.setMinRepay(minRepay);
	 		 cardBill.setBankCardNo(cardNo);
	 	} catch (Exception e) {
	 		e.printStackTrace();
	 		if(isChangeBill==null){
	 			isChangeBill=bankName+"账单";
			}else{
				isChangeBill=isChangeBill+"and"+bankName+"账单";
			}
	 		logger4J.info("Entering parsingCITICBills()******************解析中国农业银行账单错误***"+acont+"***************");
	 	}
	 	return cardBill;
	 }
	 
	 /**
	  * 解析中信银行账单数据
	  * @param acont 邮件文本内容
	  * @param billDay 发件时间
	  * @param bankName 银行名称
	  */
	 public CreditCardBill parsingCITICBillsHtml(String acont,String billDay,String bankName){
	 	 CreditCardBill cardBill= new CreditCardBill();
	 	 String repayDay = "";//还款日
	 	 String bill="";//已出账单
	 	 String minRepay="";//最低还款
	 	 String cardNo="";//银行卡号
	 	 String quota = "";//额度
	 	 try {
	 		 int u=acont.indexOf("到期还款日：");//
			 int u1=acont.indexOf("</font>",u+1);//1
			 
			 int ca=acont.indexOf("最低还款额");//
			 int ca1=acont.indexOf("</font>",ca+1);//1
			 int ca2=acont.indexOf("</font>",ca1+1);//2
			 
			 //卡号（后四位）
			 cardNo = acont.substring(ca2-4,ca2);
			 if (cardNo.matches("[0-9]+")==false || cardNo.length()!=4) {
				 isChangeBill = isBillChange(0,acont,bankName);
				 return cardBill;
			 }
			 
			 //还款日
			 repayDay =acont.substring(u1-11,u1);
			 int reDay = repayDay.indexOf("年");
			 if(reDay>=0){
				 Date date = new SimpleDateFormat("yyyy年MM月dd").parse(repayDay); 
				 repayDay = new SimpleDateFormat("yyyy-MM-dd").format(date);
			 }
			 repayDay = repayDay.replaceAll("/", "-");
			 //还款日去掉“-” 不是纯数字表示账单样式发生变化
			 if(repayDay.replaceAll("-", "").matches("[0-9]+")==false){
		 		isChangeBill = isBillChange(3,acont,bankName);
				return cardBill;
		 	 }
			 int rmb = acont.indexOf("RMB",ca+1);//1
			 int fb1 = acont.indexOf("</font>",rmb+1);//1
			 int fb2 = acont.indexOf("</font>",fb1+1);//2
			 int fb3 = acont.indexOf("</font>",fb2+1);//3
			 int fb4 = acont.indexOf("</font>",fb3+1);//4
			 int fb5 = acont.indexOf("</font>",fb4+1);//5
			 
			 int fob1 = acont.indexOf("<font",rmb+1);//1
			 int fob2 = acont.indexOf("<font",fob1+1);//2
			 int fob3 = acont.indexOf("<font",fob2+1);//3
			 int fob4 = acont.indexOf("<font",fob3+1);//4
			 int fob5 = acont.indexOf(">",fob4);//4
			 
			 //已出账单--数据
			 bill =acont.substring(fob5+1,fb5);
			 bill = bill.replaceAll("[,]", "");
			 if (bill.replaceAll("(?:-|\\.)", "").matches("[0-9]+")==false) {
				 isChangeBill = isBillChange(1,acont,bankName);
				 return cardBill;
			 }
			 
			 //最低还款--数据
			 int m = acont.indexOf("</font>",fb5+1);//5
			 int m1 = acont.indexOf("<font",fb5+1);//5
			 int m2 = acont.indexOf(">",m1);//5
			 minRepay = acont.substring(m2+1,m);
			 minRepay = minRepay.replaceAll("[,]", "");
			 if (minRepay.replaceAll("[.]", "").matches("[0-9]+")==false) {
				 isChangeBill = isBillChange(4,acont,bankName);
				 return cardBill;
			 }

			 //信用额度
			 int rmb1 = acont.indexOf("RMB");//1
			 int rmb2 = acont.indexOf("RMB",rmb1+1);//2
			 int rmb3 = acont.indexOf("RMB",rmb2+1);//3
			 int zef = acont.indexOf("</font>",rmb3+1);
			 int zef1 = acont.indexOf("</font>",zef+1);
			 
			 int zf1 = acont.indexOf("<font",rmb3+1);//1
			 int zf2 = acont.indexOf(">",zf1+1);//2
			 
			 quota = acont.substring(zf2+1,zef1);
			 quota = quota.replaceAll("[,]", "");
			 if (quota.replaceAll("[.]", "").matches("[0-9]+")==false) {
				 isChangeBill = isBillChange(2,acont,bankName);
				 return cardBill;
			 }
			 
	 		 cardBill.setBankName(bankName);
	 		 cardBill.setBill(bill);
	 		 cardBill.setBillDay(billDay);
	 		 cardBill.setRepayDay(repayDay);
	 		 cardBill.setMinRepay(minRepay);
	 		 cardBill.setBankCardNo(cardNo);
	 		 cardBill.setQuota(quota);
	 	} catch (Exception e) {
	 		e.printStackTrace();
	 		if(isChangeBill==null){
	 			isChangeBill=bankName+"账单";
			}else{
				isChangeBill=isChangeBill+"and"+bankName+"账单";
			}
	 		logger4J.info("Entering parsingCITICBills()******************解析中国农业银行账单错误***"+acont+"***************");
	 	}
	 	return cardBill;
	 }
	 
	 /**
	  * 解析中国农业银行账单数据
	  * @param acont 邮件文本内容
	  * @param billDay 发件时间
	  * @param bankName 银行名称
	  */
	 public CreditCardBill parsingABCBills(String acont,String billDay,String bankName){
	 	 CreditCardBill cardBill= new CreditCardBill();
	 	 String repayDay = "";//还款日
	 	 String bill="";//已出账单
	 	 String minRepay="";//最低还款
	 	 String cardNo="";//银行卡号
	 	 try {
	 		 int u=acont.indexOf("您的信用卡账户信息StatementInformation")+29;//1
			 int u1=acont.indexOf("CardNo.StatementCyclePaymentDueDate")+35;//2
			 int u2 = acont.indexOf("CurrNewBalanceMin.PaymentCreditLimit人民币(CNY)")+36;
			 int u3 = acont.indexOf("您的个人客户积分数为")+10;
			 
			 String messageStr = acont.substring(u,u1);
			 String msgStr = acont.substring(u2, u3);
			 cardNo = messageStr.substring(messageStr.indexOf("卡号")+14,messageStr.indexOf("账单周期"));
			 if (cardNo.matches("[0-9]+")==false || cardNo.length()!=4) {
				 isChangeBill = isBillChange(0,acont,bankName);
				 return cardBill;
			 }
			 
			 //还款日
			 repayDay =messageStr.substring(messageStr.indexOf("到期还款日")+5,messageStr.indexOf("CardNo"));
			 int reDay = repayDay.indexOf("年");
			 if(reDay>=0){
				 Date date = new SimpleDateFormat("yyyy年MM月dd").parse(repayDay); 
				 repayDay = new SimpleDateFormat("yyyy-MM-dd").format(date);
			 }else{
				 String yearStr = repayDay.substring(0, 4);
				 String monthStr = repayDay.substring(4, 6);
				 String dayStr = repayDay.substring(6, 8);
				 repayDay = yearStr+"-"+monthStr+"-"+dayStr;
			 }
			 repayDay = repayDay.replaceAll("/", "-");
			 //还款日去掉“-” 不是纯数字表示账单样式发生变化
			 if(repayDay.replaceAll("-", "").matches("[0-9]+")==false){
		 			isChangeBill = isBillChange(3,acont,bankName);
					return cardBill;
		 	 }
			 
			 //已出账单--数据
			 bill =msgStr.substring(msgStr.indexOf("人民币(CNY)")+8,msgStr.indexOf(".")+3).replaceAll("[,]", "");
			 if (bill.replaceAll("(?:-|\\.)", "").matches("[0-9]+")==false) {
				 isChangeBill = isBillChange(1,acont,bankName);
				 return cardBill;
			 }
			 
			 //最低还款--数据
			 int uu=msgStr.indexOf(".")+3;//1
			 int uu1=msgStr.indexOf(".",uu+1)+3;//2
			 minRepay = msgStr.substring(uu,uu1).replaceAll("[,]", "");
			 if (minRepay.replaceAll("[.]", "").matches("[0-9]+")==false) {
				 isChangeBill = isBillChange(2,acont,bankName);
				 return cardBill;
			 }

	 		 cardBill.setBankName(bankName);
	 		 cardBill.setBill(bill);
	 		 cardBill.setBillDay(billDay);
	 		 cardBill.setRepayDay(repayDay);
	 		 cardBill.setMinRepay(minRepay);
	 		 cardBill.setBankCardNo(cardNo);
	 	} catch (Exception e) {
	 		e.printStackTrace();
	 		if(isChangeBill==null){
	 			isChangeBill=bankName+"账单";
			}else{
				isChangeBill=isChangeBill+"and"+bankName+"账单";
			}
	 		logger4J.info("Entering parsingABCBills()******************解析中国农业银行账单错误***"+acont+"***************");
	 	}
	 	return cardBill;
	 }
	 
	 /**
	  * 解析中国农业银行账单数据
	  * @param acont 邮件文本内容
	  * @param billDay 发件时间
	  * @param bankName 银行名称
	  */
	 public CreditCardBill parsingABCBillsHtml(String acont,String billDay,String bankName){
	 	 CreditCardBill cardBill= new CreditCardBill();
	 	 String repayDay = "";//还款日
	 	 String bill="";//已出账单
	 	 String minRepay="";//最低还款
	 	 String cardNo="";//银行卡号
	 	 String quota="";//额度
	 	 try {
	 		 int u=acont.indexOf("卡号</span>");
			 int u1=acont.indexOf("</span>",u+1);//2
			 int u2 = acont.indexOf("</span>",u1+1);
			 
			 int r1 = acont.indexOf("到期还款日");
			 int r2 = acont.indexOf("</span>",r1+1);
			 int r3 = acont.indexOf("</span>",r2+1);
			 
			 int b = acont.indexOf("人民币");
			 int b1 = acont.indexOf("</span>", b+1);
			 int b2 = acont.indexOf("</span>", b1+1);
			 int b3 = acont.indexOf("<span", b+1);
			 int b4 = acont.indexOf(">", b3+1);
			 
			 int m = acont.indexOf("</span>", b2+1);
			 int m1 = acont.indexOf("<span", b2+1);
			 int m2 = acont.indexOf(">", m1+1);
			 
			 int z = acont.indexOf("</span>", m+1);
			 int z1 = acont.indexOf("<span", m+1);
			 int z2 = acont.indexOf(">", z1+1);
			 
			 //卡号
			 cardNo = acont.substring(u2-4, u2);
			 if (cardNo.matches("[0-9]+")==false || cardNo.length()!=4) {
				 isChangeBill = isBillChange(0,acont,bankName);
				 return cardBill;
			 }
			 
			 //还款日
			 repayDay =acont.substring(r3-8,r3);
			 int reDay = repayDay.indexOf("年");
			 if(reDay>=0){
				 Date date = new SimpleDateFormat("yyyy年MM月dd").parse(repayDay); 
				 repayDay = new SimpleDateFormat("yyyy-MM-dd").format(date);
			 }else{
				 String yearStr = repayDay.substring(0, 4);
				 String monthStr = repayDay.substring(4, 6);
				 String dayStr = repayDay.substring(6, 8);
				 repayDay = yearStr+"-"+monthStr+"-"+dayStr;
			 }
			 repayDay = repayDay.replaceAll("/", "-");
			 //还款日去掉“-” 不是纯数字表示账单样式发生变化
			 if(repayDay.replaceAll("-", "").matches("[0-9]+")==false){
		 			isChangeBill = isBillChange(3,acont,bankName);
					return cardBill;
		 	 }
			 
			 //已出账单--数据
			 bill =acont.substring(b4+1,b2).replaceAll("[,]", "");
			 if (bill.replaceAll("(?:-|\\.)", "").matches("[0-9]+")==false) {
				 isChangeBill = isBillChange(1,acont,bankName);
				 return cardBill;
			 }
			 
			 //最低还款--数据
			 minRepay = acont.substring(m2+1,m).replaceAll("[,]", "");
			 if (minRepay.replaceAll("[.]", "").matches("[0-9]+")==false) {
				 isChangeBill = isBillChange(2,acont,bankName);
				 return cardBill;
			 }

//			 额度
			 quota = acont.substring(z2+1,z).replaceAll("[,]", "");
			 if (quota.replaceAll("[.]", "").matches("[0-9]+")==false) {
				 isChangeBill = isBillChange(4,acont,bankName);
				 return cardBill;
			 }
	 		 cardBill.setBankName(bankName);
	 		 cardBill.setBill(bill);
	 		 cardBill.setBillDay(billDay);
	 		 cardBill.setRepayDay(repayDay);
	 		 cardBill.setMinRepay(minRepay);
	 		 cardBill.setBankCardNo(cardNo);
	 		 cardBill.setQuota(quota);
	 	} catch (Exception e) {
	 		e.printStackTrace();
	 		if(isChangeBill==null){
	 			isChangeBill=bankName+"账单";
			}else{
				isChangeBill=isChangeBill+"and"+bankName+"账单";
			}
	 		logger4J.info("Entering parsingABCBills()******************解析中国农业银行账单错误***"+acont+"***************");
	 	}
	 	return cardBill;
	 }
	 
	 /**
	  * 解析中国民生银行账单数据
	  * @param acont 邮件文本内容
	  * @param billDay 发件时间
	  * @param bankName 银行名称
	  */
	 public CreditCardBill parsingCMBCBills(String acont,String billDay,String bankName){
	 	 CreditCardBill cardBill= new CreditCardBill();
	 	 acont = acont.replaceAll("\\|", "");
	 	 String repayDay = "";//还款日
	 	 String bill="";//已出账单
	 	 String minRepay="";//最低还款
	 	 String cardNo="";//银行卡号
	 	 try {
	 		int u=acont.indexOf("本期账单日");//1
			 int u1=acont.indexOf("账单也能分期还")+7;//2
			 int u2 = acont.indexOf("RMB/USDAccountRMB")+14;
			 int u4 = acont.indexOf("RMB")+3;
			 int u5 = acont.indexOf("RMB",u4+1)+3;
			 int u6 = acont.indexOf("RMB",u5+1)+3;
			 int u7 = acont.indexOf(".",u6)+3;
			 
			 String messageStr = acont.substring(u,u1);
			 String msgStr = acont.substring(u2,u7);
			 billDay = messageStr.substring(messageStr.indexOf("本期账单日StatementDate")+18, messageStr.indexOf("本期最后还款日"));
			 int ibillDay = billDay.indexOf("年");
			 if(ibillDay>=0){
				 Date date = new SimpleDateFormat("yyyy年MM月dd").parse(repayDay); 
				 repayDay = new SimpleDateFormat("yyyy-MM-dd").format(date);
			 }
			 billDay = billDay.replaceAll("/", "-");
			 //还款日
			 repayDay =messageStr.substring(messageStr.indexOf("本期最后还款日PaymentDueDate")+21, messageStr.indexOf("账单也能分期还"));
			 int reDay = repayDay.indexOf("年");
			 if(reDay>=0){
				 Date date = new SimpleDateFormat("yyyy年MM月dd").parse(repayDay); 
				 repayDay = new SimpleDateFormat("yyyy-MM-dd").format(date);
			 }
			 repayDay = repayDay.replaceAll("/", "-");
			 //还款日去掉“-” 不是纯数字表示账单样式发生变化
			 if(repayDay.replaceAll("-", "").matches("[0-9]+")==false){
		 			isChangeBill = isBillChange(3,acont,bankName);
					return cardBill;
		 	 }
			 
			 //已出账单--数据
			 bill =msgStr.substring(msgStr.indexOf("RMB")+3,msgStr.indexOf("RMB",msgStr.indexOf("RMB")+1)).replaceAll("[,]", "");
			 
			 if (bill.replaceAll("(?:-|\\.)", "").matches("[0-9]+")==false) {
				 isChangeBill = isBillChange(1,acont,bankName);
				 return cardBill;
			 }
			 //最低还款--数据
			 int uu=msgStr.indexOf("RMB",msgStr.indexOf("RMB")+1)+3;//1
			 int uu1=msgStr.indexOf(".",uu+1)+3;//2
			 minRepay = msgStr.substring(uu,uu1).replaceAll("[,]", "");
			 if (minRepay.replaceAll("[.]", "").matches("[0-9]+")==false) {
				 isChangeBill = isBillChange(2,acont,bankName);
				 return cardBill;
			 }

	 		 cardBill.setBankName(bankName);
	 		 cardBill.setBill(bill);
	 		 cardBill.setBillDay(billDay);
	 		 cardBill.setRepayDay(repayDay);
	 		 cardBill.setMinRepay(minRepay);
	 		 cardBill.setBankCardNo(cardNo);
	 	} catch (Exception e) {
	 		e.printStackTrace();
	 		if(isChangeBill==null){
	 			isChangeBill=bankName+"账单";
			}else{
				isChangeBill=isChangeBill+"and"+bankName+"账单";
			}
	 		logger4J.info("Entering parsingABCBills()******************解析中国民生银行账单错误***"+acont+"***************");
	 	}
	 	return cardBill;
	 }
	 
	 /**
	  * 解析中国民生银行账单数据
	  * @param acont 邮件文本内容
	  * @param billDay 发件时间
	  * @param bankName 银行名称
	  */
	 public CreditCardBill parsingCMBCBillsHtml(String acont,String billDay,String bankName){
	 	 CreditCardBill cardBill= new CreditCardBill();
	 	 acont = acont.replaceAll("\\|", "");
	 	 String repayDay = "";//还款日
	 	 String bill="";//已出账单
	 	 String minRepay="";//最低还款
	 	 String cardNo="";//银行卡号
	 	 String quota="";//额度
	 	 try {
	 		 
	 		 int u=acont.indexOf("本期账单日");//1
			 int uu1=acont.indexOf("</font>",u+1);//2
			 int uu2=acont.indexOf("</font>",uu1+1);//3
			 int uu3=acont.indexOf("</font>",uu2+1);//4
			 //账单日
			 billDay = acont.substring(uu3-10,uu3);
			 int ibillDay = billDay.indexOf("年");
			 if(ibillDay>=0){
				 Date date = new SimpleDateFormat("yyyy年MM月dd").parse(repayDay); 
				 repayDay = new SimpleDateFormat("yyyy-MM-dd").format(date);
			 }
			 billDay = billDay.replaceAll("/", "-");
			 //还款日
			 int r = acont.indexOf("本期最后还款日");
			 int r1 = acont.indexOf("</font>", r);
			 int r2 = acont.indexOf("</font>", r1+1);
			 int r3 = acont.indexOf("</font>", r2+1);
			 repayDay =acont.substring(r3-10,r3);
			 int reDay = repayDay.indexOf("年");
			 if(reDay>=0){
				 Date date = new SimpleDateFormat("yyyy年MM月dd").parse(repayDay); 
				 repayDay = new SimpleDateFormat("yyyy-MM-dd").format(date);
			 }
			 repayDay = repayDay.replaceAll("/", "-");
			 //还款日去掉“-” 不是纯数字表示账单样式发生变化
			 if(repayDay.replaceAll("-", "").matches("[0-9]+")==false){
		 			isChangeBill = isBillChange(3,acont,bankName);
					return cardBill;
		 	 }
			 
			 //已出账单--数据
			//已出账单--数据
			 int rmb1 = acont.indexOf("RMB");
			 int rmb2 = acont.indexOf("RMB",rmb1+1);
			 int b = acont.indexOf("</font>",rmb2+1);
			 bill =acont.substring(rmb2+3,b).replaceAll("[,]", "").replaceAll("&nbsp;", "");
			 
			 if (bill.replaceAll("(?:-|\\.)", "").matches("[0-9]+")==false) {
				 isChangeBill = isBillChange(1,acont,bankName);
				 return cardBill;
			 }
			 //最低还款--数据
			 int rmb3 = acont.indexOf("RMB",rmb2+1);
			 int m = acont.indexOf("</font>",rmb3+1);
			 minRepay = acont.substring(rmb3+3,m).replaceAll("[,]", "").replaceAll("&nbsp;", "");
			 if (minRepay.replaceAll("[.]", "").matches("[0-9]+")==false) {
				 isChangeBill = isBillChange(2,acont,bankName);
				 return cardBill;
			 }

	 		 cardBill.setBankName(bankName);
	 		 cardBill.setBill(bill);
	 		 cardBill.setBillDay(billDay);
	 		 cardBill.setRepayDay(repayDay);
	 		 cardBill.setMinRepay(minRepay);
	 		 cardBill.setBankCardNo(cardNo);
	 		 cardBill.setQuota(quota);
	 	} catch (Exception e) {
	 		e.printStackTrace();
	 		if(isChangeBill==null){
	 			isChangeBill=bankName+"账单";
			}else{
				isChangeBill=isChangeBill+"and"+bankName+"账单";
			}
	 		logger4J.info("Entering parsingABCBills()******************解析中国民生银行账单错误***"+acont+"***************");
	 	}
	 	return cardBill;
	 }
	 
	 /**
	  *  注：\n 回车(\u000a) 
	  * 	 \t 水平制表符(\u0009) 
	  * 	 \s 空格(\u0008) 
	  * 	 \r 换行(\u000d)
	  * @param str
	  * @return
	  */
	 public String replaceBlank(String str) {
        String dest = "";
        if (str!=null) {
            Pattern p = Pattern.compile("\\s*|\t|\r|\n");
            Matcher m = p.matcher(str);
            dest = m.replaceAll("");
        }
        return dest;
	 }
	 
	 /**
	  * 为账单日补全缺失的年份
	  * (账单日的月为12 还款日的月1 账单日的年+1 拼接到还款日中)
	  * @param repayDay 还款日
	  * @param billDay 账单日
	  * @return
	  */
	 public String AnnualTreatment(String repayDay,String billDay){
	 	int rDay;
	 	int bDay;
	 	rDay = Integer.parseInt(repayDay.substring(0, repayDay.indexOf("-")));
	 	int c = billDay.indexOf("-",billDay.indexOf("-")+1);
	 	bDay = Integer.parseInt(billDay.substring(billDay.indexOf("-")+1, c));
	 	if(rDay==1 && bDay==12){
	 		bDay = Integer.parseInt(billDay.substring(0,billDay.indexOf("-")));
	 		bDay++;
	 		repayDay = Integer.toString(bDay)+"-"+repayDay;
	 	}else{
	 		bDay = Integer.parseInt(billDay.substring(0,billDay.indexOf("-")));
	 		repayDay = Integer.toString(bDay)+"-"+repayDay;
	 	}
	 	
	 	return repayDay;
	 	
	 }
	 /**
	  * 收件服务器主机名称
	  * @param userName
	  * @return
	  */
	 public String checkMailHost(String userName){
		 int isina = userName.indexOf("@sina.com");
		 int isinacn = userName.indexOf("@sina.cn");
		 int i163 = userName.indexOf("@163.com");
		 int i126 = userName.indexOf("@126.com");
		 int iHkrt = userName.indexOf("@hkrt.cn");
		 String host="";
		 if(isina>=0 || isinacn>=0){
			 host="pop.sina.com";
		 }else if(i163>=0){
			 host="pop3.163.com";
		 }else if(iHkrt>=0){
			 host="pop.qiye.163.com";
		 }else if(i126>=0){
			 host="pop.126.com";
		 }
		return host;
	 }
	 
	 /**
	  * 处理账单错误位置 记录错误账单到日志
	  * @param ii 0：卡号 ；1：已出账单；2：最低还款；3:还款日
	  * @param str 变化的账单
	  * @param bankName 账单变化的银行
	  * @return
	  */
	 public String isBillChange(int ii,String str,String bankName){
		 String isCha="";
		 if (ii==0) {
			 if(isChangeBill==null){
				 isCha=bankName+"账单";
			 }else{
				 isCha=isChangeBill+"and"+bankName+"账单";
			 }
			 logger4J.info("Entering parsingCreditCardBills()******************"+bankName+"银行账单“卡号位置发生变化”***"+str+"***************");
		 }else if(ii==1){
			 if(isChangeBill==null){
				 isCha=bankName+"账单";
			 }else{
				 isCha=isChangeBill+"and"+bankName+"账单";
			 }
			 logger4J.info("Entering parsingCreditCardBills()******************"+bankName+"银行账单“已出账单位置发生变化”***"+str+"***************");
		 }else if(ii==2){
			 if(isChangeBill==null){
				 isCha=bankName+"账单";
			 }else{
				 isCha=isChangeBill+"and"+bankName+"账单";
			 }
			 logger4J.info("Entering parsingCreditCardBills()******************"+bankName+"银行账单“最低还款位置发生变化”***"+str+"***************");
		 }else if(ii==3){
			 if(isChangeBill==null){
				 isCha=bankName+"账单";
			 }else{
				 isCha=isChangeBill+"and"+bankName+"账单";
			 }
			 logger4J.info("Entering parsingCreditCardBills()******************"+bankName+"银行账单“还款日位置发生变化”***"+str+"***************");
		 }else if(ii==4){
			 if(isChangeBill==null){
				 isCha=bankName+"账单";
			 }else{
				 isCha=isChangeBill+"and"+bankName+"账单";
			 }
			 System.out.println("Entering parsingCITICBills()******************"+bankName+"银行账单“信用额度位置发生变化”***"+str+"***************");
		 }
		return isCha;
	}
	 
	 /*
	  * 邮件条件过滤
	  */
	/* public static Message[] search(Folder folder) throws Exception {     
		    Calendar calendar = Calendar.getInstance();
	        calendar.add(Calendar.MONTH, -1);
	        calendar.set(Calendar.DAY_OF_MONTH,1);
	        Date preMonthDay = calendar.getTime();
	        SearchTerm comparisonTermGe = new SentDateTerm(ComparisonTerm.GE, preMonthDay);     
	        SearchTerm comparisonTermLe = new SentDateTerm(ComparisonTerm.LE, new Date());     
	        SearchTerm comparisonAndTerm = new AndTerm(comparisonTermGe, comparisonTermLe);
	        //String subject = "浦发银行-信用卡电子账单";  
	        //String subject = "中国建设银行信用卡电子账单";  
	        //SearchTerm subjectTerm = new SubjectTerm(subject);
	        //Message[] messages = folder.search(subjectTerm);
	        //return folder.search(comparisonAndTerm, messages);
	        return folder.search(comparisonAndTerm);
	 }
	 
	 
	  * 读取邮件超时处理
	  
	 public static Message[] overTime(final Folder folder){
		 Message[] messages = null;
		 ExecutorService exec = Executors.newFixedThreadPool(1);  
         Callable<Message[]> call = new Callable<Message[]>() {  
            public Message[] call() throws Exception {  
            	Message[] messages = search(folder);
                return messages;  
            }  
	      };  
        try {  
            Future<Message[]> future = exec.submit(call);  
            messages = future.get(1000 * 30, TimeUnit.MILLISECONDS);
			logger4J.info("<<<<<<<<<<<<<<<<<<<<<<<<<  读取邮件成功  >>>>>>>>>>>>>>>>>>>>>>>>>>>>");
            System.out.println("任务成功返回:");  
        } catch (TimeoutException ex) {
			logger4J.info("<<<<<<<<<<<<<<<<<<<<<<<<<  读取邮件超时....  >>>>>>>>>>>>>>>>>>>>>>>>>>>>");
            ex.printStackTrace();  
        } catch (Exception e) {  
			logger4J.info("<<<<<<<<<<<<<<<<<<<<<<<<<  读取邮件失败  >>>>>>>>>>>>>>>>>>>>>>>>>>>>");
            e.printStackTrace();  
        }  
        exec.shutdown();
		return messages;
	 }*/
}
