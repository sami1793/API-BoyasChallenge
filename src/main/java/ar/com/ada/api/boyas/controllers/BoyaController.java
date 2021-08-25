package ar.com.ada.api.boyas.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import ar.com.ada.api.boyas.models.request.InfoCrearBoya;
import ar.com.ada.api.boyas.models.response.GenericResponse;
import ar.com.ada.api.boyas.services.BoyaService;

@RestController
public class BoyaController {
    @Autowired
    BoyaService service;

    @PostMapping("api/boyas")
    public ResponseEntity<GenericResponse> crearBoya(@RequestBody InfoCrearBoya infoboya){
        GenericResponse respuesta= new GenericResponse();
        service.crearBoya(infoboya.longitudInstalacion, infoboya.longitudInstalacion);

        respuesta.isOk=true;
        respuesta.mensaje="Boya creada con éxito";

        return ResponseEntity.ok(respuesta);
    }
}
