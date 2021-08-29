package ar.com.ada.api.boyas.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import ar.com.ada.api.boyas.entities.Boya;
import ar.com.ada.api.boyas.models.request.ColorBoyaInfo;
import ar.com.ada.api.boyas.models.request.InfoCrearBoya;
import ar.com.ada.api.boyas.models.response.BoyaSinMuestra;
import ar.com.ada.api.boyas.models.response.GenericResponse;
import ar.com.ada.api.boyas.services.BoyaService;
import net.bytebuddy.asm.Advice.Return;

@RestController
public class BoyaController {
    @Autowired
    BoyaService service;

    //Permite la creación de boyas
    @PostMapping("api/boyas")
    public ResponseEntity<GenericResponse> crearBoya(@RequestBody InfoCrearBoya infoboya){
        GenericResponse respuesta= new GenericResponse();
        service.crearBoya(infoboya.longitudInstalacion, infoboya.longitudInstalacion);

        respuesta.isOk=true;
        respuesta.mensaje="Boya creada con éxito";

        return ResponseEntity.ok(respuesta);
    }

    //Devuelve las boyas SIN las muestras
    @GetMapping("api/boyas")
    public ResponseEntity<List<Boya>> obtenerTodasBoyas(){
        return ResponseEntity.ok(service.obtenerBoyas());
    }

    //Devuelve la info de una boya en particular(SIN las muestras)
    @GetMapping("api/boyas/{id}")
    public ResponseEntity<Boya> buscarBoya(@PathVariable Integer id){
        Boya boya = service.buscarBoya(id);
        return ResponseEntity.ok(boya);
    }

    //Actualiza solo el color de la luz de la Boya
    @PutMapping("api/boyas/{id}")
    public ResponseEntity<GenericResponse> actualizarColorBoya(@PathVariable Integer id, @RequestBody ColorBoyaInfo colorInfo ){
        GenericResponse respuesta= new GenericResponse();
        
        if(service.actualizarColorBoya(id, colorInfo.color)){
            respuesta.isOk=true;
            respuesta.mensaje="Color boya actualizada con éxito";

            return ResponseEntity.ok(respuesta);
        }
        else{
            respuesta.isOk=false;
            respuesta.mensaje="El id ingresado no existe";
            return ResponseEntity.badRequest().body(respuesta);
        }

        
    }
}
