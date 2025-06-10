import java.lang.Math;

class No {
    int valor, altura;
    No esquerda, direita;

    No(int valor) {
        this.valor = valor;
        this.altura = 1;
    }
}

public class ArvoreAVL {
    No raiz;

    private int altura(No no) {
        if (no == null) return 0;
        return no.altura;
    }

    private int fatorBalanceamento(No no) {
        if (no == null) return 0;
        return altura(no.esquerda) - altura(no.direita);
    }

    private No rotacaoDireita(No y) {
        No x = y.esquerda;
        No T2 = x.direita;

        x.direita = y;
        y.esquerda = T2;

        y.altura = 1 + Math.max(altura(y.esquerda), altura(y.direita));
        x.altura = 1 + Math.max(altura(x.esquerda), altura(x.direita));

        return x;
    }

    private No rotacaoEsquerda(No x) {
        No y = x.direita;
        No T2 = y.esquerda;

        y.esquerda = x;
        x.direita = T2;

        x.altura = 1 + Math.max(altura(x.esquerda), altura(x.direita));
        y.altura = 1 + Math.max(altura(y.esquerda), altura(y.direita));

        return y;
    }

    public void inserir(int valor) {
        raiz = inserir(raiz, valor);
    }

    public No inserir(No no, int valor) {
        if (no == null)
            return new No(valor);

        if (valor < no.valor)
            no.esquerda = inserir(no.esquerda, valor);
        else if (valor > no.valor)
            no.direita = inserir(no.direita, valor);
        else
            return no;

        no.altura = 1 + Math.max(altura(no.esquerda), altura(no.direita));
        int balance = fatorBalanceamento(no);

        // Casos de rotação
        if (balance > 1 && valor < no.esquerda.valor)
            return rotacaoDireita(no);

        if (balance < -1 && valor > no.direita.valor)
            return rotacaoEsquerda(no);

        if (balance > 1 && valor > no.esquerda.valor) {
            no.esquerda = rotacaoEsquerda(no.esquerda);
            return rotacaoDireita(no);
        }

        if (balance < -1 && valor < no.direita.valor) {
            no.direita = rotacaoDireita(no.direita);
            return rotacaoEsquerda(no);
        }

        return no;
    }

    public boolean buscar(int valor) {
        return buscar(raiz, valor);
    }

    private boolean buscar(No no, int valor) {
        if (no == null) return false;
        if (valor == no.valor) return true;
        if (valor < no.valor) return buscar(no.esquerda, valor);
        return buscar(no.direita, valor);
    }

    public void remover(int valor) {
        raiz = remover(raiz, valor);
    }

    private No remover(No no, int valor) {
        if (no == null) return null;

        if (valor < no.valor) {
            no.esquerda = remover(no.esquerda, valor);
        } else if (valor > no.valor) {
            no.direita = remover(no.direita, valor);
        } else {
            if (no.esquerda == null && no.direita == null)
                return null;

            if (no.esquerda == null)
                return no.direita;
            if (no.direita == null)
                return no.esquerda;

            No sucessor = menorValor(no.direita);
            no.valor = sucessor.valor;
            no.direita = remover(no.direita, sucessor.valor);
        }

        no.altura = 1 + Math.max(altura(no.esquerda), altura(no.direita));
        int balance = fatorBalanceamento(no);

        if (balance > 1 && fatorBalanceamento(no.esquerda) >= 0)
            return rotacaoDireita(no);

        if (balance > 1 && fatorBalanceamento(no.esquerda) < 0) {
            no.esquerda = rotacaoEsquerda(no.esquerda);
            return rotacaoDireita(no);
        }

        if (balance < -1 && fatorBalanceamento(no.direita) <= 0)
            return rotacaoEsquerda(no);

        if (balance < -1 && fatorBalanceamento(no.direita) > 0) {
            no.direita = rotacaoDireita(no.direita);
            return rotacaoEsquerda(no);
        }

        return no;
    }

    private No menorValor(No no) {
        while (no.esquerda != null)
            no = no.esquerda;
        return no;
    }

    public void percursoEmOrdem(No no) {
        if (no != null) {
            percursoEmOrdem(no.esquerda);
            System.out.print(no.valor + " ");
            percursoEmOrdem(no.direita);
        }
    }
}
