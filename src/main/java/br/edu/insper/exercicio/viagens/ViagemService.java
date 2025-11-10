package br.edu.insper.exercicio.viagens;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service
public class ViagemService {

    @Autowired
    private ViagemRepository viagemRepository;

    public List<Viagem> getViagens() {
        return viagemRepository.findAll();
    }

    public Viagem createViagem(Viagem viagem) {
        return viagemRepository.save(viagem);
    }

    public Viagem getViagem(Integer id) {
        return viagemRepository
                .findById(id)
                .orElseThrow(() -> new RuntimeException("Viagem não encontrada"));
    }

    public Viagem deleteViagem(Integer id){
        Viagem viagem = getViagem(id);
        viagemRepository.delete(viagem);
        return viagem;
    }
}
