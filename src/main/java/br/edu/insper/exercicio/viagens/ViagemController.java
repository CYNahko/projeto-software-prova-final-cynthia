package br.edu.insper.exercicio.viagens;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/viagens")
public class ViagemController {
    @Autowired
    private ViagemService viagemService;

    @GetMapping
    public List<Viagem> getViagens(){
        return viagemService.getViagens();
    }

    @PostMapping
    public Viagem createViagem(@RequestBody Viagem viagem){
        return viagemService.createViagem(viagem);
    }

    @GetMapping("/{id}")
    public Viagem getViagem(@PathVariable Integer id){
        return viagemService.getViagem(id);
    }

    @DeleteMapping("/{id}")
    public Viagem deleteViagem(@AuthenticationPrincipal Jwt jwt, @PathVariable Integer id){
        List<String> roles = jwt.getClaimAsStringList("https://stocks-insper.com/roles");
        if (!roles.contains("Admin")){
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        }
        return viagemService.deleteViagem(id);
    }
}
