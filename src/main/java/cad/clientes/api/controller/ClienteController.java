package cad.clientes.api.controller;

import cad.clientes.api.domain.cliente.*;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import java.time.LocalDate;

@RestController
@RequestMapping("clientes")
@SecurityRequirement(name = "bearer-key")
public class ClienteController {

    @Autowired
    private ClienteRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity cadastrar(@RequestBody @Valid DadosCadastroCliente dados, UriComponentsBuilder uriBuilder) {
        var cliente = new Cliente(dados);
        repository.save(cliente);
        var uri = uriBuilder.path("/clientes/{id}").buildAndExpand(cliente.getId()).toUri();

        return ResponseEntity.created(uri).body(new DadosDetalhamentoCliente(cliente));
    }

    @PutMapping
    @Transactional
    public ResponseEntity atualizar(@RequestBody @Valid DadosAtualizacaoCliente dados) {
        var cliente = repository.getReferenceById(dados.id());
        cliente.atualizarInformacoes(dados);
        return ResponseEntity.ok(new DadosDetalhamentoCliente(cliente));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity excluir(@PathVariable Long id) {
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<Page<DadosDetalhamentoCliente>> listar(@PageableDefault(size = 10, sort = {"nomeCompleto"}) Pageable paginacao) {
        var page = repository.findAll(paginacao).map(DadosDetalhamentoCliente::new);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/atributos")
    public ResponseEntity<Page<DadosDetalhamentoCliente>> listarPorAtributos(
            @PageableDefault(size = 10, sort = {"nomeCompleto"}) Pageable paginacao,
            @RequestParam(required = false) String nomeCompleto,
            @RequestParam(required = false) LocalDate dataNascimento,
            @RequestParam(required = false) Integer idade,
            @RequestParam(required = false) String cpf,
            @RequestParam(required = false) String telefone,
            @RequestParam(required = false) String email,
            @RequestParam(required = false) Sexo sexo,
            @RequestParam(required = false) EstadoCivil estadoCivil,
            @RequestParam(required = false) String profissao,
            @RequestParam(required = false) String nacionalidade,
            @RequestParam(required = false) String cep,
            @RequestParam(required = false) String cidade,
            @RequestParam(required = false) String uf) {
        var page = repository.listarPorAtributos(paginacao, nomeCompleto, dataNascimento, idade, cpf, telefone, email, sexo,
                estadoCivil, profissao, nacionalidade, cep, cidade, uf).map(DadosDetalhamentoCliente::new);
        return ResponseEntity.ok(page);
    }
}
