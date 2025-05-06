package com.events.tickets.integrations.cep;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ViaCepService {

  private final RestTemplate restTemplate;
  private final String BASE_URL = "https://viacep.com.br/ws/";

  public ViaCepService(RestTemplate restTemplate) {
    this.restTemplate = restTemplate;
  }

  public ViaCepResponse getAddressByZipCode(String zipCode) {

    String url = BASE_URL + zipCode + "/json/";
    return restTemplate.getForObject(url, ViaCepResponse.class);
  }

}
