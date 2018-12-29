package nrw.bieker.resthibernatetutorial.komponenten;

import org.springframework.stereotype.Component;

@Component
public class HelloWorldKomponente implements HelloWorldKomponenteI{

	@Override
	public String helloWorld() {
		return "Hello World von der Komponente über Interface";
	}
}
