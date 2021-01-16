package datos;

import java.sql.*;
import javax.sql.DataSource;
import org.apache.commons.dbcp2.BasicDataSource;


public class Conexion {
    private static final String URL = "jdbc:mysql://localhost:3306"
            + "/controlClientes?useSSL=false&useTimezone=true"
            + "&userTimezone=UTC&allowPublicKeyRetrieval=true";
    private static final String USER = "root";
    private static final String PASSWORD = "";
    
    /**
     * 
     * @return retorna el basic data source.
     */
    public static DataSource getDataSource(){
        BasicDataSource ds = new BasicDataSource();
        ds.setUrl(URL);
        ds.setUsername(USER);
        ds.setPassword(PASSWORD);
        //jdbc://mysql://localhost:3306/controlClientes/root/
        ds.setInitialSize(30);//simultaneamente
        return ds;
    }
    
  public static Connection getConnection() throws SQLException{
      return getDataSource().getConnection();
  }  
  
          
  /**
   * ResultSet - devuelve resultados de una consulta,
   * como por ejemplo el resultado de un SELECT
   * @param rs - objeto de ResultSet
   */
  public static void close(ResultSet rs){
      try{
          rs.close();
      }catch(SQLException ex){
          System.out.println("No se pueden ejecutar: "+ex);
      }
  }
  /**
   * Recibe la consulta de SQL para prepararla para su
   * ejecución.
   * @param stmt - objeto de PreparedStatement
   */
  public static void close(PreparedStatement stmt){
        try{
          stmt.close();
      }catch(SQLException ex){
          System.out.println("No se pueden ejecutar: "+ex);
      }
  }
  /**
   * Cerrar la conexión a la base de datos
   * @param conn - objeto de Connection
   */
  public static void close(Connection conn){
        try{
          conn.close();
      }catch(SQLException ex){
          System.out.println("No se pueden ejecutar: "+ex);
      }
  }
     
}
