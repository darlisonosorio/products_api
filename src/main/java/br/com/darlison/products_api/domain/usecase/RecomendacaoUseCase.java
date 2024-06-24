package br.com.darlison.products_api.domain.usecase;

import br.com.darlison.products_api.common.exception.BadRequestException;
import br.com.darlison.products_api.domain.model.Client;
import br.com.darlison.products_api.domain.model.Product;
import java.util.List;
import java.util.Objects;
import org.springframework.stereotype.Component;

@Component
public class RecomendacaoUseCase {

  private GetClientsUseCase getClientsUseCase;
  private GetProductsUseCase getProductsUseCase;

  RecomendacaoUseCase(final GetClientsUseCase getClientsUseCase,
      final GetProductsUseCase getProductsUseCase) {
    this.getClientsUseCase = getClientsUseCase;
    this.getProductsUseCase = getProductsUseCase;
  }

  public Product getRecomendacao(String cliente, String tipo) {

    List<Product> products = getProductsUseCase.getProducts();
    Client client = getClientsUseCase.getClients()
        .stream()
        .filter(it -> it.getName().toLowerCase().contains(cliente.toLowerCase()))
        .findFirst()
        .map(it -> {
          it.setPurchaseList(
            it.getPurchaseList().stream()
              .peek(purc -> purc.updateFields(it, products))
              .filter(purc -> compareWines(purc.getProduct().getWineType(), tipo))
              .toList()
          );
          return it;
        })
        .orElseThrow(() -> new BadRequestException("CLI_NOT_FOUND", "Cliente não encontrado."));

    return products
        .stream()
        .filter(it -> compareWines(it.getWineType(), tipo))
        .filter(it -> client.getPurchaseList().stream()
            .noneMatch(purc -> Objects.equals(purc.getProduct().getCode(), it.getCode())))
        .findFirst()
        .orElseThrow(() -> new BadRequestException("PROD_NOT_FOUND", "Nenhum produto encontrado."));
  }

  private boolean compareWines(String wine1, String wine2) {
    return wine1.equalsIgnoreCase(wine2);
  }
}
