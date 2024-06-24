package br.com.darlison.products_api.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.List;
import java.util.Objects;
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

  public void updateFields(Client client, List<Product> products) {
    this.client = client;
    this.product = products.stream()
            .filter(prod -> Objects.equals(prod.getCode().toString(), this.code))
            .findFirst().orElse(null);

  }
}
