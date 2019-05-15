package clases;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.UUID;

public abstract class Licencia implements Cloneable {

    protected final String correoElectronico;
    protected final LocalDate fechaCreacion;
    protected final String codigo;
    protected ArrayList<Transaccion> listaTransacciones;
    protected boolean licenciaRevocada = false;
    protected final String nombreServicio;

    protected Licencia(String email, String servicio) {
        this.correoElectronico = email;
        this.nombreServicio = servicio;
        this.fechaCreacion = LocalDate.now();
        this.codigo = UUID.randomUUID().toString();
        this.listaTransacciones = new ArrayList<>();
        this.licenciaRevocada = false;
    }

    //Metodos de Consulta
    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public LocalDate getFechaCreacion() {
        return fechaCreacion;
    }

    public String getCodigo() {
        return codigo;
    }

    public ArrayList<Transaccion> getListaTransacciones() {
        return listaTransacciones;
    }

    public String getNombreServicio() {
        return nombreServicio;
    }

    public boolean isLicenciaRevocada() {
        return licenciaRevocada;
    }

    public int getNumeroTransacciones() {
        return this.listaTransacciones.size();
    }

    //Funcionalidades
    public void revocarLicencia() {
        this.licenciaRevocada = true;
    }

    public abstract boolean licenciaAplicable(Licencia licencia);

    public Transaccion obtenerAutorizacionServicio(Licencia licencia) {
        if (!licenciaRevocada && licenciaAplicable(licencia)) {
            Transaccion tr = new Transaccion(licencia);
            this.listaTransacciones.add(tr);
            return tr;
        }

        return null;
    }

    @Override
    public Licencia clone() {
        try {
            Licencia lc = (Licencia) super.clone();
            lc.listaTransacciones = new ArrayList<>(lc.listaTransacciones);
            return lc;
        } catch (CloneNotSupportedException e) {
            System.err.println("ERROR EN LA CLONACION: " + e.getMessage());
        }

        return null;
    }
}
