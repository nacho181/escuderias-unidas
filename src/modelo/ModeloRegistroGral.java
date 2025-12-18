package modelo;

import entidades.*;

public class ModeloRegistroGral {
    private Registro registroGeneral;
    public ModeloRegistroGral(Registro registroGeneral) {
        this.registroGeneral = registroGeneral;
    }

    public void agregarPaisRGral(Pais pais) {
        registroGeneral.agregarPais(pais);
    }
    public void agregarAutoRGral(Auto auto) {
        registroGeneral.agregarAuto(auto);
    }
    public void agregarEscuderiaRGral(Escuderia escuderia) {
        registroGeneral.agregarEscuderia(escuderia);
    }
    public void agregarCircuitoRGral(Circuito circuito) {
        registroGeneral.agregarCircuito(circuito);
    }
    public void agregarPilotoRGral(Piloto piloto) {
        registroGeneral.agregarPiloto(piloto);
    }
    public void agregarMecanicoRGral(Mecanico mecanico) {
        registroGeneral.agregarMecanico(mecanico);
    }

}
