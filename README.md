# Desafio Spring
## Veterinária
## // Projeto em grupo
<br>
A clínica veterinária “Zoo World” quer implementar um sistema desenvolvido em Java para a administração de cada um de seus pacientes. Os veterinários têm dois tipos de especialidades: Animais Domésticos e Animais de Fazenda, com base no fato de que atendem cães, gatos, porquinhos-da-índia, coelhos, tartarugas, cavalos, vacas e ovelhas.

<br/>

● De cada um de seus pacientes, eles precisam obter os seguintes dados:<br/>
- número do paciente (gerado automaticamente),<br/>
- espécie,<br/>
- raça,<br/>
- cor/s,<br/>
- data de nascimento e<br/>
- nome.<br/>

<br/>

● Ao mesmo tempo, cada paciente tem um proprietário atribuído que deve ser conhecido:<br/>
- cpf (formatado com a máscara ###.###.###-##),<br/>
- nome,<br/>
- sobrenome,<br/>
- data_de_nascimento,<br/>
- endereço e<br/>
- telefone_contato.<br/>

<br/>

● Cada consulta que você fizer a um paciente deve ser registrada, levando em consideração:<br/>
- data e hora,<br/>
- motivo,<br/>
- diagnóstico possível e<br/>
- tratamento a ser seguido.<br/>

<br/>

● Por outro lado, cada consulta deve ter também um médico veterinário responsável do qual ficam armazenados:<br/>
- cpf (formatado com a máscara ###.###.###-##),<br/>
- nome,<br/>
- sobrenome, <br/>
- número de registro e - especialidade. <br/>

---

Solicita-se a realização da modelagem de cada uma dessas classes, levando em consideração suas relações e a interação entre cada uma delas. Implementar (nas devidas classes) os métodos necessários para:

---

● Cadastrar/editar médicos;<br/>
○ Não é permitido o cadastro de médicos em duplicidade;<br/>
○ Todos os dados do médico são obrigatórios para realização do
cadastro/edição;<br/>
○ Não é permitido a exclusão de médicos que já realizaram consultas.<br/>

<br/>

● Cadastrar/editar proprietários;<br/>
○ Não é permitido o cadastro de proprietários em duplicidade;<br/>
○ Todos os dados do proprietários são obrigatórios para realização do
cadastro/edição;<br/>
○ Não é permitido a exclusão de proprietários de animais que já realizaram
consultas.<br/>

<br/>

● Cadastrar/editar pacientes;<br/>
○ Todos os dados do paciente são obrigatórios para realização do cadastro/edição;<br/>
○ Não é permitido a exclusão de animais que já realizaram consultas.<br/>

<br/>

● Criar/editar consultas;<br/>
○ Uma consulta deve exigir o médico e o paciente para ser registrada, bem como a data e hora e o motivo.<br/>
<br/>
● Ver a lista de todos os pacientes registrados junto com seus proprietários. A lista deve estar ordenada em ordem crescente de nome do proprietário do animal;<br/><br/>
● Ver a lista de todas as consultas atribuídas a um determinado paciente. <br/><br/>
A lista deve estar ordenada pela data da consulta, em ordem decrescente.<br/><br/>
● Ver o total de consultas atribuídas a cada um dos médicos.<br/><br/>

<br/>

● Ver a lista de todas as consultas marcadas para um mesmo dia. É importante mostrar dados relevantes do animal (paciente), do proprietário e do médico. A lista deve estar ordenada em ordem crescente de data e hora. <br/><br/>
Os registros devem ser mantidos em arquivos (txt ou json), portanto os registros relacionados aos médicos, animais, proprietários e consultas devem ser armazenados em estruturas de arquivos que permitam a recuperação dos dados sempre que solicitados. Lembrem-se do seguinte requisito: uma mesma pessoa pode ter vários animais.<br/><br/>
Construa endpoints no padrão REST capazes de executar a persistência e obtenção de dados a fim de atender aos requisitos existentes. Cada grupo é responsável por definir a estrutura do payload de cada endpoint, bem como os parâmetros necessários a cada um deles. Busque utilizar diferentes formas de passagem de parâmetros.<br/><br/>


