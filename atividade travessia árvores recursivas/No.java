import java.util.LinkedList;
import java.util.Queue;

class No {
    String valor;
    No esquerda, direita;

    public No(String valor) {
        this.valor = valor;
        esquerda = direita = null;
    }

    public int contarNos(No contar){
        if (contar == null)
            return 0;
        else
            return 1 + contarNos(contar.esquerda) + contarNos(contar.direita);
    }

    public void preOrdem(No pre){
        if (pre != null) {
            System.out.print(pre.valor + " ");
            preOrdem(pre.esquerda);
            preOrdem(pre.direita);
        }
    }
       
    public void emOrdem(No em){
        if (em != null){
            emOrdem(em.esquerda);
            System.out.print(em.valor + " ");
            emOrdem(em.direita);
        }
    }

    public void posOrdem(No pos){
        if (pos != null){
            posOrdem(pos.esquerda);
            posOrdem(pos.direita);
            System.out.print(pos.valor + " ");
        }
    }

    public void emNivel(No raiz){
        if (raiz == null) return;

        Queue<No> fila = new LinkedList<>();
        fila.add(raiz);

        while (!fila.isEmpty()){
            No atual = fila.poll();
            System.out.print(atual.valor + " ");

            if (atual.esquerda != null) fila.add(atual.esquerda);
            if (atual.direita != null) fila.add(atual.direita);
        }
    }
}