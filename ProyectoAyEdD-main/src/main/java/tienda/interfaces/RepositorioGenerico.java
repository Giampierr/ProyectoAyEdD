package tienda.interfaces;

public interface RepositorioGenerico <T>{
    void agregar(T entidad);
    void eliminar(T producto);
    T buscar();
    String listar();

}
