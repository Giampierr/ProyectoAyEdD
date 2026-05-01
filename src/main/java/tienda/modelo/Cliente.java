package tienda.modelo;
import tienda.interfaces.MostrarInformación;

public class Cliente implements MostrarInformación{
    private static int contadorId = 1;
    private int id;
    private String nombre;
    private String dni;
    private String email;
    private String telefono;

    public Cliente( String nombre, String dni, String email, String telefono) {
        this.id = contadorId++;
        this.nombre = nombre;
        this.dni = dni;
        this.email = email;
        this.telefono = telefono;
    }

    public static int getContadorId() {
        return contadorId;
    }

    public static void setContadorId(int contadorId) {
        Cliente.contadorId = contadorId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    @Override
    public String mostrar() {
        return String.format(
                "ID: %d | Nombre: %s | DNI: %s | Email: %s | Teléfono: %s",
                id, nombre, dni, email, telefono);    }
}
