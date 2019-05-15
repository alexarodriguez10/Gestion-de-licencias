package clases;

import java.time.LocalDate;

public class Transaccion {

    private final Licencia licencia;
    private final LocalDate fechaCreacion;

    public Transaccion(Licencia licencia) {
        this.licencia = licencia;
        this.fechaCreacion = LocalDate.now();
    }

    //Metodos de Consulta
    public Licencia getLicencia() {
        return licencia;
    }

    public LocalDate getFechaCreacion() {
        return fechaCreacion;
    }

    @Override
    public String toString() {
        return "Transaccion{" + "licencia=" + licencia + ", fechaCreacion=" + fechaCreacion + '}';
    }
}
