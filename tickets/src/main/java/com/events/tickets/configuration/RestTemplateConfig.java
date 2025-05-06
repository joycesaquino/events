package com.events.tickets.configuration;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.DefaultResponseErrorHandler;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.DefaultUriBuilderFactory;

/**
 * Singleton rest template
 */
@Slf4j
@Configuration
public class RestTemplateConfig {

  @Bean
  public RestTemplate restTemplate(RestTemplateBuilder builder) {
    return builder
        .additionalMessageConverters(
            new StringHttpMessageConverter(StandardCharsets.UTF_8),
            new MappingJackson2HttpMessageConverter()
        )
        .interceptors(clientHttpRequestInterceptor())
        .errorHandler(new CustomResponseErrorHandler())
        .uriTemplateHandler(new DefaultUriBuilderFactory())
        .build();
  }

  private ClientHttpRequestInterceptor clientHttpRequestInterceptor() {
    return (request, body, execution) -> execution.execute(request, body);
  }

  public static class CustomResponseErrorHandler extends DefaultResponseErrorHandler {

    @Override
    public void handleError(ClientHttpResponse response) throws IOException {
      if (response.getStatusCode().isError()) {
        log.info("Server error occurred: {} -  {}", response.getStatusCode(),
            response.getStatusText());
        super.handleError(response);
      }
    }
  }
}
