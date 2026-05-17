public abstract class Servicio {
    protected String descripcion;
    protected double importe;


    public Servicio(String descripcion, double importe) {
        this.descripcion = descripcion;
        this.importe = importe;
    }

    public abstract double calcularTotal(double valor);

    public void mostrarDetalle() {
        System.out.println("Descripción: " + descripcion + " | Importe: " + importe);
    }
}
