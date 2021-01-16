package dominio;

public class Cliente {

    private int idCliente;
    private String nombre;
    private String apellido;
    private String email;
    private String telefono;
    private double saldo;
    
    
    public Cliente(){    
    }

    /**
     * Se crea un constructor únicamente con el idCliente para crear objetos
     * con un ID, se crea principalmente para eliminar a un cliente de la base
     * de datos.
     * @param idCliente 
     */
    public Cliente(int idCliente) {
        this.idCliente = idCliente;
    }

    /**
     * Se crea un constructor con todos los campos, excepto el ID, para
     * crear un objeto y agregarlo a la base de datos, se ignora el ID
     * porque es auto_increment en la base de datos.
     * @param nombre
     * @param apellido
     * @param email
     * @param telefono
     * @param saldo 
     */
    public Cliente(String nombre, String apellido, String email, String telefono, double saldo) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.telefono = telefono;
        this.saldo = saldo;
    }

    /**
     * El constructor con todos los parámetros, se encarga de traer todos los
     * atributos del objeto Cliente para poder actualizar todos los campos en la
     * base de datos.
     * @param idCliente
     * @param nombre
     * @param apellido
     * @param email
     * @param telefono
     * @param saldo 
     */
    public Cliente(int idCliente, String nombre, String apellido, String email, String telefono, double saldo) {
        this.idCliente = idCliente;
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.telefono = telefono;
        this.saldo = saldo;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    @Override
    public String toString() {
        return "Cliente{" + "idCliente=" + idCliente + ", nombre=" + nombre + ", apellido=" + apellido + ", email=" + email + ", telefono=" + telefono + ", saldo=" + saldo + '}';
    }
    
}
