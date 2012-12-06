package gov.unb.cic.ed.trabalhopilhaed;

public class Pilha {

    private int tamanho;
    private Elemento topo;

    public Pilha() {
        tamanho = 0;
        topo = null;
    }

    public void empilhar(Object valor) {
        Elemento e = new Elemento(valor, topo);
        topo = e;
        tamanho++;
    }

    public Object desempilhar() throws PilhaVaziaException {
        if (isVazio()) {
            throw new PilhaVaziaException("Pilha vazia");
        } else {
            Object retorno = topo.getValor();
            topo = topo.getProximo();
            tamanho--;
            return retorno;
        }
    }

    public int getTamanho() {
        return tamanho;
    }

    public boolean isVazio() {
        return topo == null;
    }

    public Object getTopo() throws PilhaVaziaException {
        if (isVazio()) {
            throw new PilhaVaziaException("Pilha vazia");
        } else {
            return topo.getValor();
        }
    }

    @Override
    public String toString() {
        String retorno = "";
        if (!isVazio()) {
            Elemento e = topo;
            int i = 1;
            do {                
                retorno += "Elemento: " + i + " - " + e.getValor().toString() + "\n";
                i++;
                e = e.getProximo();
            } while (e != null);
        }
        return retorno;
    }
}
