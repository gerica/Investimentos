package br.cs.config;

import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.view.UrlBasedViewResolver;
import org.springframework.web.servlet.view.tiles3.TilesConfigurer;
import org.springframework.web.servlet.view.tiles3.TilesView;

@Configuration
@EnableWebMvc
@ComponentScan({"br.cs.*"})
public class WebConfig
  extends WebMvcConfigurerAdapter
{
  private static final Logger logger = LoggerFactory.getLogger(WebConfig.class);
  
  public void addResourceHandlers(ResourceHandlerRegistry registry)
  {
    logger.debug("setting up resource handlers");
    registry.addResourceHandler(new String[] { "/resources/**" }).addResourceLocations(new String[] { "/resources/" });
    registry.addResourceHandler(new String[] { "/resources/img*" }).addResourceLocations(new String[] { "/resources/img/" });
    registry.addResourceHandler(new String[] { "/img/*" }).addResourceLocations(new String[] { "/img/" });
  }
  
  public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer)
  {
    logger.debug("configureDefaultServletHandling");
    configurer.enable();
  }
  
  public void addInterceptors(InterceptorRegistry registry)
  {
    LocaleChangeInterceptor localeChangeInterceptor = new LocaleChangeInterceptor();
    localeChangeInterceptor.setParamName("lang");
    registry.addInterceptor(localeChangeInterceptor);
  }
  
  @Bean
  public ViewResolver viewResolver()
  {
    logger.debug("Configurando view");
    UrlBasedViewResolver viewResolver = new UrlBasedViewResolver();
    viewResolver.setViewClass(TilesView.class);
    return viewResolver;
  }
  
  @Bean
  public TilesConfigurer tilesConfigurer()
  {
    logger.debug("Configurando tiles");
    TilesConfigurer tilesConfigurer = new TilesConfigurer();
    String[] definition = { "/WEB-INF/layouts/layouts.xml", "WEB-INF/layouts/views.xml" };
    
    tilesConfigurer.setDefinitions(definition);
    return tilesConfigurer;
  }
  
  @Bean
  public SimpleMappingExceptionResolver simpleMappingExceptionResolver()
  {
    SimpleMappingExceptionResolver b = new SimpleMappingExceptionResolver();
    
    Properties mappings = new Properties();
    mappings.put("org.springframework.web.servlet.PageNotFound", "p404");
    mappings.put("org.springframework.dao.DataAccessException", "dataAccessFailure");
    mappings.put("org.springframework.transaction.TransactionException", "dataAccessFailure");
    b.setExceptionMappings(mappings);
    return b;
  }
  
  @Bean
  public LocaleResolver localeResolver()
  {
    CookieLocaleResolver cookieLocaleResolver = new CookieLocaleResolver();
    cookieLocaleResolver.setDefaultLocale(StringUtils.parseLocaleString("br"));
    return cookieLocaleResolver;
  }
  
  @Bean
  public MessageSource messageSource()
  {
    ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
    messageSource.setBasenames(new String[] { "classpath:messages/messages", "classpath:messages/validation" });
    
    messageSource.setUseCodeAsDefaultMessage(true);
    messageSource.setDefaultEncoding("UTF-8");
    
    messageSource.setCacheSeconds(0);
    return messageSource;
  }
  
  @Bean
  public MultipartResolver multipartResolver()
  {
    CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
    multipartResolver.setDefaultEncoding("utf-8");
    multipartResolver.setMaxUploadSize(5242880L);
    return multipartResolver;
  }
}
