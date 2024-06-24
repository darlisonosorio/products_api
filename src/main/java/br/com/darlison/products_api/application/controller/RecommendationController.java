package br.com.darlison.products_api.application.controller;

import br.com.darlison.products_api.application.config.CommonResponseAnnontation;
import br.com.darlison.products_api.domain.model.Product;
import br.com.darlison.products_api.domain.usecase.RecomendacaoUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Endpoint de Maior Compra")
@RequestMapping("/recomendacao")
@RestController
public class RecommendationController {

  private final RecomendacaoUseCase recomendacaoUseCase;

  RecommendationController(RecomendacaoUseCase recomendacaoUseCase) {
    this.recomendacaoUseCase = recomendacaoUseCase;
  }

  @GetMapping("/{cliente}/{tipo}")
  @Operation(
      method = "GET",
      description = "Requisição GET para obter a recomendação para o cliente",
      summary = "GET recomendação"
  )
  @CommonResponseAnnontation
  public Product getRecomendacao(@PathVariable("cliente") String cliente,
                                 @PathVariable("tipo") String tipo) {
    return recomendacaoUseCase.getRecomendacao(cliente, tipo);
  }

}
