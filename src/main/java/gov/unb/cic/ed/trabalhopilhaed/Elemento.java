package gov.unb.cic.ed.trabalhopilhaed;

public class Elemento {        

    private Object valor;
    private Elemento proximo;
    
    public Elemento() {        
    }
    
    public Elemento(Object valor, Elemento proximo){
        this.valor = valor;
        this.proximo = proximo;
    }    

    public Object getValor() {
        return valor;
    }

    public void setValor(Object valor) {
        this.valor = valor;
    }

    public Elemento getProximo() {
        return proximo;
    }

    public void setProximo(Elemento proximo) {
        this.proximo = proximo;
    }
}
