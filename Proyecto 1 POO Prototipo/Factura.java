import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;

public class Factura extends Servicio {
    private static ArrayList<Cliente> ListCliente = gestionDatos.cargarDatos();
    private static final String ARCHIVO_CLIENTES = "clientes.txt";
    private int numFactura;
    private double iva;
    private double total;
    //Agregué estas 2 variables
    private String fechaEmision;
    private Cliente cliente; 

    public Factura(Cliente cliente, String descripcion, double importe, int numFactura, double iva, double total) {
        super(descripcion,importe);
        this.numFactura = numFactura;
        this.iva = iva;
        this.total = total; 
    }
    
    public static void crearFactura(Scanner scanner) {
        Factura factura;
        Cliente cliente;
        int opcion;
        
        while (true) {
            System.out.println("1) Mostrar usuarios registrados \n2) Crear la factura \n3) Salir");
            opcion = scanner.nextInt();
            scanner.nextLine(); // agregue para la limpieza del enter

            switch (opcion) {
                case 1:
                    Cliente.mostrarClienteRegistrados(ListCliente);
                    break;
                
                case 2:
                    System.out.println("ingresa el numero de cliente para crear la factura");
                    cliente = buscarNum(scanner.nextInt());
                    scanner.nextLine(); // igual para limpiar "Enter" 

                    // hice una validación para saber si el cliente existe
                    if (cliente.getNombreCliente() == null) {
                        System.out.println("Error: El cliente no existe. Por favor, registralo.");
                        break;
                    }

                    // Para crear la factura
                    
                    System.out.println("Ingresa el número de la nueva factura: ");
                    int nFactura = scanner.nextInt();
                    scanner.nextLine(); // Limpia el "Enter" del número de factura **

                    System.out.println("Ingresa la fecha de emisión (DD/MM/AAAA): ");
                    String fecha = scanner.nextLine();

                    System.err.println("Ingresa la descripción: ");
                    String descripcion = scanner.nextLine();

                    System.out.println("Ingresa el importe: ");
                    double importe = scanner.nextDouble();
                    scanner.nextLine(); // Limpia el "Enter" final del importe **

                    // en este caso, se crea una instancia provisional para usar los métodos de cálculo heredados de Servicio
                    Factura calc = new Factura(cliente, descripcion, importe, nFactura, 0, 0);
                    double ivaCalc = calc.calcularIva(importe);
                    double totalCalc = calc.calcularTotal(importe, ivaCalc);

                    // inicializa el objeto factura real con todos los datos correctos
                    factura = new Factura(cliente, descripcion, importe, nFactura, ivaCalc, totalCalc);
                    factura.setFechaEmision(fecha);
                    factura.setCliente(cliente);

                    // aquí se guarda la factura en el txt
                    gestionDatos.guardarFactura(factura);
                    break;

                case 3:
                    return; // y esto para salir del bucle while
                default:
                    System.out.println("Opción no válida.");
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
                    
                    try {
                        // convertir el texto a entero para poder comparar números reales
                        int idArchivo = Integer.parseInt(parts[0].trim());
                        
                        if(idArchivo == numero) {
                            if (parts.length >= 3) { 
                                cliente.setNumCliente(idArchivo);
                                cliente.setNombreCliente(parts[1].trim());
                                cliente.setFechaAlta(parts[2].trim());
                                break; // Cuando encudentra al cliente, sale del ciclo
                            }
                        }
                    } catch (NumberFormatException e) {

                    }
                }
            }

        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
        return cliente;
    }


    public static void filtrarFacturas(Scanner scanner) {
        System.out.println("Ingrese el numero de cliente para buscar sus facturas:");
        int idBuscar = scanner.nextInt();
        scanner.nextLine(); 

        ArrayList<Factura> todasLasFacturas = gestionDatos.cargarFacturas();
        System.out.println("\n--- FACTURAS ENCONTRADAS DEL CLIENTE " + idBuscar + " ---");
        boolean bandera = false;

        for (Factura f : todasLasFacturas) {
            if (f.getCliente().getNumCliente() == idBuscar) {
                System.out.println("Factura #" + f.getNumFactura() + " | Fecha: " + f.getFechaEmision() + " | Concepto: " + f.descripcion + " | Total: $" + f.getTotal());
                bandera = true;
            }
        }

        if (bandera == false) {
            System.out.println("No se encontraron facturas registradas para este cliente.");
        }
        
    }
   
    @Override
    public double calcularTotal(double importe, double iva) {
        // removí el código dejando una sola expresión limpia (sugerencia)
        this.total = importe + iva; 
        return this.total; 
    }

    @Override
    public double calcularIva(double importe) {
        // cambio de código (sugerencia)
        this.iva = importe * 0.16;
        return this.iva; 
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

    public String getFechaEmision() {
        return fechaEmision;
    }

    // cambie 
    public void setFechaEmision(String fechaEmision) {
        this.fechaEmision = fechaEmision;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    } 
} 