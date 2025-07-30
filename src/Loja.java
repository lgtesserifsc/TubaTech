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
        // Eletrônicos
        adicionarProduto(new Produto(1, "Smartphone Samsung", 899.99, "Eletrônicos"));
        adicionarProduto(new Produto(2, "Notebook Dell", 2499.99, "Eletrônicos"));
        adicionarProduto(new Produto(3, "Fone Bluetooth", 199.99, "Eletrônicos"));
        adicionarProduto(new Produto(4, "Smart TV 55\"", 1899.99, "Eletrônicos"));

        // Roupas
        adicionarProduto(new Produto(5, "Camiseta Básica", 39.90, "Roupas"));
        adicionarProduto(new Produto(6, "Calça Jeans", 89.90, "Roupas"));
        adicionarProduto(new Produto(7, "Tênis Esportivo", 159.90, "Roupas"));
        adicionarProduto(new Produto(8, "Jaqueta de Couro", 299.90, "Roupas"));

        // Casa
        adicionarProduto(new Produto(9, "Panela Antiaderente", 79.90, "Casa"));
        adicionarProduto(new Produto(10, "Aspirador de Pó", 299.90, "Casa"));
        adicionarProduto(new Produto(11, "Conjunto de Pratos", 149.90, "Casa"));
        adicionarProduto(new Produto(12, "Liquidificador", 189.90, "Casa"));
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
        System.out.println("\n=== PRODUTOS - " + categoria.toUpperCase() + " ===");
        boolean encontrou = false;
        for (int i = 0; i < totalProdutos; i++) {
            if (produtos[i].getCategoria().equalsIgnoreCase(categoria)) {
                System.out.println(produtos[i]);
                encontrou = true;
            }
        }
        if (!encontrou) {
            System.out.println("Nenhum produto encontrado nesta categoria.");
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

    public void finalizarCompra() {
        if (carrinho.estaVazio()) {
            System.out.println("Carrinho vazio! Adicione produtos antes de finalizar a compra.");
            return;
        }

        System.out.println("\n=== FINALIZANDO COMPRA ===");

        double subtotal = carrinho.calcularTotal();
        double frete = CalculadoraFrete.calcularFrete(subtotal);
        double total = subtotal + frete;

        System.out.printf("Subtotal: R$ %.2f\n", subtotal);
        System.out.printf("Frete: R$ %.2f\n", frete);
        System.out.println("------------------------");
        System.out.printf("TOTAL: R$ %.2f\n", total);
        System.out.println("✅ Compra finalizada com sucesso!");

        carrinho.limparCarrinho();
    }

    public void exibirCategorias() {
        System.out.println("\n=== CATEGORIAS DISPONÍVEIS ===");
        System.out.println("• Eletrônicos - 1");
        System.out.println("• Roupas - 2");
        System.out.println("• Casa - 3");
    }
}
