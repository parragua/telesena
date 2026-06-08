
public class ControleTeleSena {
    private Pessoa[] pessoas;
    private int totalVendidas;
    private int[] sorteados;
    private int quantidadeSorteada;

    public ControleTeleSena() {
        this.pessoas = new Pessoa[20];
        this.totalVendidas = 0;
        this.sorteados = new int[60];
        this.quantidadeSorteada = 0;
    }

    // Verifica se um número já existe no array (uso interno)
    private boolean contemNumero(int[] array, int tamanho, int numero) {
        for (int i = 0; i < tamanho; i++) {
            if (array[i] == numero) {
                return true;
            }
        }
        return false;
    }

    // Sorteia nomes e quantidades de TeleSenas para cada pessoa
    public void realizarVendas() {
        String[] nomes = {"Ana", "Bruno", "Carla", "Diego", "Elena",
                "Felipe", "Gabriela", "Henrique", "Isabela", "João",
                "Karen", "Lucas", "Marina", "Nicolas", "Olivia",
                "Pedro", "luiza", "Rafael", "Sabrina", "Thiago"};

        System.out.println("=== VENDAS ===");
        for (int i = 0; i < 20; i++) {
            int quantidade = (int) (Math.random() * 15 + 1);
            pessoas[i] = new Pessoa(nomes[i], quantidade);
            totalVendidas += quantidade;
            System.out.println(nomes[i] + " comprou " + quantidade + " TeleSena(s)");
        }
        if (totalVendidas <= 300) {
            System.out.println("\nTotal vendido: " + totalVendidas + " TeleSenas");
        }  else {
            System.out.println("numero de teles excedeu o numero maximo");
            System.exit(0);
        }
    }

    // Realiza o sorteio e verifica ganhadores
    public void realizarSorteio() {
        System.out.println("\n=== SORTEIO ===");

        // Passo 1: sortear os 25 primeiros números
        while (quantidadeSorteada < 25) {
            int numero = (int) (Math.random() * 60 + 1);
            if (!contemNumero(sorteados, quantidadeSorteada, numero)) {
                sorteados[quantidadeSorteada] = numero;
                quantidadeSorteada++;
            }
        }
        System.out.println("25 números sorteados! Verificando ganhadores...");

        // Passo 2: verificar ganhadores, sorteando +1 até alguém ganhar
        boolean houveGanhador = false;
        while (!houveGanhador) {
            for (int i = 0; i < pessoas.length; i++) {
                TeleSena[] teleSenas = pessoas[i].getTeleSenas();
                for (int j = 0; j < teleSenas.length; j++) {
                    if (teleSenas[j].contemNumeros(sorteados, quantidadeSorteada)) {
                        houveGanhador = true;
                    }
                }
            }

            // Passo 3: se não houve ganhador, sorteia mais 1
            if (!houveGanhador) {
                int numero = (int) (Math.random() * 60 + 1);
                if (!contemNumero(sorteados, quantidadeSorteada, numero)) {
                    sorteados[quantidadeSorteada] = numero;
                    quantidadeSorteada++;
                    System.out.println("Sem ganhadores... sorteando número " + quantidadeSorteada);
                }
            }
        }
    }

    // Calcula e distribui o prêmio entre os ganhadores
    public void calcularPremio() {
        double totalVendas = totalVendidas * TeleSena.VALOR_VENDA;
        double premioTotal = totalVendas * 0.80;

        // Conta ganhadores
        int totalGanhadores = 0;
        for (int i = 0; i < pessoas.length; i++) {
            TeleSena[] teleSenas = pessoas[i].getTeleSenas();
            for (int j = 0; j < teleSenas.length; j++) {
                if (teleSenas[j].contemNumeros(sorteados, quantidadeSorteada)) {
                    totalGanhadores++;
                    break; // conta a pessoa só uma vez mesmo que tenha mais de uma TeleSena ganhadora
                }
            }
        }

        // Distribui o prêmio
        double premioPorGanhador = premioTotal / totalGanhadores;
        for (int i = 0; i < pessoas.length; i++) {
            TeleSena[] teleSenas = pessoas[i].getTeleSenas();
            for (int j = 0; j < teleSenas.length; j++) {
                if (teleSenas[j].contemNumeros(sorteados, quantidadeSorteada)) {
                    pessoas[i].adicionarPremio(premioPorGanhador);
                    break;
                }
            }
        }
    }

    // Exibe todos os resultados finais
    public void exibirResultados() {
        double totalVendas = totalVendidas * TeleSena.VALOR_VENDA;
        double premioTotal = totalVendas * 0.80;
        double lucro = totalVendas - premioTotal;
        int totalGanhadores = 0;

        System.out.println("\n=== RESULTADOS ===");
        System.out.print("Números sorteados: ");
        for (int i = 0; i < quantidadeSorteada; i++) {
            System.out.print(sorteados[i] + " ");
        }
        System.out.println("\nTeleSenas vendidas: " + totalVendidas);
        System.out.println("Total arrecadado: R$ " + totalVendas);
        System.out.println("Prêmio total (80%): R$ " + premioTotal);
        System.out.println("Lucro: R$ " + lucro);

        try { Thread.sleep(3000); } catch (Exception e) {}

        System.out.println("\n=== GANHADORES ===");
        for (int i = 0; i < pessoas.length; i++) {
            if (pessoas[i].getPremio() > 0) {
                totalGanhadores++;
                System.out.println(pessoas[i].getNome() + " - Prêmio: R$ " + pessoas[i].getPremio());
            }
        }

        System.out.println("total de ganhadores: " + totalGanhadores);
    }

    // Método principal que executa tudo em ordem
    public void executar() {
        realizarVendas();
        realizarSorteio();
        calcularPremio();
        exibirResultados();
    }
}