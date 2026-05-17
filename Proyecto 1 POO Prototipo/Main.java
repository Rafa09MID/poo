import java.util.ArrayList;
import java.util.Scanner;

public class Main {
     private static ArrayList<Cliente> clientes = gestionDatos.cargarDatos();
    public static void main(String[] args) {
        Scanner board = new Scanner(System.in);
        int opcion;

        do {

            System.out.println("--------------------------------------- \n1) Mostrar cleinte registrados \n2) Mostrar todas las facturas ");
            System.out.println("3) Crear factura \n4) Mostrar facturas de un cliente \n5) Agregar cliente \n6) Salir \n---------------------------------------");
            opcion = board.nextInt();
            board.nextLine();


            switch(opcion) {
                case 1 :
                    Cliente.mostrarClienteRegistrados(clientes);
                    break;

                case 2 :
                    Factura.cargarFacturas();
                    break;

                case 3 :
                    Factura.crearFactura(board);
                    break;

                case 4 :
                    //Factura.filtrarFacturas();


                case 5 : 
                    gestionDatos.guardarCliente(board,clientes);
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