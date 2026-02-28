import java.util.Scanner;

public class Principal {
    public static void exibirMenu(){
        System.out.println("""    
                Escolha uma moeda:
                
                1 - ARS Argentine Peso
                2 - BRL Brazilian Real
                3 - CAD Canadian Dollar
                4 - CNY Chinese Renminbi
                5 - EUR Euro
                6 - JPY Japanese Yen
                7 - RUB Russian Ruble
                
                """);}
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Conversor conversor = new Conversor();

        int i = -1;

        while(i != 0){
            //pegando moeda de origem
            exibirMenu();
            System.out.println("Moeda de origem: ");
            int opcaoOrigem = scanner.nextInt();

            if(opcaoOrigem == 0){
                System.out.println("Opcao invalida!");
                continue;
            }

            String moedaOrigem = conversor.pegarSigla(opcaoOrigem);

            if(moedaOrigem == null){
                continue;
            }

            //pegando moeda de destino
            exibirMenu();
            System.out.println("Moeda de destino: ");
            int opcaoDestino = scanner.nextInt();

            if(opcaoDestino == 0){
                System.out.println("Opcao invalida!");
                continue;
            }

            String moedaDestino = conversor.pegarSigla(opcaoDestino);

            if(moedaDestino == null){
                continue;
            }

            //pegando valor a ser convertido
            System.out.println("Valor a ser convertido: ");
            double valorMoeda = scanner.nextDouble();

            double resultado = conversor.converterMoeda(moedaOrigem, moedaDestino, valorMoeda);
            System.out.printf("Resultado: %s %.2f -> %s %.2f%n", moedaOrigem, valorMoeda, moedaDestino, resultado);
            System.out.println("""
                    Deseja fazer outra operação?
                    0 - Não
                    1 - Sim
                    """);

            i = scanner.nextInt();
        }
    }
}