package com.postech.mscustomers.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
@AllArgsConstructor
public class CustomerDTO {
    private String id;
    @NotNull(message = "O nome não pode ser nulo.")
    private String nome;

}