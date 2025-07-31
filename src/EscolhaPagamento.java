import java.util.Scanner;

public class EscolhaPagamento {
    private static final double DESCONTO_PIX = 0.10; // 10% de desconto

    public static final int CREDITO = 1;
    public static final int DEBITO = 2;
    public static final int PIX = 3;

    private double valorOriginal;
    private double desconto;
    private double valorFinal;
    private int metodoPagamento;
    private boolean sucesso;

    public EscolhaPagamento() {
        this.valorOriginal = 0;
        this.desconto = 0;
        this.valorFinal = 0;
        this.metodoPagamento = 0;
        this.sucesso = false;
    }

    public double getValorOriginal() { return valorOriginal; }
    public double getDesconto() { return desconto; }
    public double getValorFinal() { return valorFinal; }
    public int getMetodoPagamento() { return metodoPagamento; }
    public boolean isSucesso() { return sucesso; }

    public void exibirOpcoesMetodoPagamento() {
        System.out.println("\n=== MÉTODOS DE PAGAMENTO ===");
        System.out.println("1. Cartão de Crédito");
        System.out.println("2. Cartão de Débito");
        System.out.println("3. PIX (10% de desconto)");
        System.out.print("Escolha o método de pagamento: ");
    }

    public void processarPagamento(double valorTotal, Scanner scanner) {
        exibirOpcoesMetodoPagamento();
        int opcaoPagamento = scanner.nextInt();

        this.valorOriginal = valorTotal;
        this.metodoPagamento = opcaoPagamento;

        // Calcular desconto se for PIX
        if (opcaoPagamento == PIX) {
            this.desconto = valorTotal * DESCONTO_PIX;
            this.valorFinal = valorTotal - desconto;
        } else {
            this.desconto = 0;
            this.valorFinal = valorTotal;
        }

        if (opcaoPagamento == CREDITO) {
            this.sucesso = processarCredito();
        } else if (opcaoPagamento == DEBITO) {
            this.sucesso = processarDebito();
        } else if (opcaoPagamento == PIX) {
            this.sucesso = processarPix();
        } else {
            System.out.println("Método de pagamento inválido!");
            this.sucesso = false;
        }
    }


    private boolean processarCredito() {

        System.out.printf("Pagamento de R$ %.2f aprovado no cartão de crédito!\n", valorFinal);
        return true;
    }

    private boolean processarDebito() {

        System.out.printf("Pagamento de R$ %.2f aprovado no cartão de débito!\n", valorFinal);
        return true;
    }


    private boolean processarPix() {
        System.out.println("\n=== PAGAMENTO COM PIX ===");
        System.out.printf("Valor com desconto de 10%%: R$ %.2f\n", valorFinal);
        System.out.printf("Você economizou: R$ %.2f\n", desconto);


        System.out.printf("Pagamento PIX de R$ %.2f confirmado!\n", valorFinal);
        return true;
    }


    public void exibirResumoPagamento() {
        System.out.println("\n=== RESUMO DO PAGAMENTO ===");
        System.out.printf("Valor original: R$ %.2f\n", valorOriginal);

        if (desconto > 0) {
            System.out.printf("Desconto PIX (10%%): -R$ %.2f\n", desconto);
            System.out.println("------------------------");
        }

        System.out.printf("Valor pago: R$ %.2f\n", valorFinal);

        //escolhe o metodo de compra
        System.out.print("Método: ");
        if (metodoPagamento == CREDITO) {
            System.out.println("Cartão de Crédito");
        } else if (metodoPagamento == DEBITO) {
            System.out.println("Cartão de Débito");
        } else if (metodoPagamento == PIX) {
            System.out.println("PIX");
        }

    }
}

