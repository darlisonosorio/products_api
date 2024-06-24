package br.com.darlison.products_api.domain.usecase;

import br.com.darlison.products_api.domain.model.Client;
import br.com.darlison.products_api.domain.model.Product;
import br.com.darlison.products_api.domain.model.Purchase;
import java.util.List;
import java.util.Objects;
import org.springframework.stereotype.Component;

@Component
public class GetPurchasesUseCase {

  private GetClientsUseCase getClientsUseCase;
  private GetProductsUseCase getProductsUseCase;

  GetPurchasesUseCase(final GetClientsUseCase getClientsUseCase,
      final GetProductsUseCase getProductsUseCase) {
    this.getClientsUseCase = getClientsUseCase;
    this.getProductsUseCase = getProductsUseCase;
  }

  public List<Purchase> list() {
    List<Product> products = getProductsUseCase.getProducts();
    List<Client> clients = getClientsUseCase.getClients();

    return clients
        .stream()
        .flatMap(
            it -> it.getPurchaseList().stream().peek(purc -> updateFields(purc, it, products)))
        .toList();
  }

  private void updateFields(Purchase purchase, Client client, List<Product> products) {
    purchase.setClient(client);
    purchase.setProduct(
        products.stream()
            .filter(prod -> Objects.equals(prod.getCode().toString(), purchase.getCode()))
            .findFirst().orElse(null));
  }

}
