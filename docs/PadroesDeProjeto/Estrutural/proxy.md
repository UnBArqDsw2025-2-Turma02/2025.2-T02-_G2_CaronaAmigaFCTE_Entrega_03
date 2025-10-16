# Proxy

## Introdução

O padrão Proxy faz parte dos padrões estruturais e fornece um representante para outro objeto, atuando como camada intermediária que controla o acesso ao objeto real. Essa abstração permite adicionar comportamentos como validação, cache ou registro de chamadas sem alterar a implementação original.

É especialmente útil quando a criação ou uso do objeto real é custoso — por exemplo, quando envolve acesso a banco de dados, leitura de arquivos ou comunicação remota — ou quando é necessário garantir condições antes de permitir a interação.

Na prática, o Proxy implementa a mesma interface do objeto verdadeiro, de modo que o cliente o utiliza de forma transparente, sem perceber a presença dessa camada adicional.

Usos comuns:
- Protection Proxy: impõe verificações de permissão antes do acesso;
- Virtual Proxy: evita criar objetos pesados até que sejam realmente necessários (lazy initialization);
- Logging Proxy: grava chamadas e parâmetros para auditoria ou depuração;
- Caching Proxy: armazena resultados de operações dispendiosas para reutilização;
- Remote Proxy: oferece uma representação local de objetos que residem em outro processo ou máquina.

## Metodologia


## Referências 

> REFRACTORING.GURU. Proxy. [S. l.], [s. d.]. Disponível em: https://refactoring.guru/design-patterns/proxy. 

> SOURCEMAKING. Proxy. [S. l.], [s. d.]. Disponível em: https://sourcemaking.com/design_patterns/proxy. 

##  Histórico de Versões
| Versão | Data       | Descrição                             | Autor                                                 | Revisor                                               |
| :----: | ---------- | ---------------------------           | ----------------------------------------------------- | ----------------------------------------------------- |
| `1.0`  | 16/10/2025 | Criação do documento                  |  [Arthur](https://github.com/Tutzs)                   |                                                       | 