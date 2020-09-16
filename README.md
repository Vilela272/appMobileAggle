```bash

# Objetivos: 
•	Criar o projeto do aplicativo final do curso (Baseado na OPE ou outro projeto)
•	Aplicar os conceitos básicos de desenvolvimento de um aplicativo para o Android
o	Criação e configuração do projeto
o	Utilização de Activity
o	Utilizar elementos de tela
o	Vincular e tratar eventos
•	Aplicar os conceitos de Action Bar vistos em aula
•	Aplicar os conceitos de Layout e Views
•	Aplicar os conceitos de cores para criar a identidade visual do App
•	Criar algumas telas para navegar no app
•	Disponibilizar o projeto no GitHub ou similar para a avaliação da atividade

# Grupo: Esta atividade deve ser feita em grupo, o mesmo grupo de desenvolvimento do aplicativo final da disciplina.

# Prazo: 
# Descrição: Nesta atividade você deve fazer o seguinte (pelo menos):
•	Criar um projeto de aplicativo móvel no AndroidStudio. Este será o projeto desenvolvido até o final da disciplina, preferencialmente baseado na sua OPE.
•	Criar uma tela de login com:
o	Um campo de texto com uma mensagem para o usuário, como “Bem-vindo”, “Faça seu login”, etc (apenas exemplos)
o	Campo para entrar com o nome de usuário
	Você pode optar por usar um TextView com o label “Usuário” e o campo para entrada de dados, ou usar só o campo de entrada de dados com o texto de ajuda (hint)
o	Campo para entrar com a senha
	Você pode optar por usar um TextView com o label “Senha” e o campo para entrada de dados, ou usar só o campo de entrada de dados com o texto de ajuda (hint)
o	Um botão para fazer o login
o	Uma imagem relacionada ao tema do app
o	Implementar o evento de clique no botão de login para direcionar para uma segunda tela/Activity, fazendo a verificação de usuário e senha
	Só poderá direcionar se usuário for ‘aluno’ e senha ‘impacta’ (tudo minúsculo)
	Caso usuário e senha forem inválidos, deve mostrar um campo de texto na tela de login com a mensagem “Usuário ou senha incorretos” (você pode colocar algum componente de alerta, se preferir)
•	Uma segunda tela/Activity que será redirecionada ao clicar no botão de login
o	Esta tela deve ter uma Action bar com ações relevantes para app, por exemplo (coloque pelo menos 3 ações):
	Buscar: filtrar elementos de uma lista na tela (veja código no GitHub de aula para ver a implementação). Por enquanto faça apenas a simulação de busca, mostrando o texto inserido no Toast quando estiver digitando e quando finalizar a busca
	Adicionar: Incluir um novo elemento. Ao clicar, direciona para uma nova tela/Activity de cadastro. Essa segunda tela deve ter uma opção de voltar na ActionBar (up navigation)
	Atualizar: atualizar a lista da tela. Fazer uma simulação, colocado uma ProgressBar na tela e escondendo ela após 10 segundos
	Configurações: abrir uma nova tela
	Sair: volta para a tela de login do aplicativo
•	Colocar na tela inicial 3 botões para simular três ações na tela inicial. Ao clicar, abre uma nova tela com um texto na ActionBar relativo ao item clicado. A tela deve ser a mesma para os três botões, mudando apenas o texto na ActionBar. 
o	Por exemplo, no LMS poderia ser um botão para cada disciplina, que ao clicar, abre a tela com o nome da disciplina na ActionBar
•	Definir as cores em colors.xml e aplicar o padrão de cores nas telas do aplicativo, utilizando o styles.xml
•	Compartilhar o projeto no GitHub ou similar
o	Dica: coloque este arquivo .gitignore (https://goo.gl/JbiaWG) na raiz do projeto. Ele vai ignorar arquivos desnecessários, como os binários. 
•		Crie uma branch AC02 com o conteúdo desta AC. O Nome deve ser exatamente AC02. Preste muita atenção. Branches diferentes não serão consideradas e não serão corrigidas posteriormente.
•	Compartilhe o link do repositório no form anexo à tarefa
•	Atenção: utilize o mesmo repositório até o final da disciplina. Após a correção e disponibilização da nota das atividades, elas não serão revistas caso estejam em um repositório diferente do cadastrado no formulário da tarefa. Se houver mudança de repositório avisar em aula antes da tarefa ser corrigida.

```