package br.com.zup.pact.client.service;

import br.com.zup.pact.client.dto.ClientDetailsDTO;

import java.util.Optional;

public interface ClientService {
    Optional<ClientDetailsDTO> getClientDetails(Integer clientId);
}
