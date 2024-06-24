package br.com.darlison.products_api.application.output;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PurchaseResponse {

  private String clientName;
  private String cpf;
  private Integer quantity;
  private Double totalPrice;
  private Integer productCode;
  private String wineType;
  private Double unitPrice;
  private String yearHarvest;
  private Integer yearSell;

}
