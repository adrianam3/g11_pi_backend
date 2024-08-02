package com.hd.helpDesk;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.hd.helpDesk.modules.*")
@EntityScan("com.hd.helpDesk.modules.*")
@ComponentScan(basePackages = { "com.hd.helpDesk.modules.*.controller", "com.hd.helpDesk.modules.*.service",
		"com.hd.helpDesk.modules.*.*.controller", "com.hd.helpDesk.modules.*.*.controller",
		"com.helpDesk.modules.configurations" })
public class HelpDeskApplication {

	public static void main(String[] args) {
		SpringApplication.run(HelpDeskApplication.class, args);
	}

	@Value("${openai.api.key}")
	private String openaiApiKey;

	///***** */
	@Bean
	public RestTemplate restTemplate() {
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.getInterceptors().add(((request, body, execution) -> {
			request.getHeaders().add("Authorization", "Bearer " + openaiApiKey);
			return execution.execute(request, body);
		}));
		return restTemplate;
	}

}
