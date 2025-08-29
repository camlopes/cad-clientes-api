package cad.clientes.api.domain.cliente;

import cad.clientes.api.domain.endereco.Endereco;
import java.time.LocalDate;

public record DadosDetalhamentoCliente(
        Long id,
        String nomeCompleto,
        LocalDate dataNascimento,
        int idade,
        String cpf,
        String telefone,
        String email,
        Sexo sexo,
        EstadoCivil estadoCivil,
        String profissao,
        String nacionalidade,
        Endereco endereco) {

    public DadosDetalhamentoCliente(Cliente cliente) {
        this(cliente.getId(),
                cliente.getNomeCompleto(),
                cliente.getDataNascimento(),
                cliente.getIdade(),
                cliente.getCpf(),
                cliente.getTelefone(),
                cliente.getEmail(),
                cliente.getSexo(),
                cliente.getEstadoCivil(),
                cliente.getProfissao(),
                cliente.getNacionalidade(),
                cliente.getEndereco());
    }
}
