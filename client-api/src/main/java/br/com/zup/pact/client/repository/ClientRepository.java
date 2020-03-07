package br.com.zup.pact.client.repository;

import br.com.zup.pact.client.dto.ClientDetailsDTO;
import br.com.zup.pact.client.entity.Client;
import br.com.zup.pact.client.stub.ClientStub;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ClientRepository {

    private final ClientStub clientStub;

    public Optional<ClientDetailsDTO> findByClientId(Integer clientId) {
        return Optional.ofNullable(Client.fromEntityToDto(clientStub.getClients().get(clientId)));
    }
}
