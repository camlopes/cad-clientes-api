package cad.clientes.api.domain.cliente;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.time.LocalDate;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    @Query("""
            SELECT c FROM Cliente c
            WHERE (:nomeCompleto IS NULL OR LOWER(c.nomeCompleto) LIKE LOWER(CONCAT('%', :nomeCompleto, '%')))
            AND (:dataNascimento IS NULL OR c.dataNascimento = :dataNascimento)
            AND (:idade IS NULL OR c.idade = :idade)
            AND (:cpf IS NULL OR c.cpf = :cpf)
            AND (:telefone IS NULL OR c.telefone = :telefone)
            AND (:email IS NULL OR c.email = :email)
            AND (:sexo IS NULL OR c.sexo = :sexo)
            AND (:estadoCivil IS NULL OR c.estadoCivil = :estadoCivil)
            AND (:profissao IS NULL OR LOWER(c.profissao) LIKE LOWER(CONCAT('%', :profissao, '%')))
            AND (:nacionalidade IS NULL OR c.nacionalidade = :nacionalidade)
        """)
    Page<Cliente> listarPorAtributos(
            Pageable paginacao,
            String nomeCompleto,
            LocalDate dataNascimento,
            Integer idade,
            String cpf,
            String telefone,
            String email,
            Sexo sexo,
            EstadoCivil estadoCivil,
            String profissao,
            String nacionalidade);
}
