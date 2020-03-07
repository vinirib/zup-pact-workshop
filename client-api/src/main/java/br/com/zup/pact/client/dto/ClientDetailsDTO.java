package br.com.zup.pact.client.dto;

import br.com.zup.pact.client.entity.Client;
import lombok.Builder;
import lombok.Data;

import java.util.Objects;

@Data
@Builder
public class ClientDetailsDTO {

    private Integer id;
    private String name;
    private String finalName;
    private Integer age;

    public static Client fromDtoToEntity(ClientDetailsDTO clientDetailsDTO) {
        if (Objects.nonNull(clientDetailsDTO)){
            return Client.builder()
                    .id(clientDetailsDTO.getId())
                    .name(clientDetailsDTO.getName())
                    .finalName(clientDetailsDTO.getFinalName())
                    .age(clientDetailsDTO.getAge())
                    .build();

        }
        return null;
    }
}
