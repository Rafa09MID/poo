import java.util.ArrayList;
import java.util.Scanner;

public class Main {
     private static ArrayList<Cliente> cliente = Cliente.cargarDatos();
    public static void main(String[] args) {
        Scanner board = new Scanner(System.in);
        int opcion;

        do {

            System.out.println("--------------------------------------- \n1) Mostrar cleinte registrados \n2) Mostrar todas las facturas ");
            System.out.println("3) Crear factura \n4) Mostrar facturas de un cliente \n5) Agregar cliente \n6) Salir \n---------------------------------------");
            opcion = board.nextInt();
            System.lineSeparator();


            switch(opcion) {
                case 1 :
                    Cliente.mostrarClienteRegistrados(cliente);
                    break;

                case 2 :
                    Factura.cargarFacturas();
                    break;

                case 3 :
                    //Factura.crearFactura();
                    break;

                case 4 :
                    //Factura.filtrarFacturas();


                case 5 : 
                    Cliente.guardarCliente(board);
                    break;


                case 6 :
                    System.out.println("Saliendo");
                    System.exit(0);
                    break;

                default: 
                    System.out.println("Seleccione una opcion valida");
                    break;
            }

        } while (opcion != 2);
        
        board.close();
    }
} 