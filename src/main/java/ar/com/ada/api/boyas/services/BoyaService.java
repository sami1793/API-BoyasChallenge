package ar.com.ada.api.boyas.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.com.ada.api.boyas.entities.Boya;
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
    
}
