package org.ssd.logsimulator.app.configuration;

import java.util.HashSet;
import java.util.Set;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ConversionServiceFactoryBean;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.converter.Converter;

@Configuration
@ComponentScan(basePackages = {"org.ssd.logsimulator.web.controller,org.ssd.logsimulator.service"})
public class LogSimulatorConfiguration {
    
	@Bean(name="conversionService")
	public ConversionService getConversionService(){
		ConversionServiceFactoryBean bean = new ConversionServiceFactoryBean();
		bean.setConverters(getConverters());
		bean.afterPropertiesSet();
		return bean.getObject();
	}
	
	@SuppressWarnings("rawtypes")
	protected Set<Converter> getConverters(){
		Set<Converter> converters = new  HashSet<Converter>();
		return converters;
	}
}
