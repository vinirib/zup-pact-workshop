package br.com.zup.pact.client.service.impl;

import br.com.zup.pact.client.dto.ClientDetailsDTO;
import br.com.zup.pact.client.repository.ClientRepository;
import br.com.zup.pact.client.service.ClientService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;

    @Override
    public Optional<ClientDetailsDTO> getClientDetails(Integer clientId) {
        return clientRepository.findByClientId(clientId);
    }

    @Override
    public Optional<List<ClientDetailsDTO>> getAll() {
        return clientRepository.getAll();
    }
}
