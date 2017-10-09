package com.yxd;

import org.apache.log4j.Logger;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.ComponentScan;


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
}

