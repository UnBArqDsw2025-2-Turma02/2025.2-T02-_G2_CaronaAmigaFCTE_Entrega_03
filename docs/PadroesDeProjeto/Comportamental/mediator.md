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

---

## Referências

> [Refactoring.Guru – Padrão Mediator](https://refactoring.guru/design-patterns/mediator)

---

## Histórico de Versões


| Versão | Data       | Descrição                                                                                             | Autor                                          | Revisor |
| :----: | ---------- | ----------------------------------------------------------------------------------------------------- | ---------------------------------------------- | ------- |
|  `1.0` | 20/10/2025 | Criação do tópico de Introdução. |  [Maria Eduarda](https://github.com/pyramidsf)  | [Caio Venâncio](https://github.com/caio-venancio), [Pedro Henrique](https://github.com/pedro-hsf), [Caio Melo](https://github.com/CaioMelo25) |
