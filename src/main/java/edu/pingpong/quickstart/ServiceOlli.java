package edu.pingpong.quickstart;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.core.Response;
import java.util.Optional;

@ApplicationScoped
public class ServiceOlli {

    public Usuaria cargaUsuaria(String nombre){
        Optional<Usuaria> usuaria = Usuaria.find("user_nom", nombre).firstResultOptional();
        return usuaria.isPresent()?
                usuaria.get()
                : new Usuaria("", 0);
    }
}
