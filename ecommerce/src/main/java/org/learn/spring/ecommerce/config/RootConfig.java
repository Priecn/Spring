package org.learn.spring.ecommerce.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@ComponentScan(basePackages = {"org.learn.spring.ecommerce"}, excludeFilters= {
		@Filter(type=FilterType.ANNOTATION, value=EnableWebMvc.class)
})

//@PropertySources(value= {@PropertySource("classpath:db.properties")})
public class RootConfig {

	/*@Bean
	public PropertySourcesPlaceholderConfigurer getPropertySourcesPlaceHolderConfigurer() {
		return new PropertySourcesPlaceholderConfigurer();
	}*/
}
