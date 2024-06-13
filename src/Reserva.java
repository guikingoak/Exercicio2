public class Reserva implements Pagamento {
    private Cliente cliente;
    private boolean pagamentoAVista;

    public Reserva(Cliente cliente, boolean pagamentoAVista) {
        this.cliente = cliente;
        this.pagamentoAVista = pagamentoAVista;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public boolean isPagamentoAVista() {
        return pagamentoAVista;
    }

    public void setPagamentoAVista(boolean pagamentoAVista) {
        this.pagamentoAVista = pagamentoAVista;
    }

    @Override
    public double calcularPagamento() {
        if (pagamentoAVista) {
            return 3200 * 0.9;
        } else {
            return 3200;
        }
    }

    @Override
    public String toString() {
        return cliente.toString() + ", Pagamento: " + (pagamentoAVista ? "A vista" : "Parcelado");
    }
}