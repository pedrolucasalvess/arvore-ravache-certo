public class ArvoreRubroNegra {
    private No raiz;

    private static class No {
        enum Cor { Red, Black }
        int valor;
        Cor cor;
        No esquerdo, direito, pai;

        No(int valor) {
            this.valor = valor;
            this.cor = Cor.Red;
            this.esquerdo = null;
            this.direito = null;
            this.pai = null;
        }
    }

    // VER SE O NÓ É VERMELHO
    private boolean ehVermelho(No no) {
        return no != null && no.cor == No.Cor.Red;
    }

    // TROCA DE COR QUANDO TEM DOIS FILHOS VERMELHOS/ COLORAÇÃO
    private void trocarCor(No no) {
        no.cor = (no.cor == No.Cor.Red) ? No.Cor.Black : No.Cor.Red;
        if (no.esquerdo != null)
            no.esquerdo.cor = (no.esquerdo.cor == No.Cor.Red) ? No.Cor.Black : No.Cor.Red;
        if (no.direito != null)
            no.direito.cor = (no.direito.cor == No.Cor.Red) ? No.Cor.Black : No.Cor.Red;
    }

    private No rotacaoEsquerda(No no) {
        No x = no.direito;
        no.direito = x.esquerdo;
        if (x.esquerdo != null) x.esquerdo.pai = no;
        x.esquerdo = no;

        x.cor = no.cor;
        no.cor = No.Cor.Red;

        x.pai = no.pai;
        no.pai = x;
        if (x.esquerdo != null) x.esquerdo.pai = x;

        return x;
    }

    private No rotacaoDireita(No no) {
        No x = no.esquerdo;
        no.esquerdo = x.direito;
        if (x.direito != null) x.direito.pai = no;
        x.direito = no;

        x.cor = no.cor;
        no.cor = No.Cor.Red;

        x.pai = no.pai;
        no.pai = x;
        if (x.direito != null) x.direito.pai = x;

        return x;
    }

    // BALANCEAMENTO DO NÓ 
    private No balancear(No no) {
        if (ehVermelho(no.direito) && !ehVermelho(no.esquerdo))
            no = rotacaoEsquerda(no);

        if (ehVermelho(no.esquerdo) && ehVermelho(no.esquerdo.esquerdo))
            no = rotacaoDireita(no);

        if (ehVermelho(no.esquerdo) && ehVermelho(no.direito))
            trocarCor(no);

        return no;
    }

    // INSERÇÃO DO NÓ
    public void inserir(int valor) {
        raiz = inserir(raiz, valor);
        raiz.cor = No.Cor.Black;
    }

    private No inserir(No no, int valor) {
        if (no == null)
            return new No(valor);

        if (valor < no.valor) {
            no.esquerdo = inserir(no.esquerdo, valor);
            no.esquerdo.pai = no;
        } else if (valor > no.valor) {
            no.direito = inserir(no.direito, valor);
            no.direito.pai = no;
        }

        return balancear(no);
    }

    public boolean buscar(int valor) {
        return buscar(raiz, valor);
    }

    // BUSCA DO NÓ
    private boolean buscar(No no, int valor) {
        if (no == null) return false;
        if (valor == no.valor) return true;
        if (valor < no.valor) return buscar(no.esquerdo, valor);
        return buscar(no.direito, valor);
    }

     public void remover(int valor) {
        if (buscar(valor)) {
            if (!ehVermelho(raiz.esquerdo) && !ehVermelho(raiz.direito))
                raiz.cor = No.Cor.Red;

            raiz = remover(raiz, valor);
            if (raiz != null)
                raiz.cor = No.Cor.Black;
        }
    }

    private No moverVermelhoEsquerda(No no) {
        trocarCor(no);
        if (ehVermelho(no.direito.esquerdo)) {
            no.direito = rotacaoDireita(no.direito);
            no = rotacaoEsquerda(no);
            trocarCor(no);
        }
        return no;
    }

    private No moverVermelhoDireita(No no) {
        trocarCor(no);
        if (ehVermelho(no.esquerdo.esquerdo)) {
            no = rotacaoDireita(no);
            trocarCor(no);
        }
        return no;
    }

    private No removerMinimo(No no) {
        if (no.esquerdo == null)
            return null;

        if (!ehVermelho(no.esquerdo) && !ehVermelho(no.esquerdo.esquerdo))
            no = moverVermelhoEsquerda(no);

        no.esquerdo = removerMinimo(no.esquerdo);
        return balancear(no);
    }

    // REMOÇÃO DO NÓ
    private No remover(No no, int valor) {
        if (valor < no.valor) {
            if (no.esquerdo != null) {
                if (!ehVermelho(no.esquerdo) && !ehVermelho(no.esquerdo.esquerdo))
                    no = moverVermelhoEsquerda(no);
                no.esquerdo = remover(no.esquerdo, valor);
            }
        } else {
            if (ehVermelho(no.esquerdo))
                no = rotacaoDireita(no);

            if (valor == no.valor && no.direito == null)
                return null;

            if (no.direito != null) {
                if (!ehVermelho(no.direito) && !ehVermelho(no.direito.esquerdo))
                    no = moverVermelhoDireita(no);

                if (valor == no.valor) {
                    No sucessor = menor(no.direito);
                    no.valor = sucessor.valor;
                    no.direito = removerMinimo(no.direito);
                } else {
                    no.direito = remover(no.direito, valor);
                }
            }
        }

        return balancear(no);
    }

    private No menor(No no) {
        while (no.esquerdo != null)
            no = no.esquerdo;
        return no;
    }
}
