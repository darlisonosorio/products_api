package br.com.darlison.products_api.application.mapper;

import br.com.darlison.products_api.application.output.PurchaseResponse;
import br.com.darlison.products_api.domain.model.Product;
import br.com.darlison.products_api.domain.model.Purchase;
import java.util.Optional;

public final class PurchaseResponseMapper {

  public static PurchaseResponse map(Purchase purchase) {
    return PurchaseResponse.builder()
        .cpf(purchase.getClient().getCpf())
        .clientName(purchase.getClient().getName())
        .quantity(purchase.getQuantity())
        .productCode(Optional.ofNullable(purchase.getProduct()).map(Product::getCode).orElse(null))
        .wineType(Optional.ofNullable(purchase.getProduct()).map(Product::getWineType).orElse(null))
        .unitPrice(Optional.ofNullable(purchase.getProduct()).map(Product::getPrice).orElse(null))
        .yearHarvest(Optional.ofNullable(purchase.getProduct()).map(Product::getYear).orElse(null))
        .yearSell(Optional.ofNullable(purchase.getProduct()).map(Product::getYearSell).orElse(null))
        .totalPrice(purchase.getQuantity() * Optional.of(purchase.getProduct()).map(
            Product::getPrice).orElse(0.0))
        .build();
  }

}
