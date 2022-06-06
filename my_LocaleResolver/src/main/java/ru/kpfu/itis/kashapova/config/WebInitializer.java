package ru.kpfu.itis.kashapova.config;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.support.AbstractDispatcherServletInitializer;

public class WebInitializer extends AbstractDispatcherServletInitializer {

  @Override
  protected WebApplicationContext createRootApplicationContext() {
    return null;
  }

  @Override
  protected WebApplicationContext createServletApplicationContext() {
    AnnotationConfigWebApplicationContext cxt = new AnnotationConfigWebApplicationContext();
    cxt.register(WebMvcConfig.class);
    return cxt;
  }

  @Override
  protected String[] getServletMappings() {
    return new String[]{"/"};
  }

}
