package gov.unb.cic.ed.trabalhopilhaed;

public class ExpressaoConsole {

    public static void main(String[] args) throws ProcessadorException {
        System.out.println("Universidade de Brasília - Instituto de Ciências Exatas");
        System.out.println("Departamento de Ciência da Computação");
        System.out.println("Avaliação de Expressões Aritméticas (forma posfixa)");
        System.out.println("Prof. Eduardo A. P. Alchieri");
        System.out.println("Novembro 2012");
        System.out.println("Alunos:");
        System.out.println("Tiago Lage Payne de Pádua - 12/0142457");
        System.out.println("Alex Leite");
        System.out.println("Ronaldo S. Ferreira Jr.");
                
        if(args.length == 0 || "-h".equals(args[0]) || args.length > 1){
            help();
            return;
        }
        
        ExpressaoAritmetica e = new ExpressaoAritmetica();
        e.setExpressao(args[0].replaceAll(" ", "").toUpperCase());

        if (e.getExpressao().isEmpty()) {
            help();
            return;
        }

        if (!e.isExpressaoValida()) {
            System.out.println("Expressão inválida.");
            help();
            return;
        }
        
        try {
            e.validarVariavel();
            System.out.println("Posfixa: " + e.getPosfixa(true));
            return;
        } catch (Exception ex) {
            //Nao faz nada, tenta processar como valores
        }
        
        try {
            System.out.println("Posfixa: " + e.getPosfixa(false));
            System.out.println("Resultado: " + Integer.toString(e.avaliar()));
        } catch (Exception ex) {
            System.out.println("Erro: " + ex.getMessage());
            help();
        }
    }
    
    public static void help(){
        System.out.println("Digite a expressão a ser avaliada sem espaços.");
    }
}
