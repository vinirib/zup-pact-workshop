package br.com.zup.pact.accountapi.repository;

import br.com.zup.pact.accountapi.dto.AccountDetailsDTO;
import br.com.zup.pact.accountapi.entity.Account;
import br.com.zup.pact.accountapi.stub.AccountStub;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class AccountRepository {

    private final AccountStub accountStub;

    public Optional<AccountDetailsDTO> findByClientId(Integer clientId) {
        return Optional.ofNullable(Account.fromEntityToDto(accountStub.getAccounts().get(clientId)));
    }

}
