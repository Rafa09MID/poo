import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner board = new Scanner(System.in);
        int opcion;

        do {

            System.out.println("--------------------------------------- \n1) Mostrar cleinte registrados \n2) Mostrar todas las facturas ");
            System.out.println("\n3) Crear factura \n4) Mostrar facturas de un cliente \n5) Salir \n---------------------------------------");
            opcion = board.nextInt();
            System.lineSeparator();


            switch(opcion) {
                case 1 :
                    mostrarClienteRegistrados();
                    break;

                case 2 :
                    Factura.cargarFacturas();
                    break;

                case 3 :
                    Factura.crearFactura();
                    break;

                case 4 :
                    Factura.filtrarFacturas();


                case 5 : 
                    System.out.println("Saliendo");
                    System.exit(0);
                    break;

                case 6 :
                    Cliente.guardarCliente(board);
                    break;

                default: 
                    System.out.println("Seleccione una opcion valida");
                    break;
            }

        } while (opcion != 2);
        
        board.close();
    }
} 