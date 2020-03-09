package br.com.zup.pact.client.pact;

import au.com.dius.pact.provider.junit.Provider;
import au.com.dius.pact.provider.junit.State;
import au.com.dius.pact.provider.junit.VerificationReports;
import au.com.dius.pact.provider.junit.loader.PactBroker;
import au.com.dius.pact.provider.junit.target.Target;
import au.com.dius.pact.provider.junit.target.TestTarget;
import au.com.dius.pact.provider.junit5.HttpTestTarget;
import au.com.dius.pact.provider.junit5.PactVerificationContext;
import au.com.dius.pact.provider.junit5.PactVerificationInvocationContextProvider;
import br.com.zup.pact.client.dto.BalanceDTO;
import br.com.zup.pact.client.integration.account.service.AccountIntegrationService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestTemplate;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;

@Provider("AccountBalanceProvider")
@PactBroker(host = "localhost", port = "80")
@VerificationReports
@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AccountProviderPactTest {

    @LocalServerPort
    private int localServerPort;

    @MockBean
    private AccountIntegrationService accountIntegrationService;

    @BeforeEach
    void setUp(PactVerificationContext context) {
        context.setTarget(new HttpTestTarget("localhost", localServerPort, "/"));
    }

    @BeforeAll
    static void enablePublishingPact() {
        System.setProperty("pact.verifier.publishResults", "true");
    }

    @TestTemplate
    @ExtendWith(PactVerificationInvocationContextProvider.class)
    void testTemplate(PactVerificationContext context) {
        context.verifyInteraction();
    }

    @State("get balance by account id")
    public void getBalanceDTO() {
        final BalanceDTO balanceDTO = BalanceDTO.builder().clientId(1).accountId(1).balance(new BigDecimal("100")).build();
        given(accountIntegrationService.getBalance(eq(1))).willReturn(Optional.of(balanceDTO));
        //final Optional<BalanceDTO> balance = accountIntegrationService.getBalance(1);
    }

    @State("No accounts exist")
    public void getBalanceDTONotWorking() {
        given(accountIntegrationService.getBalance(eq(1))).willReturn(Optional.empty());
    }
}