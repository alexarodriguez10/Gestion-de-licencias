package TiposdeLicencias;

import clases.Licencia;

public class LicenciaTransaccionesLimitadas extends Licencia {

    protected final int numTransacciones;

    public LicenciaTransaccionesLimitadas(String email, String servicio, int numTransacciones) {
        super(email, servicio);
        this.numTransacciones = numTransacciones;
    }

    //Funcionalidades
    public int getTransaccionesRestantes() {
        int resultado = this.numTransacciones - super.getNumeroTransacciones();
        return resultado;
    }

    @Override
    public boolean licenciaAplicable(Licencia licencia) {
        if (this.getTransaccionesRestantes() > 0) {
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return super.toString() + "LicenciaTransaccionesLimitadas{" + "numTransacciones=" + numTransacciones + '}';
    }

    @Override
    public Licencia clone() {
        LicenciaTransaccionesLimitadas ltl = (LicenciaTransaccionesLimitadas) super.clone();
        return ltl;
    }
}
