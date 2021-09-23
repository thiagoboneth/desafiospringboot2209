package com.meli.desafiospringboot2209.service;

import com.meli.desafiospringboot2209.crudteste.Crud;
import com.meli.desafiospringboot2209.entity.Veterinario;
import com.meli.desafiospringboot2209.persistence.VeterinarioPersistence;
import org.springframework.stereotype.Service;

import javax.imageio.IIOException;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

@Service
public class VeterinarioService {

    private VeterinarioPersistence persistence = new VeterinarioPersistence();

    private boolean numeroRegistroNaoUtilizado(String numeroRegistro) {
        for (Veterinario veterinario : persistence.listagem()) {
            if (veterinario.getNumeroRegistro().equals(numeroRegistro)) {
                return false;
            }
        }
        return true;
    }

    private boolean novoMedico(String numeroRegistro){
        for (Veterinario veterinario : persistence.listagem()){
            if(veterinario.getNumeroRegistro().equals(numeroRegistro)){
                return false;
            }
        }
        return true;
    }
    public void cadastrar(Veterinario veterinario){
        if(novoMedico(veterinario.getNumeroRegistro())){
            veterinario.setIdMedico(++Veterinario.idMedico);
            try {
                persistence.cadastro(veterinario,true);
            }catch (IOException e){
                throw new RuntimeException("Erro ao cadastrar Médico Veterinário");
            }
        }else {
            throw new RuntimeException("Esse número de registro já está em uso em outro Cadastro");
        }
    }
    public void remover(String id){
        List<Veterinario> veterinarios = persistence.listagem();
        Iterator<Veterinario> iterator = veterinarios.iterator();
        while (iterator.hasNext()){
            Veterinario v = iterator.next();
            if (Veterinario.getIdMedico().equals(id)){
                iterator.remove();
            }
        }
        try {
            persistence.cadastro(veterinarios);
        }catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao Persistir os veterinários");
        }
    }

    public void cadastraMedico(Veterinario veterinario) {

        if (numeroRegistroNaoUtilizado(veterinario.getNumeroRegistro())) {
            try {
                String[] campos = veterinario.toString().split(";");

                BufferedWriter bw  = new BufferedWriter(new FileWriter("db/medico.csv", true));

                bw.write(campos[0]+";"+campos[1]+";"+campos[2]+";"+campos[3]+";"+campos[4]+";"+campos[5]+";"+campos[6]);
                bw.flush();
                bw.newLine();
                bw.close();

                persistence.cadastro(veterinario, true);
            } catch (IOException e) {
                throw new RuntimeException("Erro ao cadastrar o veterianario");
            }
        } else {
            throw new RuntimeException("Número de registro " + numeroRegistroNaoUtilizado(veterinario.getNumeroRegistro()) + " já cadastrado");
        }
    }
}
