package tienda.base;

import tienda.modelo.Cliente;
import tienda.modelo.Item;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public abstract class Venta {
    private static int contadorId = 1;
    private int id;
    private Cliente cliente;
    private ArrayList<Item> listaItems;
    private DateTimeFormatter fechaFormato;
    private LocalDateTime fecha;
    private double valorTotal;

    public Venta(Cliente cliente, ArrayList<Item> listaItems) {
        this.id = contadorId++;
        this.cliente = cliente;
        this.listaItems = listaItems;
        this.valorTotal = obtenerValorTotal();

        this.fecha = LocalDateTime.now();
        this.fechaFormato = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

    }

    public int getId() {
        return id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public ArrayList<Item> getListaItems() {
        return listaItems;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public double obtenerValorTotal()
    {
        double valorTotal = 0;
        for (Item item : listaItems){
            valorTotal += item.calcularSubtotal();
        }

        return valorTotal;
    }

    public String mostrar(){
        StringBuilder miBuilder = new StringBuilder();

        miBuilder.append("---------Factura---------\n")
                .append("Cliente :").append(cliente.getNombre()).append("\n")
                .append("DNI:").append(cliente.getDni()).append("\n");
        for (Item item : listaItems) {
            miBuilder.append(item.mostrar()).append("\n");
        }

        miBuilder.append("Fecha : ").append(fecha.format(fechaFormato)).append("\n")
                .append("Valor total : ").append(this.valorTotal).append("\n");

        return  miBuilder.toString();
    }

    public String mostrarResumen(){
        StringBuilder miBuilder = new StringBuilder();

        miBuilder.append("--------Resumen Venta---------\n")
                .append("Nro Venta :").append(id).append("\n")
                .append(cliente.getNombre()).append("\n")
                .append("Fecha : ").append(fecha.format(fechaFormato)).append("\n")
                .append("Valor total : ").append(this.valorTotal).append("\n");

        return miBuilder.toString();
    }
}
