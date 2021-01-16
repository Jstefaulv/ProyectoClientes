package datos;

import dominio.Cliente;
import java.sql.*;
import java.util.*;

public class ClientesDAO {

    //consultas a bdd
    //Select para traer todos los campos de la bdd
    //atributos de tipo final = constantes
    private static final String SELECT = "SELECT id_cliente,nombre,"
            + "apellido,email,telefono,saldo "
            + "FROM cliente";

    //select para traer todos los campos de la bdd, pero
    //por un ID especifico
    private static final String SELECTBYID = "SELECT id_cliente,nombre,"
            + "apellido,email,telefono,saldo "
            + "FROM cliente WHERE id_cliente=?";

    //consulta para insertar datos
    private static final String INSERT = "INSERT INTO cliente"
            + "(nombre,apellido,email,telefono,saldo) "
            + "VALUES (?,?,?,?,?)";

    //consulta para Actualizar Datos
    private static final String UPDATE = "UPDATE cliente "
            + "SET nombre=?, apellido=?,email=?,telefono=?,"
            + "saldo=? WHERE id_cliente=?";

    //consulta para Eliminar Clientes
    private static final String DELETE = "DELETE FROM cliente "
            + "WHERE id_cliente=?";

    /**
     *
     * @return
     */
    public List<Cliente> listar() {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Cliente cliente = null;
        List<Cliente> listadoClientes = new ArrayList<>();

        try {
            //Establecer conexión con la bdd 
            conn = Conexion.getConnection();
            //preparar la consulta a ejecutar
            stmt = conn.prepareStatement(SELECT);

            //obtener un resultado a partir de la consulta
            rs = stmt.executeQuery();

            //especificar de qué forma traer los resultados
            while (rs.next()) {
                int idCliente = rs.getInt("id_cliente");
                String nombre = rs.getString("nombre");
                String apellido = rs.getString("apellido");
                String email = rs.getString("email");
                String telefono = rs.getString("telefono");
                double saldo = rs.getDouble("saldo");

                cliente = new Cliente(idCliente, nombre, apellido,
                        email, telefono, saldo);

                listadoClientes.add(cliente);
            }

        } catch (SQLException ex) {
            System.out.println("error: " + ex);
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return listadoClientes;
    }

    public Cliente encontrar(Cliente cliente) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SELECTBYID);
            stmt.setInt(1, cliente.getIdCliente());
            rs = stmt.executeQuery();
            //focus sobre una columna, en este caso
            //la columna de id_cliente
            rs.absolute(1);

            String nombre = rs.getString("nombre");
            String apellido = rs.getString("apellido");
            String email = rs.getString("email");
            String telefono = rs.getString("telefono");
            double saldo = rs.getDouble("saldo");
            
            cliente.setNombre(nombre);
            cliente.setApellido(apellido);
            cliente.setEmail(email);
            cliente.setTelefono(telefono);
            cliente.setSaldo(saldo);

        } catch (SQLException ex) {
            System.out.println("Error: " + ex);
        }finally{
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return cliente;
    }
    
    public int insertar(Cliente cliente){
        Connection conn = null;
        PreparedStatement stmt = null;
        int row = 0;
        
        try{
            
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(INSERT);
            stmt.setString(1, cliente.getNombre());
            stmt.setString(2,cliente.getApellido());
            stmt.setString(3,cliente.getEmail());
            stmt.setString(4,cliente.getTelefono());
            stmt.setDouble(5,cliente.getSaldo());
            
            row = stmt.executeUpdate();
            //1 si es que insertó
            //0 si no insertó
            
        }catch(SQLException ex){
            System.out.println("Error: "+ex);
        }finally{
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return row;    
    }
    
    public int actualizar(Cliente cliente){
        Connection conn = null;
        PreparedStatement stmt = null;
        int row = 0;
        
        try{
            
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(UPDATE);
            stmt.setString(1, cliente.getNombre());
            stmt.setString(2,cliente.getApellido());
            stmt.setString(3,cliente.getEmail());
            stmt.setString(4,cliente.getTelefono());
            stmt.setDouble(5,cliente.getSaldo());
            stmt.setInt(6, cliente.getIdCliente());
            
            row = stmt.executeUpdate();
            //1 si es que insertó
            //0 si no insertó
            
        }catch(SQLException ex){
            System.out.println("Error: "+ex);
        }finally{
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return row; 
    }
    
    
    public int eliminar(Cliente cliente){
        Connection conn = null;
        PreparedStatement stmt = null;
        int row = 0;
        //si todo sale bien - row=1
        //si no resulta nada - row = 0
        
        try{
            /*
                Objeto de conn - lo que hace es lo siguiente:
                1. Obtener la ruta y todos los datos de donde
                debe sacar la info. (en terminos de casa, tener la llave,
            la direccion).
            
            y el Conexion.getConnection(); -
            me dice que utilizando los datos definidos en mi clase Conexion,
            getConnection puede acceder a esa bdd.
            */
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(DELETE);
            stmt.setInt(1, cliente.getIdCliente());
            
            row = stmt.executeUpdate(); 
            
            /*
            -- antes
            "DELETE FROM cliente 
            WHERE id_cliente="+cliente.getIdCliente();
            -- ahora
            "DELETE FROM cliente 
            WHERE id_cliente=?";
            */ 
        }catch(SQLException ex){
            System.out.println("Error: "+ex);
        }finally{
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return row;  
    }
    
}
