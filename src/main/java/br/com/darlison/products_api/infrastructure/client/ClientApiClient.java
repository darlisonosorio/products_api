package br.com.darlison.products_api.infrastructure.client;

import br.com.darlison.products_api.domain.model.Client;
import br.com.darlison.products_api.domain.model.Product;
import br.com.darlison.products_api.domain.model.Purchase;
import br.com.darlison.products_api.infrastructure.dto.ClientDto;
import br.com.darlison.products_api.infrastructure.dto.ProductDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ClientApiClient {

  private final RestTemplate restTemplate;
  private final ObjectMapper objectMapper;

  public ClientApiClient(RestTemplate restTemplate, ObjectMapper objectMapper) {
    this.restTemplate = restTemplate;
    this.objectMapper = objectMapper;
  }

  @Value("${external_api.clients.server.url}")
  private String apiUrl;

  public List<Client> getClients() throws JsonProcessingException {

    String jsonResponse = restTemplate.getForObject(apiUrl, String.class);
    return objectMapper.readValue(jsonResponse, new TypeReference<List<ClientDto>>(){})
        .stream()
        .map(it -> Client
            .builder()
            .name(it.getNome())
            .cpf(it.getCpf())
            .purchaseList(Optional.ofNullable(it.getCompras()).orElse(Collections.emptyList())
                .stream()
                .map(comp -> Purchase
                    .builder()
                    .quantity(comp.getQuantidade())
                    .code(comp.getCodigo())
                    .build())
                .toList())
            .build())
        .toList();
  }

}
