package br.com.darlison.products_api.infrastructure.mapper;

import br.com.darlison.products_api.domain.model.Client;
import br.com.darlison.products_api.domain.model.Purchase;
import br.com.darlison.products_api.infrastructure.dto.ClientDto;
import java.util.Collections;
import java.util.Optional;

public class ClientMapper {

  private ClientMapper() {}

  public static Client map(final ClientDto dto) {
    return Client
        .builder()
        .name(dto.getNome())
        .cpf(dto.getCpf())
        .purchaseList(Optional.ofNullable(dto.getCompras()).orElse(Collections.emptyList())
            .stream()
            .map(comp -> Purchase
                .builder()
                .quantity(comp.getQuantidade())
                .code(comp.getCodigo())
                .build())
            .toList())
        .build();
  }

}
