import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
//no se

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

    


    public static void mostrarClienteRegistrados(List<Cliente> clientes) {
        System.out.println("| Número de cliente | Nombre Cliente | Fecha de alta");
        System.out.println("-----------------------------------------------------");

        for(Cliente cliente : clientes) {
            System.out.printf(" %d, %s, %s \n",cliente.getNumCliente(), cliente.getNombreCliente(), cliente.getFechaAlta());
        }
    }

    public static int obtenerNumCliente() {
        File file = new File(ARCHIVO_CLIENTE);
        if(!file.exists()) {
            return 1;
        }

        int ultimoNumero = 0;
        try(BufferedReader br = new BufferedReader(new FileReader(ARCHIVO_CLIENTE))) {
            String line;
            boolean primero = true;

            while((line = br.readLine()) != null) {
                if(primero) {
                    primero = false;
                    continue;
                }
                
                if(!line.trim().isBlank()) {
                    String[] parts = line.split(",");
                    
                    if(parts.length >= 1) {
                        try {
                            ultimoNumero = Integer.parseInt(parts[0]);
                        } catch (NumberFormatException e) {
                            System.out.println(e.getMessage());
                        }
                    }
                }
            }

        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
        return ultimoNumero + 1;
    }

    public String toCsvLine() {
        return numCliente + "," + nombreCliente + "," + fechaAlta;
    }

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
