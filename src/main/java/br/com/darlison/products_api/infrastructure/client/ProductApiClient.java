package br.com.darlison.products_api.infrastructure.client;

import br.com.darlison.products_api.domain.model.Product;
import br.com.darlison.products_api.infrastructure.dto.ProductDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ProductApiClient {

  private final RestTemplate restTemplate;

  private final ObjectMapper objectMapper;

  public ProductApiClient(RestTemplate restTemplate, ObjectMapper objectMapper) {
    this.restTemplate = restTemplate;
    this.objectMapper = objectMapper;
  }

  @Value("${external_api.products.server.url}")
  private String apiUrl;

  public List<Product> getClients() throws JsonProcessingException {

    String jsonResponse = restTemplate.getForObject(apiUrl, String.class);
    return objectMapper.readValue(jsonResponse, new TypeReference<List<ProductDto>>(){})
        .stream()
        .map(it -> Product
            .builder()
            .code(it.getCodigo())
            .price(it.getPreco())
            .wineType(it.getTipoVinho())
            .yearSell(it.getAnoCompra())
            .year(it.getSafra())
            .build())
        .toList();
  }

}
