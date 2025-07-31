public class Carrinho {
    private ItemCarrinho[] itens;
    private int totalItens;
    private static final int CAPACIDADE_MAXIMA = 50;
    // Faz gerenciamento de todo o carrinho
    public Carrinho() {
        itens = new ItemCarrinho[CAPACIDADE_MAXIMA];
        totalItens = 0;
    }

    public boolean adicionarProduto(Produto produto, int quantidade) {
        // Ve se o carrinho ta vazio
        for (int i = 0; i < totalItens; i++) {
            if (itens[i].getProduto().getId() == produto.getId()) {
                int novaQuantidade = itens[i].getQuantidade() + quantidade;
                itens[i].setQuantidade(novaQuantidade);
                System.out.println("Quantidade atualizada no carrinho!");
                return true;
            }
        }

        if (totalItens < CAPACIDADE_MAXIMA) {
            itens[totalItens] = new ItemCarrinho(produto, quantidade);
            totalItens++;
            System.out.println("Produto adicionado ao carrinho!");
            return true;
        } else {
            System.out.println("Carrinho cheio!");
            return false;
        }
    }

    public boolean removerProduto(int idProduto) {
        for (int i = 0; i < totalItens; i++) {
            if (itens[i].getProduto().getId() == idProduto) {
                // Move itens 1 posição para trás
                for (int j = i; j < totalItens - 1; j++) {
                    itens[j] = itens[j + 1];
                }
                totalItens--;
                itens[totalItens] = null; // Limpa a referência
                System.out.println("Produto removido do carrinho!");
                return true;
            }
        }
        System.out.println("Produto não encontrado no carrinho!");
        return false;
    }

    public double calcularTotal() {
        double total = 0;
        for (int i = 0; i < totalItens; i++) {
            total += itens[i].getSubtotal();
        }
        return total;
    }

    public boolean estaVazio() {
        return totalItens == 0;
    }

    public ItemCarrinho[] getItens() {
        // Retorna uma cópia do array apenas com os itens válidos
        ItemCarrinho[] itensValidos = new ItemCarrinho[totalItens];
        for (int i = 0; i < totalItens; i++) {
            itensValidos[i] = itens[i];
        }
        return itensValidos;
    }

    public void limparCarrinho() {
        for (int i = 0; i < totalItens; i++) {
            itens[i] = null;
        }
        totalItens = 0;
    }

    public void exibirCarrinho() {
        if (estaVazio()) {
            System.out.println("Carrinho vazio!");
            return;
        }

        System.out.println("\n=== CARRINHO DE COMPRAS ===");
        for (int i = 0; i < totalItens; i++) {
            System.out.println(itens[i]);
        }
        System.out.printf("TOTAL: R$ %.2f\n", calcularTotal());
    }
}