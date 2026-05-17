public class Factura extends Servicio {
    private int numFactura;
    private double iva;
    private double total;

    public Factura(String descripcion, double importe, int numFactura, double iva, double total) {
        super(descripcion,importe);
        this.numFactura = numFactura;
        this.iva = iva;
        this.total = total; 

    }
    
    @Override
    public double calcularTotal() {
        return 0.0; //Provicional
    }
}
