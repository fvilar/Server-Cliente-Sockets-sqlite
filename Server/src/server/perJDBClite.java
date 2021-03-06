
package server;
import java.sql.*;
import java.util.LinkedList;

public class perJDBClite{
   private static String nameBD="baseZ.db";

   public static void main( String args[] ) {          
   }


public static void conectarBD() {
      Connection c = null;
      
      try {
         Class.forName("org.sqlite.JDBC");
         c = DriverManager.getConnection("jdbc:sqlite:"+nameBD);
      } catch ( Exception e ) {
         System.err.println( e.getClass().getName() + ": " + e.getMessage() );
         System.exit(0);
      }
      System.out.println("Opened database successfully-1");
   }
    

public static void crearTABLA() {
      Connection c = null;
      Statement stmt = null;
      
      try {
         Class.forName("org.sqlite.JDBC");
         c = DriverManager.getConnection("jdbc:sqlite:"+nameBD);
         System.out.println("Opened database successfully-2");

         stmt = c.createStatement();
         String sql = "CREATE TABLE PERSONA " +
                        "(p_id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
                        " p_nombre       TEXT    NOT NULL,"
                        +"p_apellido TEXT NOT NULL,"
                        + "p_cedula TEXT NOT NULL,"
                        + "p_edad NUMBER NOT NULL)"; 
         stmt.executeUpdate(sql);
         stmt.close();
         c.close();
      } catch ( Exception e ) {
         System.err.println( e.getClass().getName() + ": " + e.getMessage() );
         System.exit(0);
      }
      System.out.println("Table created successfully");
   }

public static Boolean insertarBD(String nombre,String apellido,String cedula,int edad) {
      Connection c = null;
      Statement stmt = null;
      Boolean res = false;
      try {
         Class.forName("org.sqlite.JDBC");
         c = DriverManager.getConnection("jdbc:sqlite:"+nameBD);
         c.setAutoCommit(false);
         System.out.println("Opened database successfully-3");

         stmt = c.createStatement();
         String sql = "INSERT INTO PERSONA (p_nombre,p_apellido,p_cedula,p_edad) VALUES('"+nombre+"','"+apellido+"','"+cedula+"','"+edad+"')";          
         stmt.executeUpdate(sql);
         
         stmt.close();
         c.commit();
         c.close();
         res = true;

      } catch ( Exception e ) {
         System.err.println( e.getClass().getName() + ": " + e.getMessage() );
         System.exit(0);
      }
      System.out.println("Records created successfully");
      return res;
   }

  

public static String consultarBD() {

   Connection c = null;
   Statement stmt = null;
   String res = "";       
   try {
      Class.forName("org.sqlite.JDBC");
      c = DriverManager.getConnection("jdbc:sqlite:"+nameBD);
      c.setAutoCommit(false);
      System.out.println("Opened database successfully-4");

      stmt = c.createStatement();
      ResultSet rs = stmt.executeQuery( "SELECT * FROM PERSONA;" );
      
      while ( rs.next() ) {
         res += String.valueOf(rs.getInt("p_id"))+",";
         res += rs.getInt("p_id")+",";         
         res += rs.getString("p_nombre")+",";         
         res += rs.getString("p_apellido")+",";
         res += rs.getString("p_cedula")+",";
         res += rs.getInt("p_edad");         
         
         res +="-";                          
      }            
      rs.close();
      stmt.close();
      c.close();
   } catch ( Exception e ) {
      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
      System.exit(0);
   }
   System.out.println("Operation done successfully");
   return res;
  }   
    public static Boolean delete(int id) {
          Connection c = null;
          Statement stmt = null;
          Boolean res = false;
          try {
             Class.forName("org.sqlite.JDBC");
             c = DriverManager.getConnection("jdbc:sqlite:"+nameBD);
             c.setAutoCommit(false);
             System.out.println("Opened database successfully-3");

             stmt = c.createStatement();
             String sql = "DELETE FROM PERSONA WHERE PERSONA.p_id='"+id+"'";              
             stmt.executeUpdate(sql);

             stmt.close();
             c.commit();
             c.close();
             res = true;

          } catch ( Exception e ) {
             System.err.println( e.getClass().getName() + ": " + e.getMessage() );
             System.exit(0);
          }
          System.out.println("Records deleted successfully");
          return res;
    }

    public static Boolean update(int id,String nombre,String apellido,String cedula,int edad) {
      Connection c = null;
      Statement stmt = null;
      Boolean res = false;
      try {
         Class.forName("org.sqlite.JDBC");
         c = DriverManager.getConnection("jdbc:sqlite:"+nameBD);
         c.setAutoCommit(false);
         System.out.println("Opened database successfully-3");

         stmt = c.createStatement();
         String sql = "UPDATE PERSONA SET p_nombre='"+nombre+"',p_apellido='"+apellido+"',p_cedula='"+cedula+"' ,p_edad= '"+edad+"' where p_id='"+id+"'";          
         stmt.executeUpdate(sql);
         
         stmt.close();
         c.commit();
         c.close();
         res = true;

      } catch ( Exception e ) {
         System.err.println( e.getClass().getName() + ": " + e.getMessage() );
         System.exit(0);
      }
      System.out.println("Records updated successfully");
      return res;
   }

    public static String consultarUno(int id) {

   Connection c = null;
   Statement stmt = null;
   String res = "";       
   try {
      Class.forName("org.sqlite.JDBC");
      c = DriverManager.getConnection("jdbc:sqlite:"+nameBD);
      c.setAutoCommit(false);
      System.out.println("Opened database successfully-4");

      stmt = c.createStatement();
      ResultSet rs = stmt.executeQuery( "SELECT * FROM PERSONA WHERE p_id='"+id+"';" );
      
      while ( rs.next() ) {         
         res += rs.getInt("p_id")+",";         
         res += rs.getString("p_nombre")+",";         
         res += rs.getString("p_apellido")+",";
         res += rs.getString("p_cedula")+",";
         res += rs.getInt("p_edad");                           
      }            
      rs.close();
      stmt.close();
      c.close();
   } catch ( Exception e ) {
      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
      System.exit(0);
   }
   System.out.println("Operation done successfully");
   return res;
  }

}



