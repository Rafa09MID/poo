import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;

public class Factura extends Servicio {
    private static ArrayList<Cliente> ListCliente = Cliente.cargarDatos();
    private static final String ARCHIVO_FACTURAS = "facturas.csv";
    private static final String ARCHIVO_CLIENTES = "clientes.csv";
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
        File file = new File(ARCHIVO_FACTURAS);
        boolean existe = file.exists();


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

    
    
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file,true))) {
            if(!existe) {
                bw.write("| Cliente | Numero de cliente | Fecha de emisión | descripción del servicio | importe | iva | total ");
                bw.newLine();      
            }

            bw.write();
            bw.newLine();

        } catch (IOException e) {
            System.err.println("Error" + e.getMessage());
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

    public static void cargarFacturas() {

    }

    public String toCsvLine(Cliente cliente) {
        return cliente + "";
    }
    
    @Override
    public double calcularTotal(double importe) {
        double iva = importe * 0.16;

        return iva; //Provicional
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
