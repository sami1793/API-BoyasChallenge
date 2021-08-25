package ar.com.ada.api.boyas.entities;

import java.util.*;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
@Entity
@Table(name="boya")
public class Boya {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "boya_id")
    private Integer boyaId;

    @Column(name="color_luz")
    private String colorLuz;// el color de la boya tiene valores ROJO, VERDE, AZUL y AMARILLO
                            //la primera vez es nulo
    
    @Column(name="longitud_instalacion")
    private double longitudInstalacion;

    @Column(name="latitud_instalacion")
    private double latitudInstalacion;

    @JsonIgnore
    @OneToMany(mappedBy = "boya", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Muestra> muestras = new ArrayList<>();

    public Integer getBoyaId() {
        return boyaId;
    }

    public void setBoyaId(Integer boyaId) {
        this.boyaId = boyaId;
    }

    public String getColorLuz() {
        return colorLuz;
    }

    public void setColorLuz(String colorLuz) {
        this.colorLuz = colorLuz;
    }

    public double getLongitudInstalacion() {
        return longitudInstalacion;
    }

    public void setLongitudInstalacion(double longitudInstalacion) {
        this.longitudInstalacion = longitudInstalacion;
    }

    public double getLatitudInstalacion() {
        return latitudInstalacion;
    }

    public void setLatitudInstalacion(double latitudInstalacion) {
        this.latitudInstalacion = latitudInstalacion;
    }

    public List<Muestra> getMuestras() {
        return muestras;
    }

    public void setMuestras(List<Muestra> muestras) {
        this.muestras = muestras;
    }

    public void agregarMuestra(Muestra muestra){
        this.muestras.add(muestra);
        muestra.setBoya(this);//relacion BIDIRECCIONAL
    }

    

}
