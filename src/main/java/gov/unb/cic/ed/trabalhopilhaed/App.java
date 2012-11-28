package gov.unb.cic.ed.trabalhopilhaed;

public class App {

    public static void main(String[] args) {

        String[] expressao = processar("(56+67))");

        System.out.println("Expressão equilibrada: " + isParentesesEquilibrados(expressao));
    }

    public static boolean isParentesesEquilibrados(String[] expressao) {
        Pilha p = new Pilha();
        for (int i = 0; i < expressao.length; i++) {
            if (expressao[i].equals("(")) {
                p.inserir("(");
            }

            if (expressao[i].equals(")")) {
                try{
                    p.remover();
                }catch(PilhaVaziaException ex){
                    return false;
                }
            }

        }
        
        return p.isVazio();
    }

    public static String[] processar(String expressao) {
        String[] resposta = new String[expressao.length()];
        for (int i = 0; i < expressao.length(); i++) {
            resposta[i] = expressao.substring(i, i + 1);
        }
        return resposta;
    }
}
