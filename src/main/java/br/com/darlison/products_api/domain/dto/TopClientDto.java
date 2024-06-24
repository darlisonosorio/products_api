package br.com.darlison.products_api.domain.dto;

import br.com.darlison.products_api.domain.model.Client;
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
public class TopClientDto {

  private Client client;
  private Double totalAmount;
  private Integer total;

}
