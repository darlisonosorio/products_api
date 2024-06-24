package br.com.darlison.products_api.application.controller;

import br.com.darlison.products_api.application.config.CommonResponseAnnontation;
import br.com.darlison.products_api.application.mapper.PurchaseResponseMapper;
import br.com.darlison.products_api.application.output.PurchaseResponse;
import br.com.darlison.products_api.domain.model.Purchase;
import br.com.darlison.products_api.domain.usecase.GetPurchasesUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.Comparator;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Endpoint de Compras")
@RestController
@RequestMapping("/compras")
public class PurchaseController {

  private final GetPurchasesUseCase getPurchasesUseCase;

  public PurchaseController(GetPurchasesUseCase getPurchasesUseCase) {
    this.getPurchasesUseCase = getPurchasesUseCase;
  }

  @GetMapping
  @Operation(
      method = "GET",
      description = "Requisição GET para obter a lista de Compr",
      summary = "GET list Compras"
  )
  @CommonResponseAnnontation
  public List<PurchaseResponse> list() {
    List<Purchase> result = getPurchasesUseCase.list();
    return result.stream()
        .map(PurchaseResponseMapper::map)
        .sorted(Comparator.comparing(PurchaseResponse::getTotalPrice))
        .toList();
  }

}
