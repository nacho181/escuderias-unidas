package modelo;

import entidades.*;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;

public class ModeloRegistrarResultado {
    private final Modelo modelo;
    private LocalDate fecha;
    private Circuito circuito;

    public ModeloRegistrarResultado(Modelo modelo) {
        this.modelo = modelo;
    }
    /** Suma puntos al piloto con el DNI indicado. */
    public void agregarPuntaje(String dni, int puntaje) {
        for (Persona persona : modelo.getRegistroGeneral().getPersonas()) {
            if (persona instanceof Piloto p && persona.getDni().equals(dni)) {
                p.agregarPuntosAcumulados(puntaje);
            }
        }
    }

    public boolean verificarPosDuplicadas(ArrayList<Object[]> filas) {
        for (int i = 0; i < filas.size(); i++) {
            Object valor1 = filas.get(i)[3];
            if (!(valor1 instanceof Integer)) continue;
            int pos1 = (Integer) valor1;

            for (int j = i + 1; j < filas.size(); j++) {
                Object valor2 = filas.get(j)[3];
                if (!(valor2 instanceof Integer)) continue;
                int pos2 = (Integer) valor2;

                if (pos1 == pos2) {
                    return true;
                }
                }
            }
            return false;
        }

   public ArrayList<ResultadoCarrera> procesarPilotos(ArrayList<Object[]> filas){
       ArrayList<ResultadoCarrera> resultados = new ArrayList<>(Collections.nCopies(20, null));
        for (Object[] fila : filas) {
           String dni = (String) fila[0];
           String autoModelo = (String) fila[2];
           Object valorPosicion = fila[3];

           // Determinar posición y puntos
           Posicion posicion = null;
           int puntos = 0;

           if (valorPosicion instanceof Integer) {
               int posNum = (Integer) valorPosicion;
               if (posNum >= 1 && posNum <= Posicion.values().length) {
                   posicion = Posicion.values()[posNum - 1];
                   puntos = posicion.getPuntos();
               }
           } else if (valorPosicion instanceof Posicion) {
               posicion = (Posicion) valorPosicion;
               puntos = posicion.getPuntos();
           }

           // Si no se puede determinar posición, saltar la fila
           if (posicion == null) continue;

           Piloto piloto = modelo.buscarPiloto(dni);
           Auto auto = modelo.buscarAuto(autoModelo);

           // Crear relación Auto-Piloto
           int posicionIndex = posicion.ordinal();
           resultados.add(new ResultadoCarrera(posicionIndex, new AutoPiloto(fecha, piloto, auto), puntos));

           // Actualizar estadísticas del piloto
           modelo.getModeloRegistrarResultado().agregarPuntaje(dni, puntos);

           int pos = posicion.ordinal() + 1;
           if (pos == 1) {
               modelo.buscarPiloto(dni).setVictorias(piloto.getVictorias() + 1);
               modelo.buscarPiloto(dni).setPodios(piloto.getPodios() + 1);
           } else if (pos == 2 || pos == 3) {
               modelo.buscarPiloto(dni).setPodios(piloto.getPodios() + 1);
           }
       }
        return resultados;
   }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }
    public LocalDate getFecha() {
        return fecha;
    }
    public Circuito getCircuito() {
        return circuito;
    }
    public void setCircuito(Circuito circuito) {
        this.circuito = circuito;
    }
}


