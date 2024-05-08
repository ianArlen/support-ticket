package com.gruposalinas.orchestrator.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.ResponseErrorHandler;
import org.springframework.web.client.RestTemplate;
import com.gruposalinas.orchestrator.exception.CustomException;

import java.io.IOException;

@Configuration
public class RestTemplateConfig {

  @Value("${rest.client.timeout:5000}")
  private int timeout;

  @Bean
  public RestTemplate restTemplate() {
    RestTemplate restTemplate = new RestTemplate(clientHttpRequestFactory());
    restTemplate.setErrorHandler(errorHandler());
    // Aquí podrías agregar interceptores si necesitas
    return restTemplate;
  }

  private ClientHttpRequestFactory clientHttpRequestFactory() {
    SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
    factory.setConnectTimeout(timeout);
    factory.setReadTimeout(timeout);
    return factory;
  }

  private ResponseErrorHandler errorHandler() {
    return new CustomResponseErrorHandler();
  }

  // Puedes definir aquí un error handler personalizado
  public static class CustomResponseErrorHandler implements ResponseErrorHandler {
    @Override
    public boolean hasError(org.springframework.http.client.ClientHttpResponse response) throws IOException {
      // Aquí puedes definir cuándo considerar una respuesta como error
      return response.getStatusCode().isError();
    }

    @Override
    public void handleError(org.springframework.http.client.ClientHttpResponse response) throws IOException {
      // Aquí manejas el error, por ejemplo lanzando una excepción personalizada
      throw new CustomException("Error al comunicarse con el servicio de dominio");
    }
  }
}
