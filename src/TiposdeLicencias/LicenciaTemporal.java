package TiposdeLicencias;

import clases.Licencia;
import java.time.LocalDate;

public class LicenciaTemporal extends Licencia {

    private LocalDate fechaCaducidad;
    private boolean caducada;

    public LicenciaTemporal(String email, String servicio, int meses) {
        super(email, servicio);
        this.fechaCaducidad = this.fechaCreacion;
        this.fechaCaducidad = fechaCaducidad.plusMonths(meses);
    }

    public LicenciaTemporal(String email, String servicio) {
        this(email, servicio, 3);
    }

    //Funcionalidades
    public boolean licenciaCaducada() {
        LocalDate fechaActual = LocalDate.now();
        return (fechaActual.isEqual(fechaCaducidad) || fechaActual.isAfter(fechaCaducidad));
    }

    public void extenderFechaCaducidad(int meses) {
        this.fechaCaducidad = fechaCaducidad.plusMonths(meses);
    }

    @Override
    public boolean licenciaAplicable(Licencia licencia) {
        if(licenciaCaducada()) {
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return super.toString() + "LicenciaTemporal{" + "fechaCaducidad=" + fechaCaducidad + ", caducada=" + caducada + '}';
    }

    @Override
    public LicenciaTemporal clone() {
        LicenciaTemporal lt = (LicenciaTemporal) super.clone();
        return lt;
    }
}
