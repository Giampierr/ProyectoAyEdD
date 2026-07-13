package tienda.interfaces;

public interface RepositorioGenerico <T>{
    String agregar(T entidad);
    void eliminar(T producto);
    T buscar();
    String listar();

}
