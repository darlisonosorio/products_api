package br.com.darlison.products_api.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Purchase {

  private String code;
  private Integer quantity;

  @JsonIgnore
  private Client client;

  @JsonIgnore
  private Product product;

}
