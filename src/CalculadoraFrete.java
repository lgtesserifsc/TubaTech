 public class CalculadoraFrete {
        private static final double VALOR_FRETE = 29.90;
        private static final double LIMITE_FRETE_GRATIS = 200.0;

        public static double calcularFrete(double valorCompra) {
            if (valorCompra >= LIMITE_FRETE_GRATIS) {
                return 0.0;
            }
            return VALOR_FRETE;
        }

        public static void exibirInfoFrete(double valorCompra) {
            double frete = calcularFrete(valorCompra);
            if (frete == 0) {
                System.out.println("Frete GRÁTIS!");
            } else {
                double faltaParaFreteGratis = LIMITE_FRETE_GRATIS - valorCompra;
                System.out.printf("Frete: R$ %.2f\n", frete);
                System.out.printf("Adicione mais R$ %.2f para ganhar frete grátis!\n", faltaParaFreteGratis);
            }
        }
    }
