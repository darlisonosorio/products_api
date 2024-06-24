package br.com.darlison.products_api.domain.usecase;

import br.com.darlison.products_api.domain.model.Product;
import br.com.darlison.products_api.infrastructure.client.ProductApiClient;
import java.util.Collections;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class GetProductsUseCase {

  private final ProductApiClient productApiClient;

  public GetProductsUseCase(ProductApiClient productApiClient) {
    this.productApiClient = productApiClient;
  }

  public List<Product> getProducts() {
    try {
      return productApiClient.getClients();
    } catch (Exception e) {
      e.printStackTrace();
      return Collections.emptyList();
    }
  }

}
