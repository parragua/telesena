public class Pessoa {
    private String nome;
    private TeleSena[] teleSenas;
    private int quantidadeComprada;
    private double premio;

    public Pessoa(String nome, int quantidade) {
        this.nome = nome;
        this.premio = 0;
        if (quantidade > 15) {
            quantidade = 15;
        }
        this.quantidadeComprada = quantidade;
        this.teleSenas = new TeleSena[quantidade];

        for (int i = 0; i < quantidade; i++) {
            teleSenas[i] = new TeleSena();
        }
    }

    public String getNome() { return nome; }
    public TeleSena[] getTeleSenas() { return teleSenas; }
    public int getQuantidadeComprada() { return quantidadeComprada; }
    public double getPremio() { return premio; }

    public void adicionarPremio(double valor) {
        this.premio += valor;
    }
}