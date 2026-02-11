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

    public boolean comprobarAutoId(String modeloAuto) {
        for (Auto a : modelo.getRegistroGeneral().getAutos()) {
            if (modeloAuto.equals(a.getModelo())) {
                return true;
            }
        }
        return false;
    }

    public Escuderia comprobarEscuderia(String nombre) {
        for (Escuderia e : modelo.getRegistroGeneral().getEscuderias()) {
            if (nombre.equals(e.getNombre())) {
                return e;
            }
        }
        return null;
    }

    public boolean comprobarCircuito(String nombre) {
        for (Circuito c : modelo.getRegistroGeneral().getCircuitos()) {
            if (nombre.equals(c.getNombre())) {
                return true;
            }
        }
        return false;
    }

    public boolean comprobarPersonaDni(String dni) {
        for (Persona p : modelo.getRegistroGeneral().getPersonas()) {
            if (dni.equals(p.getDni())) {
                return true;
            }
        }
        return false;
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
