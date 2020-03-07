package br.com.zup.pact.accountapi.resource;

import br.com.zup.pact.accountapi.dto.AccountDetailsDTO;
import br.com.zup.pact.accountapi.entity.Account;
import br.com.zup.pact.accountapi.service.AccountService;
import br.com.zup.pact.accountapi.stub.AccountStub;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import java.util.Optional;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class AccountResourceEndpointTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AccountService accountService;

    @Autowired
    private AccountStub accountStub;

    @Test
    void getAccountDetails() throws Exception {
        final AccountDetailsDTO accountDetailsDTO = Account.fromEntityToDto(accountStub.getAccounts().get(1));
        when(accountService.getAccountDetailsByClientId(anyInt())).thenReturn(Optional.of(accountDetailsDTO));
        mockMvc.perform(get("/v1/accounts/1"))
                .andDo(print())
                .andExpect(jsonPath("$.accountId").exists())
                .andExpect(jsonPath("$.balance").exists())
                .andExpect(jsonPath("$.accountType").exists())
                .andExpect(jsonPath("$.accountId").value(1))
                .andExpect(jsonPath("$.balance").value(100))
                .andExpect(jsonPath("$.accountType").value(accountDetailsDTO.getAccountType().toString()))
                .andExpect(status().isOk());
    }
}