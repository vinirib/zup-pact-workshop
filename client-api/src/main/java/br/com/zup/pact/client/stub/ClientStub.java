package br.com.zup.pact.client.stub;

import br.com.zup.pact.client.entity.Client;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@Slf4j
public class ClientStub {

    @Getter
    private Map<Integer, Client> clients;
    private static final Integer NUMBER_OF_STUBS = 10;
    private static final Integer MIN_AGE = 18;
    private static final Integer MAX_AGE = 70;

    public ClientStub() {
        log.info("\n\n\n\t\t\t\t\t\t ============================ Creating Client Stubs! ============================ \n");
        clients = createStubs(NUMBER_OF_STUBS);
    }

    private Map<Integer, Client> createStubs(int numberOfStubs) {
        Map<Integer, Client> clients = new HashMap<>(NUMBER_OF_STUBS);
        for (int i = 1; i <= numberOfStubs; i++) {
            final Client client = Client.builder()
                    .id(i)
                    .age(new Random().nextInt((MAX_AGE - MIN_AGE) + 1) + MIN_AGE)
                    .name(NameStub.getRandomFirstName())
                    .finalName(NameStub.getRandomLastName())
                    .build();
            clients.put(i, client);
        }
        return clients;
    }
}
