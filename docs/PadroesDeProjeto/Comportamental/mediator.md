# Mediator

## Introdução

O **padrão Mediator** é um dos padrões comportamentais do catálogo GoF. Ele tem como objetivo **reduzir o acoplamento direto entre os objetos**, centralizando a comunicação em um único objeto mediador.  

Segundo o [Refactoring.Guru](https://refactoring.guru/design-patterns/mediator):

> “O Mediator é um padrão de projeto que reduz as dependências caóticas entre objetos. O padrão restringe as comunicações diretas entre objetos e os força a colaborar apenas através de um mediador.”

Esse padrão é especialmente útil em sistemas onde **muitos objetos precisam interagir entre si**, pois evita a criação de múltiplas dependências cruzadas, tornando o código mais **organizado, flexível e fácil de manter**.  
Entre seus principais benefícios estão a **redução do acoplamento** entre classes que precisam se comunicar, a **centralização da lógica de interação** em um único ponto, a **facilidade de manutenção e evolução do sistema**, e a **melhoria na reutilização de componentes**, já que eles passam a ser menos dependentes uns dos outros.

Alguns usos comuns são:

Um cenário típico seria um **sistema de chat**, no qual o mediador gerencia a comunicação entre os usuários.  
Em vez de cada usuário enviar mensagens diretamente para todos os outros, eles se comunicam através do mediador, que **coordena e direciona** as mensagens de forma centralizada.

---

## Metodologia

**Para o desenvolvimento do Mediator, foram adotadas as seguintes etapas metodológicas:**

1. Foram elaborados um diagrama de casos de uso e um diagrama de classes que serviram como base para estruturar os elementos envolvidos no padrão, suas relações e hierarquias. 

2. Como embasamento teórico, foram consultados os materiais disponibilizados pela professora e o site Refactoring Guru, que auxiliaram na compreensão dos princípios, funcionamento e aplicações do padrão Mediator.

3. O diagrama foi elaborado na ferramenta draw.io, proporcionando uma visualização clara da estrutura dos objetos, de seus relacionamentos e da hierarquia estabelecida conforme os princípios da UML.

---

## Desenvolvimento 

O diagrama representa como o padrão Mediator estrutura a interação entre os tipos de usuários Driver, Passenger e Admin, evitando dependências diretas entre eles. As ações específicas de cada papel são mediadas pelo ConcreteMediator, que coordena a comunicação e simplifica o gerenciamento do sistema. Essa organização favorece a manutenção e a extensibilidade da aplicação.

---

## Código 

### Vídeo 

--- 

## Conclusão 
A aplicação do padrão Mediator na CaronaAmiga foi fundamental para diminuir o acoplamento entre os componentes da interface. Sua adoção proporcionou uma comunicação centralizada e eficiente, resultando em um sistema mais claro, escalável e de fácil manutenção. Com isso, futuras modificações ou expansões puderam ser realizadas de forma mais simples e segura, contribuindo para a qualidade geral do projeto.

---

## Referências

> [Refactoring.Guru – Padrão Mediator](https://refactoring.guru/design-patterns/mediator)

---

## Histórico de Versões


| Versão | Data       | Descrição                                                                                             | Autor                                          | Revisor |
| :----: | ---------- | ----------------------------------------------------------------------------------------------------- | ---------------------------------------------- | ------- |
|  `1.0` | 20/10/2025 | Criação do tópico de Introdução. |  [Maria Eduarda](https://github.com/pyramidsf)  | [Caio Venâncio](https://github.com/caio-venancio), [Pedro Henrique](https://github.com/pedro-hsf), [Caio Melo](https://github.com/CaioMelo25) |
|  `1.1` | 22/10/2025 | Criação dos tópicos de Metodologia, Desenvolvimento e Conclusão. |  [Maria Eduarda](https://github.com/pyramidsf) [Caio Venâncio](https://github.com/caio-venancio), [Pedro Henrique](https://github.com/pedro-hsf), [Caio Melo](https://github.com/CaioMelo25) | |

