package br.com.darlison.products_api.infrastructure.dto;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ClientDto {

  private String nome;
  private String cpf;
  private List<PurchaseDto> compras;

}
