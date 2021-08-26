package ar.com.ada.api.boyas.controllers;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import ar.com.ada.api.boyas.entities.Muestra;
import ar.com.ada.api.boyas.models.request.MuestraInfo;
import ar.com.ada.api.boyas.models.response.GenericResponse;
import ar.com.ada.api.boyas.services.MuestraService;

@RestController
public class MuestraController {
    @Autowired
    MuestraService service;

    @PostMapping("api/muestras")
    public ResponseEntity<GenericResponse> registrarMuestra(@RequestBody MuestraInfo muestra){
        GenericResponse respuesta = new GenericResponse();

        service.registarMuestra(muestra.boyaId,muestra.horario, muestra.matricula, muestra.latitud,
                                muestra.longitud, muestra.alturaNivelDelMar);

        
        respuesta.isOk=true;
        respuesta.mensaje="muestra creada con éxito";

        return ResponseEntity.ok(respuesta);
    }

}
