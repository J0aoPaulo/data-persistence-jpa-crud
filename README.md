# data-persistence-jpa-crud

1. Implemente a persistência das entidades propostas na Etapa 0, lembrando que seu trabalho pode ter no mínimo 3 entidades, que de algum modo se associem entre si.

O trabalho deve ter entidades, DAOs usando JPA para persistir (consultar e salvar/atualizar/deletar - CRUD) os objetos relacionados às entidades da aplicação.

A aplicação deve usar preferencialmente o Spring Boot (recomendado).


2. A aplicação deve ter uma interface com o usuário com menus e submenus para realizar todas as operações de persistência implementadas. Não deve ter mais de um método main(). Apenas um ponto de entrada ao sistema, onde TODAS as funcionalidades são realizadas a partir dele. Importante tratar as exceções para a aplicação não "quebrar" de forma abrupta ou inesperada.

3. A aplicação deve ter consultas de cada um dos tipos de consulta a seguir:

a) JPQL
b) Native Query
c) Named Query
d) Consultas baseadas no nome do método (recurso disponível no Spring Data JPA).
Não poupe consultas. Quanto mais diversas e verdadeiramente úteis forem as consultas, melhor.
A seguir, alguns exemplos de consultas para um contexto específico, diferente do seu, mas que serve de base para você entender melhor.
O contexto a seguir, envolve Filmes e Atores para se ter uma ideia de consultas que poderiam ser realizadas:

a) Obter ator e filme a partir de seus respectivos ids, sendo uma busca separada para cada um deles.
b) Listar todos os títulos de filmes de um determinado ator.
c) Listar os nomes de todos os atores de um determinado filme.
d) Listar os títulos de filmes lançados em determinado ano.
e) Listar os títulos de filmes cujo título contém determinada string.
f) Listar os nomes de atores nascidos em determinado ano.
g) Mostrar a quantidade total de filmes cadastrados.
h) Mostrar a quantidade total de filmes cadastrados por categoria.
i) Mostrar a quantidade de atores por filme.
j) Mostrar filmes com classificação IMDB (notas de 1 a 10) acima de determinado valor.
4. Sua implementação deve ser capaz de funcionar no SQLite e PostgreSQL. É importante enviar uma base de dados sqlite já contendo dados (quanto mais reais forem os dados, melhor), juntamente com o projeto enviado. Cadastre ao menos 10 instâncias de cada entidade no Banco de Dados SQLite que será enviado juntamente com a aplicação.
