package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import Record.Giochi;
import utility.RicercaInterpolata;

class testRicercaInterpolata {

	@Test
	void testRicercaStringhe() {
		RicercaInterpolata ricercaInterpolataTest = new RicercaInterpolata();
		String[] v = {"aaa", "bbb", "ccc","ddd","eee","fff","ggg","hhh","iii","lll","mmm"};
		
		
		assertFalse(RicercaInterpolata.TrovaElementoComp(v, "prova2"), "prova1 non è nell'array");
		assertFalse(RicercaInterpolata.TrovaElementoComp(v, "testfallito"),"testfallito non è nell'array");
		assertTrue(RicercaInterpolata.TrovaElementoComp(v, "aaa"),"aaa è nell'array in prima posizione, indice 0");
		assertTrue(RicercaInterpolata.TrovaElementoComp(v, "hhh"),"hhh è nell'array in ottava posizione, indice 7");
	}
	
	
	@Test
	void testRicercaGiochi() {
		RicercaInterpolata ricercaInterpolataTest = new RicercaInterpolata();
		Giochi g[] = new Giochi[90];
		for(int i=10;i<g.length;i++)
			g[i]= new Giochi("prova"+i, 0, 0, 0);
		
		
		assertTrue(RicercaInterpolata.TrovaElementoComp(g, new Giochi("prova50",0,0,0)), "il gioco prova50 è in quarantunesima posizione");
		assertFalse(RicercaInterpolata.TrovaElementoComp(g, new Giochi("prova101",0,0,0)),"prova101 non è nell'array");
		assertTrue(RicercaInterpolata.TrovaElementoComp(g, new Giochi("prova73",0,0,0)),"il gioco prova73 è in sessantatresima posizione");
	}

}
