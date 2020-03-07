package br.com.zup.pact.accountapi.stub;

import br.com.zup.pact.accountapi.entity.Account;
import br.com.zup.pact.accountapi.enums.AccountType;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;

@Service
@Slf4j
public class AccountStub {

    @Getter
    private Map<Integer, Account> accounts;
    private static final Integer NUMBER_OF_STUBS = 10;
    private static final BigDecimal INITIAL_BALANCE = new BigDecimal("100");
    private static final List<AccountType> ACCOUNT_TYPES = Arrays.asList(AccountType.class.getEnumConstants());


    public AccountStub() {
        log.info("\n\n\n\t\t\t\t\t\t ============================ Creating Account Stubs! ============================ \n");
        accounts = createStubs(NUMBER_OF_STUBS);
    }

    private Map<Integer, Account> createStubs(int numberOfStubs) {
        Map<Integer, Account> accounts = new HashMap<>(NUMBER_OF_STUBS);
        Collections.shuffle(ACCOUNT_TYPES);
        for (int i = 1; i <= numberOfStubs; i++) {
            final Account account = Account.builder()
                    .id(i)
                    .clientId(i)
                    .balance(INITIAL_BALANCE)
                    .accountType(ACCOUNT_TYPES.get(0))
                    .build();
            accounts.put(i, account);
        }
        return accounts;
    }
}
