package cad.clientes.api.controller;

import cad.clientes.api.domain.cliente.*;
import cad.clientes.api.domain.endereco.DadosEndereco;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.http.HttpStatus;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.http.MediaType;
import java.time.LocalDate;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
class ClienteControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private JacksonTester<DadosCadastroCliente> dadosCadastroClienteJson;

    @Autowired
    private JacksonTester<DadosDetalhamentoCliente> dadosDetalhamentoClienteJson;

    @MockitoBean
    private ClienteRepository repository;

    @Test
    @DisplayName("Deveria devolver codigo http 400 quando informacoes estao invalidas")
    @WithMockUser
    void cadastrar_cenario1() throws Exception {
        var response = mvc
                .perform(post("/clientes"))
                .andReturn().getResponse();

        assertThat(response.getStatus())
                .isEqualTo(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    @DisplayName("Deveria devolver codigo http 201 quando informacoes estao validas")
    @WithMockUser
    void cadastrar_cenario2() throws Exception {
        var dataNascimento = LocalDate.of(2003, 8, 8);
        var nomeCompleto = "Fernando Silva";
        var cpf = "122.994.049-94";
        var email = "fernando.silva@gmail.com";

        var dadosCadastroCliente = dadosCliente(nomeCompleto, dataNascimento, cpf, email);
        when(repository.save(any())).thenReturn(new Cliente(dadosCadastroCliente));

        var response = mvc
                .perform(post("/clientes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(dadosCadastroClienteJson.write(dadosCadastroCliente).getJson()))
                .andReturn().getResponse();

        var dadosDetalhamento = dadosDetalhamentoCliente(dadosCadastroCliente);
        var jsonEsperado = dadosDetalhamentoClienteJson.write(dadosDetalhamento).getJson();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.CREATED.value());
        assertThat(response.getContentAsString()).isEqualTo(jsonEsperado);
    }

    private DadosDetalhamentoCliente dadosDetalhamentoCliente(DadosCadastroCliente dadosCadastroCliente) {
        var cliente = new Cliente(dadosCadastroCliente);
        return new DadosDetalhamentoCliente(cliente);
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
