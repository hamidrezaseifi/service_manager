package de.seifi.wernberg_java.security;

import java.util.Arrays;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsWebConfig implements WebMvcConfigurer {

    private static final Logger LOGGER = LoggerFactory.getLogger(CorsWebConfig.class);

	/*private List<String> allowedOrigins = Arrays.asList(
			"http://localhost:4200", 
			"https://schadenanlageservice-frontend.core.dev.aws.tacp.cloud-talanx.com");
	*/

	@Value("${wernberg_java.cors.allowedOrigings}")
	private String[] allowedOrigins;
	
	@PostConstruct
	public void init() {
		LOGGER.info("Allowed cors origind: " + allowedOrigins);
	}
	
	@Override
	public void addCorsMappings(CorsRegistry registry) {
		 registry.addMapping("/**")
	        .allowedMethods("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS")
	        .exposedHeaders("Authorization").allowedOrigins("*");
	}
	
	
	@Bean
	public CorsFilter corsFilter() {
	    final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();

	    final CorsConfiguration config = new CorsConfiguration();
	    config.setAllowCredentials(true);
	    
		config.setAllowedOrigins(Arrays.asList(allowedOrigins));
	    config.setAllowedHeaders(Arrays.asList("Origin", "Content-Type", "Accept", "Authorization", "User-Agent"));
	    config.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
	    config.setExposedHeaders(Arrays.asList("Authorization"));

	    source.registerCorsConfiguration("/**", config);
	    return new CorsFilter(source);
	}

}
