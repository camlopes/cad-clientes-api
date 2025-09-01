package cad.clientes.api.domain.usuario;

import jakarta.validation.constraints.NotBlank;

public record DadosUsuario(
        @NotBlank
        String nome,
        @NotBlank
        String email) {
}
