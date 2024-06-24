package br.com.darlison.products_api.application.controller;

import br.com.darlison.products_api.application.config.CommonResponseAnnontation;
import br.com.darlison.products_api.domain.model.PurchaseInfo;
import br.com.darlison.products_api.domain.usecase.GetPurchasesUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
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
      description = "Requisição GET para obter a lista de Compras",
      summary = "GET list Compras"
  )
  @CommonResponseAnnontation
  public List<PurchaseInfo> list() {
    return getPurchasesUseCase.list();
  }

}
