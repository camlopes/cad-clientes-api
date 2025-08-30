package cad.clientes.api.domain.cliente;

import cad.clientes.api.domain.endereco.DadosEndereco;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import java.time.LocalDate;

public record DadosAtualizacaoCliente(
        @NotNull
        Long id,
        String nomeCompleto,
        LocalDate dataNascimento,
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
        DadosEndereco endereco) {
}
