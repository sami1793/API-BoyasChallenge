package ar.com.ada.api.boyas.services;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.com.ada.api.boyas.entities.Boya;
import ar.com.ada.api.boyas.entities.Muestra;
import ar.com.ada.api.boyas.repos.MuestraRepository;

@Service
public class MuestraService {
    @Autowired
    MuestraRepository repo;

    @Autowired
    BoyaService boyaService;

    public void registarMuestra(Integer boyaId, Date horario, String matricula, double latitud,
                                double longitud, double alturaNivelMar) {
        Muestra muestra= new Muestra();
        Boya boya= boyaService.buscarBoya(boyaId);
        muestra.setBoya(boya);//ver relacion
        muestra.setHorarioMuestra(horario);
        muestra.setMatriculaEmbaracion(matricula);
        muestra.setLatitud(latitud);
        muestra.setLongitud(longitud);
        muestra.getAlturaNivelMar();

        //repo.save(muestra);
        boyaService.guardarBoya(boya);
    }
    
}
