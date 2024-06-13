import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App {
    private List<Reserva> reservas = new ArrayList<>();
    private List<Reserva> listaEspera = new ArrayList<>();

    public void menu() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Menu:");
            System.out.println("1. Reserva");
            System.out.println("2. Pesquisa");
            System.out.println("3. Imprimir reservas");
            System.out.println("4. Imprimir lista de espera");
            System.out.println("5. Cancelar reserva");
            System.out.println("0. Sair");

            int opcao;

            try {
                opcao = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Opcao invalida , tente novamente");
                continue;
            }

            switch (opcao) {
                case 1:
                    reservarMesa(scanner);
                    break;
                case 2:
                    pesquisarReserva(scanner);
                    break;
                case 3:
                    imprimirReservas();
                    break;
                case 4:
                    imprimirListaEspera();
                    break;
                case 5:
                    cancelarReserva(scanner);
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Opcao invalida.");
            }
        }
    }

    private void reservarMesa(Scanner scanner) {
        System.out.println("Nome do cliente:");
        String nome = scanner.nextLine();
        System.out.println("Pessoa Física (1) ou Jurídica (2):");
        int tipoCliente = Integer.parseInt(scanner.nextLine());
        Cliente cliente;

        if (tipoCliente == 1) {
            System.out.println("CPF:");
            String cpf = scanner.nextLine();
            cliente = new PessoaFisica(nome, cpf);
        } else {
            System.out.println("CNPJ:");
            String cnpj = scanner.nextLine();
            cliente = new PessoaJuridica(nome, cnpj);
        }

        System.out.println("Pagamento a vista (1 para sim e 2 para não) :");
        boolean pagamentoAVista = Boolean.parseBoolean(scanner.nextLine());

        Reserva reserva = new Reserva(cliente, pagamentoAVista);
        if (reservas.size() < 6) {
            reservas.add(reserva);
            System.out.println("Reserva realizada com sucesso.");
        } else {
            listaEspera.add(reserva);
            System.out.println("Reserva adicionada na lista de espera.");
        }
    }

    private void pesquisarReserva(Scanner scanner) {
        System.out.println("Digite CPF ou o CNPJ:");
        String documento = scanner.nextLine();

        for (Reserva reserva : reservas) {
            if ((reserva.getCliente() instanceof PessoaFisica && ((PessoaFisica) reserva.getCliente()).getCpf().equals(documento)) ||
                (reserva.getCliente() instanceof PessoaJuridica && ((PessoaJuridica) reserva.getCliente()).getCnpj().equals(documento))) {
                System.out.println("Reserva encontrada: " + reserva);
                return;
            }
        }

        System.out.println("A Reserva nao existe.");
    }

    private void imprimirReservas() {
        System.out.println("Reservas existentes:");
        for (int i = 0; i < Math.min(6, reservas.size()); i++) {
            System.out.println((i + 1) + "." + reservas.get(i));
        }
    }

    private void imprimirListaEspera() {
        System.out.println("Lista de espera:");
        for (int i = 0; i < listaEspera.size(); i++) {
            System.out.println((i + 1) + "." + listaEspera.get(i));
        }
    }

    private void cancelarReserva(Scanner scanner) {
        System.out.println("Digite CPF ou CNPJ:");
        String documento = scanner.nextLine();

        for (int i = 0; i < reservas.size(); i++) {
            Reserva reserva = reservas.get(i);
            if ((reserva.getCliente() instanceof PessoaFisica && ((PessoaFisica) reserva.getCliente()).getCpf().equals(documento)) ||
                (reserva.getCliente() instanceof PessoaJuridica && ((PessoaJuridica) reserva.getCliente()).getCnpj().equals(documento))) {
                reservas.remove(i);
                System.out.println("Reserva cancelada.");

                if (!listaEspera.isEmpty()) {
                    reservas.add(listaEspera.remove(0));
                    System.out.println("Reserva da lista de espera confirmada.");
                }
                return;
            }
        }

        System.out.println("Reserva não existente.");
    }

    public static void main(String[] args) {
        
        App sistema = new App();
        sistema.menu();
    }
}