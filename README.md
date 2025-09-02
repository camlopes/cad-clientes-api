<h1>API para cadastro de clientes pessoa física</h1>

<h2> 📌 Descrição do projeto</h2>

<p> Desenvolvimento de um MVP de uma API para cadastro de clientes pessoa física: é possível incluir, editar, listar e excluir os registros de clientes. </p>

<p> Requisitos:

- Permitir inclusão de novos clientes.  
- Permitir a atualização de clientes existentes.  
- Permitir a exclusão de clientes existentes.  
- Permitir a listagem dos clientes de forma paginada.  
- Permitir buscas por atributos cadastrais do cliente.  
- Retornar, em cada elemento da API, a **idade calculada** a partir da data de nascimento.  
- Utilizar **Swagger** para documentação/especificação da API.  
- Utilizar **Spring Security + JWT** para segurança da aplicação.  
- Utilizar **Spring Data JPA** para abstração da camada de acesso a dados.
- Realizer a Cobertura de testes (unitários, integrados e/ou de comportamento). 

</p>

<h2>:hammer: Funcionalidades dos endpoints do projeto </h2>

- `GET /clientes`: Listar todos os clientes cadastrados. Somente usuário com role de administrador deve ter acesso a toda a base de dados.
- `GET /clientes/atributos`: Listagem de Clientes por parâmetros. Somente usuário com role de administrador deve ter acesso a toda a base de dados.
- `DELETE /clientes/{id}:`: Deletar dados de Cliente. Somente usuário com role de administrador deve poder remover dados, pois isso pode afetar a integridade legal dos registros.
- `PUT /clientes`: Atualizar dados de Cliente. Somente usuário com role de administrador, pois isso pode afetar a integridade legal dos registros.
- `POST /clientes`: Cadastro de Clientes. Pode ser acessado por um atendente sem perfil de administrador.
- `POST /login`: Cria o token JWT para um usuário cadastrado acessar a API. Pode ser acessado por um atendente sem perfil de administrador.

<h2> 🧠 Tecnologias utilizadas </h2>

- Java 21 (Java SE)
- Spring Boot
- Spring Data JPA/Hibernate
- Maven
- Lombok
- Spring Web
- Flyway Migration
- MySQL Driver
- Validation
- Spring Security JWT
- Swagger/OpenAPI 
- Insomnia



