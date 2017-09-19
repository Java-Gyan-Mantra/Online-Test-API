package com.sj.online.api.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.xhtmlrenderer.pdf.ITextRenderer;

@Component
public class PdfGeneratortUtil {
	@Autowired
	private TemplateEngine templateEngine;

	public void createPdf(String templateName, Map<Object, Object> map)
			throws Exception {
		Assert.notNull(templateName, "The templateName can not be null");
		Context ctx = new Context();
		if (map != null) {
			Iterator<Entry<Object, Object>> itMap = map.entrySet().iterator();
			while (itMap.hasNext()) {
				Map.Entry<Object, Object> pair = itMap.next();
				ctx.setVariable(pair.getKey().toString(), pair.getValue());
			}
		}
		String processedHtml = templateEngine.process(templateName, ctx);
		FileOutputStream os = null;
		String fileName = "result";
		String home = System.getProperty("user.home");
		try {
			final File outputFile = File.createTempFile(fileName, ".pdf",
					new File(home + "/Downloads/"));
			os = new FileOutputStream(outputFile);
			ITextRenderer renderer = new ITextRenderer();
			renderer.setDocumentFromString(processedHtml);
			renderer.layout();
			renderer.createPDF(os, false);
			renderer.finishPDF();
			System.out.println("PDF created successfully");
		} finally {
			if (os != null) {
				try {
					os.close();
				} catch (IOException e) { /* ignore */
				}
			}
		}

	}
}