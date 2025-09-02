package cad.clientes.api.domain.cliente;

import cad.clientes.api.domain.endereco.DadosEndereco;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.ActiveProfiles;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.assertEquals;


@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
class ClienteRepositoryTest {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private TestEntityManager em;

    @Test
    @DisplayName("Devera devolver um cliente contendo todos os parametros pesquisados")
    void listarPorAtributosCenario1() throws Exception {
        var dataNascimento = LocalDate.of(2003, 8, 8);
        var nomeCompleto = "Fernando Silva";
        var cpf = "122.994.049-94";
        var email = "fernando.silva@gmail.com";

        cadastrarCliente(nomeCompleto, dataNascimento, cpf, email);

        Page<Cliente> resultado = clienteRepository.listarPorAtributos(
                PageRequest.of(0, 10),
                nomeCompleto,
                dataNascimento,
                22,
                cpf,
                "61999999999",
                email,
                Sexo.MASCULINO,
                EstadoCivil.CASADO,
                "Engenheiro",
                "Brasileira",
                "00000000",
                "Rio de Janeiro",
                "RJ"
        );

        assertEquals(1, resultado.getTotalElements());
        assertEquals(nomeCompleto, resultado.getContent().get(0).getNomeCompleto());
        assertEquals(dataNascimento, resultado.getContent().get(0).getDataNascimento());
        assertEquals(22, resultado.getContent().get(0).getIdade());
        assertEquals(cpf, resultado.getContent().get(0).getCpf());
        assertEquals("61999999999", resultado.getContent().get(0).getTelefone());
        assertEquals(email, resultado.getContent().get(0).getEmail());
        assertEquals(Sexo.MASCULINO, resultado.getContent().get(0).getSexo());
        assertEquals(EstadoCivil.CASADO, resultado.getContent().get(0).getEstadoCivil());
        assertEquals("Engenheiro", resultado.getContent().get(0).getProfissao());
        assertEquals("Brasileira", resultado.getContent().get(0).getNacionalidade());
        assertEquals("00000000", resultado.getContent().get(0).getEndereco().getCep());
        assertEquals("Rio de Janeiro", resultado.getContent().get(0).getEndereco().getCidade());
        assertEquals("RJ", resultado.getContent().get(0).getEndereco().getUf());
    }

    @Test
    @DisplayName("Devera devolver todos os clientes cadastrados no banco de dados se não colocar parametros para a pesquisa")
    void listarPorAtributosCenario2() throws Exception {
        var dataNascimento1 = LocalDate.of(2003, 8, 8);
        var nomeCompleto1 = "Fernando Silva";
        var cpf1 = "122.994.049-94";
        var email1 = "fernando.silva@gmail.com";
        var cliente1 = cadastrarCliente(nomeCompleto1, dataNascimento1, cpf1, email1);

        var dataNascimento2 = LocalDate.of(1980, 8, 8);
        var nomeCompleto2 = "Roberto Silva";
        var cpf2 = "122.157.049-94";
        var email2 = "roberto.silva@gmail.com";
        var cliente2 = cadastrarCliente(nomeCompleto2, dataNascimento2, cpf2, email2);

        Page<Cliente> resultado = clienteRepository.listarPorAtributos(
                PageRequest.of(0, 10),
                null,   // nomeCompleto
                null,    // dataNascimento
                null,    // idade
                null,    // cpf
                null,    // telefone
                null,    // email
                null,    // sexo
                null,    // estadoCivil
                null,    // profissao
                null,    // nacionalidade
                null,    // cep
                null,    // cidade
                null     // uf
        );

        assertEquals(2L, resultado.getTotalElements());
        assertEquals(cliente1, resultado.getContent().get(0));
        assertEquals(cliente2, resultado.getContent().get(1));
    }

    @Test
    @DisplayName("Devera devolver somente um cliente cadastrado de acordo com a pesquisa pelo seu cpf")
    void listarPorAtributosCenario3() throws Exception {
        var dataNascimento1 = LocalDate.of(2003, 8, 8);
        var nomeCompleto1 = "Fernando Silva";
        var cpf1 = "122.994.049-94";
        var email1 = "fernando.silva@gmail.com";
        var cliente1 = cadastrarCliente(nomeCompleto1, dataNascimento1, cpf1, email1);

        var dataNascimento2 = LocalDate.of(1980, 8, 8);
        var nomeCompleto2 = "Roberto Silva";
        var cpf2 = "122.157.049-94";
        var email2 = "roberto.silva@gmail.com";
        var cliente2 = cadastrarCliente(nomeCompleto2, dataNascimento2, cpf2, email2);

        Page<Cliente> resultado = clienteRepository.listarPorAtributos(
                PageRequest.of(0, 10),
                null,   // nomeCompleto
                null,    // dataNascimento
                null,    // idade
                "122.994.049-94",    // cpf
                null,    // telefone
                null,    // email
                null,    // sexo
                null,    // estadoCivil
                null,    // profissao
                null,    // nacionalidade
                null,    // cep
                null,    // cidade
                null     // uf
        );

        assertEquals(1L, resultado.getTotalElements());
        assertEquals(cliente1, resultado.getContent().get(0));
    }

    private Cliente cadastrarCliente(String nomeCompleto, LocalDate dataNascimento, String cpf, String email) {
        var cliente = new Cliente(dadosCliente(nomeCompleto, dataNascimento, cpf, email));
        em.persist(cliente);
        return cliente;
    }

    private DadosCadastroCliente dadosCliente(String nomeCompleto, LocalDate dataNascimento, String cpf, String email) {
        return new DadosCadastroCliente(
                nomeCompleto,
                dataNascimento,
                cpf,
                "61999999999",
                email,
                Sexo.MASCULINO,
                EstadoCivil.CASADO,
                "Engenheiro",
                "Brasileira",
                dadosEndereco()
        );
    }

    private DadosEndereco dadosEndereco() {
        return new DadosEndereco(
                "rua xpto",
                "bairro",
                "00000000",
                "Rio de Janeiro",
                "RJ",
                null,
                null
        );
    }
}