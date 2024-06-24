package br.com.darlison.products_api.domain.model;


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
public class Product {

  private Integer code;
  private String wineType;
  private Double price;
  private String year;
  private Integer yearSell;

}
