package com.francisco.api_client.dto;

import java.time.LocalDate;

public record ClientDTO (
        Long id,
        String name,
        String email,
        String phone,
        String cpf,
        LocalDate birthDate,
        Boolean active

){
}
