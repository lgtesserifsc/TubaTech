import java.util.Scanner;

public class LojaOnline {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Loja loja = new Loja();

        System.out.println("🛒 Bem-vindo à Loja Online TubaTech!");

        int opcao;

        do {
            loja.exibirProdutos();

            System.out.println("\nOpções:");
            System.out.println("1. Adicionar produto ao carrinho");
            System.out.println("2. Exibir produtos por categoria");
            System.out.println("3. Ver Carrinho");
            System.out.println("4. Finalizar Compra");
            System.out.println("-1. Sair");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();

            if (opcao == -1) {
                break;
            } else if (opcao == 2) {
                loja.exibirCategorias();
                System.out.print("Digite o numero da categoria desejada: ");
                scanner.nextLine(); // Consumir a nova linha pendente
                String categoria = scanner.nextLine();
                loja.exibirProdutosPorCategoria(categoria);
                System.out.print("\nDeseja adicionar um produto desta categoria ao carrinho? (1 - Sim / 0 - Não): ");
                int adicionarDaCategoria = scanner.nextInt();
                if (adicionarDaCategoria == 1) {
                    System.out.print("Digite o ID do produto que deseja adicionar ao carrinho: ");
                    int idProdutoCat = scanner.nextInt();
                    System.out.print("Digite a quantidade: ");
                    int quantidadeCat = scanner.nextInt();
                    loja.adicionarAoCarrinho(idProdutoCat, quantidadeCat);
                    System.out.println("✅ Produto adicionado com sucesso!");
                }
            } else if (opcao == 3) {
                loja.exibirCarrinho();
                System.out.print("\nDeseja finalizar a compra? (1 - Sim / 0 - Não): ");
                int finalizar = scanner.nextInt();
                if (finalizar == 1) {
                    loja.finalizarCompra();
                    break; // Sai do loop principal após finalizar a compra
                }
            } else if (opcao == 4) {
                loja.finalizarCompra();
                break; // Sai do loop principal após finalizar a compra
            } else if (opcao == 1) {
                System.out.print("\nDigite o ID do produto que deseja adicionar ao carrinho: ");
                int idProduto = scanner.nextInt();

                Produto produto = loja.buscarProduto(idProduto);
                if (produto != null) {
                    System.out.print("Digite a quantidade: ");
                    int quantidade = scanner.nextInt();
                    loja.adicionarAoCarrinho(idProduto, quantidade);
                    System.out.println("✅ Produto adicionado com sucesso!");
                } else {
                    System.out.println("❌ Produto não encontrado.");
                }
            } else {
                System.out.println("Opção inválida. Tente novamente.");
            }

        } while (true);

        scanner.close();
    }
}