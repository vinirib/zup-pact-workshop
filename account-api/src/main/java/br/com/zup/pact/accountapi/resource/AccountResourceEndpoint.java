package br.com.zup.pact.accountapi.resource;

import br.com.zup.pact.accountapi.dto.AccountDetailsDTO;
import br.com.zup.pact.accountapi.service.AccountService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/v1/accounts")
@Api("Account API")
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class AccountResourceEndpoint {

    private final AccountService accountService;

    @GetMapping("/{clientId}")
    @ApiOperation(notes = "Return details of and Account by ClientId",
            value = "Get Account Details",
            nickname = "getAccountDetails",
            response = AccountDetailsDTO.class)
    @ApiResponses({
            @ApiResponse(code = 200, message = "Account Returned", response = AccountDetailsDTO.class),
            @ApiResponse(code = 404, message = "account Not Found"),
    })
    public ResponseEntity<AccountDetailsDTO> getAccountDetailsByClientId(@PathVariable("clientId") Integer clientId) {
        final Optional<AccountDetailsDTO> accountDetailsDTO = accountService.getAccountDetailsByClientId(clientId);
        if (accountDetailsDTO.isPresent()) {
            final AccountDetailsDTO sampleDTOS = accountDetailsDTO.get();
            return new ResponseEntity<>(sampleDTOS, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
