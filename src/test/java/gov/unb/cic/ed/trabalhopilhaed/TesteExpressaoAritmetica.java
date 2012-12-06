package gov.unb.cic.ed.trabalhopilhaed;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class TesteExpressaoAritmetica
        extends TestCase {

    public TesteExpressaoAritmetica(String testName) {
        super(testName);
    }

    public static Test suite() {
        return new TestSuite(TesteExpressaoAritmetica.class);
    }

    public void testExpressoes() throws ProcessadorException {
        //  Infixa                          Posfixa
        //1 (A+B*C)                         A B C * +
        //2 (A*(B+C)/D-E)                   A B C + * D / E -
        //3 (A+B*(C-D*(E-F)-G*H)-I*3)       A B C D E F - * - G H * - * + I 3 * -
        //4 (A+B*C/D*E-F)                   A B C * D / E * + F -
        //5 (A+(B-(C+(D-(E+F)))))           A B C D E F + - + - +
        //6 (A*(B+(C*(D+(E*(F+G))))))       A B C D E F G + * + * + *
        ExpressaoAritmetica e = new ExpressaoAritmetica();
        
        //1
        e.setExpressao("(A+B*C)");
        assertEquals("A B C * +", e.getPosfixa(true));
        
        //2
        e.setExpressao("(A*(B+C)/D-E)");
        assertEquals("A B C + * D / E -", e.getPosfixa(true));
        
        //3
        e.setExpressao("(A+B*(C-D*(E-F)-G*H)-I*J)");
        assertEquals("A B C D E F - * - G H * - * + I J * -", e.getPosfixa(true));
        
        //4
        e.setExpressao("(A+B*C/D*E-F)");
        assertEquals("A B C * D / E * + F -", e.getPosfixa(true));
        
        //5
        e.setExpressao("(A+(B-(C+(D-(E+F)))))");
        assertEquals("A B C D E F + - + - +", e.getPosfixa(true));
        
        //6
        e.setExpressao("(A*(B+(C*(D+(E*(F+G))))))");
        assertEquals("A B C D E F G + * + * + *", e.getPosfixa(true));                
        
    }
}
