package gov.unb.cic.ed.trabalhopilhaed;

public class App {

    public static void main(String[] args) throws ProcessadorException {
//        String s = "1+3+65*(3*5)";
//        Pattern pattern = Pattern.compile("[0-9]+|.");
//        Matcher matcher = pattern.matcher(s);
//        while (matcher.find()) {
//          System.out.println("M:" + matcher.group());
//        }
//        
//        System.out.println("spliting");
////        String[] arr = s.split("[0-9]+|.");
//        String[] arr = pattern.split(s);
//        for(int i=0;i<arr.length;i++){
//            System.out.println("id: "+i+" - "+arr[i]);
//        }

        ExpressaoAritmetica e = new ExpressaoAritmetica();
        //e.setExpressao("1*2+3-(4/5+6)");
        e.setExpressao("A*B+C-(D/E+F)");
//        e.setExpressao("1*2+3-4");
//        for (String s : e.getParenteses()) {
//            System.out.println("s: " + s);
//        }
//
//        for (String v : e.getVetor()) {
//            System.out.println("v: " + v);
//        }
        
        System.out.println("posfixa:["+e.getPosfixa(true)+"]");



//        ExpressaoAritmetica e = new ExpressaoAritmetica();
//        e.setExpressao("123+()/");
//        System.out.println(e.getExpressao()+ " "+e.isExpressaoValida());
//        
//        e.setExpressao("123+");
//        System.out.println(e.getExpressao()+ " "+e.isExpressaoValida());
//        
//        e.setExpressao("123+()/");
//        System.out.println(e.getExpressao()+ " "+e.isExpressaoValida());
//        
//        e.setExpressao("123+()/");
//        System.out.println(e.getExpressao()+ " "+e.isExpressaoValida());
    }
}
