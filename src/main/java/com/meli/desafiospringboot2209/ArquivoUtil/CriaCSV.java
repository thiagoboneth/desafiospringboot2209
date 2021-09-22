package com.meli.desafiospringboot2209.ArquivoUtil;

import com.meli.desafiospringboot2209.model.Consulta;
import com.meli.desafiospringboot2209.model.Paciente;
import com.meli.desafiospringboot2209.model.Proprietario;
import com.meli.desafiospringboot2209.model.Veterinario;

import java.io.IOException;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CriarCsv {

    public static void main(String[] args) throws IOException, CsvDataTypeMismatchException, CsvRequiredFieldEmptyException {

        List<Veterinario> veterinarios = new ArrayList<>();
        veterinarios.add(new Veterinario("100.000.000-01","Luana","Medina","1995/04/22",1,"123456789","Animais Domesticos"));
        veterinarios.add(new Veterinario("200.000.000-01","Pedro","Luis","1996/05/25",2,"987654321","Animais de Fazenda"));

        // criando proprietario
        List<Proprietario> proprietarios = new ArrayList<>();

        proprietarios.add(new Proprietario("100.000.000-01","Karol","Lopes","1992/05/22", "Avenida primavera, Condomínio Girassol II apt 408", "01 9 0000-0002"));
        proprietarios.add(new Proprietario("200.000.000-01","Carlos","Luis","1993/04/22", "Rua LaCoruna 15 apt 500", "092-88406584"));


        // criando paciente
        List<Paciente> pacientes = new ArrayList<>();
        pacientes.add(new Paciente( "Gato", "Maine Coon", "Marrom", "2018/07/10", "Max", "M", proprietarios.get(0)));
        pacientes.add(new Paciente("Cachorro", "Pinche", "Preto", "2018/07/10", "Lulu", "F", proprietarios.get(1)));


        // criando consulta

        //Date dataHora, Paciente paciente, String motivo, Veterinario veterinario, String diagnostico, String tratamento

        List<Consulta> consultas= new ArrayList<>();
        consultas.add(new Consulta( LocalDate.parse("2021-10-25"), pacientes.get(0), "Animal com caimento de pelos em excesso", veterinarios.get(0),
                "Foliculite bacteriana superficial",
                "Banhos semanais com clorexidina 3% (HEPTADERMI Spherulites), durante 8 semanas"));

        consultas.add(new Consulta(LocalDate.parse("2021-11-15"), pacientes.get(1),
                "Rachaduras nos cascos",
                veterinarios.get(1),
                "Animal tem caminhado em superficies duras e inóspitas, falta fazer manutencao nos cascos",
                "Limpar os cascos, utilizar reina nas linhas de fraturas para cobrir os machucados"));



        Writer writerProprietario = Files.newBufferedWriter(Paths.get("db/proprietario.csv"));
        StatefulBeanToCsv<Proprietario> proprietarioToCsv = new StatefulBeanToCsvBuilder(writerProprietario).build();

        proprietarioToCsv.write(proprietarios);

        writerProprietario.flush();
        writerProprietario.close();

        // criando veterinario
        Writer writerVeterinario = Files.newBufferedWriter(Paths.get("db/veterinarios.csv"));
        StatefulBeanToCsv<Veterinario> veterinarioToCsv = new StatefulBeanToCsvBuilder(writerVeterinario).build();

        veterinarioToCsv.write(veterinarios);

        writerVeterinario.flush();
        writerVeterinario.close();

        // criando Paciente

        Writer writerPaciente = Files.newBufferedWriter(Paths.get("db/paciente.csv"));
        StatefulBeanToCsv<Paciente> PacienteioToCsv = new StatefulBeanToCsvBuilder(writerPaciente).build();

        PacienteioToCsv.write(pacientes);

        writerPaciente.flush();
        writerPaciente.close();


        // criando Paciente

        Writer writerConsulta = Files.newBufferedWriter(Paths.get("db/consultas.csv"));
        StatefulBeanToCsv<Consulta> ConsultaioToCsv = new StatefulBeanToCsvBuilder(writerConsulta).build();

        ConsultaioToCsv.write(consultas);

        writerConsulta.flush();
        writerConsulta.close();
    }

}
