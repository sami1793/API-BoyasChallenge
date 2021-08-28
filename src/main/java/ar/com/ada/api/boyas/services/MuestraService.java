package ar.com.ada.api.boyas.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.com.ada.api.boyas.entities.Boya;
import ar.com.ada.api.boyas.entities.Muestra;
import ar.com.ada.api.boyas.models.response.MuestraColor;
import ar.com.ada.api.boyas.repos.MuestraRepository;

@Service
public class MuestraService {
    @Autowired
    MuestraRepository repo;

    @Autowired
    BoyaService boyaService;

    public Muestra registarMuestra(Integer boyaId, Date horario, String matricula, double latitud,
                                double longitud, double alturaNivelMar) {
        Muestra muestra= new Muestra();
        Boya boya= boyaService.buscarBoya(boyaId);
        muestra.setBoya(boya);//ver relacion
        muestra.setHorarioMuestra(horario);
        muestra.setMatriculaEmbaracion(matricula);
        muestra.setLatitud(latitud);
        muestra.setLongitud(longitud);
        muestra.setAlturaNivelMar(alturaNivelMar);

        //repo.save(muestra);
        boyaService.guardarBoya(boya);
        return muestra;
    }

    public void resetearColorBoyaMuestra(Integer muestraId) {
        Muestra muestra = repo.findBymuestraId(muestraId);
        Integer boyaId = muestra.getBoya().getBoyaId();
        Boya boya = boyaService.buscarBoya(boyaId);
        boya.setColorLuz("AZUL");
        boyaService.guardarBoya(boya);
    }

    public List<Muestra> traerMuestras(Integer idBoya) {
        Boya boya = boyaService.buscarBoya(idBoya);
        return boya.getMuestras();

    }

    public String colorMuestra(Muestra muestra){
               
        if(muestra.getAlturaNivelMar()<-100 || muestra.getAlturaNivelMar()>100 ){
            return "ROJO";
        }
        else if (muestra.getAlturaNivelMar()<-50 || muestra.getAlturaNivelMar()>50) {
            return "AMARILLO";
        } 
        else {
            return "VERDE"; 
        }
    }

    public List<MuestraColor> traerMuestrasPorColor(String color) {
        List<MuestraColor> muestrasPorColor = new ArrayList<>();
        MuestraColor muestraPorColor= new MuestraColor();
        
        for (Muestra muestra : repo.findAll()) {            
            
            if (colorMuestra(muestra).equals(color)){ 
                muestraPorColor.boyaId = muestra.getBoya().getBoyaId();
                muestraPorColor.horario=muestra.getHorarioMuestra();
                muestraPorColor.alturaNivelDelMar=muestra.getAlturaNivelMar();                            
                
                muestrasPorColor.add(muestraPorColor);
                
            }            

        }
        return muestrasPorColor;
        
    }

    public Muestra MuestraAlturaMinima(Integer idBoya) {
        Boya boya = boyaService.buscarBoya(idBoya);
             
        Muestra muestraMinima = boya.getMuestras().get(0);  

        for (Muestra muestra : boya.getMuestras()) {
            if(muestra.getAlturaNivelMar()<muestraMinima.getAlturaNivelMar()){
                muestraMinima=muestra;
            }
            
        }
        return muestraMinima;
    }
    
}
