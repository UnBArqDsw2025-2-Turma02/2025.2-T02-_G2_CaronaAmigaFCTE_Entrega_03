# Facade 

## Definição

O padrão Facade é um dos padrões estruturais do catálogo GoF que cria uma interface de nível mais alto para um subsistema complexo, tornando-o mais fácil de usar pela camada cliente.

> Segundo o Refactoring.Guru:
> 
> "O Facade fornece uma interface simplificada para um subsistema complexo", permitindo que clientes interajam com funcionalidades agrupadas sem conhecer os detalhes internos.
>
> Fonte : [Refactoring.Guru](https://refactoring.guru/pt-br/design-patterns/facade)

Esse padrão é especialmente útil quando o sistema possui diversos módulos que precisam cooperar. Seus principais benefícios são:

- Tornar o código do cliente mais limpo e legível;
- Esconder detalhes de implementação do subsistema;
- Facilitar manutenção e evolução do sistema.

Um cenário típico seria uma plataforma educacional em que a fachada encapsula a lógica de iniciar um módulo, registrar respostas e atualizar o progresso do aluno em uma única chamada.

## Metodologia




##  Referências 

> Refactoring.Guru - Padrão Facade: https://refactoring.guru/pt-br/design-patterns/facade. 

> Slides da Prof.ª Milene – Aula GoFs Estruturais UnB (2025).

> Gamma, E., Helm, R., Johnson, R., & Vlissides, J. (1994). *Design Patterns: Elements of Reusable Object-Oriented Software*. Addison-Wesley.

##  Histórico de Versões
| Versão | Data       | Descrição                             | Autor                                                 | Revisor                                               |
| :----: | ---------- | ---------------------------           | ----------------------------------------------------- | ----------------------------------------------------- |
| `1.0`  | 16/10/2025 | Criação do documento                  |  [Arthur](https://github.com/Tutzs)                   |                                                       | 