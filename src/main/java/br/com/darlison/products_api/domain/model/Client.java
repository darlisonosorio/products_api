package br.com.darlison.products_api.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.List;
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
public class Client {

  private String name;
  private String cpf;

  @JsonIgnore
  private List<Purchase> purchaseList;
}
