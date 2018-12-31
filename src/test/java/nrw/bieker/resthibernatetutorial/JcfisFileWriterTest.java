package nrw.bieker.resthibernatetutorial;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import nrw.bieker.resthibernatetutorial.io.JcfisFileWriter;

@RunWith(SpringRunner.class)
@SpringBootTest
public class JcfisFileWriterTest {
	
	@Autowired
	private JcfisFileWriter jcfisFileWriter;
	
	@Test
	public void jcfisFileWriterTest() {
		assertEquals(true,jcfisFileWriter.createFileInNetworkFolder());
	}

}
