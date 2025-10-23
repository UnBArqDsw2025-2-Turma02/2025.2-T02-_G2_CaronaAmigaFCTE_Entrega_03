# Observer

## Introdução

O **padrão Observer** é um dos padrões comportamentais do catálogo GoF. Ele tem como objetivo **definir uma dependência um-para-muitos entre objetos**, de modo que quando um objeto muda de estado, todos os seus dependentes sejam automaticamente notificados e atualizados.  

Segundo o [Refactoring.Guru](https://refactoring.guru/design-patterns/observer):

> “O Observer é um padrão de projeto que permite que um objeto notifique outros objetos sobre mudanças em seu estado, sem precisar saber quem são esses objetos.”

Esse padrão é especialmente útil em sistemas **reativos ou baseados em eventos**, nos quais é importante que várias partes do sistema respondam automaticamente a uma alteração ocorrida em outro componente. Ele promove um **baixo acoplamento** entre os objetos, já que o sujeito (objeto observado) não precisa conhecer os detalhes de quem está recebendo as notificações.  

Entre seus principais benefícios estão a **facilidade de extensão do sistema** com novos observadores sem alterar o código existente, a **automação na propagação de atualizações** entre os componentes, e a **redução da dependência direta** entre as classes. Além disso, o padrão melhora a **organização e manutenção do código**, especialmente em contextos onde várias partes do sistema precisam reagir de forma sincronizada a determinados eventos.

Alguns usos comuns são:

Um cenário típico seria um **sistema de notícias**, no qual diversos assinantes (observadores) recebem automaticamente novas atualizações quando uma notícia é publicada.  
Outro exemplo é uma **interface gráfica**, onde elementos visuais são atualizados automaticamente quando os dados do modelo sofrem alterações.

---

## Metodologia

---

## Referências 

> [Refactoring.Guru – Padrão Observer](https://refactoring.guru/design-patterns/observer)

---

## Histórico de Versões

| Versão | Data       | Descrição                                                                                             | Autor                                          | Revisor |
| :----: | ---------- | ----------------------------------------------------------------------------------------------------- | ---------------------------------------------- | ------- |
|  `1.0` | 20/10/2025 | Criação do tópico de Introdução. |  [Maria Eduarda](https://github.com/pyramidsf)  | [Caio Venâncio](https://github.com/caio-venancio), [Pedro Henrique](https://github.com/pedro-hsf), [Caio Melo](https://github.com/CaioMelo25) |
