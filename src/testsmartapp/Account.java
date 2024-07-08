package testsmartapp;

import java.sql.ResultSet;

public class Account {
    public static String id;
    public  static String  positionid;
    Account(String id){
        this.id = id;
        try {
        ConnectionDB c = new ConnectionDB();
        String query = "SELECT * FROM public.employee WHERE id = '"+id+"'";
        ResultSet rs = c.s.executeQuery(query);
            if (rs.next()){
                this.positionid = rs.getString("positionid");
              
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
