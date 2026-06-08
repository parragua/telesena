public class TeleSena {
    // Constante para o valor de venda (R$ 10,00)
    public static final double VALOR_VENDA = 10.00;

    // Arrays para guardar os dois conjuntos de 25 números
    private int[] conjunto1;
    private int[] conjunto2;

    // Construtor: preenche os conjuntos assim que o objeto é criado
    public TeleSena() {
        this.conjunto1 = gerarConjuntoSemRepeticao();
        this.conjunto2 = gerarConjuntoSemRepeticao();
    }

    // Gera um conjunto de 25 números únicos entre 1 e 60
    private int[] gerarConjuntoSemRepeticao() {
        int[] conjunto = new int[25];
        int quantidadeGerados = 0;

        while (quantidadeGerados < 25) {
            int numeroSorteado = (int) (Math.random() * 60 + 1);
            if (!contemNumero(conjunto, quantidadeGerados, numeroSorteado)) {
                conjunto[quantidadeGerados] = numeroSorteado;
                quantidadeGerados++;
            }
        }
        return conjunto;
    }

    // Verifica se um número já existe no array
    public boolean contemNumero(int[] conjunto, int tamanhoAtual, int numero) {
        for (int i = 0; i < tamanhoAtual; i++) {
            if (conjunto[i] == numero) {
                return true;
            }
        }
        return false;
    }

    // Verifica se algum dos dois conjuntos foi completamente acertado
    public boolean contemNumeros(int[] sorteados, int quantidadeSorteada) {
        return conjuntoFoiAcertado(conjunto1, sorteados, quantidadeSorteada) ||
                conjuntoFoiAcertado(conjunto2, sorteados, quantidadeSorteada);
    }

    // Verifica se todos os 25 números do conjunto estão nos sorteados
    private boolean conjuntoFoiAcertado(int[] conjunto, int[] sorteados, int quantidadeSorteada) {
        for (int numero : conjunto) {
            if (!contemNumero(sorteados, quantidadeSorteada, numero)) {
                return false;
            }
        }
        return true;
    }

    public int[] getConjunto1() { return conjunto1; }
    public int[] getConjunto2() { return conjunto2; }
}