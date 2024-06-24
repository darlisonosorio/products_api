package br.com.darlison.products_api.infrastructure.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProductDto {

  private Integer codigo;
  private String tipoVinho;
  private Double preco;
  private String safra;
  private Integer anoCompra;

}
