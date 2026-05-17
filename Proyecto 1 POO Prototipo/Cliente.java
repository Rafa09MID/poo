public class Cliente {
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

    public void listar() {
        System.out.println("Cliente #" + numCliente + ": " + nombreCliente + " (Fecha alta: " + fechaAlta + ")");
    }
}
