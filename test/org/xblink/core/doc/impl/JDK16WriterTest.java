package org.xblink.core.doc.impl;

import java.io.BufferedWriter;
import java.io.StringWriter;
import java.io.Writer;

import org.junit.Test;
import org.xblink.core.doc.DocWriter;

public class JDK16WriterTest {

	@Test
	public void writeXml() throws Exception {
		Writer writer = new StringWriter();
		DocWriter dw = new JDK16Writer(new BufferedWriter(writer));
		// DocWriter dw = new JDK16Writer(writer);

		// 编写文件
		dw.writeStartDocument();

		dw.writeStartTag("person");
		dw.writeAttribute("firstName", "wu");
		dw.writeAttribute("lastName", "peiwen");

		dw.writeStartTag("phone");
		dw.writeElementText("code", "123");
		dw.writeElementText("nubner", "456");
		dw.writeEndTag("phone");

		dw.writeEndTag("person");

		dw.writeEndDocument();
		dw.close();

		// System.out.println(writer.toString());
	}

}
