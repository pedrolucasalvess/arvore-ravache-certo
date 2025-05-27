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


}