package br.cs.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class WebAppInitializer
  extends AbstractAnnotationConfigDispatcherServletInitializer
{
  protected String[] getServletMappings()
  {
    return new String[] { "/" };
  }
  
  protected Class<?>[] getRootConfigClasses()
  {
    return new Class[] { RootConfig.class };
  }
  
  protected Class<?>[] getServletConfigClasses()
  {
    return new Class[] { WebConfig.class };
  }
}
