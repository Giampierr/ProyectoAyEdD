package tienda.servicios;
import tienda.interfaces.Actualizar;
import tienda.interfaces.AlertarBajoStock;
import tienda.modelo.Producto;
import tienda.repositorio.ProductosRepositorio ;

import java.util.ArrayList;
import java.util.List;

public class StockServicio implements Actualizar, AlertarBajoStock {
    private ProductosRepositorio repo;

    public StockServicio(ProductosRepositorio repo) {
        this.repo = repo;
    }

    @Override
    public boolean actualizarStock(int miStockNuevo, String miProducto) {
        for (Producto producto:repo.listar()){
            if (producto.getNombre().equalsIgnoreCase(miProducto)){
                producto.setStock((producto.getStock()+miStockNuevo));
                return true;
            }
        }
        return false;
    }

    @Override
    public String alertar() {


        StringBuilder miBuilder = new StringBuilder();
        for (Producto producto:repo.listar()){
            if (producto.getStock() <10){
                miBuilder.append("¡¡¡ Stock bajo -> ")
                        .append(producto.getNombre())
                        .append(" | Stock: ")
                        .append(producto.getStock())
                        .append("\n");
            }
        }
        return miBuilder.toString();
    }

    public List<Producto> devolverAlerta(){
        List<Producto> productoConBajoStock = new ArrayList<>();

        for (Producto forProducto : repo.listar()){
            if (forProducto.getStock() < 10){
                productoConBajoStock.add(forProducto);
            }
        }

        return productoConBajoStock;
    }
}
