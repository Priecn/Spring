package org.learn.spring.ecommerce.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {"org.learn.spring.ecommerce", "org.learn.spring.ecommerce_backend"})
//@PropertySources(value= {@PropertySource("classpath:db.properties")})
public class RootConfig {

	/*@Bean
	public PropertySourcesPlaceholderConfigurer getPropertySourcesPlaceHolderConfigurer() {
		return new PropertySourcesPlaceholderConfigurer();
	}*/
}
