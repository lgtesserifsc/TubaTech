import java.util.Scanner;

public class LojaOnline {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Loja loja = new Loja();

        System.out.println("Bem-vindo à Loja Online TubaTech!");

        int opcao;

        do {
            loja.exibirProdutos();

            System.out.println("\nOpções:");
            System.out.println("1. Adicionar produto ao carrinho");
            System.out.println("2. Exibir produtos por categoria");
            System.out.println("3. Ver Carrinho");
            System.out.println("4. Finalizar Compra");
            System.out.println("5. Remover produto do carrinho");
            System.out.println("-1. Sair");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();

            if (opcao == -1) {
                System.out.println("👋 Obrigado por visitar a TubaTech! Até logo!");
                break;
            } else if (opcao == 2) {
                loja.exibirCategorias();
                int numeroCategoria = scanner.nextInt();

                // Converter número para string para usar no método existente
                String categoria = String.valueOf(numeroCategoria);
                loja.exibirProdutosPorCategoria(categoria);

                System.out.println("\nO que deseja fazer?");
                System.out.println("1 - Adicionar ao carrinho");
                System.out.println("2 - Comprar agora");
                System.out.println("0 - Voltar ao menu principal");
                System.out.print("Escolha uma opção: ");
                int opcaoCompra = scanner.nextInt();

                if (opcaoCompra == 1 || opcaoCompra == 2) {
                    System.out.print("Digite o ID do produto: ");
                    int idProdutoCat = scanner.nextInt();
                    System.out.print("Digite a quantidade: ");
                    int quantidadeCat = scanner.nextInt();

                    Produto produto = loja.buscarProduto(idProdutoCat);
                    if (produto != null) {
                        if (opcaoCompra == 1) {
                            loja.adicionarAoCarrinho(idProdutoCat, quantidadeCat);
                            System.out.println("Produto adicionado ao carrinho com sucesso!");
                        } else {
                            loja.comprarAgora(produto, quantidadeCat, scanner);
                        }
                    } else {
                        System.out.println("Produto não encontrado.");
                    }
                }
            } else if (opcao == 3) {
                loja.exibirCarrinho();

                if (!loja.getCarrinho().estaVazio()) {
                    System.out.println("\nOpções:");
                    System.out.println("1 - Finalizar compra");
                    System.out.println("2 - Remover produto");
                    System.out.println("0 - Voltar ao menu");
                    System.out.print("Digite sua escolha: ");
                    int opcaoCarrinho = scanner.nextInt();

                    if (opcaoCarrinho == 1) {
                        loja.finalizarCompra(scanner);
                    } else if (opcaoCarrinho == 2) {
                        System.out.print("Digite o ID do produto a remover: ");
                        int idRemover = scanner.nextInt();
                        loja.removerDoCarrinho(idRemover);
                    }
                }
            } else if (opcao == 4) {
                loja.finalizarCompra(scanner);
            } else if (opcao == 5) {
                loja.exibirCarrinho();
                if (!loja.getCarrinho().estaVazio()) {
                    System.out.print("Digite o ID do produto a remover: ");
                    int idRemover = scanner.nextInt();
                    loja.removerDoCarrinho(idRemover);
                }
            } else if (opcao == 1) {
                System.out.print("\nDigite o ID do produto: ");
                int idProduto = scanner.nextInt();

                Produto produto = loja.buscarProduto(idProduto);
                if (produto != null) {
                    System.out.print("Digite a quantidade: ");
                    int quantidade = scanner.nextInt();

                    System.out.println("\nO que deseja fazer?");
                    System.out.println("1 - Adicionar ao carrinho");
                    System.out.println("2 - Comprar agora");
                    System.out.print("Escolha uma opção: ");
                    int opcaoCompra = scanner.nextInt();

                    if (opcaoCompra == 1) {
                        loja.adicionarAoCarrinho(idProduto, quantidade);
                        System.out.println("Produto adicionado ao carrinho com sucesso!");
                    } else if (opcaoCompra == 2) {
                        loja.comprarAgora(produto, quantidade, scanner);
                    } else {
                        System.out.println("Opção inválida.");
                    }
                } else {
                    System.out.println("Produto não encontrado.");
                }
            } else {
                System.out.println("Opção inválida. Tente novamente.");
            }

        } while (true);

        scanner.close();
    }
}