package org.jmknpk.standardPoker;

import static org.junit.Assert.*;
import java.util.Arrays;

import org.junit.Test;

public class PipTest {

	@Test
	public void test_Pip() {

		Pip pip;
		Pip[] pipValueArray = new Pip[14];
		Pip[] pipPipNameArray = new Pip[14];
		int[] hashCodes = new int[14];
		Pip pipNamePip;
		Pip pipPip;
		Pip valuePip;
		Pip tempPip;
		int arrayCounter = 0;

		pip = new Pip();
		assertTrue(pip != null);

		for (PipName pipName : PipName.values()) {

			pipNamePip = new Pip(pipName);
			assertTrue(pipNamePip != null);
			assertEquals(pipNamePip.getPipName(),pipName);
			pipPipNameArray[arrayCounter] = pipNamePip;
			hashCodes[arrayCounter++] = pipNamePip.hashCode();
			Pip nullPip = null;
			Object badObject = new String();
			assertTrue(!pipNamePip.equals(nullPip));
			assertTrue(!pipNamePip.equals(badObject));

			switch (pipName) {
				case TWO:
					assertEquals(pipNamePip.getPipName(),PipName.TWO);
					assertEquals(pipNamePip.getValue(),0);
					assertEquals(pipNamePip.getName(),"Two");
					assertEquals(pipNamePip.getInitial(),"2");
					break;
				case THREE:
					assertEquals(pipNamePip.getPipName(),PipName.THREE);
					assertEquals(pipNamePip.getValue(),1);
					assertEquals(pipNamePip.getName(),"Three");
					assertEquals(pipNamePip.getInitial(),"3");
					break;
				case FOUR:
					assertEquals(pipNamePip.getPipName(),PipName.FOUR);
					assertEquals(pipNamePip.getValue(),2);
					assertEquals(pipNamePip.getName(),"Four");
					assertEquals(pipNamePip.getInitial(),"4");
					break;
				case FIVE:
					assertEquals(pipNamePip.getPipName(),PipName.FIVE);
					assertEquals(pipNamePip.getValue(),3);
					assertEquals(pipNamePip.getName(),"Five");
					assertEquals(pipNamePip.getInitial(),"5");
					break;
				case SIX:
					assertEquals(pipNamePip.getPipName(),PipName.SIX);
					assertEquals(pipNamePip.getValue(),4);
					assertEquals(pipNamePip.getName(),"Six");
					assertEquals(pipNamePip.getInitial(),"6");
					break;
				case SEVEN:
					assertEquals(pipNamePip.getPipName(),PipName.SEVEN);
					assertEquals(pipNamePip.getValue(),5);
					assertEquals(pipNamePip.getName(),"Seven");
					assertEquals(pipNamePip.getInitial(),"7");
					break;
				case EIGHT:
					assertEquals(pipNamePip.getPipName(),PipName.EIGHT);
					assertEquals(pipNamePip.getValue(),6);
					assertEquals(pipNamePip.getName(),"Eight");
					assertEquals(pipNamePip.getInitial(),"8");
					break;
				case NINE:
					assertEquals(pipNamePip.getPipName(),PipName.NINE);
					assertEquals(pipNamePip.getValue(),7);
					assertEquals(pipNamePip.getName(),"Nine");
					assertEquals(pipNamePip.getInitial(),"9");
					break;
				case TEN:
					assertEquals(pipNamePip.getPipName(),PipName.TEN);
					assertEquals(pipNamePip.getValue(),8);
					assertEquals(pipNamePip.getName(),"Ten");
					assertEquals(pipNamePip.getInitial(),"T");
					break;
				case JACK:
					assertEquals(pipNamePip.getPipName(),PipName.JACK);
					assertEquals(pipNamePip.getValue(),9);
					assertEquals(pipNamePip.getName(),"Jack");
					assertEquals(pipNamePip.getInitial(),"J");
					break;
				case QUEEN:
					assertEquals(pipNamePip.getPipName(),PipName.QUEEN);
					assertEquals(pipNamePip.getValue(),10);
					assertEquals(pipNamePip.getName(),"Queen");
					assertEquals(pipNamePip.getInitial(),"Q");
					break;
				case KING:
					assertEquals(pipNamePip.getPipName(),PipName.KING);
					assertEquals(pipNamePip.getValue(),11);
					assertEquals(pipNamePip.getName(),"King");
					assertEquals(pipNamePip.getInitial(),"K");
					break;
				case ACE:
					assertEquals(pipNamePip.getPipName(),PipName.ACE);
					assertEquals(pipNamePip.getValue(),12);
					assertEquals(pipNamePip.getName(),"Ace");
					assertEquals(pipNamePip.getInitial(),"A");
					break;
				case UNDEFINED:
					assertEquals(pipNamePip.getPipName(),PipName.UNDEFINED);
					assertEquals(pipNamePip.getValue(),-1);
					assertEquals(pipNamePip.getName(),"Undefined");
					assertEquals(pipNamePip.getInitial(),"u");
					break;
				default:
					fail("Unexpected pipName");
					break;
			}
			
			pip = new Pip();
			pip.setPipName(pipName);
			assertEquals(pip.getPipName(),pipName);

			pipPip = new Pip(pipNamePip);
			assertTrue(pipPip != null);
			assertEquals(pipPip.getPipName(),pipNamePip.getPipName());

		}
		for (int i = -1; i < 13; i++) {
			valuePip = new Pip(i);
			assertTrue(valuePip != null);
			if (i >= -1 && i <= 13) {
				assertEquals(valuePip.getValue(),i);
				pipValueArray[i+1] = new Pip(i);
				assertEquals(pipValueArray[i+1],valuePip); // assure equality is not based on object pointer.
				assertTrue(!valuePip.equals(new String())); //assure non equality of non-class object.
				assertTrue(!valuePip.equals(null)); // assure non equality with null pointer.
			} else {
				assertEquals(valuePip.getValue(),-1);
			}
		}
		// un-sort in order to test compareTo() during sorts.
		tempPip = pipValueArray[3];
		pipValueArray[3] = pipValueArray[8];
		pipValueArray[8] = tempPip;
		tempPip = pipValueArray[0];
		pipValueArray[0] = pipValueArray[13];
		pipValueArray[13] = tempPip;
		
		Arrays.sort(pipValueArray);
		Arrays.sort(pipPipNameArray);
		Arrays.sort(hashCodes);
		
		for (int i = 0; i < 14; i++) {
			assertEquals(hashCodes[i],pipValueArray[i].hashCode());
			assertEquals(pipPipNameArray[i].hashCode(),pipValueArray[i].hashCode());
			assertTrue(pipValueArray[i].equals(pipPipNameArray[i]));
			assertTrue(pipPipNameArray[i].equals(pipValueArray[i]));
		}

		
// Catch NullPointerException on all methods accepting object(s).		
/*
  		try { ; fail("NullPointerException NOT thrown!");
 		} catch (NullPointerException e) {}
*/				

		PipName nullPipName = null;
		Pip nullPip = null;

		try { new Pip(nullPipName); fail("NullPointerException NOT thrown!");
		} catch (NullPointerException e) {}

		try { new Pip(nullPip); fail("NullPointerException NOT thrown!");
		} catch (NullPointerException e) {}

		pip = new Pip();
  		try { pip.setPipName(nullPipName); fail("NullPointerException NOT thrown!");
 		} catch (NullPointerException e) {}

		
		Pip pipc1 = new Pip();
		Pip pipc2 = null;
		try { pipc1.compareTo(pipc2); fail("NullPointerException NOT thrown!");
		} catch (NullPointerException e) {}
		
		
	}

}
