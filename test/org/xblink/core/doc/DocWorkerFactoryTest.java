package org.xblink.core.doc;

import java.io.StringReader;
import java.io.StringWriter;

import org.junit.Assert;
import org.junit.Test;
import org.xblink.core.doc.impl.XmlWriter;

public class DocWorkerFactoryTest {

	@Test
	public void createANYReader() throws Exception {
		DocReader docReader = DocWorkerFactory.createAnyReader(new StringReader("123"), "MyType");
		Assert.assertNotNull(docReader);
	}

	@Test
	public void createANYWriter() throws Exception {
		DocWriter docWriter = DocWorkerFactory.createAnyWriter(new StringWriter(), "MyType");
		Assert.assertNotNull(docWriter);
	}

	@Test
	public void createANYWriter2() throws Exception {
		DocWriter docWriter = DocWorkerFactory.createAnyWriter(new StringWriter(), "XML");
		Assert.assertTrue(docWriter instanceof XmlWriter);
	}

	@Test
	public void createXmlWriter() throws Exception {
		DocWriter docWriter = DocWorkerFactory.createXmlWriter(new StringWriter());
		Assert.assertTrue(docWriter instanceof XmlWriter);
	}

	@Test
	public void createNotExistWriter() throws Exception {
		boolean notExist = false;
		try {
			DocWriter docWriter = DocWorkerFactory.createAnyWriter(new StringWriter(), "NotExist");
			Assert.assertNotNull(docWriter);
		} catch (Exception e) {
			notExist = true;
		}
		Assert.assertTrue(notExist);
	}

}
