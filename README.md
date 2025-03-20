## Chat-app: Sistema de bate-papo simples com autenticação e websocket

### Funcionalidades:
- Autenticação de usuários, por log-in e registro
- Sistema de pedidos de amizade, onde você só pode mandar mensagens para alguém se você for amigo da pessoa
- Bate-papo em tempo real com Websockets
- Arquitetura hexagonal, DDD e SOLID aplicados.

### Estrutura:
O projeto segue a **Arquitetura hexagonal**, separando as camadas em domínio, infraestrutura, adaptadores e aplicação. Além disso, adota ao máximo princípios como SOLID e Domain-Driven-Design (DDD), para garantir um código modular e escalável.

- Arquitetura: Hexagonal
- Princípios: SOLID, DDD
- Tecnologias:
   - java 23
   - Banco de dados H2
   - Framework springboot

You can find all endpoints at [swagger](https://noruga-messaging-system-backend.onrender.com/swagger-ui/index.html)

obs: You may insert the token in 'authorization' header.


