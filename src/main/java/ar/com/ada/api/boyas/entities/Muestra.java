package ar.com.ada.api.boyas.entities;

import java.util.Date;

import javax.persistence.*;

@Entity
@Table(name ="muestra")
public class Muestra {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="muestra_id")
    private Integer muestraId;

    @ManyToOne
    @JoinColumn(name = "boya_id", referencedColumnName = "boya_id")
    private Boya boya;

    @Column(name="horario_muestra")
    private Date horarioMuestra;

    @Column(name="matricula_embarcacion")
    private String matriculaEmbaracion;


    private double longitud;

    private double latitud;

    @Column(name = "altura_nivel_mar")
    private double alturaNivelMar;

    public Integer getMuestraId() {
        return muestraId;
    }

    public void setMuestraId(Integer muestraId) {
        this.muestraId = muestraId;
    }

    public Boya getBoya() {
        return boya;
    }

    public void setBoya(Boya boya) {
        this.boya = boya;
        this.boya.agregarMuestra(this);
    }

    public Date getHorarioMuestra() {
        return horarioMuestra;
    }

    public void setHorarioMuestra(Date horarioMuestra) {
        this.horarioMuestra = horarioMuestra;
    }

    public String getMatriculaEmbaracion() {
        return matriculaEmbaracion;
    }

    public void setMatriculaEmbaracion(String matriculaEmbaracion) {
        this.matriculaEmbaracion = matriculaEmbaracion;
    }

    public double getLongitud() {
        return longitud;
    }

    public void setLongitud(double longitud) {
        this.longitud = longitud;
    }

    public double getLatitud() {
        return latitud;
    }

    public void setLatitud(double latitud) {
        this.latitud = latitud;
    }

    public double getAlturaNivelMar() {
        return alturaNivelMar;
    }

    public void setAlturaNivelMar(double alturaNivelMar) {
        this.alturaNivelMar = alturaNivelMar;
    }

    

}
