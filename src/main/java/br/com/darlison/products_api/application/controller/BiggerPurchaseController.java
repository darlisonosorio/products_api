package br.com.darlison.products_api.application.controller;

import br.com.darlison.products_api.application.config.CommonResponseAnnontation;
import br.com.darlison.products_api.domain.model.PurchaseInfo;
import br.com.darlison.products_api.domain.usecase.GetPurchasesUseCase;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/maior-compra")
@RestController
public class BiggerPurchaseController {

  private GetPurchasesUseCase getPurchasesUseCase;

  BiggerPurchaseController(GetPurchasesUseCase getPurchasesUseCase) {
    this.getPurchasesUseCase = getPurchasesUseCase;
  }

  @GetMapping("/{ano}")
  @Operation(
      method = "GET",
      description = "Requisição GET para obter a maior compra pelo Ano",
      summary = "GET maior Compra"
  )
  @CommonResponseAnnontation
  public PurchaseInfo getBiggerPurchase(@PathVariable("ano") Integer year) {
    return getPurchasesUseCase.getBiggerPurchase(year);
  }

}
