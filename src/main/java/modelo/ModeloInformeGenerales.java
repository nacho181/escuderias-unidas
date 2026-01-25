package modelo;

import entidades.Carrera;
import entidades.EstadoCarrera;

import java.time.LocalDate;
import java.util.ArrayList;

public class ModeloInformeGenerales {
    private final Modelo modelo;

    public ModeloInformeGenerales(Modelo modelo) {
        this.modelo = modelo;
    }

    /**
     * Devuelve las carreras que se realizaron entre dos fechas dadas.
     * @param fechaInicio fecha inicial del rango.
     * @param fechaFinal fecha final del rango.
     * @return lista de carreras dentro del rango.
     */
    public ArrayList<Carrera> obtenerCarrerasEntreFechas(LocalDate fechaInicio, LocalDate fechaFinal) {
        ArrayList<Carrera> carrerasEntreFechas = new ArrayList<>();

        for (Carrera r : modelo.getRegistroGeneral().getCarreras()) {
            LocalDate fecha = r.getFechaRealizacion();
            if (fecha != null && r.getEstado().equals(EstadoCarrera.FINALIZADA)) {
                if (!fecha.isBefore(fechaInicio) && !fecha.isAfter(fechaFinal)) {
                    carrerasEntreFechas.add(r);
                }
            }
        }
        return carrerasEntreFechas;
    }

}
