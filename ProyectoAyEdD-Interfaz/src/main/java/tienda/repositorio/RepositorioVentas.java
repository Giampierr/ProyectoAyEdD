package tienda.repositorio;

import tienda.AppContext;
import tienda.base.Venta;
import tienda.interfaces.RepositorioGenerico;
import tienda.modelo.*;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static tienda.Factory.VentaFactory.crearVenta;

public class RepositorioVentas implements RepositorioGenerico<Venta> {
    private ArrayList<Venta> listaVentaDirecta = new ArrayList<>();
    private HashMap <Integer, Venta> hashVentaDirecta = new HashMap<>();


    public RepositorioVentas() {
        cargarDatosIniciales();
    }

    public void cargarDatosIniciales(){
        Producto p = new Producto("Mouse", TipoCategoria.MOUSE,300,10);
        Item item = new Item(p,1);
        ArrayList<Item> arrayList = new ArrayList<>();
        arrayList.add(item);
        agregar(new VentaDirecta(new Cliente("angelo","7709774","angelo@email.com","967458926"),arrayList));
    }

    @Override
    public String agregar(Venta entidad) {
        listaVentaDirecta.add(entidad);
        hashVentaDirecta.put(entidad.getId(),entidad);

        return "Venta Agregada";
    }

    @Override
    public Venta buscar() {
        return null;
    }

    @Override
    public void eliminar(Venta producto) {

    }

    public String buscarPorId(int id){
        Venta venta = hashVentaDirecta.get(id);

        if (venta != null) {
            return venta.mostrar();
        } else {
            return "Id no encontrado ";
        }
    }


    @Override
    public String listar() {
        StringBuilder miBuilder = new StringBuilder();
        miBuilder.append("--------Lista Venta---------").append("\n");

        for (Venta venta : listaVentaDirecta) {
            miBuilder.append(venta.mostrarResumen()).append("\n");
        }


        return miBuilder.toString();
    }

    public List<Venta> devolverVentas(){
        return listaVentaDirecta;
    }

    public Map<TipoCategoria,Integer> ventasPorCategoria(){
        Map<TipoCategoria,Integer> ventasCategoria = new HashMap<>();
        for (Venta venta : listaVentaDirecta) {
            for (Item item : venta.getListaItems()){
                TipoCategoria categoria = item.getProducto().getTipo();

                ventasCategoria.put(categoria,ventasCategoria.getOrDefault(categoria,0)+ item.getCantidad());
            }
        }
         return ventasCategoria;
    }

    public Map<String,Double> ventasPorDia(){
        Map<String,Double> ventasDia = new HashMap<>();

        for (Venta venta : listaVentaDirecta) {
            DayOfWeek dia = venta.getFecha().toLocalDate().getDayOfWeek();

            String nombreDia = switch (dia){
                case MONDAY -> "Lun";
                case TUESDAY -> "Mar";
                case WEDNESDAY -> "Mie";
                case THURSDAY -> "Jue";
                case FRIDAY -> "Vie";
                case SUNDAY -> "Sab";
                case SATURDAY -> "Dom";
            };



            ventasDia.put(nombreDia,ventasDia.getOrDefault(nombreDia,0.0)+venta.getValorTotal() );
        }
        return ventasDia;
    }
}
