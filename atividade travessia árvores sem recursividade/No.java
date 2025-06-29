import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

class No {
    String valor;
    No esquerda, direita;

    public No(String valor) {
        this.valor = valor;
        esquerda = direita = null;
    }

public int contarNosIterativo(No raiz) {
    if (raiz == null) return 0;

    Queue<No> fila = new LinkedList<>();
    fila.add(raiz);
    int contador = 0;

    while (!fila.isEmpty()) {
        No atual = fila.poll();
        contador++;

        if (atual.esquerda != null) fila.add(atual.esquerda);
        if (atual.direita != null) fila.add(atual.direita);
    }

    return contador;
}

public void preOrdemIterativa(No raiz) {
    if (raiz == null) return;

    Stack<No> pilha = new Stack<>();
    pilha.push(raiz);

    while (!pilha.isEmpty()) {
        No atual = pilha.pop();
        System.out.print(atual.valor + " ");

        if (atual.direita != null) pilha.push(atual.direita);
        if (atual.esquerda != null) pilha.push(atual.esquerda);
    }
}

public void emOrdemIterativa(No raiz) {
    Stack<No> pilha = new Stack<>();
    No atual = raiz;

    while (atual != null || !pilha.isEmpty()) {
        while (atual != null) {
            pilha.push(atual);
            atual = atual.esquerda;
        }

        atual = pilha.pop();
        System.out.print(atual.valor + " ");
        atual = atual.direita;
    }
}

public void posOrdemIterativa(No raiz) {
    if (raiz == null) return;

    Stack<No> pilha1 = new Stack<>();
    Stack<No> pilha2 = new Stack<>();

    pilha1.push(raiz);

    while (!pilha1.isEmpty()) {
        No atual = pilha1.pop();
        pilha2.push(atual);

        if (atual.esquerda != null) pilha1.push(atual.esquerda);
        if (atual.direita != null) pilha1.push(atual.direita);
    }

    while (!pilha2.isEmpty()) {
        System.out.print(pilha2.pop().valor + " ");
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