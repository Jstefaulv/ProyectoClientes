package web;

import datos.ClientesDAO;
import dominio.Cliente;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/Controlador")
public class ServletControlador extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest peticion, HttpServletResponse respuesta)
            throws ServletException, IOException {
       String accion = peticion.getParameter("accion");
       if(accion != null){
           switch(accion){
               case "editar":
                   this.editarCliente(peticion, respuesta);
                   break;
               case "eliminar":
                   this.eliminarCliente(peticion, respuesta);
                   break;
                   
               default:
                   this.accionDefault(peticion,respuesta);
           }
       }
       else{
           this.accionDefault(peticion, respuesta);
       }
    }
    
    private void editarCliente(HttpServletRequest peticion, HttpServletResponse respuesta)
        throws ServletException, IOException{
        //recuperar idcliente desde la vista de listadoClientes.jsp
        int idCliente = Integer.parseInt(peticion.getParameter("idCliente"));
        //el cliente se busca por medio del ID
        Cliente cliente = new ClientesDAO().encontrar(new Cliente(idCliente));
        peticion.setAttribute("cliente",cliente);
        String jspEditar = "/WEB-INF/cliente/editarCliente.jsp";
        peticion.getRequestDispatcher(jspEditar).forward(peticion, respuesta);
       
    }
    
    private void eliminarCliente(HttpServletRequest peticion, HttpServletResponse respuesta)
        throws ServletException, IOException{
        int idCliente = Integer.parseInt(peticion.getParameter("idCliente"));
        
        //creamos un objeto de cliente utilizando el ID
        Cliente cliente = new Cliente(idCliente);
        
        //Eliminar el registro de la bdd
        int registrosModificados = new ClientesDAO().eliminar(cliente);
        System.out.println("registros: "+registrosModificados);
        //redirigir la pagina para listar los clientes
        this.accionDefault(peticion, respuesta);
    }
    
    private void modificarCliente(HttpServletRequest peticion, HttpServletResponse respuesta)
        throws ServletException, IOException{
       //recuperar idcliente desde la vista de listadoClientes.jsp
        int idCliente = Integer.parseInt(peticion.getParameter("idCliente"));
        String nombre = peticion.getParameter("nombre");
        String apellido = peticion.getParameter("apellido");
        String email = peticion.getParameter("email");
        String telefono = peticion.getParameter("telefono");
        double saldo = 0;
        String saldoStr = peticion.getParameter("saldo");
         if(saldoStr != null && !saldoStr.equals("")){
            //saldoStr.equals("") == "".equals(saldoStr)
            saldo = Double.parseDouble(saldoStr);
        }
         //crear el objeto del modelo cliente
         Cliente cliente = new Cliente(idCliente,nombre,apellido,email,telefono,saldo);
         int registrosModificados = new ClientesDAO().actualizar(cliente);
         System.out.println("registros :"+registrosModificados);
         
         this.accionDefault(peticion, respuesta);
    }
    
    
    private void accionDefault (HttpServletRequest peticion, HttpServletResponse respuesta)
        throws ServletException, IOException{
        List <Cliente> listadoClientes = new ClientesDAO().listar();
        System.out.println("clientes: "+listadoClientes);
        
       //scope - limites - alcances
       HttpSession sesion = peticion.getSession();
       // el string de clientes: es la variable con la que puedo identificarlo
       //en un JSP - osea, en la parte visual
       //y el otro parametro es un objeto de java, que dice
       //que la variable String clientes es lo mismo que listadoClientes.
       //el jsp tiene un elemento clientes y este se comunica con ClientesDAO y le dice
       //que debe obtener un listado de clientes.
       sesion.setAttribute("clientes", listadoClientes);
       
       //peticion.getRequestDispatcher("clientes.jsp").forward(peticion, respuesta);
       respuesta.sendRedirect("clientes.jsp");
        
    }

    @Override
    protected void doPost(HttpServletRequest peticion, HttpServletResponse respuesta)
            throws ServletException, IOException {
       String accion = peticion.getParameter("accion");
       if(accion != null){ 
           switch(accion){
               case "insertar":
                   this.insertarCliente(peticion,respuesta);
                   break;
               case "modificar":
                   this.modificarCliente(peticion, respuesta);
                   break;
                   
               default:
                   this.accionDefault(peticion,respuesta);
           }
       }
       else{
           this.accionDefault(peticion, respuesta);
       }
    }
    
    private void insertarCliente(HttpServletRequest peticion, HttpServletResponse respuesta)
            throws ServletException, IOException { 
    //recuperar la información desde el formulario de agregarCliente.jsp
        String nombre = peticion.getParameter("nombre");
        String apellido = peticion.getParameter("apellido");
        String email = peticion.getParameter("email");
        String telefono = peticion.getParameter("telefono");
        double saldo = 0;
        String saldoStr = peticion.getParameter("saldo");
        if(saldoStr != null && !saldoStr.equals("")){
            //saldoStr.equals("") == "".equals(saldoStr)
            saldo = Double.parseDouble(saldoStr);
        }
        //Cramos un objeto de tipo cliente (modelo)
        Cliente cliente = new Cliente(nombre,apellido,email,telefono,saldo);
        
        //insertar el objeto de cliente en la base de datos
        int registrosModificados = new ClientesDAO().insertar(cliente);
        System.out.println("Registros modificados = "+registrosModificados);
        
        //redirigimos nuevamente a la pantalla principal para que muestre el nuevo cliente en pantalla
        this.accionDefault(peticion, respuesta);
        
    }
}
