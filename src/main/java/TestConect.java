import infraestructura.DBConnection;

import java.sql.Connection;

public class TestConect {

    public static void main(String[] args) {

        try {
            Connection conn = DBConnection.getConnection();
            System.out.println("Conexion exitosa");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}

