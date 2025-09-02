package cad.clientes.api.domain.cliente;

import cad.clientes.api.domain.endereco.DadosEndereco;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import java.time.LocalDate;

public record DadosCadastroCliente(
        @NotBlank
        String nomeCompleto,
        @NotNull
        @PastOrPresent
        LocalDate dataNascimento,
        @NotBlank
        @Pattern(regexp = "\\d{3}\\.\\d{3}\\.\\d{3}\\-\\d{2}")
        String cpf,
        @NotBlank
        String telefone,
        @NotBlank
        @Email
        String email,
        @NotNull
        Sexo sexo,
        @NotNull
        EstadoCivil estadoCivil,
        @NotBlank
        String profissao,
        @NotBlank
        String nacionalidade,
        @NotNull
        @Valid
        DadosEndereco endereco) {
}
