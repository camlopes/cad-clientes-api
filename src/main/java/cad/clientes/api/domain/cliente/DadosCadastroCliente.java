package cad.clientes.api.domain.cliente;

import cad.clientes.api.domain.endereco.DadosEndereco;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import java.time.LocalDate;

public record DadosCadastroCliente(
        @NotBlank
        String nomeCompleto,
        @NotNull
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
