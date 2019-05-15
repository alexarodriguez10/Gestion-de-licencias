package main;

import TiposdeLicencias.LicenciaTransaccionesLimitadas;
import TiposdeLicencias.LicenciaTemporal;
import TiposdeLicencias.LicenciaLimiteDiario;
import clases.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;

public class App {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        /*Crea tres licencias para el servicio “http://api.um.es/disco/v1/” asociadas a los usuarios
        “juan@um.es”, “pepe@um.es” y “paco@um.es”.*/
        /*La primera será una licencia temporal que caduca dentro de un mes. La segunda será una
        licencia con transacciones limitadas con un máximo de 3. La última será una licencia con límite
        diario, con un máximo de 4 transacciones, 1 por día.*/
        LicenciaTemporal licenciaTemporal = new LicenciaTemporal("juan@um.es", "http://api.um.es/disco/v1/", 1);
        LicenciaTransaccionesLimitadas licenciaTransaccionesLimitadas;
        licenciaTransaccionesLimitadas = new LicenciaTransaccionesLimitadas("pepe@um.es", "http://api.um.es/disco/v1/", 3);
        LicenciaLimiteDiario licenciaLimiteDiario;
        licenciaLimiteDiario = new LicenciaLimiteDiario("paco@um.es", "http://api.um.es/disco/v1/", 4, 1);

        /*Crea una licencias de licencias y almacena las licencias anteriores en esa licencias. Recorre la colección
        de licencias y muestra su información por la consola (toString).*/
        ArrayList<Licencia> licencias = new ArrayList<>();
        Collections.addAll(licencias, licenciaTemporal, licenciaTransaccionesLimitadas, licenciaLimiteDiario);

        for (int i = 0; i < licencias.size(); i++) {
            Licencia licencia = licencias.get(i);
            System.out.println(licencia.toString());
        }

        /*Recorre la colección de licencias:
            - Si es una licencia con límite diario, muestra por la consola las transacciones restantes
            para hoy.
            - Revoca la licencia temporal.*/
        for (int i = 0; i < licencias.size(); i++) {
            Licencia licencia = licencias.get(i);
            if (licencia instanceof LicenciaLimiteDiario) {
                System.out.println("Transacciones Restantes Hoy: " + ((LicenciaLimiteDiario) licencia).transaccionesPorDia(LocalDate.now()));
            } else if (licencia instanceof LicenciaTemporal) {
                licencia.revocarLicencia();
            }
        }

        /*Recorre la colección de licencias y para cada una: solicita 4 autorizaciones (transacciones) de
        forma consecutiva. Tras cada solicitud muestra la transacción por la consola (si no es un valor
        nulo), y en caso contrario, el texto “no autorizada”.*/
        for (int i = 0; i < licencias.size(); i++) {
            Licencia licencia = licencias.get(i);
            for (int j = 0; j < 4; j++) {
                Transaccion t = licencia.obtenerAutorizacionServicio(licencia);
                if (t != null) {
                    System.out.println(t.toString());
                } else {
                    System.out.println("NO AUTORIZADA");
                }
            }
            System.out.println("\n------------------\n");
        }

        //Recorre la colección de licencias y muestra su información por la consola (toString).
        for (int i = 0; i < licencias.size(); i++) {
            Licencia licencia = licencias.get(i);
            System.out.println(licencia.toString());
        }

        /*Crea una licencias de licencias llamada copiasLicencias. Recorre la colección de licencias y para cada una
        obtén una copia. Almacena la copia en la nueva colección. Recorre la colección de copiasLicencias y
        muestra la información por la consola.*/
        ArrayList<Licencia> copiasLicencias = new ArrayList<>();
        System.out.println(".:Copias de las Licencias:.");
        for (int i = 0; i < licencias.size(); i++) {
            Licencia licencia = licencias.get(i);
            copiasLicencias.add(licencia.clone());
        }

        for (int i = 0; i < copiasLicencias.size(); i++) {
            Licencia licencia = copiasLicencias.get(i);
            System.out.println(licencia.toString());
        }
    }
}
