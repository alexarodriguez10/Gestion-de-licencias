package TiposdeLicencias;

import clases.Licencia;
import java.time.LocalDate;
import java.util.HashMap;

public class LicenciaLimiteDiario extends LicenciaTransaccionesLimitadas {

    private final int transaccionesDia;
    private final HashMap<LocalDate, Integer> mapaTransacciones;

    public LicenciaLimiteDiario(String email, String servicio, int numTransacciones, int transaccionesDia) {
        super(email, servicio, numTransacciones);
        this.transaccionesDia = transaccionesDia;
        this.mapaTransacciones = new HashMap<>();
        this.mapaTransacciones.put(LocalDate.now(), 1);
    }

    //Funcionalidades
    public int transaccionesPorDia(LocalDate fecha) {
        return this.transaccionesDia - this.mapaTransacciones.get(fecha);
    }

    @Override
    public boolean licenciaAplicable(Licencia licencia) {
        if (super.licenciaAplicable(licencia)) {
            LocalDate fechaActual = LocalDate.now();
            if (this.mapaTransacciones.containsKey(fechaActual)) {
                int numT = this.mapaTransacciones.get(fechaActual);
                if (numT < this.transaccionesDia) {
                    numT = numT + 1;
                    this.mapaTransacciones.replace(fechaActual, numT);
                    return true;
                }
            } else {
                this.mapaTransacciones.put(fechaActual, 1);
                return true;
            }
        }

        return false;
    }

    @Override
    public String toString() {
        return super.toString() + "LicenciaLimiteDiario{" + "transaccionesDia=" + transaccionesDia + ", mapaTransacciones=" + mapaTransacciones + '}';
    }

}
