package br.com.darlison.products_api.domain.dto;

import br.com.darlison.products_api.domain.model.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RecomendationProduct {

  private Product product;
  private Integer occurences;

}
