import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class gestionDatos {
    private static ArrayList<Cliente> ListCliente = gestionDatos.cargarDatos();
    private static final String ARCHIVO_CLIENTE = "clientes.txt";
    private static final String ARCHIVO_FACTURAS = "facturas.txt";
    
    //Constructor vacio por que no necesitamos instanciar por que todos los métodos son estaticos
    public gestionDatos() {
    }

    //Gestion de datos de Cliente

    //Guadardo de cleintes(No se pidio pero lo agregamos para mas dinamismo)
    public static void guardarCliente(Scanner scanner, List<Cliente> clientes) {
        File file = new File(ARCHIVO_CLIENTE);
        boolean existe = file.exists();

        Cliente cliente = new Cliente();

            System.out.println("Ingrese el nombre del cliente: ");
            cliente.setNombreCliente(scanner.nextLine());
            
            cliente.setNumCliente(Cliente.obtenerNumCliente());
            System.out.println("Numero de cliente designado " + cliente.getNumCliente());

            System.out.println("Ingrese Fecha de alta");
            cliente.setFechaAlta(scanner.nextLine());

        clientes.add(cliente);
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file,true))) {
            if(!existe) {
                bw.write("Numero de cliente,Cliente,Fecha de alta");
                bw.newLine();  
            }

            bw.write(cliente.toTxtLine());
            bw.newLine();

        } catch (IOException e) {
            System.err.println("Error" + e.getMessage());
        }
        System.out.println("Cliente registrado preciona ENTER para continuar");
        scanner.nextLine();

    }


    //Cargado de datos de cliente esto realmente solo carga los datos y los devuelve como una lista,
    //En la clase cliente esta para listar
    public static ArrayList<Cliente> cargarDatos() {
        ArrayList<Cliente> clientes = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(ARCHIVO_CLIENTE))) {
            String line = br.readLine();

            while((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if(parts.length == 3) {
                    int id = Integer.parseInt(parts[0]);
                    String nombre = parts[1];
                    String fechaAlta = parts[2];

                    clientes.add(new Cliente(id, nombre, fechaAlta));

                    
                }
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return clientes;


    }

    // Gestion de datos de factura
    
    //Guardado de facturas
    public static void guardarFactura(Factura factura) {
        File file = new File(ARCHIVO_FACTURAS);
        boolean existe = file.exists();

        try {
            FileWriter fw = new FileWriter(file, true);
            BufferedWriter bw = new BufferedWriter(fw);

            if (existe == false) {
                bw.write("numFactura,numCliente,fechaEmision,descripcion,importe,iva,total");
                bw.newLine();
            }

            bw.write(factura.getNumFactura() + "," +
                     factura.getCliente().getNumCliente() + "," +
                     factura.getFechaEmision() + "," +
                     factura.descripcion + "," + 
                     factura.importe + "," +     
                     factura.getIva() + "," +
                     factura.getTotal());
            bw.newLine();
            
            bw.close();

            System.out.println("----------------------------------------");
            System.out.println("  ***********  FACTURA     ************  ");
            System.out.println("----------------------------------------");
            System.out.println("Numero de factura: " + factura.getNumFactura());
            System.out.println("Cliente: " + factura.getCliente().getNombreCliente());
            System.out.println("Fecha de Emision: " + factura.getFechaEmision());
            System.out.println("Descripcion: " + factura.descripcion);
            System.out.println("Importe: $" + factura.importe);
            System.out.println("IVA: $" + factura.getIva());
            System.out.println("Total: $" + factura.getTotal());
            System.out.println("----------------------------------------\n");

        } catch (IOException e) {
            System.out.println("Error al escribir el archivo: " + e.getMessage());
        }
    }

     public static ArrayList<Factura> cargarFacturas() {
        ArrayList<Factura> listaFacturas = new ArrayList<>();
        File file = new File(ARCHIVO_FACTURAS);

        if (file.exists() == false) {
            return listaFacturas;
        }

        try {
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            
            String linea;
            br.readLine(); 

            while ((linea = br.readLine()) != null) {
                if (!linea.trim().equals("")) {
                    String[] datos = linea.split(",");
                    
                    int numFact = Integer.parseInt(datos[0].trim());
                    int numCli = Integer.parseInt(datos[1].trim());
                    String fecha = datos[2].trim();
                    String desc = datos[3].trim();
                    double imp = Double.parseDouble(datos[4].trim());
                    double ivaVal = Double.parseDouble(datos[5].trim());
                    double totalVal = Double.parseDouble(datos[6].trim());

                    Cliente clienteAsociado = null;
                    for (Cliente c : ListCliente) {
                        if (c.getNumCliente() == numCli) {
                            clienteAsociado = c;
                            break;
                        }
                    }

                    if (clienteAsociado == null) {
                        clienteAsociado = new Cliente(numCli, "Desconocido", "N/A");
                    }

                    Factura f = new Factura(clienteAsociado, desc, imp, numFact, ivaVal, totalVal);
                    f.setFechaEmision(fecha);
                    f.setCliente(clienteAsociado);
                    
                    listaFacturas.add(f);
                }
            }
            br.close(); 
            
        } catch (IOException e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
        }

        return listaFacturas;
    }
    
}
