package tienda.modelo;

public class Admin {
    private int contador = 1;
    private int id;
    private String password;


    public Admin(String password) {
        this.id = contador++;
        this.password = password;
    }
    public boolean validarPassword(String password) {
        if (password.equals(this.password)) {
            return true;
        }
        return false;
    }
}
