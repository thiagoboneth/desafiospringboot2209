package com.meli.desafiospringboot2209.service;

import com.meli.desafiospringboot2209.entity.Veterinario;
import com.meli.desafiospringboot2209.persistence.VeterinarioPersistence;
import org.springframework.stereotype.Service;

import javax.imageio.IIOException;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

@Service
public class VeterinarioService {

    private VeterinarioPersistence persistence = new VeterinarioPersistence();
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
}
