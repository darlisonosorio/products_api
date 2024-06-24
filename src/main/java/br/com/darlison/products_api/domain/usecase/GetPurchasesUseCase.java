package br.com.darlison.products_api.domain.usecase;

import br.com.darlison.products_api.domain.dto.TopClientDto;
import br.com.darlison.products_api.domain.mapper.PurchaseInfoMapper;
import br.com.darlison.products_api.domain.model.Client;
import br.com.darlison.products_api.domain.model.Product;
import br.com.darlison.products_api.domain.model.PurchaseInfo;
import java.util.Comparator;
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

  public List<PurchaseInfo> list() {
    List<Product> products = getProductsUseCase.getProducts();
    List<Client> clients = getClientsUseCase.getClients();

    return clients
        .stream()
        .flatMap(
            it -> it.getPurchaseList().stream().peek(purc -> purc.updateFields(it, products)))
        .map(PurchaseInfoMapper::map)
        .sorted(Comparator.comparing(PurchaseInfo::getTotalPrice))
        .toList();
  }

  public PurchaseInfo getBiggerPurchase(Integer year) {
    List<Product> products = getProductsUseCase.getProducts();
    List<Client> clients = getClientsUseCase.getClients();

    return clients
        .stream()
        .flatMap(
            it -> it.getPurchaseList().stream()
                .peek(purc -> purc.updateFields(it, products))
        )
        .filter(it -> Objects.equals(it.getProduct().getYearSell(), year))
        .map(PurchaseInfoMapper::map)
        .max(Comparator.comparing(PurchaseInfo::getTotalPrice))
        .orElse(null);
  }

  public List<Client> getTopClients() {
    List<Product> products = getProductsUseCase.getProducts();
    List<Client> clients = getClientsUseCase.getClients();

    return clients
        .stream()
        .map(it -> TopClientDto
            .builder()
            .client(it)
            .total(it.getPurchaseList().size())
            .totalAmount(it.getPurchaseList()
                .stream()
                .map(purc -> {
                  purc.updateFields(it, products);
                  return purc.getQuantity() * purc.getProduct().getPrice();
                })
                .reduce(0.0, Double::sum))
            .build())
        .sorted(Comparator.comparing(TopClientDto::getTotalAmount).reversed()
            .thenComparing(Comparator.comparing(TopClientDto::getTotal).reversed()))
        .map(TopClientDto::getClient)
        .limit(3)
        .toList();
  }
}
