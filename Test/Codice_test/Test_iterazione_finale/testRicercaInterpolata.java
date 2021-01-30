package test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import record.Giochi;
import utility.RicercaInterpolata;

class testRicercaInterpolata {

	@Test
	void testRicercaStringhe() {
		RicercaInterpolata ricercaInterpolataTest = new RicercaInterpolata();
		ArrayList <String> arrayTest = new ArrayList<>();
		String[] v = {"eee", "fff", "ggg","hhh","aaa","bbb","ccc","ddd","iii","lll","mmm"};
		for(int i=0;i<v.length;i++)
			arrayTest.add(v[i]);
		
		
		Collections.sort(arrayTest);
		
		assertEquals(-1, RicercaInterpolata.TrovaElementoComp(arrayTest, "prova2"), "prova1 non è nell'array");
		assertEquals(-1, RicercaInterpolata.TrovaElementoComp(arrayTest, "testfallito"),"testfallito non è nell'array");
		assertEquals(0, RicercaInterpolata.TrovaElementoComp(arrayTest, "aaa"),"aaa è nell'array in prima posizione, indice 0");
		assertEquals(7, RicercaInterpolata.TrovaElementoComp(arrayTest, "hhh"),"hhh è nell'array in ottava posizione, indice 7");
	}
	
	
	@Test
	void testRicercaGiochi() {
		RicercaInterpolata ricercaInterpolataTest = new RicercaInterpolata();
		ArrayList <Giochi> arrayTest = new ArrayList<>();
		//Riempio l'ArrayList di Giochi
		for(int i=10;i<100;i++) {
			arrayTest.add(new Giochi("prova"+i,0,0,0));

		}
		
		Collections.sort(arrayTest);
		
		
		assertEquals(40, RicercaInterpolata.TrovaElementoComp(arrayTest, new Giochi("prova50",0,0,0)), "il gioco prova50 è in quarantunesima posizione");
		assertEquals(-1, RicercaInterpolata.TrovaElementoComp(arrayTest, new Giochi("prova101",0,0,0)),"prova101 non è nell'array");
		assertEquals(63, RicercaInterpolata.TrovaElementoComp(arrayTest, new Giochi("prova73",0,0,0)),"il gioco prova73 è in sessantatresima posizione");
	}

}
