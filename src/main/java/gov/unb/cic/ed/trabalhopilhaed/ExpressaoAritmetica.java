package gov.unb.cic.ed.trabalhopilhaed;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ExpressaoAritmetica {

    private String expressao;

    private boolean possuiCaracteresInvalidosNumericos() {
        return !expressao.matches("([0-9]|[(|)|+|\\-|*|/])+$");
    }
    
    private boolean possuiCaracteresInvalidosVariaveis() {
        return !expressao.matches("([A-Z]|[(|)|+|\\-|*|/])+$");
    }

    //Item 3.4 do trabalho
    public int avaliar() throws ProcessadorException {
        validarNumerico();        
        Pilha p = new Pilha();
        String[] vetor = processaRegex("[0-9]+|.", getPosfixa(false));
        for(String e : vetor){
            if(" ".equals(e)){
                continue;
            }
            
            if(isValorNumerico(e)){
                p.empilhar(e);
            }
            
            if(isOperador(e)){
                int valor1, valor2, res = 0;
                valor1 = Integer.parseInt((String)p.desempilhar());
                valor2 = Integer.parseInt((String)p.desempilhar());
                if("+".equals(e)){
                    res = valor2+valor1;
                }
                
                if("-".equals(e)){
                    res = valor2-valor1;
                }
                
                if("*".equals(e)){
                    res = valor2*valor1;
                }
                
                if("/".equals(e)){
                    res = valor2/valor1;
                }
                
                p.empilhar(Integer.toString(res));
            }
        }
        if(p.getTamanho()>1){
            throw new ProcessadorException("Erro ao processar expressão: "+getExpressao());
        }
        return Integer.parseInt((String)p.getTopo());

    }

    public void validarVariavel() throws ProcessadorException {
        if (possuiCaracteresInvalidosVariaveis()) {
            throw new ProcessadorException("A expressão possui caracteres inválidos.");
        }

        if (!isExpressaoValida()) {
            throw new ProcessadorException("A expressão é inválida (parênteses não balanceados).");
        }

    }
    
    public void validarNumerico() throws ProcessadorException {
        if (possuiCaracteresInvalidosNumericos()) {
            throw new ProcessadorException("A expressão possui caracteres inválidos.");
        }

        if (!isExpressaoValida()) {
            throw new ProcessadorException("A expressão é inválida (parênteses não balanceados).");
        }

    }

    public static int getPrioridade(String operador) throws ProcessadorException {
        if ("+".equals(operador) || "-".equals(operador)) {
            return 1;
        }

        if ("*".equals(operador) || "/".equals(operador)) {
            return 2;
        }

        throw new ProcessadorException("Prioridade indefinida para operador: " + operador);
    }

    public static boolean isParenteses(String s) {
        if ("(".equals(s) || ")".equals(s)) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean isOperador(String s) {
        if ("+".equals(s) || "-".equals(s) || "/".equals(s) || "*".equals(s)) {
            return true;
        } else {
            return false;
        }
    }

    //Caso seja passado comVariaveis = true, aceita expressoes do tipo A+B, caso
    //contrário, somente expressões numéricas do tipo 1+2
    //Item 3.3 do trabalho
    public String getPosfixa(boolean comVariaveis) throws ProcessadorException {        
        String[] vetor;
        
        if(comVariaveis){
            validarVariavel();
            vetor = getVetorVariavel();
        }else{
            validarNumerico();
            vetor = getVetorNumerico();
        }
        
        String resposta = "";
        Pilha pilha = new Pilha();
        
        for (String e : vetor) {
            if (!comVariaveis && isValorNumerico(e)) {
                resposta += " " + e;
            }
            
            if (comVariaveis && isValorVariavel(e)) {
                resposta += " " + e;
            }

            if (isOperador(e)) {
                while (!pilha.isVazio() && !isParenteses((String) pilha.getTopo()) && !(getPrioridade(e) > getPrioridade((String) pilha.getTopo()))) {
                    resposta += " " + pilha.desempilhar();
                }
                pilha.empilhar(e);
            }

            if (isParenteses(e)) {
                if ("(".equals(e)) {
                    pilha.empilhar(e);
                } else if (")".equals(e)) {
                    while (!"(".equals(pilha.getTopo())) {
                        resposta += " " + pilha.desempilhar();
                    }
                    pilha.desempilhar();
                }
            }
        }


        while (!pilha.isVazio()) {
            resposta += " " + pilha.desempilhar();
        }
        
        resposta = resposta.trim();

        return resposta;
    }

    public String getExpressao() {
        return expressao;
    }

    public void setExpressao(String expressao) {
        if (expressao != null) {
            expressao.replaceAll(" ", "");
        }

        this.expressao = expressao;
    }

    public static boolean isValorNumerico(String s) {
        try {
            Integer.parseInt(s);
            return true;
        } catch (NumberFormatException ex) {
            return false;
        }
    }
    
    public static boolean isValorVariavel(String s) {
        return s.matches("[A-Z]+");        
    }

    //Item 3.2 do trabalho
    public boolean isExpressaoValida() {
        Pilha p = new Pilha();
        String[] vetor = getParenteses();
        for (String e : vetor) {
            if ("(".equals(e)) {
                p.empilhar(e);
            } else {
                try {
                    p.desempilhar();
                } catch (PilhaVaziaException ex) {
                    return false;
                }
            }
        }
        return p.isVazio();
    }

    public String[] getVetorNumerico() {
        return processaRegex("[0-9]+|.", expressao);
    }
    
    public String[] getVetorVariavel() {
        return processaRegex("[A-Z]+|.", expressao);
    }

    public String[] getParenteses() {
        return processaRegex("\\(|\\)", expressao);
    }

    public static String[] processaRegex(String regex, String s) {
        int tamanho = 0;
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(s);
        while (matcher.find()) {
            tamanho++;
        }

        String[] resposta = new String[tamanho];
        matcher = pattern.matcher(s);
        tamanho = 0;
        while (matcher.find()) {
            resposta[tamanho] = matcher.group();
            tamanho++;
        }
        return resposta;
    }
}
