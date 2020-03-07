package br.com.zup.pact.client.resource;

import br.com.zup.pact.client.dto.ClientDetailsDTO;
import br.com.zup.pact.client.service.ClientService;
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
@RequestMapping("/v1/clients")
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ClientResourceEndpoint {

    private final ClientService clientService;

    @GetMapping("/{clientId}")
    @ApiOperation(notes = "Return details of and Client",
            value = "Get Client Details",
            nickname = "getClientDetails",
            response = ClientDetailsDTO.class)
    @ApiResponses({
            @ApiResponse(code = 200, message = "Client Returned", response = ClientDetailsDTO.class),
            @ApiResponse(code = 404, message = "Client Not Found"),
    })
    public ResponseEntity<ClientDetailsDTO> getClientDetails(@PathVariable("clientId") Integer clientId) {
        final Optional<ClientDetailsDTO> ClientDetailsDTO = clientService.getClientDetails(clientId);
        if (ClientDetailsDTO.isPresent()) {
            final ClientDetailsDTO sampleDTOS = ClientDetailsDTO.get();
            return new ResponseEntity<>(sampleDTOS, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
