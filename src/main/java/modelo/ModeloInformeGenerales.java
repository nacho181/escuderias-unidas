package modelo;

import entidades.*;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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

    public ArrayList<Carrera> obtenerPodio(String dni){
        ArrayList<Carrera> carreras = new ArrayList<>();
        for (Carrera carrera : modelo.getRegistroGeneral().getCarreras()) {
            if(carrera.getEstado().equals(EstadoCarrera.FINALIZADA)){
                List<ResultadoCarrera> posiciones = carrera.getResultados();
                for (ResultadoCarrera posicion : posiciones) {
                    if (posicion == null || posicion.getAutoPiloto() == null) {
                        continue; // pasa a la siguiente posición
                    }
                    if (posicion.getPosicion() == 0 || posicion.getPosicion() == 1 || posicion.getPosicion() == 2) {
                        AutoPiloto ap = posicion.getAutoPiloto();
                        Piloto pilotoBuscado = modelo.buscarPiloto(dni);
                        if (ap.getPiloto().equals(pilotoBuscado)) {
                            carreras.add(carrera);
                            break;
                        }
                    }
                }
            }
        }
        return carreras;
    }

    public ArrayList<Carrera> obtenerCarreraAuto(ArrayList<Auto> autosEscuderia){
        ArrayList<Carrera> carrerasEncontradas = new ArrayList<>();
        for (Carrera reg : modelo.getRegistroGeneral().getCarreras()) {
            if ((EstadoCarrera.FINALIZADA).equals(reg.getEstado())) {
                for (ResultadoCarrera ap : reg.getResultados()) {
                    // Si pasa la validación, sigue la lógica normal
                    if (ap == null || ap.getAutoPiloto().getAuto() == null) {
                        continue;
                    }
                    if (autosEscuderia.contains(ap.getAutoPiloto().getAuto()) && !carrerasEncontradas.contains(reg)) {
                        carrerasEncontradas.add(reg);
                    }
                }
            }
        }
        return carrerasEncontradas;
    }

    public int contadorPilCirc(String dni, String circuito){
        int contador = 0;
        for (Carrera reg : modelo.getRegistroGeneral().getCarreras()) {
            if (reg.getCircuito().getNombre().equalsIgnoreCase(circuito) && reg.getEstado().equals(EstadoCarrera.FINALIZADA)) {
                for (ResultadoCarrera ap : reg.getResultados()) {
                    // Validaciones para evitar NullPointerException
                    if (ap == null || ap.getAutoPiloto().getPiloto() == null) {
                        continue; // pasa a la siguiente posición
                    }

                    if (ap.getAutoPiloto().getPiloto().getDni().equalsIgnoreCase(dni)) {
                        contador++;
                    }
                }
            }
        }
        return contador;
    }

    public int contadorCarrCirc(String circuito){
        int contador = 0;
        for (Carrera reg : modelo.getRegistroGeneral().getCarreras()) {
            if (reg.getCircuito().getNombre().equalsIgnoreCase(circuito) && reg.getEstado().equals(EstadoCarrera.FINALIZADA)) {
                contador++;
            }
        }
        return contador;
    }

}
