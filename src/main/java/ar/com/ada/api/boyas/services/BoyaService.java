package ar.com.ada.api.boyas.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.com.ada.api.boyas.entities.Boya;
import ar.com.ada.api.boyas.models.response.BoyaSinMuestra;
import ar.com.ada.api.boyas.repos.BoyaRepository;

@Service
public class BoyaService {
    @Autowired 
    BoyaRepository repo;

    public void crearBoya(double longitudInstalacion, double latitudInstalacion) {
        Boya boya = new Boya();
        boya.setLongitudInstalacion(longitudInstalacion);
        boya.setLatitudInstalacion(latitudInstalacion);

        repo.save(boya);
    }

    public List<Boya> obtenerBoyas() {
        return repo.findAll();
    }

    public Boya buscarBoya(Integer boyaId) {
        return repo.findByboyaId(boyaId);
    }

    public void guardarBoya(Boya boya){
        repo.save(boya);
    }

    public boolean actualizarColorBoya(Integer boyaId, String colorLuz){
        Boya boya= buscarBoya(boyaId);
        if(boya!=null){
            boya.setColorLuz(colorLuz);
            guardarBoya(boya);
            return true;
        }
        return false;
    }
    
}
