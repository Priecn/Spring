package org.learn.spring.ecommerce.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.webflow.config.AbstractFlowConfiguration;
import org.springframework.webflow.definition.registry.FlowDefinitionRegistry;
import org.springframework.webflow.engine.builder.support.FlowBuilderServices;
import org.springframework.webflow.executor.FlowExecutor;
import org.springframework.webflow.mvc.builder.MvcViewFactoryCreator;
import org.springframework.webflow.mvc.servlet.FlowHandlerAdapter;
import org.springframework.webflow.mvc.servlet.FlowHandlerMapping;

@Configuration
public class WebFlowConfig extends AbstractFlowConfiguration{

	@Autowired
	private WebConfig webConfig;
	
	@Bean
	public FlowExecutor flowExecutor() {
		return getFlowExecutorBuilder(flowRegistry())
				//.addFlowExecutionListener(new SecurityFlowExecutionListener())
				.build();
	}
	
	@Bean
	public FlowDefinitionRegistry flowRegistry() {
		return getFlowDefinitionRegistryBuilder(flowBuilderServices())
				.setBasePath("/WEB-INF/views/flows")
				.addFlowLocationPattern("/**/*-flow.xml")
				.build();
	}
	
	@Bean
	public FlowBuilderServices flowBuilderServices() {
		return getFlowBuilderServicesBuilder()
				.setViewFactoryCreator(mvcViewFactoryCreator())
				.setValidator(validator())
				//.setDevelopmentMode(true)
				.build();
	}
	
	@Bean
	public FlowHandlerAdapter flowHandlerAdapter() {
		FlowHandlerAdapter adapter = new FlowHandlerAdapter();
		adapter.setFlowExecutor(flowExecutor());
		return adapter;
	}
	
	@Bean
	public FlowHandlerMapping flowHandlerMapping() {
		FlowHandlerMapping mapping = new FlowHandlerMapping();
		mapping.setFlowRegistry(flowRegistry());
		//mapping.setDefaultHandler(new UrlFilenameViewController());
		mapping.setOrder(-1);
		return mapping;
	}
	
	@Bean
	public MvcViewFactoryCreator mvcViewFactoryCreator() {
		MvcViewFactoryCreator factoryCreator = new MvcViewFactoryCreator();
		factoryCreator.setViewResolvers(Arrays.<ViewResolver>asList(webConfig.getViewResolver()));
		return factoryCreator;
	}
	
	@Bean
	public LocalValidatorFactoryBean validator() {
		return new LocalValidatorFactoryBean();
	}
}
