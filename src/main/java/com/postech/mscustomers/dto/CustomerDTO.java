package com.postech.mscustomers.dto;

import com.postech.mscustomers.interfaces.ValidCPF;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDTO {
    @NotNull(message = "Nome não pode ser nulo")
    private String nome;

    @NotNull(message = "CPF não pode ser nulo")
    @ValidCPF
    private String cpf;

    @NotNull(message = "Email não pode ser nulo")
    @Email(message = "Email deve ser válido")
    private String email;

    @NotNull(message = "Endereço não pode ser nulo")
    private String endereco;

    @NotNull(message = "Cidade não pode ser nula")
    private String cidade;

    @NotNull(message = "Estado não pode ser nulo")
    private String estado;

    @NotNull(message = "CEP não pode ser nulo")
    private String cep;
}