import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;

public class Factura extends Servicio {
    private static ArrayList<Cliente> ListCliente = gestionDatos.cargarDatos()

    private static final String ARCHIVO_FACTURAS = "facturas.txt";
    private static final String ARCHIVO_CLIENTES = "clientes.txt";
    private int numFactura;
    private double iva;
    private double total;

    public Factura(Cliente cliente, String descripcion, double importe, int numFactura, double iva, double total) {
        super(descripcion,importe);
        this.numFactura = numFactura;
        this.iva = iva;
        this.total = total; 

    }
    
    public static void crearFactura(Scanner scanner) {
        Factura factura;
        Cliente cliente;
        int numElegido = 0;
        int opcion;
        
        while (true) {
            System.out.println("1) Mostrar usuarios registrados \n2) Crear la factura");
            opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    Cliente.mostrarClienteRegistrados(ListCliente);
                    break;
                
                case 2:
                    System.out.println("ingresa el numero de cliente para crear la factura");
                    cliente = buscarNum(scanner.nextInt());
                    System.err.println("Ingresa la descripción: ");
                    String descripcion = scanner.nextLine();
                    System.out.println("Ingresa el importe: ");
                    double importe = scanner.nextDouble();

                default:
                    break;
            }
        }   

    }

    public static Cliente buscarNum(int numero) {
        Cliente cliente = new Cliente();


        try(BufferedReader br = new BufferedReader(new FileReader(ARCHIVO_CLIENTES))) {
            String line;
            boolean primero = true;

            while((line = br.readLine()) != null) {
                if(primero) {
                    primero = false;
                    continue;
                }

                if(!line.trim().isBlank()) {
                    String[] parts = line.split(",");
                    if(parts[0].equals(numero)) {
                        if (parts.length >= 1) { 
                            try { 
                            cliente.setNumCliente(Integer.parseInt(parts[0]));
                            } catch (NumberFormatException e) {
                                System.out.println(e.getMessage());
                            }
                            cliente.setNombreCliente(parts[1]);
                            cliente.setFechaAlta(parts[2]);
                        }
                    }
                }
            }

        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
        return cliente;
      
    }

    public String toTxtLine(Cliente cliente) {
        return cliente + "";
    }
    
    @Override
    public double calcularTotal(double importe, double iva) {

        return total += importe + iva; //Provicional
    }

    @Override
    public double calcularIva(double importe) {
        return iva = importe * 0.16;
    }

    public int getNumFactura() {
        return numFactura;
    }

    public void setNumFactura(int numFactura) {
        this.numFactura = numFactura;
    }

    public double getIva() {
        return iva;
    }

    public void setIva(double iva) {
        this.iva = iva;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

}
