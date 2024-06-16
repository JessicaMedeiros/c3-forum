
Desafio 3 - Fórum - Alura Next One Backend

Descrição
Este repositório contém a implementação do sistema de fórum desenvolvido para o terceiro desafio da formação backend do Next One da Alura. O sistema simula um fórum online semelhante ao da Alura, onde os usuários podem criar, visualizar, editar e excluir tópicos de discussão.

Tecnologias Utilizadas
Java
Spring Boot
Autenticação JWT
Endpoints da API
Tópicos:

GET /topics: Lista todos os tópicos do fórum.
GET /topics/{id}: Obtém detalhes de um tópico específico.
POST /topics: Cria um novo tópico.
DELETE /topics/{id}: Exclui um tópico (apenas o autor pode excluir).
PUT /topics/{id}: Edita as informações de um tópico (apenas o autor pode editar).
Observações:

A autenticação JWT é necessária para acessar todos os endpoints.
O usuário só pode excluir ou editar seus próprios tópicos.
Instalação e Execução
