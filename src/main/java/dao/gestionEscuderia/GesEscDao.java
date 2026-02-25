package dao.gestionEscuderia;

public interface GesEscDao {
        boolean existPilotoByDniDate(String dni, String fecha, String fecha2);
        boolean existAutoByModelo(String modelo);
        void savePiloto();
        void saveMecanico();
        void saveAuto();
}
