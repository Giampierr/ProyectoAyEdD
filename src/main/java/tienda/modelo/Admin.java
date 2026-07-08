package tienda.modelo;

public class Admin {
    private String usuario;
    private String password;


    public Admin(String password,String usuario) {
        this.password = password;
        this.usuario = usuario;
    }
    public boolean validarPassword(String password,String usuario) {
        if (password.equals(this.password) && usuario.equals(usuario)) {
            return true;
        }
        return false;
    }
}
