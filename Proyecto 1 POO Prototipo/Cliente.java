import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Cliente {
    private static final String ARCHIVO_CLIENTE = "clientes.txt";


    private int numCliente;
    private String nombreCliente;
    private String fechaAlta;

    public Cliente() {
        this.numCliente = 0;
        this.nombreCliente = null;
        this.fechaAlta = null;
    }

    public Cliente(int numCliente, String nombreCliente, String fechaAlta) {
        this.numCliente = numCliente;
        this.nombreCliente = nombreCliente;
        this.fechaAlta = fechaAlta;
    }

    public static void guardarCliente(Scanner Scanner) {
        File file = new File(ARCHIVO_CLIENTE);
        boolean existe = file.exists();

        Cliente cliente = new Cliente();
        while (true) {
            
            Scanner.nextLine();
            System.out.println("Ingrese el nombre del cliente: ");
            cliente.setNombreCliente(Scanner.nextLine());

            System.out.println("Ingrese num");
            cliente.setNumCliente(Scanner.nextInt());
            Scanner.nextLine();

            System.out.println("Ingrese Fecha de alta");
            cliente.setFechaAlta(Scanner.nextLine());
            break;
        }
        

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file,true))) {
            if(!existe) {
                bw.write("| Cliente | Numero de cliente | Fecha de alta");
                bw.newLine();   
            }

            bw.write(cliente.toCsvLine());
            bw.newLine();

        } catch (IOException e) {
            System.err.println("Error" + e.getMessage());
        }

    }

    public String toCsvLine() {
        return "Cliente: " + nombreCliente + " Numero de cliente: " + numCliente + " Fecha de alta: " + fechaAlta;
    }

    /*public Cliente tomarDatos(String nombreCliente, int numCliente, String fechaAlta) {

    }*/

    public int getNumCliente() {
        return numCliente;
    }

    public void setNumCliente(int numCliente) {
        this.numCliente = numCliente;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    public String getFechaAlta() {
        return fechaAlta;
    }

    public void setFechaAlta(String fechaAlta) {
        this.fechaAlta = fechaAlta;
    }

    
    
}
