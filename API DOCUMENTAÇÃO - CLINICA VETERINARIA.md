## **API DOCUMENTAÇÃO - CLINICA VETERINÁRIA**

API elaborada para controle de sistema de um modelo de negócio voltado para clínica veterinária.

Disponibilizando um sistema que tenha a possibilidade de fazer o controle do sistema como, por exemplo:



*Cadastrar Paciente,

*Cadastrar Proprietário,

*Cadastrar Veterinário,

*Cadastrar e controlar consultas.



## **Parâmetros de Paciente:**

**Cadastrar Paciente:**

O cadastro do paciente é recebido via JSON  e sem seguida é salvo junto com as outras dependências do banco de dados na pasta raiz do diretório chamada "db/arquivos.json":

Segue modelo de JSON para **cadastro** via **POST**:

http://localhost:8080/paciente/cadastrar

```
{
  "especie" : "especieDoAnimal",
  "raca" : "raçaDoAnimal",
  "cor" : "descricaoDaPelagemDoAnimal",
  "dataNascimento" : "01/01/2019",
  "nome" : "nomeDoAnimal",
  "sexo" : "sexoDoAnimal",
  "cpfProprietario" : "123.456.789-582",
  "numeroColeira" : "1"
}
```

*Em caso de erro será lançado uma exceção no sistema informando que os campos não podem ser nulos.*

*O sistema de cadastro não permite duplicidade de número de coleira.*



**Consultar a Lista de Pacientes:**

A consulta dos pacientes cadastrados é feito via consulta de arquivo que retorna todos os pacientes cadastrados no banco de dados.

Para efetuar a consulta dos pacientes listados com o HTTP **GET** acesse o seguinte link:

http://localhost:8080/paciente/listar

*A consulta irá retornar a lista em modo crescente*



**Alterar paciente pelo número da coleira do paciente:**

Caso queira efetuar uma mudança no cadastro do seu paciente, você precisa referenciar o número da coleira do seu paciente via HTTP pelo seguinte link:

http://localhost:8080/paciente/alterar/{numeroColeira}

Utilizando o **PUT** siga com o seguinte modelo de JSON para atualizar um paciente já cadastrado com as informações que você deseja atualizar:

```
{
  "especie" : "especieDoAnimal",
  "raca" : "raçaDoAnimal",
  "cor" : "descricaoDaPelagemDoAnimal",
  "dataNascimento" : "01/01/2019",
  "nome" : "nomeDoAnimal",
  "sexo" : "sexoDoAnimal",
  "cpfProprietario" : "123.456.789-582",
  "numeroColeira" : "1"
}
```

**Deletar um paciente cadastrado em sistema:**

Para deletar um paciente já cadastrado em sistema basta informar o número da coleira via HTTP com o modo **DELETE**, segue modelo HTTP:

http://localhost:8080/paciente/deleta/{numeroColeira}

*OBS: Caso o paciente tenha uma consulta cadastrada em sistema o mesmo não poderá ser deletado.*



## **Parâmetros de Proprietário:**



**Cadastrar Proprietário:**

O cadastro do proprietário é recebido via JSON  e sem seguida é salvo com as outras dependências do banco de dados na pasta raiz do diretório chamada "db/arquivos.json":

Segue modelo de JSON para **cadastro** via **POST**:

http://localhost:8080/proprietario/cadastrar

```
{
  "cpf":"123.456.789-582",
  "nome":"nome",
  "sobrenome":"sobrenome",
  "dataNascimento":"01/05/91",
  "endereco":"endereco",
  "telefone":"numeroTelefone"
}
```

*Em caso de erro será lançado uma exceção no sistema informando que os campos não podem ser nulos.*

*O sistema de cadastro não permite duplicidade de número de CPF.*



**Consultar a Lista de Proprietário:**

A consulta dos proprietários cadastrados é feito via consulta de arquivo que retorna todos os proprietários cadastrados no banco de dados.

Para efetuar a consulta dos pacientes listados HTTP **GET**  acesse o seguinte link:

http://localhost:8080/proprietario/listar

**Alterar proprietário pelo número de CPF:**

Caso queira efetuar uma mudança no cadastro do seu proprietário, você precisa referenciar o **CPF** do seu proprietário via HTTP pelo seguinte link:

http://localhost:8080/proprietario/alterar/{cpf}

Utilizando o **PUT** siga com o seguinte modelo de JSON para atualizar um proprietário já cadastrado com as informações que você deseja atualizar:

```
{
  "cpf":"123.456.789-582",
  "nome":"nome",
  "sobrenome":"sobrenome",
  "dataNascimento":"01/05/91",
  "endereco":"endereco",
  "telefone":"numeroTelefone"
}
```

**Deletar um proprietário cadastrado em sistema:**

Para deletar um proprietário já cadastrado em sistema basta informar o número de **CPF** via HTTP com o modo **DELETE**, segue modelo HTTP:

http://localhost:8080/proprietario/deletar/{cpf}

*OBS: Caso o proprietário tenha uma consulta cadastrada em sistema o mesmo não poderá ser deletado.*



## **Parâmetros de Veterinário:**

**Cadastrar Veterinário:**

O cadastro do veterinário é recebido via JSON  e sem seguida é salvo com as outras dependências do banco de dados na pasta raiz do diretório chamada "db/arquivos.json":

Segue modelo de JSON para **cadastro** via **POST**:

http://localhost:8080/veterinario/cadastra

```
{
  "cpf" : "223.456.789-20",
  "nome" : "nome",
  "sobrenome" : "sobrenome",
  "dataNascimento" : "25/10/92",
  "numeroRegistro" : "numeroRegistro",
  "especialidade" : "especialidade"
}
```

*Em caso de erro será lançado uma exceção no sistema informando que os campos não podem ser nulos.*

*O sistema de cadastro não permite duplicidade de veterinário.*

**Consultar a Lista de Veterinário:**

A consulta dos veterinários cadastrados é feito via consulta de arquivo que retorna todos os veterinários cadastrados no banco de dados.

Para efetuar a consulta dos veterinários listados com o HTTP **GET** acesse o seguinte link:

http://localhost:8080/veterinario/listar

*A consulta irá retornar a lista em modo decrescente*



**Alterar veterinário pelo número de registro:**

Caso queira efetuar uma mudança no cadastro do seu veterinário, você precisa referenciar o número de registro do veterinário via HTTP pelo seguinte link:

http://localhost:8080/veterinario/alterar/{codigo}

Utilizando o **PUT** siga com o seguinte modelo de JSON para atualizar um veterinário já cadastrado com as informações que você deseja atualizar:

```
{
  "cpf" : "223.456.789-20",
  "nome" : "nome",
  "sobrenome" : "sobrenome",
  "dataNascimento" : "25/10/92",
  "numeroRegistro" : "numeroRegistro",
  "especialidade" : "especialidade"
}
```

**Deletar um veterinário cadastrado em sistema:**

Para deletar um veterinário já cadastrado em sistema basta informar o número de **registro** via HTTP com o modo **DELETE**, segue modelo HTTP:

http://localhost:8080/veterinario/deleta/{codigo}

*OBS: Caso o veterinário tenha uma consulta cadastrada em sistema o mesmo não poderá ser deletado.*



## **Parâmetros de Consulta:**

**Cadastrar Consulta:**

O cadastro da consulta é recebido via JSON  e sem seguida é salvo junto com as outras dependências do banco de dados na pasta raiz do diretório chamada "db/arquivos.json":

Segue modelo de JSON para **cadastro** via **POST**:

http://localhost:8080/consultas/cadastra

```
{
  "numeroConsulta" : "numeroConsulta",
  "numeroColeira" : "numeroColeira",
  "motivo" : "motivoConsulta",
  "diagnostico" : "diagnostico",
  "tratamento" : "tratamento",
  "numeroRegistroVeterinario" : "numeroRegistroVeterinario",
}
```

*Em caso de erro será lançado uma exceção no sistema informando: *

- Necessidade de número de coleira.
- Necessário número de registro de veterinário

*O sistema de cadastro não permite duplicidade de consulta por número de consulta.*

*O sistema informa caso tenha ocorrido um erro ao cadastrar a consulta no banco de dados.*

**Consultar a Lista de Consultas:**

A consulta das consultas cadastrados é feito via leitura de arquivo que retorna todos as Consultas cadastrados no banco de dados.

Para efetuar a consulta das Consultas listados com o HTTP **GET** acesse o seguinte link:

http://localhost:8080/consultas/listar

*A consulta irá retornar a lista em modo decrescente*



**Consultar a Lista de Consultas do dia:**

A consulta das consultas do dia cadastrados é feito via consulta de arquivo que retorna todas as Consultas cadastrados no banco de dados relacionadas ao dia informado.

Para efetuar a consulta das Consultas listados com o HTTP **GET** acesse o seguinte link:

http://localhost:8080/consultas/listarPorDia/25-09-2021

*A consulta irá retornar a lista em modo crescente*



**Alterar consulta pelo número de registro:**

Caso queira efetuar uma mudança na consulta, você precisa referenciar o **número da consulta** via HTTP pelo seguinte link:

http://localhost:8080/consultas/alterar/{codigo}

Utilizando o **PUT** siga com o seguinte modelo de JSON para atualizar uma consulta já cadastrado com as informações que você deseja atualizar:



```
{
  "numeroConsulta" : "numeroConsulta",
  "numeroColeira" : "numeroColeira",
  "motivo" : "motivo",
  "diagnostico" : "diagnostico",
  "tratamento" : "tratamento",
  "numeroRegistroVeterinario" : "numeroRegistroVeterinario"
}
```

*Obs: Todos os campos quando atualizados devem estar preenchidos.*



**Deletar uma consulta com cadastrado em sistema:**

Para deletar uma consulta já cadastrado em sistema basta informar o **numero da consulta** via HTTP com o modo **DELETE**, segue modelo HTTP:

http://localhost:8080/consultas/deleta/{codigo}

*OBS: Caso o ID esteja errado o sistema irá informar uma mensagem de erro ao deletar ID.*



**Informar todas as consultas de cada veterinário em sistema:**

Para informar todas as consultas já cadastrado em sistema basta chamar o HTTP com o modo **GET**, segue modelo HTTP:

http://localhost:8080/consultas/listarTotalCadaVeterinario

*O endPoint irá retornar todas as consultas em sistemas mostrando quantas consultas cada médico tem.*
