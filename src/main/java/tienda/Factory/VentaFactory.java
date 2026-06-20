package tienda.Factory;

import tienda.base.Venta;
import tienda.modelo.Cliente;
import tienda.modelo.Item;
import tienda.modelo.VentaDirecta;
import tienda.modelo.VentaPedido;

import java.util.ArrayList;


public class VentaFactory {
    public static Venta crearVenta(
            TipoVenta tipo,
            Cliente cliente,
            ArrayList<Item> items){
        return switch (tipo){
            case DIRECTA -> new VentaDirecta(cliente, items);
            case PEDIDO -> new VentaPedido(cliente, items);
        };
    }
}
