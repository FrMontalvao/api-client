package com.francisco.api_client.mapper;


import com.francisco.api_client.dto.ClientDTO;
import com.francisco.api_client.model.Client;
import org.mapstruct.*;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ClientMapper {
    ClientDTO toDTO(Client client);
    Client toEntity(ClientDTO clientDTO);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateFromDTO(ClientDTO clientDTO, @MappingTarget Client client);
}
