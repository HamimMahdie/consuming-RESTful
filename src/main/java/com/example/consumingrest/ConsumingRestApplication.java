package com.example.consumingrest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class ConsumingRestApplication {

    private static final Logger log = LoggerFactory.getLogger(ConsumingRestApplication.class);

    // Inject the base URL from application properties or environment variables
    
	@Value("${quoters_base_url}")
    private String quotersBaseUrl;
    

    public static void main(String[] args) {
        SpringApplication.run(ConsumingRestApplication.class, args);
    }

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }

    @Bean
    @Profile("!test")  // Ensure this runner is not executed in test environments
    public CommandLineRunner run(RestTemplate restTemplate) throws Exception {
        return args -> {
            // Fetching random quote
            Quote randomQuote = restTemplate.getForObject(quotersBaseUrl + "/api/random", Quote.class);
            log.info("Random Quote: {}", randomQuote);

            // Fetching quote with ID 1
            Quote quote1 = restTemplate.getForObject(quotersBaseUrl + "/api/1", Quote.class);
            log.info("Quote 1: {}", quote1);

            // Fetching quote with ID 2
            Quote quote2 = restTemplate.getForObject(quotersBaseUrl + "/api/2", Quote.class);
            log.info("Quote 2: {}", quote2);

            // Fetching quote with ID 3
            Quote quote3 = restTemplate.getForObject(quotersBaseUrl + "/api/3", Quote.class);
            log.info("Quote 3: {}", quote3);
        };
    }
}
