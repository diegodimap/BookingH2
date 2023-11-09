package com.itprofessor.bookingh2.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DataCheck {

    private Statement stmt;
    private ResultSet rs;
    public Connection conn;
    public String resultado = "";

    public DataCheck() {
        try {
            String url = "jdbc:h2:mem:bookings";
            String usr = "sa";
            String pas = "";

            try {
                conn = DriverManager.getConnection(url, usr, pas);
                stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            } catch (Exception e) {
                System.out.println("Error" + e.getMessage());
            }
        } catch (Exception ex) {
            Logger.getLogger(DataCheck.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        }
    }

    public String getOverlappingBookings(String start_date, String end_date, int property_id){
        String sql = "SELECT * FROM booking WHERE start_date >= '"+start_date+"' AND end_date <= '"+end_date+"' AND property_id="+property_id;
        try{
            rs = stmt.executeQuery(sql);
            resultado = "";

            while (rs.next()) {
                resultado += rs.getInt("id");
            }
        }catch(Exception e){
            e.printStackTrace();
        }

        return resultado;
    }

}
