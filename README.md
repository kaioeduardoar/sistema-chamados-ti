## sistema-chamados-ti

Sistema de console desenvolvido em Java para gerenciamento de chamados de suporte técnico, utilizando conceitos de Programação Orientada a Objetos (POO).

#### Objetivo

Projeto de estudo criado para praticar POO em Java, simulando um sistema real de abertura e acompanhamento de chamados de TI, como os usados em empresas para controle de suporte técnico.

#### Tecnologias

- Java 17 ou superior (usa `switch` com expressões)
- Git / GitHub

#### Estrutura do projeto

O código está organizado em pacotes: `model` (entidades do sistema), `service` (regras de negócio) e a classe `Main` (interface de console).

```
src/
├── Main.java
├── model/
│   ├── Chamado.java
│   ├── Prioridade.java
│   ├── StatusChamado.java
│   ├── Tecnico.java
│   └── Usuario.java
└── service/
    ├── ChamadoService.java
    └── UsuarioService.java
```

#### Funcionalidades

- Cadastro (signup) e login de usuários por e-mail
- Abertura de chamados com título, descrição e prioridade
- Listagem de todos os chamados
- Busca de chamados por ID e por nome do solicitante
- Alteração de status do chamado (Aberto, Em atendimento, Resolvido, Fechado)
- Validação de entradas numéricas, sem quebrar o programa
- Cadastro de técnicos e atribuição de técnico a chamados (implementados nas classes, ainda sem menu no console)

#### Como executar

Pré-requisito: JDK 17 ou superior instalado.

No Windows (PowerShell), na raiz do projeto:

```
chcp 65001
javac -encoding UTF-8 -d out (Get-ChildItem -Recurse src -Filter *.java).FullName
java "-Dfile.encoding=UTF-8" -cp out Main
```

No Linux ou macOS:

```
javac -encoding UTF-8 -d out $(find src -name "*.java")
java -cp out Main
```

As opções de codificação (`-encoding UTF-8` e `chcp 65001`) evitam que os acentos apareçam quebrados no Windows.

Também é possível abrir a pasta em uma IDE (IntelliJ, Eclipse ou VS Code) e executar a classe `Main`.

#### Exemplo de uso

```
0 - Encerrar
1 - Login
2 - Signup
Escolha: 2
Insira seu nome: Kaio
Insira o seu e-mail: kaio@email.com
=== SISTEMA DE CHAMADOS ===
1 - Abrir chamado
2 - Listar chamados
3 - Buscar chamado
4 - Alterar status
5 - Sair
Escolha: 1
Titulo do chamado: Computador não liga
Descrição do chamado: Sem sinal de energia
Prioridade do chamado:
1 - BAIXA
2 - MEDIA
3 - ALTA
4 - URGENTE
Escolha: 3
Seu chamado foi aberto. ID: 1
```

#### Conceitos de POO aplicados

- Encapsulamento: atributos privados com getters controlados
- Herança: Tecnico herda de Usuario
- Polimorfismo: sobrescrita do método toString()
- Composição: Chamado associa objetos Usuario, Tecnico, Prioridade e StatusChamado
- Enums: Prioridade e StatusChamado
- Collections: uso de ArrayList para armazenar chamados e usuários
- Separação em camadas: entidades (`model`), regras de negócio (`service`) e interface (`Main`)

#### Próximos passos

- Perfil de técnico no menu (acesso protegido por senha, assumir chamados e atualizar status)
- Usuário comum visualizando apenas os próprios chamados
- Persistência de dados (arquivo ou banco de dados)
- Transformação em API REST
