# Tries (Árvores Digitais)

Uma árvore digital é uma árvore genérica onde cada nó pode ter quaisquer quanti- dade de filhos. Ela é utilizada para armazenar palavras que podem ser pesquisadas a partir de um prefixo comum.

### CLI

```console
java -jar trie.jar arquivo_com_as_palavras prefixo [quantidade]
```

## Como executar?

### Para receber sugestões de palavras a partir de um prefixo.

```console
java -jar trie.jar palavras.txt am
```

### Pode passar o parâmetro opcional para limitar a quantidade de palavras sugeridas.

```console
java -jar trie.jar palavras.txt am 4
```
