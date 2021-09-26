# Desafio Spring

## Veterinária

Link da documentação:
[Link da Documentação de uso da API](https://github.com/thiagoboneth/desafiospringboot2209/blob/master/API%20DOCUMENTA%C3%87%C3%83O%20-%20CLINICA%20VETERINARIA.md)


### // Projeto em grupo

A clínica veterinária “Zoo World” quer implementar um sistema desenvolvido em Java para a administração de cada um de
seus pacientes. Os veterinários têm dois tipos de especialidades: Animais Domésticos e Animais de Fazenda, com base no
fato de que atendem cães, gatos, porquinhos-da-índia, coelhos, tartarugas, cavalos, vacas e ovelhas.

<br/><br/>

● De cada um de seus pacientes, eles precisam obter os seguintes dados:<br/>

- [x] espécie,<br/>
- [x] raça,<br/>
- [x] cor/s,<br/>
- [x] data de nascimento e<br/>
- [x] nome.<br/>

● Ao mesmo tempo, cada paciente tem um proprietário atribuído que deve ser conhecido:<br/>

- [x] cpf (formatado com a máscara ###.###.###-##),<br/>
- [x] nome,<br/>
- [x] sobrenome,<br/>
- [x] data_de_nascimento,<br/>
- [x] endereço e<br/>
- [x] telefone_contato.<br/>

● Cada consulta que você fizer a um paciente deve ser registrada, levando em consideração:<br/>

- [x] data e hora,<br/>
- [x] motivo,<br/>
- [x] diagnóstico possível e<br/>
- [x] tratamento a ser seguido.<br/>

● Por outro lado, cada consulta deve ter também um médico veterinário responsável do qual ficam armazenados:<br/>

- [x] cpf (formatado com a máscara ###.###.###-##),<br/>
- [x] nome,<br/>
- [x] sobrenome,<br/>
- [x] número de registro, <br/>
- [x] especialidade.<br/>
  <br/><br/>

---

- [x] Solicita-se a realização da modelagem de cada uma dessas classes, levando em consideração suas relações e a
  interação entre cada uma delas.<br/>
  Implementar (nas devidas classes) os métodos necessários para:<br/>

---

- [x] ● Cadastrar/editar médicos;<br/>
    - [x] Não é permitido o cadastro de médicos em duplicidade;<br/>
    - [x] Todos os dados do médico são obrigatórios para realização do cadastro/edição;<br/>
    - [x] Não é permitido a exclusão de médicos que já realizaram consultas.<br/>
- [x] ● Cadastrar/editar proprietários;<br/>
    - [X] Não é permitido o cadastro de proprietários em duplicidade;<br/>
    - [x] Todos os dados do proprietários são obrigatórios para realização do cadastro/edição;<br/>
    - [X] Não é permitido a exclusão de proprietários de animais que já realizaram consultas.<br/>
- [x] ● Cadastrar/editar pacientes;<br/>
    - [x] Todos os dados do paciente são obrigatórios para realização do cadastro/edição;<br/>
    - [X] Não é permitido a exclusão de animais que já realizaram consultas.<br/>
- [x] ● Criar/editar consultas;
    - [x] Uma consulta deve exigir o médico e o paciente para ser registrada, bem como a data e hora e o motivo.<br/>
- [x] ● Ver a lista de todos os pacientes registrados junto com seus proprietários.A lista deve estar ordenada em ordem
  crescente de nome do proprietário do animal;<br/>
- [x] ● Ver a lista de todas as consultas atribuídas a um determinado paciente. A lista deve estar ordenada pela data da
  consulta, em ordem decrescente.<br/>
- [x] ● Ver o total de consultas atribuídas a cada um dos médicos.<br/>
- [x] ● Ver a lista de todas as consultas marcadas para um mesmo dia.É importante mostrar dados relevantes do animal (
  paciente), do proprietário e do médico.A lista deve estar ordenada em ordem crescente de data e hora.<br/>
- [x] Os registros devem ser mantidos em arquivos (txt ou json), portanto os registros relacionados aos médicos,
  animais, proprietários e consultas devem ser
- [x] armazenados em estruturas de arquivos que permitam a recuperação dos dados sempre que solicitados. <br/>
- [x] Os registros devem ser mantidos em arquivos (txt ou json), portanto os registros relacionados aos médicos,
  animais, proprietários e consultas devem ser
- [x] armazenados em estruturas de arquivos que permitam a recuperação dos dados sempre que solicitados. <br/>
- [x] Os registros devem ser mantidos em arquivos (txt ou json), portanto os registros relacionados aos médicos,
  animais, proprietários e consultas devem ser
- [x] armazenados em estruturas de arquivos que permitam a recuperação dos dados sempre que solicitados. <br/>
- [x] Lembrem-se do seguinte requisito: uma mesma pessoa pode ter vários animais.<br/>
- [x] Construa endpoints no padrão REST capazes de executar a persistência e obtenção de dados a fim de atender aos
  requisitos existentes. <br/>
- [x] Cada grupo é responsável por definir a estrutura do payload de cada endpoint, bem como os parâmetros necessários a
  cada um deles. <br/>
- [x] Busque utilizar diferentes formas de passagem de parâmetros.<br/>
