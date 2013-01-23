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
        e.setExpressao("(100*(200+300)/400-500)");
        assertEquals(-375, e.avaliar());
        
        //3
        e.setExpressao("(A+B*(C-D*(E-F)-G*H)-I*J)");
        assertEquals("A B C D E F - * - G H * - * + I J * -", e.getPosfixa(true));
        e.setExpressao("(100+200*(300-400*(500-600)-700*800)-900*1000)");
        assertEquals(-104839900, e.avaliar());
        
        //4
        e.setExpressao("(A+B*C/D*E-F)");
        assertEquals("A B C * D / E * + F -", e.getPosfixa(true));
        e.setExpressao("(100+200*300/400*500-600)");
        assertEquals(74500, e.avaliar());
        
        //5
        e.setExpressao("(A+(B-(C+(D-(E+F)))))");
        assertEquals("A B C D E F + - + - +", e.getPosfixa(true));
        e.setExpressao("(100+(200-(300+(400-(500+600)))))");
        assertEquals(700, e.avaliar());
        
        //6
        e.setExpressao("(A*(B+(C*(D+(E*(F+G))))))");
        assertEquals("A B C D E F G + * + * + *", e.getPosfixa(true));                
        e.setExpressao("(1*(2-(3*(4+(5*(6+7))))))");
        assertEquals(-205, e.avaliar());
        
    }
}
