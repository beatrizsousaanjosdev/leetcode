# LeetCode em Java 25

Workspace de prática com JDK 25. Os desafios ficam em `src/`.

## Rodar o modelo

Na raiz do projeto, execute:

```sh
javac --release 25 -d out src/Modelo.java
java -cp out Modelo
```

## Adicionar um desafio

1. Crie um arquivo por problema, como `src/P0001TwoSum.java`, e use o mesmo nome na classe pública.
2. Mantenha a assinatura do método fornecida pelo enunciado e use o `main` para testar os exemplos e os casos de borda.
3. Compile e execute trocando `Modelo` pelo nome da sua classe nos comandos acima.
4. Para enviar ao LeetCode, coloque o método e seus auxiliares na classe fornecida pelo editor do problema, geralmente `Solution`, e inclua os imports usados.
