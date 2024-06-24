package br.com.darlison.products_api.application.controller;

import br.com.darlison.products_api.application.config.CommonResponseAnnontation;
import br.com.darlison.products_api.domain.model.Client;
import br.com.darlison.products_api.domain.model.PurchaseInfo;
import br.com.darlison.products_api.domain.usecase.GetPurchasesUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Endpoint de Clients Fieis")
@RequestMapping("/clients-fieis")
@RestController
public class TopClientsController {

  private final GetPurchasesUseCase getPurchasesUseCase;

  TopClientsController(GetPurchasesUseCase getPurchasesUseCase) {
    this.getPurchasesUseCase = getPurchasesUseCase;
  }

  @GetMapping
  @Operation(
      method = "GET",
      description = "Requisição GET para obter os clientes fiéis",
      summary = "GET clientes fiéis"
  )
  @CommonResponseAnnontation
  public List<Client> getTopClients() {
    return getPurchasesUseCase.getTopClients();
  }

}
