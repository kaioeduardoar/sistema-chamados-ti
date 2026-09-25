## sistema-chamados-ti

Sistema desenvolvido em Java para gerenciamento de chamados de suporte técnico, utilizando conceitos de Programação Orientada a Objetos (POO).

#### Objetivo

Projeto de estudo criado para praticar POO em Java, simulando um sistema real de abertura e acompanhamento de chamados de TI, como os usados em empresas para controle de suporte técnico.

#### Tecnologias

- Java
- Git / GitHub

#### Estrutura do projeto

O código está organizado em pacotes: `model` (entidades do sistema), 
`service` (regras de negócio) e a classe `Main`.

#### Funcionalidades

- Cadastro de usuários e técnicos
- Abertura de chamados
- Listagem de chamados (em desenvolvimento)
- Busca de chamado por id (em desenvolvimento)
- Alteração de status (em desenvolvimento)
- Atribuição de técnico (em desenvolvimento)

#### Conceitos de POO aplicados

- Encapsulamento: atributos privados com getters controlados
- Herança: Tecnico herda de Usuario
- Polimorfismo: sobrescrita do método toString()
- Composição: Chamado associa objetos Usuario, Tecnico, Prioridade e StatusChamado
- Enums: Prioridade e StatusChamado
- Collections: uso de ArrayList para armazenar os chamados

#### Próximos passos

- Persistência de dados
- Transformação em API REST