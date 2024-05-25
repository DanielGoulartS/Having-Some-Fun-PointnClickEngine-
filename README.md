# Having Some Fun
Engine para operar jogos de point and click. Deve ser alimentado com dados e imagens (não disponíveis aqui). Instruções virão futuramente.

A engine possui Cenários que possuem interações de ações, interações de personagens, e interações de senha.
Personagens possuem interações de dialogos, que possuem conversas.

Para a confecção de cada jogo basta preencher as tabelas da base de dados com as informações necessárias.
O modelo da base se encontra como 'hsf Dump de Estrutura.sql', e gerará uma base de dados chamada 'hsf' em referência a 'Having Some Fun' nome original do projeto.

Sugere-se que para abastecer seu jogo de media, crie-se uma pasta chamada media com a organização que julgar ideal lembrando que ela deverá constar na base de dados, e segundo as seguintes instruções:

[1- Cole a pasta 'media' nos caminhos 

 HavingSomeFun\build\classes (se houver)
e
 HavingSomeFun\src

2- Execute o 'hsf Dump de Dados.sql' script em sua base de dados. 

3- Lembre-se de alterar as credenciais da base de dados no arquivo Connecntion.java, no try/catch para que o software possa acessar os dados importados no passo 2.

OBS: A pasta GAME RSC contém arquivos GIMP para a criação da media do jogo.]
