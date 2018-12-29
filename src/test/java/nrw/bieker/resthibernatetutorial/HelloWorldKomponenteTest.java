package nrw.bieker.resthibernatetutorial;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import nrw.bieker.resthibernatetutorial.komponenten.HelloWorldKomponenteI;

@RunWith(SpringRunner.class)
@SpringBootTest
public class HelloWorldKomponenteTest {
	
	@Autowired
	private HelloWorldKomponenteI helloWorldKomponente;
	
	@Test
	public void helloWorldKomponenteTest() throws Exception{
		String helloWorld = helloWorldKomponente.helloWorld();
		assertEquals("Hello World von der Komponente über Interface", helloWorld);
	}
	
}
