package modelo;

import entidades.*;

public class ModeloRegistroGral {
    private final Modelo modelo;

    public ModeloRegistroGral(Modelo modelo) {
        this.modelo = modelo;
    }

    /** Comprueba si existe un país con el ID indicado. */
    public Pais comprobarPais(int id) {
        for (Pais p : modelo.getRegistroGeneral().getPaises()) {
            if (p.getIdPais() == id) {
                return p;
            }
        }
        return null;
    }

    public void agregarPaisRGral(Pais pais) {
        modelo.getRegistroGeneral().agregarPais(pais);
    }
    public void agregarAutoRGral(Auto auto) {
        modelo.getRegistroGeneral().agregarAuto(auto);
    }
    public void agregarEscuderiaRGral(Escuderia escuderia) {
        modelo.getRegistroGeneral().agregarEscuderia(escuderia);
    }
    public void agregarCircuitoRGral(Circuito circuito) {
        modelo.getRegistroGeneral().agregarCircuito(circuito);
    }
    public void agregarPersonaGral(Persona persona) {
        modelo.getRegistroGeneral().agregarPersona(persona);
    }
}
