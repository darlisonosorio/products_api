package br.com.darlison.products_api.domain.usecase;

import br.com.darlison.products_api.domain.model.Client;
import br.com.darlison.products_api.infrastructure.client.ClientApiClient;
import java.util.Collections;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class GetClientsUseCase {

  private final ClientApiClient clientApiClient;

  GetClientsUseCase(final ClientApiClient clientApiClient) {
    this.clientApiClient = clientApiClient;
  }

  public List<Client> getClients() {
    try {
      return clientApiClient.getClients();
    } catch (Exception e) {
      e.printStackTrace();
      return Collections.emptyList();
    }
  }

}
