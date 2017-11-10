package com.yxd;

import java.util.Properties;

import org.apache.log4j.Logger;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import com.github.pagehelper.PageHelper;


@SpringBootApplication
@ServletComponentScan
@ComponentScan("com.yxd")
@MapperScan("com.yxd.mapper")
public class App {
	private static Logger logger = Logger.getLogger(App.class);
    public static void main( String[] args ) {
        
        SpringApplication.run(App.class, args);
        logger.info("*****************************************YangXiaoDongDemo*****************************************************");
        logger.debug("****************************************YangXiaoDongDemo*****************************************************");
    }
     //配置mybatis的分页插件pageHelper
	 @Bean
	 public PageHelper pageHelper(){
		 PageHelper pageHelper = new PageHelper();
		 Properties properties = new Properties();
		 properties.setProperty("offsetAsPageNum","true");
		 properties.setProperty("rowBoundsWithCount","true");
		 properties.setProperty("reasonable","true");
		 //properties.setProperty("dialect","mysql");    //配置mysql数据库的方言
		 pageHelper.setProperties(properties);
		 return pageHelper;
	 }
}

