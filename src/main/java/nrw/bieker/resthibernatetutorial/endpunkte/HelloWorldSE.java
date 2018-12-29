package nrw.bieker.resthibernatetutorial.endpunkte;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import nrw.bieker.resthibernatetutorial.komponenten.HelloWorldKomponenteI;

@RestController
public class HelloWorldSE {
	
	@Autowired
	protected HelloWorldKomponenteI helloWorldKomponente;

	@GetMapping(path="/helloworldse")
	public String helloWorldSE() {
		return helloWorldKomponente.helloWorld();
	}
}
