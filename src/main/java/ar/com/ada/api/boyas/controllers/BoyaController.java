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

    @PostMapping("api/boyas")
    public ResponseEntity<GenericResponse> crearBoya(@RequestBody InfoCrearBoya infoboya){
        GenericResponse respuesta= new GenericResponse();
        service.crearBoya(infoboya.longitudInstalacion, infoboya.longitudInstalacion);

        respuesta.isOk=true;
        respuesta.mensaje="Boya creada con éxito";

        return ResponseEntity.ok(respuesta);
    }

    @GetMapping("api/boyas")
    public ResponseEntity<List<Boya>> obtenerTodasBoyas(){
        return ResponseEntity.ok(service.obtenerBoyas());
    }

    @GetMapping("api/boyas/{id}")//devuelve la info de una boya en particular(SIN las muestras)
    public ResponseEntity<Boya> buscarBoya(@PathVariable Integer id){
        Boya boya = service.buscarBoya(id);
        return ResponseEntity.ok(boya);
    }

    @PutMapping("api/boyas/{id}")//actualiza solo el color de la luz de la Boya
    public ResponseEntity<GenericResponse> actualizarColorBoya(@PathVariable Integer id, @RequestBody ColorBoyaInfo colorInfo ){
        GenericResponse respuesta= new GenericResponse();
        Boya boya = service.buscarBoya(id);
        boya.setColorLuz(colorInfo.color);
        service.guardarBoya(boya);
        

        respuesta.isOk=true;
        respuesta.mensaje="Color boya actualizada con éxito";

        return ResponseEntity.ok(respuesta);
    }
}
