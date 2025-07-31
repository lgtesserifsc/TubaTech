import java.util.Scanner;

public class Loja {
    private Produto[] produtos;
    private int totalProdutos;
    private Carrinho carrinho;
    private static final int CAPACIDADE_PRODUTOS = 100;

    public Loja() {
        produtos = new Produto[CAPACIDADE_PRODUTOS];
        totalProdutos = 0;
        carrinho = new Carrinho();
        inicializarProdutos();
    }

    private void inicializarProdutos() {
        // Celular
        adicionarProduto(new Produto(1, "Samsung A15", 1149.00, "Celular"));
        adicionarProduto(new Produto(2, "Smartphone B2", 2000.00, "Celular"));

        // Tablet
        adicionarProduto(new Produto(3, "Tablet Max", 700.00, "Tablet"));
        adicionarProduto(new Produto(4, "Samsung Galaxy Tab A9+", 1149.00, "Tablet"));

        // Computador
        adicionarProduto(new Produto(5, "PC Gamer i5", 1179.00, "Computador"));
        adicionarProduto(new Produto(6, "PC Gamer i7", 1000.00, "Computador"));

        // Notebook
        adicionarProduto(new Produto(7, "MacBook Asus ROG", 10000.00, "Notebook"));
        adicionarProduto(new Produto(8, "Notebook HP 250", 5000.00, "Notebook"));

        // Periférico
        adicionarProduto(new Produto(9, "Mouse Redragon Cobra", 25.00, "Periférico"));
        adicionarProduto(new Produto(10, "Teclado Mecânico Rise Mode", 250.00, "Periférico"));
    }

    public void adicionarProduto(Produto produto) {
        if (totalProdutos < CAPACIDADE_PRODUTOS) {
            produtos[totalProdutos] = produto;
            totalProdutos++;
        }
    }

    public void exibirProdutos() {
        System.out.println("\n=== PRODUTOS DISPONÍVEIS ===");
        for (int i = 0; i < totalProdutos; i++) {
            System.out.println(produtos[i]);
        }
    }

    public void exibirProdutosPorCategoria(String categoria) {
        // Converter número para nome da categoria
        String nomeCategoria = obterNomeCategoria(categoria);

        System.out.println("\n=== PRODUTOS - " + nomeCategoria.toUpperCase() + " ===");
        boolean encontrou = false;
        for (int i = 0; i < totalProdutos; i++) {
            if (produtos[i].getCategoria().equalsIgnoreCase(nomeCategoria)) {
                System.out.println(produtos[i]);
                encontrou = true;
            }
        }
        if (!encontrou) {
            System.out.println("Nenhum produto encontrado nesta categoria.");
        }
    }

    // Método para converter o numero para nome
    private String obterNomeCategoria(String entrada) {
        switch (entrada.trim()) {
            case "1":
                return "Celular";
            case "2":
                return "Tablet";
            case "3":
                return "Computador";
            case "4":
                return "Notebook";
            case "5":
                return "Periférico";
            default:
                // Se não for número, retorna como tava
                return entrada;
        }
    }

    public Produto buscarProduto(int id) {
        for (int i = 0; i < totalProdutos; i++) {
            if (produtos[i].getId() == id) {
                return produtos[i];
            }
        }
        return null;
    }

    public void adicionarAoCarrinho(int idProduto, int quantidade) {
        Produto produto = buscarProduto(idProduto);
        if (produto != null) {
            carrinho.adicionarProduto(produto, quantidade);
        } else {
            System.out.println("Produto não encontrado!");
        }
    }

    public void removerDoCarrinho(int idProduto) {
        carrinho.removerProduto(idProduto);
    }

    public void exibirCarrinho() {
        carrinho.exibirCarrinho();
        if (!carrinho.estaVazio()) {
            System.out.println();
            CalculadoraFrete.exibirInfoFrete(carrinho.calcularTotal());
        }
    }

    public void finalizarCompra(Scanner scanner) {
        if (carrinho.estaVazio()) {
            System.out.println("Carrinho vazio! Adicione produtos antes de finalizar a compra.");
            return;
        }

        System.out.println("\n=== FINALIZANDO COMPRA ===");

        carrinho.exibirCarrinho();

        double subtotal = carrinho.calcularTotal();
        double frete = CalculadoraFrete.calcularFrete(subtotal);
        double total = subtotal + frete;

        System.out.printf("\nSubtotal: R$ %.2f\n", subtotal);
        System.out.printf("Frete: R$ %.2f\n", frete);
        System.out.println("------------------------");
        System.out.printf("TOTAL: R$ %.2f\n", total);

        EscolhaPagamento pagamento = new EscolhaPagamento();
        pagamento.processarPagamento(total, scanner);

        if (pagamento.isSucesso()) {
            pagamento.exibirResumoPagamento();
            System.out.println("\nSeu pedido será processado e enviado em breve!");
            System.out.println("Obrigado por comprar na TubaTech! Até logo!");
            carrinho.limparCarrinho();
            System.exit(0); // Finaliza o programa
        } else {
            System.out.println("Falha no pagamento. Tente novamente.");
        }
    }

    //Método para compra direta
    public void comprarAgora(Produto produto, int quantidade, Scanner scanner) {
        System.out.println("\n=== COMPRAR AGORA ===");
        System.out.println("Produto: " + produto.getDescricao());
        System.out.println("Quantidade: " + quantidade);

        double subtotal = produto.getPreco() * quantidade;
        double frete = CalculadoraFrete.calcularFrete(subtotal);
        double total = subtotal + frete;

        System.out.printf("Subtotal: R$ %.2f\n", subtotal);

        if (frete == 0) {
            System.out.println("Frete GRÁTIS!");
        } else {
            System.out.printf("Frete: R$ %.2f\n", frete);
            double faltaParaFreteGratis = 200.0 - subtotal;
            if (faltaParaFreteGratis > 0) {
                System.out.printf("Adicione mais R$ %.2f para ganhar frete grátis!\n", faltaParaFreteGratis);
            }
        }

        System.out.println("------------------------");
        System.out.printf("TOTAL: R$ %.2f\n", total);

        EscolhaPagamento pagamento = new EscolhaPagamento();
        pagamento.processarPagamento(total, scanner);

        if (pagamento.isSucesso()) {
            pagamento.exibirResumoPagamento();
            System.out.println("\nSeu pedido será processado e enviado em breve!");
            System.out.println("Obrigado por comprar na TubaTech! Até logo!");
            System.exit(0); // Finaliza o programa
        } else {
            System.out.println("Falha no pagamento. Tente novamente.");
        }
    }

    public void exibirCategorias() {
        System.out.println("\n=== CATEGORIAS DISPONÍVEIS ===");
        System.out.println("1 - Celular");
        System.out.println("2 - Tablet");
        System.out.println("3 - Computador");
        System.out.println("4 - Notebook");
        System.out.println("5 - Periférico");
        System.out.println("\nDigite o número da categoria desejada:");
    }

    public Carrinho getCarrinho() {
        return carrinho;
    }
}