package ar.com.ada.api.boyas.models.response;

import java.util.Date;

public class AnomaliaResponse {
    public double alturaNivelDelMar;
    public Date horarioInicioAnomalia;
    public Date horarioInicioFinAnomalia;
    public TipoAlertaEnum tipoAlerta;

    public enum TipoAlertaEnum {
        KAIJU(1), IMPACTO(2);

        private final Integer value;

        // NOTE: Enum constructor tiene que estar en privado
        private TipoAlertaEnum(Integer value) {
            this.value = value;
        }

        public Integer getValue() {
            return value;
        }

        public static TipoAlertaEnum parse(Integer id) {
            TipoAlertaEnum status = null; // Default
            for (TipoAlertaEnum item : TipoAlertaEnum.values()) {
                if (item.getValue().equals(id)) {
                    status = item;
                    break;
                }
            }
            return status;
        }
    }
}
