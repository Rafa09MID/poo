public abstract class Servicio {
    protected String descripcion;
    protected double importe;


    public Servicio(String descripcion, double importe) {
        this.descripcion = descripcion;
        this.importe = importe;
    }

    public abstract double calcularTotal(double importe, double iva);

    public abstract double calcularIva(double valor);

    public void mostrarDetalle() {
        System.out.println("Descripción: " + descripcion + " | Importe: " + importe);
    }
}
