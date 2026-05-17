import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner board = new Scanner(System.in);
        int opcion;

        do {

            System.out.println("1) Agregar usuario \n2) Salir");
            opcion = board.nextInt();
            System.lineSeparator();


            switch(opcion) {
                case 1:

                    Cliente.guardarCliente(board);

                    break;
                case 2: 
                    System.out.println("Saliendo");
                    System.exit(0);
                    break;
            }

        } while (opcion != 2);
        
        board.close();
    }
} 