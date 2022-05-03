# MultiMovieCatalog

## Repositório criado como resposta ao desafio abaixo.

Criado por Guilherme Goulart Cruvinel

# Desafio técnico - Dev Android Nativo

## Lista de filmes com melhor classsificação
Você deve implementar um aplicativo que consumirá a API do themoviedb (https://developers.themoviedb.org/3/getting-started)
Para criar um token de acesso a API, será necessário criar uma conta no themoviedb. (https://www.themoviedb.org/signup)
Você não precisa comitar o seu token, nem nos envia-lo.  

O App exibirá uma lista de filmes com a melhor classificação e caso o usuário toque em um item da lista, os detalhes do filme deverão ser exibidos.
 

## Obrigatório - O App deve ter as seguintes features:
    Tela 1
    - Exibir uma lista dos filmes com melhor classificação. (consumir a seguinte rota: https://developers.themoviedb.org/3/movies/get-top-rated-movies)
    
    Tela 2
    - Ao tocar em um item da lista, exibir os detalhes do filme. (https://developers.themoviedb.org/3/movies/get-movie-details)
    
    
    - Não se esqueça de tratar os possíveis erros retornados da API


    - Implementar testes unitários	
    

## No arquivo wireframe.jpg pode ser vista uma referência a ser seguida, idéias/melhorias no layout são muito bem vindas =) 

## Requisitos
    Dê preferência para a linguagem Kotlin.
    O design é importante, mas a UX é mais importante (Validações, navegações, formatações de textos/datas, tratamento de erros com mensagens amigáveis e etc).
    Você deve utilizar o git criando um repositório privado no Bitbucket/Github/Gitlab e liberar o acesso para a pessoa que irá avaliar seu desafio. 

    Lembre-se de realizar pequenos commits com a descrição do que você implementou .
    
    Envie o APK final para instalação em um celular.

## Diferenciais
    - Na Tela 1
        - Utilizar paginação na lista dos filmes com melhor classificação.
        - Para cada item da lista, exibir o gênero do filme.  (Veja: https://developers.themoviedb.org/3/genres/get-movie-list)

    - Na Tela 2
        - Exibir os filmes recomendados  (https://developers.themoviedb.org/3/movies/get-movie-recommendations)
        e/ou a listagem de créditos do filme (https://developers.themoviedb.org/3/movies/get-movie-credits)  
        e/ou lista de reviews (https://developers.themoviedb.org/3/movies/get-movie-reviews)

    - Modularização do projeto (https://medium.com/google-developer-experts/modularizing-android-applications-9e2d18f244a0)
    - Arquitetura Limpa
    - Padrões MVP / MVVM
    - Utilização de injeção de dependência
    - Monitoramento/Métricas/Crash Report (Firebase)
    - Utilização de RxJava/Coroutines
    - CI/CD
    - Novas telas
    - Implementar testes Unitários
