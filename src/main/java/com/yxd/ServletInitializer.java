package com.yxd;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;

@SuppressWarnings("deprecation")
public class ServletInitializer extends SpringBootServletInitializer{
	  protected SpringApplicationBuilder configure(SpringApplicationBuilder application)
	  {
	    return application.sources(new Class[] { App.class });
	  }
}
