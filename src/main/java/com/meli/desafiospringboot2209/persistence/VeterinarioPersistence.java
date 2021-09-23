package com.meli.desafiospringboot2209.persistence;

import com.meli.desafiospringboot2209.entity.Veterinario;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class VeterinarioPersistence {

    private File arquivo = new File("Veterinario.csv");

    public void cadastro(Veterinario veterinario, boolean manter) throws IOException{
        String registro = veterinario.toString();


        FileOutputStream fos = new FileOutputStream(arquivo, manter);
        OutputStreamWriter osw = new OutputStreamWriter(fos);
        BufferedWriter bw = new BufferedWriter(osw);
        bw.append(registro);
        bw.newLine();
        bw.close();
    }

    public void cadastro(List<Veterinario> veterinarios) throws IOException {
        FileOutputStream x = new FileOutputStream(arquivo);
        OutputStreamWriter y = new OutputStreamWriter(x);
        BufferedWriter z = new BufferedWriter(y);
        z.write("");
        z.close();


        FileOutputStream fos = new FileOutputStream(arquivo, true);
        OutputStreamWriter osw = new OutputStreamWriter(fos);
        BufferedWriter bw = new BufferedWriter(osw);

        for (Veterinario veterinario : veterinarios) {
            String registro = veterinario.toString();
            bw.append(registro);
            bw.newLine();
        }
        bw.close();
    }

    public List<Veterinario> listagem() {
        List<Veterinario> veterinariosExistentes = new ArrayList<>();

        List<String> registros = retornaRegistros();
        registros.forEach(registro -> {
            Veterinario veterinario = converte(registro);
            veterinariosExistentes.add(veterinario);
        });
        return veterinariosExistentes;

    }
    // Utilizando conversão a partir de interfaces fluentes
    private Veterinario converte(String registro) {
        String[] campos = registro.split(";");
        new Veterinario().comCpf(campos[0])
                .comNome(campos[1])
                .comSobrenome(campos[2])
                .comDataNascimento(campos[3])
                .comIdMedico(Integer.valueOf(campos[4]))
                .comNumeroRegistro(campos[5])
                .comEspecialidade(campos[6]);
        Veterinario veterinario = new Veterinario();
        return veterinario;
    }

    private List<String> retornaRegistros() {
        FileInputStream fis;
        List<String> registros = new ArrayList<String>();
        try {
            fis = new FileInputStream(arquivo);
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader br = new BufferedReader(isr);
            registros = new ArrayList<String>();
            while(true) {
                String linha = br.readLine();
                if(linha==null) {
                    break;
                }
                registros.add(linha);
            }
            br.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException("Arquivo nao encontrado");
        } catch(IOException e) {
            e.printStackTrace();
        }
        return registros;
    }
}
