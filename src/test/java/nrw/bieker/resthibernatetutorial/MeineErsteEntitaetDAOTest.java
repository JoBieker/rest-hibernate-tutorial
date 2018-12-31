package nrw.bieker.resthibernatetutorial;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Iterator;
import java.util.List;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import nrw.bieker.resthibernatetutorial.entity.MeineErsteEntitaet;
import nrw.bieker.resthibernatetutorial.entity.MeineErsteEntitaetDAO;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MeineErsteEntitaetDAOTest {
	
	@Autowired
	private MeineErsteEntitaetDAO dao;
	
	/**
	 *  1. MeineErsteEntitaet anlegen
	 *  2. Alle Objekte aus der Datenbank holen
	 *  3. Prüfen, dass die Entität korrekt angelegt wurde
	 *  @throws Exception
	 */
	
	@Test
	public void simplerDAOTest() throws Exception {
		// Kein Element in der Datenbank
		List<MeineErsteEntitaet> alleEntitaeten = pruefeKeineEntitaetAngelegt();
		// Element anlegen
		MeineErsteEntitaet e = new MeineErsteEntitaet();
		e.setEinDatenfeld("Das ist ein Test");
		dao.create(e);
		alleEntitaeten = pruefeEineEntitaetAngelegt();
		// In der Enitität wurde der richtige Inhalt abgelegt
		String inhalt = alleEntitaeten.get(0).getEinDatenfeld();
		assertEquals("Das ist ein Test", inhalt);
	}
	
	@Test
	public void testConstraints() throws Exception {
		// Test anlegen leeres Feld
		try {
			MeineErsteEntitaet e = new MeineErsteEntitaet();
			//e.setEinDatenfeld("Das ist ein Test.");
			dao.create(e);
		} catch(ConstraintViolationException e) {
			Iterator<ConstraintViolation<?>> iter = e.getConstraintViolations().iterator();
			while(iter.hasNext()) {
				ConstraintViolation<?> constraint = iter.next();
				
				assertTrue("MeineErsteEntitaet.einDatenfeld:NOTEMPTY".equals(constraint.getMessage()) 
						^ ("MeineErsteEntitaet.einDatenfeld:NOTNULL".equals(constraint.getMessage())));
			}
			pruefeKeineEntitaetAngelegt();
			//System.out.println(e.getConstraintViolations().iterator().next().getMessage());
		}

		// Test anlegen 16 Zeichen Max Rule
		try {
			MeineErsteEntitaet e = new MeineErsteEntitaet();
			e.setEinDatenfeld("12345678123456789");
			dao.create(e);
		} catch(ConstraintViolationException e) {
			assertEquals(1,e.getConstraintViolations().size());
			assertEquals("MeineErsteEntitaet.einDatenfeld:LENGTH", e.getConstraintViolations().iterator().next().getMessage());
			pruefeKeineEntitaetAngelegt();
			//System.out.println(e.getConstraintViolations().iterator().next().getMessage());
		}
		
		try {
			MeineErsteEntitaet e = new MeineErsteEntitaet();
			e.setEinDatenfeld("");
			dao.create(e);
		} catch(ConstraintViolationException e) {
			assertEquals(1,e.getConstraintViolations().size());
			assertEquals("MeineErsteEntitaet.einDatenfeld:NOTEMPTY", e.getConstraintViolations().iterator().next().getMessage());
			pruefeKeineEntitaetAngelegt();

		}
	}
	
	private List<MeineErsteEntitaet> pruefeKeineEntitaetAngelegt() {
		List<MeineErsteEntitaet> alleEntitaeten = dao.findAll();
		assertEquals(0, alleEntitaeten.size());
		return alleEntitaeten;
	}
	
	private List<MeineErsteEntitaet> pruefeEineEntitaetAngelegt() {
		List<MeineErsteEntitaet> alleEntitaeten = dao.findAll();
		assertEquals(1, alleEntitaeten.size());
		return alleEntitaeten;
	}

}
