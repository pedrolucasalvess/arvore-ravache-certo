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
}