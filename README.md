# Sistema Calculador de Peso Ideal

O sistema de calculo de pesoa ideal permite o usuário saber qual é o seu peso ideal com base em sua altura. No sistema basicamente, o usuario cadastra seus dados e solicita o calculo.

# Montando o Ambiente Localmente

A arquitetura do sistema se divide em duas partes: O Backend, onde uma API faz a parte de toda lógica para o processamento dos dados; O FrontEnd onde estão as telas do sistema e onde são apresentados os dados já processados. A seguir instruções para rodar o backend e o frontend na sua máquina.

## Rodando Localmente o Backend

### O Que Preciso?
Você vai precisar ter instalado na sua máquina o banco de dados MySQL (Conhecer a porta, usuario e senha) e o JAVA na versão 17 ou superior. Você pode obter o MySQL nesse [link](https://www.mysql.com/) e o JAVA nesse [link](https://www.oracle.com/java/technologies/downloads/#java17). Caso já tenha o JAVA instalado verifique se a variável de ambiente JAVA_HOME aponta para versão 17 ou superior do Java.

### Instruções
Antes de tudo, saiba a porta, usuario e senha do banco de dados MySQL que está instalado na sua máquina, feito isso abra o terminal do seu sistema operacional e vá até dentro da pasta server do projeto e rode os seguintes comandos:

1. mvn clean package.

Após terminar esse processo ainda no mesmo terminal vá até a pasta target (ela está na raiz da pasta backend) e digite o seguinte comando, obs: substitua depois do igual pelo o que se pede:

2. java -jar -DPORT=PORTA_DO_SEU_BANCO_DE_DADOS -DUSER=USUARIO_DO_SEU_BANCO_DE_DADOS -DPASSWORD=SENHA_DO_SEU_BANCO_DE_DADOS server.jar.

Se der tudo certo, no final aparecerá uma mensagem dizendo que o servidor foi instanciado na porta 8080.
Tudo pronto seu backend já está rodando.

## Rodando Localmente o FrontEnd

### O Que Preciso? 
Você vai precisar ter instalado na sua máquina o NODE versão 14.16.0 ou superior, você pode encontrar nesse [link](https://nodejs.org/en/)

### Instruções.
Com o repositório já na sua máquina, abra o terminal do seu sistema operacional e vá até dentro da pasta client do projeto, e rode os seguintes comandos nessa ordem:

1. npm install.

2. npm start.

Se der tudo certo (creio que sim :) ) aparecerá uma mensagem da seguinte forma: Servidor iniciado na porta 4200 - Sistema de Calculo de Peso Ideal

Pronto o frontend já está rodando localmente.

Abra seu navegador no seguinte endereço: http://localhost:4200 e use e abuse do sistema!! :)

# Deu ruim !:( , e a agora ?

Verifique se está tudo ok com o NODE, JAVA e o MySQL, verifique se a variável de ambiente JAVA_HOME aponta para o JAVA versão 17 ou superior. Pode ser também que tenha algo rodando na porta 8080 ou 4200 na sua máquina, neste caso deve parar o que estiver rodando e tentar rodar o projeto novamente.