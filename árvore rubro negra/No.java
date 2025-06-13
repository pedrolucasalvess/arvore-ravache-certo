public class No {
    public enum Cor {
        Red, Black;
    }
    public int valor;
    public No esquerdo, direito, pai;
    public Cor cor;


    public No(int valor) {
        this.valor = valor;
        this.cor = Cor.Red; 
        this.esquerdo = null;
        this.direito = null;
        this.pai = null;
    }
}