package cad.clientes.api.domain.cliente;

import cad.clientes.api.domain.endereco.Endereco;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Table(name = "clientes")
@Entity(name = "Cliente")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nomeCompleto;
    private LocalDate dataNascimento;
    private int idade;
    private String cpf;
    private String telefone;
    private String email;
    @Enumerated(EnumType.STRING)
    private Sexo sexo;
    @Enumerated(EnumType.STRING)
    private EstadoCivil estadoCivil;
    private String profissao;
    private String nacionalidade;
    @Embedded
    private Endereco endereco;

    public Cliente(DadosCadastroCliente dados) {
        this.nomeCompleto = dados.nomeCompleto();
        this.dataNascimento = dados.dataNascimento();
        this.idade = calculaIdade(dados.dataNascimento());
        this.cpf = dados.cpf();
        this.telefone = dados.telefone();
        this.email = dados.email();
        this.sexo = dados.sexo();
        this.estadoCivil = dados.estadoCivil();
        this.profissao = dados.profissao();
        this.nacionalidade = dados.nacionalidade();
        this.endereco = new Endereco(dados.endereco());
    }

    public int calculaIdade(LocalDate dataNascimento) {
        var anoAtual = LocalDate.now().getYear();
        var idade = anoAtual - dataNascimento.getYear();
        return idade;
    }
}
