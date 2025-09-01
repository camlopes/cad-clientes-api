package cad.clientes.api.domain.usuario;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum Perfil {
    ADMIN_ROLE,
    USER_ROLE;

    private String role;
}
