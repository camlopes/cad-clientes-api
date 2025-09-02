package cad.clientes.api.domain.cliente;

import cad.clientes.api.domain.endereco.DadosEndereco;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import java.time.LocalDate;

public record DadosAtualizacaoCliente(
        @NotNull
        Long id,
        @Size(min = 3, max = 100)
        String nomeCompleto,
        @PastOrPresent
        LocalDate dataNascimento,
        @PositiveOrZero
        int idade,
        @Pattern(regexp = "\\d{3}\\.\\d{3}\\.\\d{3}\\-\\d{2}")
        String cpf,
        String telefone,
        @Email
        String email,
        Sexo sexo,
        EstadoCivil estadoCivil,
        String profissao,
        String nacionalidade,
        @Valid
        DadosEndereco endereco) {
}
