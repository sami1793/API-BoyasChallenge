package ar.com.ada.api.boyas.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
import ar.com.ada.api.boyas.models.response.BadResponse;
import ar.com.ada.api.boyas.models.response.BoyaSinMuestra;
import ar.com.ada.api.boyas.models.response.GenericResponse;
import ar.com.ada.api.boyas.services.BoyaService;
import net.bytebuddy.asm.Advice.Return;

@RestController
public class BoyaController {
    @Autowired
    BoyaService service;

    //POST:Permite la creación de boyas
    //agregar validaciones!!!!!!!!!
    @PostMapping("api/boyas")
    public ResponseEntity<GenericResponse> crearBoya(@RequestBody InfoCrearBoya infoboya){
        GenericResponse respuesta= new GenericResponse();
        Boya boya= service.crearBoya(infoboya.longitudInstalacion, infoboya.longitudInstalacion);

        respuesta.isOk=true;
        respuesta.id=boya.getBoyaId();
        respuesta.mensaje="Boya creada con éxito";

        return ResponseEntity.status(HttpStatus.CREATED).body(respuesta);
    }

    //GET: Devuelve las boyas SIN las muestras
    @GetMapping("api/boyas")
    public ResponseEntity<List<Boya>> obtenerTodasBoyas(){
        return ResponseEntity.ok(service.obtenerBoyas());
    }

    //GET: Devuelve la info de una boya en particular(SIN las muestras)
    @GetMapping("api/boyas/{id}")
    public ResponseEntity<?> buscarBoya(@PathVariable Integer id){
        BadResponse respuesta= new BadResponse();
        if(service.existeBoya(id)){
            Boya boya = service.buscarBoya(id);
            return ResponseEntity.ok(boya);
        }
        else{
            respuesta.isOk=false;
            respuesta.mensaje="El id ingresado no éxiste";
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(respuesta);
        }

        
    }

    //PUT: Actualiza solo el color de la luz de la Boya
    @PutMapping("api/boyas/{id}")
    public ResponseEntity<?> actualizarColorBoya(@PathVariable Integer id, @RequestBody ColorBoyaInfo colorInfo ){
        GenericResponse respuesta= new GenericResponse();
        BadResponse badRespuesta= new BadResponse();
        
        if(service.actualizarColorBoya(id, colorInfo.color)){
            respuesta.isOk=true;
            respuesta.mensaje="Color boya actualizada con éxito";

            return ResponseEntity.ok(respuesta);
        }
        else{
            badRespuesta.isOk=false;
            badRespuesta.mensaje="El id ingresado no existe";
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(badRespuesta);
        }

        
    }
}
