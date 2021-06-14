package edu.pingpong.quickstart;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.core.Response;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@ApplicationScoped
public class ServiceOlli {

    public Usuaria cargaUsuaria(String nombre){
        Optional<Usuaria> usuaria = Usuaria.find("user_nom", nombre).firstResultOptional();
        return usuaria.isPresent()
                ? usuaria.get()
                : new Usuaria("", 0);
    }
    public Item cargaItem(String nombre){
        Optional<Item> item = Item.find("item_nom", nombre).firstResultOptional();
        return item.isPresent()
                ? item.get()
                : new Item("",0,"");
    }
    public List<Orden> cargaOrden(String nombre){
        Optional<Orden> ordenCheck = Orden.find("ord_user", nombre).firstResultOptional();
        return ordenCheck.isPresent()
                ? Orden.find("ord_user", nombre).list()
                : Collections.emptyList();
    }

    public Orden comanda(String nombreUsuario, String nombreItem){
        Optional<Usuaria> usuaria = Usuaria.find("user_nom", nombreUsuario).firstResultOptional();
        Optional<Item> item = Item.find("item_nom", nombreItem).firstResultOptional();

        if(usuaria.isPresent() && item.isPresent() && (item.get().quality < usuaria.get().destreza)){
            Orden orden = new Orden(usuaria.get(), item.get());
            orden.persist();
            return orden;
        }
        else{
            return null;
        }
    }
    public List<Orden> comandaMultiple(String nombreUsuario, List<String> items){
        List<Orden> ordenes = new ArrayList<Orden>();
        Optional<Usuaria> usuaria = Usuaria.find("user_nom", nombreUsuario).firstResultOptional();
        if(usuaria.isPresent()){
            for (int i = 0; i < items.size(); i++){
                Orden orden;
                Optional<Item> item = Item.find("item_nom",items.get(i)).firstResultOptional();
                if (item.isPresent()) {
                    orden = new Orden(usuaria.get(), item.get());
                    orden.persist();
                    ordenes.add(orden);
                }
            }
        }
        return ordenes;
    }
}
