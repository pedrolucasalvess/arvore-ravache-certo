public class ArvoreAVL {
    private No raiz;

    public void inserir(int valor) {
        raiz = inserir(raiz, valor);   
    }

    private No inserir(No no, int valor) {
        if (no == null) 
            return new No(valor);

        if (valor < no.valor)
          no.esquerda = inserir(no.esquerda, valor);
        else if (valor > no.valor)
            no.direita = inserir(no.direita, valor);
            
        return no;
    }

    public boolean buscar (int valor) {
        return buscar(raiz,valor);
    }   

    private boolean buscar(No no, int valor){
        if (no == null) 
            return false;
        if (valor == no.valor) 
            return true;
        if (valor < no.valor) 
            return buscar(no.esquerda, valor);
            return buscar (no.direita, valor);
    }
    

    public void remover(int valor){
        raiz = remover(raiz, valor);
    }

    private No remover (No no, int valor){
        if (no == null) 
            return null;
        
        if (valor < no.valor){
            no.esquerda = remover(no.esquerda, valor);
        } 
        else if (valor > no.valor) {
            no.direita = remover (no.direita, valor);
        }
        else{ 
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

        return no;
    }

    private No menorValor(No no) {
        while (no.esquerda != null)
            no = no.esquerda;
        return no;
}
}