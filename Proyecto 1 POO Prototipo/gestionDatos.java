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
    private static final String ARCHIVO_CLIENTE = "clientes.txt";
    
    public gestionDatos() {
    }

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

            bw.write(cliente.toCsvLine());
            bw.newLine();

        } catch (IOException e) {
            System.err.println("Error" + e.getMessage());
        }
        System.out.println("Cliente registrado preciona ENTER para continuar");
        scanner.nextLine();

    }


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
    
}
