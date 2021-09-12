package ar.com.ada.api.boyas.controllers;

import java.util.List;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import ar.com.ada.api.boyas.entities.Muestra;
import ar.com.ada.api.boyas.models.request.MuestraInfo;
import ar.com.ada.api.boyas.models.response.AnomaliaResponse;
import ar.com.ada.api.boyas.models.response.GenericResponse;
import ar.com.ada.api.boyas.models.response.MuestraColor;
import ar.com.ada.api.boyas.models.response.MuestraMinimaResponse;
import ar.com.ada.api.boyas.models.response.MuestraResponse;
import ar.com.ada.api.boyas.services.MuestraService;

@RestController
public class MuestraController {
    @Autowired
    MuestraService service;

    //Registra una muestra
    @PostMapping("api/muestras")
    public ResponseEntity<MuestraResponse> registrarMuestra(@RequestBody MuestraInfo muestra){
        MuestraResponse respuestaMuestra = new MuestraResponse();

        Muestra muestraEntera = service.registarMuestra(muestra.boyaId,muestra.horario, muestra.matricula, muestra.latitud,
                                muestra.longitud, muestra.alturaNivelDelMar);

        
        respuestaMuestra.id=muestraEntera.getMuestraId();//id de muestra
        
        respuestaMuestra.color=service.colorMuestra(muestraEntera);

        return ResponseEntity.ok(respuestaMuestra);
    }

    //Reseteara el color de la luz de la boya a “AZUL” a partir de una muestra especifica
    @DeleteMapping("api/muestras/{id}")
    public ResponseEntity<GenericResponse> resetarColorBoyaMuestra(@PathVariable Integer id){
        GenericResponse respuesta = new GenericResponse();
        if(service.resetearColorBoyaMuestra(id)){//reseteo y a la vez devuelvo true o false
            respuesta.isOk=true;
            respuesta.mensaje="Color de boya resetado a azul con éxito";

            return ResponseEntity.ok(respuesta);
        }
        else{
            respuesta.isOk=false;
            respuesta.mensaje="El id de muestra no existe";
            return ResponseEntity.badRequest().body(respuesta);
        }    

    }

    //devuelve la lista de muestras de una boya, indicado por "idBoya"
    @GetMapping("api/muestras/boyas/{idBoya}")
    public ResponseEntity<?> traerMuestras(@PathVariable Integer idBoya){
        GenericResponse respuesta= new GenericResponse();
        if(service.traerMuestras(idBoya)!=null){
            return ResponseEntity.ok(service.traerMuestras(idBoya));
        }
        else{
            respuesta.isOk=false;
            respuesta.mensaje="No existe id ingresado";
            return ResponseEntity.badRequest().body(respuesta);
        }
                    
    }

    //devuelve la lista de muestras de un color en el siguiente formato JSON MuestraColor:
    @GetMapping("api/muestras/colores/{color}")
    public ResponseEntity<List<MuestraColor>> traerMuestrasPorColor(@PathVariable String color){
                
        return ResponseEntity.ok(service.traerMuestrasPorColor(color.toUpperCase()));//por si se ingresa en minuscula
    }

    //que devuelva la muestra donde la altura nivel del mar sea la minima 
    //para una boya en particular en formato JSON MuestraMinimaResponse
    @GetMapping("api/muestras/minima/{idBoya}")
    public ResponseEntity<MuestraMinimaResponse> muestraAlturaMinima(@PathVariable Integer idBoya){
        Muestra muestraMinima= service.MuestraAlturaMinima(idBoya);
        
        MuestraMinimaResponse muestraMinimaResponse= new MuestraMinimaResponse();

        muestraMinimaResponse.alturaNivelDelMarMinima=muestraMinima.getAlturaNivelMar();
        muestraMinimaResponse.color=muestraMinima.getBoya().getColorLuz();
        muestraMinimaResponse.horario=muestraMinima.getHorarioMuestra();

        return ResponseEntity.ok(muestraMinimaResponse);
    }


    @GetMapping("api/muestras/anomalias/{idBoya}")
    public ResponseEntity<AnomaliaResponse> obtenerAnomalia(@PathVariable Integer idBoya){
        
        return ResponseEntity.ok(service.alertaImpacto(idBoya));
    }


}
