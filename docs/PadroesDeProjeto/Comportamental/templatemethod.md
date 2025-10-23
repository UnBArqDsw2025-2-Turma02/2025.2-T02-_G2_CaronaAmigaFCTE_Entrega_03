# Template Method

## Introdução

O **padrão Template Method** é um dos **padrões comportamentais** do catálogo GoF e tem como propósito **definir o esqueleto de um algoritmo em uma superclasse, permitindo que subclasses implementem ou alterem passos específicos do algoritmo sem modificar sua estrutura global**.

Segundo o Refactoring.Guru:

> “Template Method is a behavioral design pattern that defines the skeleton of an algorithm in the superclass but lets subclasses override specific steps of the algorithm without changing its structure.” 

Esse padrão é especialmente útil em sistemas onde existem diversas classes que compartilham **algoritmos comuns** com **variações específicas**. Ele permite que você **centre e padronize a estrutura do algoritmo** em uma classe base, enquanto subclasses cuidam das particularidades. Assim, o código fica mais **organizado**, **mais fácil de manter** e com **menor duplicação**.

Entre seus principais benefícios estão a **eliminação de código duplicado** por meio da elevação de trechos comuns para a superclasse, a **facilidade de extensão e adaptação** das etapas variantes sem mexer na estrutura principal do algoritmo, e o **controle centralizado** sobre o fluxo global do processo, tornando a manutenção e evolução mais seguras.

Alguns usos comuns são:  
Um cenário típico seria um sistema de mineração de dados que suporta múltiplos formatos (DOC, CSV, PDF) — a estrutura de processamento (abrir arquivo, extrair dados, analisar, gerar relatório) é a mesma, mas cada formato exige implementação específica em certas etapas.
Outro exemplo é um motor de IA para jogo onde várias “raças” compartilham o mesmo algoritmo de turno (coletar recursos, construir estruturas, atacar), mas cada raça possui variações customizadas nas suas etapas concretas. 

---

## Metodologia

---

## Referências

> [Refactoring.Guru – Padrão Template Method](https://refactoring.guru/design-patterns/template-method) 

---

| Versão | Data       | Descrição                                                                                             | Autor                                          | Revisor |
| :----: | ---------- | ----------------------------------------------------------------------------------------------------- | ---------------------------------------------- | ------- |
|  `1.0` | 20/10/2025 | Criação do tópico de Introdução. |  [Maria Eduarda](https://github.com/pyramidsf)  | [Caio Venâncio](https://github.com/caio-venancio), [Pedro Henrique](https://github.com/pedro-hsf), [Caio Melo](https://github.com/CaioMelo25) |
|  `1.1` | 20/10/2025 | Consertar detalhe gramatical. |  [Maria Eduarda](https://github.com/pyramidsf)  |  |
