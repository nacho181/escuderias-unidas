package entidades;

import java.time.LocalDate;
import java.util.ArrayList;


public class RegistroCarrera {
   private Carrera carrera;
   private ArrayList<AutoPiloto> posiciones;
   private AutoPiloto [] podio;
   private LocalDate fecha;
   public RegistroCarrera() {
      posiciones = new ArrayList<>();
      for (int i = 0; i < 20; i++) {
         posiciones.add(null);
      }
      podio = new AutoPiloto[3];
      fecha = null;
   }

   public AutoPiloto[] getPodio() {
      return podio;
   }
   public Piloto conultarPiloto(String dni) {
      for(AutoPiloto p : posiciones) {
         if(dni.equals(p.getPiloto().getDni())) {
            return p.getPiloto();
         }
      }
      return null;
   }
   public int consultarPodioPiloto(String dni){
     for(AutoPiloto p : posiciones){
        if(dni.equals(p.getPiloto().getDni())){
           return posiciones.indexOf(p);
        }
     }
     return -1;
   }
   public void agregarCarrera(Carrera c) {
      carrera = c;
   }
   public void agregarPodio( int posicion, AutoPiloto p) {
      podio[posicion] = p;
   }

   public void setFecha(LocalDate fecha) {
      this.fecha = fecha;
   }

   public LocalDate getFecha() {
      return fecha;
   }

   public ArrayList<AutoPiloto> getPosiciones() {
      return posiciones;
   }

   public void agregarAutoPiloto(int posicion, AutoPiloto p ) {
      posiciones.add(posicion, p);
   }
   public void agregarAutoPiloto(AutoPiloto p) {
      posiciones.add(p);
   }
   public Carrera getCarrera() {
      return carrera;
   }

   @Override
   public String toString() {
      return "=== REGISTRO DE CARRERA ===\n" +
      """
      Fecha de realización :\s""" + carrera.getFechaRealizacion() + """
      Número de vueltas     :\s""" + carrera.getNumeroVueltas() + """
      Hora de realización   :\s""" + carrera.getHoraRealizacion() + """
      Circuito              :\s""" + carrera.getCircuito().getNombre() + """
      Total carreras corridas:\s""" + carrera.getTotalCarrerasCorridas() +
      "Posiciones  : " + posiciones + "\n";
   }

}
