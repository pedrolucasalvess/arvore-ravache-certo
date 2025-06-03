public class Main {
    public static void main(String[] args) {
        ArvoreAVL arvore = new ArvoreAVL();

        arvore.inserir(10);
        arvore.inserir(20);
        arvore.inserir(30);

        arvore.remover(20);
        System.out.println(arvore.buscar(20));
        System.out.println(arvore.buscar(30));
    }
}
