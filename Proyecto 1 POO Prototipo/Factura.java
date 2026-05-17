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
    private int numFactura;
    private double iva;
    private double total;

    public Factura(String descripcion, double importe, int numFactura, double iva, double total) {
        super(descripcion,importe);
        this.numFactura = numFactura;
        this.iva = iva;
        this.total = total; 

    }
    
    public static void guardarFactura(Cliente cliente, Scanner Scanner) {
        int opcion;
        File file = new File(ARCHIVO_FACTURAS);
        boolean existe = file.exists();


        while (true) {
            System.out.println("1) Mostrar usuarios registrados \n2) Crear la factura");
            opcion = Scanner.nextInt();

            switch (opcion) {
                case 1:
                    Cliente.mostrarClienteRegistrados(ListCliente);
                    System.out.println("-------------------------------------------------------");
                    break;
                
                case 2:
                    Factura.guardarFactura(cliente, Scanner);

                default:
                    break;
            }

            

    }

    
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file,true))) {
            if(!existe) {
                bw.write("| Cliente | Numero de cliente | Fecha de emisión | descripción del servicio | importe | iva | total ");
                bw.newLine();      
            }

            bw.write(Factura.toCsvLine());
            bw.newLine();

        } catch (IOException e) {
            System.err.println("Error" + e.getMessage());
        }
}

    public static void cargarFacturas() {

    }

    /*public String toCsvLine() {
        return 
    }*/
    
    @Override
    public double calcularTotal() {
        return 0.0; //Provicional
    }
}
