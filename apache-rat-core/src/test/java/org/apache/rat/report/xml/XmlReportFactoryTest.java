/*
 * Licensed to the Apache Software Foundation (ASF) under one   *
 * or more contributor license agreements.  See the NOTICE file *
 * distributed with this work for additional information        *
 * regarding copyright ownership.  The ASF licenses this file   *
 * to you under the Apache License, Version 2.0 (the            *
 * "License"); you may not use this file except in compliance   *
 * with the License.  You may obtain a copy of the License at   *
 *                                                              *
 *   http://www.apache.org/licenses/LICENSE-2.0                 *
 *                                                              *
 * Unless required by applicable law or agreed to in writing,   *
 * software distributed under the License is distributed on an  *
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY       *
 * KIND, either express or implied.  See the License for the    *
 * specific language governing permissions and limitations      *
 * under the License.                                           *
 */
package org.apache.rat.report.xml;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.util.regex.Pattern;

import org.apache.rat.ReportConfiguration;
import org.apache.rat.analysis.MockLicenseMatcher;
import org.apache.rat.api.MetaData;
import org.apache.rat.api.RatException;
import org.apache.rat.report.RatReport;
import org.apache.rat.report.claim.ClaimStatistic;
import org.apache.rat.report.xml.writer.IXmlWriter;
import org.apache.rat.report.xml.writer.impl.base.XmlWriter;
import org.apache.rat.test.utils.Resources;
import org.apache.rat.walker.DirectoryWalker;
import org.junit.Before;
import org.junit.Test;

/**
 * The Class XmlReportFactoryTest.
 */
public class XmlReportFactoryTest {

	/** The Constant IGNORE_EMPTY. */
	private static final Pattern IGNORE_EMPTY = Pattern
			.compile(".svn|Empty.txt");

	/** The out. */
	private StringWriter out;

	/** The writer. */
	private IXmlWriter writer;

	/**
	 * Sets the up.
	 * 
	 * @throws Exception
	 *             the exception
	 */
	@Before
	public void setUp() throws Exception {
		out = new StringWriter();
		writer = new XmlWriter(out);
		writer.startDocument();
	}

	/**
	 * Report.
	 * 
	 * @param directory
	 *            the directory
	 * @param report
	 *            the report
	 * @throws RatException
	 *             the rat exception
	 */
	private void report(final DirectoryWalker directory, final RatReport report)
			throws RatException {
		directory.run(report);
	}

	/**
	 * Standard report.
	 * 
	 * @throws RatException
	 *             the rat exception
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	@Test
	public void standardReport() throws RatException, IOException {
		final String elementsPath = Resources
				.getResourceDirectory("elements/Source.java");
		final MockLicenseMatcher mockLicenseMatcher = new MockLicenseMatcher();
		DirectoryWalker directory = new DirectoryWalker(new File(elementsPath),
				IGNORE_EMPTY);
		final ClaimStatistic statistic = new ClaimStatistic();
		final ReportConfiguration configuration = new ReportConfiguration();
		configuration.setHeaderMatcher(mockLicenseMatcher);
		RatReport report = new XmlReportFactory().createStandardReport(writer,
				statistic, configuration);
		report.startReport();
		report(directory, report);
		report.endReport();
		writer.closeDocument();
		final String output = out.toString();
		assertTrue("Preamble and document element are OK",
				output.startsWith("<?xml version='1.0'?>"
						+ "<rat-report timestamp="));
		assertTrue("Part after timestamp attribute is OK", output.endsWith(">"
				+ "<resource name='"
				+ elementsPath
				+ "/ILoggerFactory.java'><type name='standard'/></resource>"
				+ "<resource name='"
				+ elementsPath
				+ "/Image.png'><type name='binary'/></resource>"
				+ "<resource name='"
				+ elementsPath
				+ "/LICENSE'><type name='notice'/></resource>"
				+ "<resource name='"
				+ elementsPath
				+ "/NOTICE'><type name='notice'/></resource>"
				+ "<resource name='"
				+ elementsPath
				+ "/Source.java'><type name='standard'/>"
				+ "</resource>"
				+ "<resource name='"
				+ elementsPath
				+ "/Text.txt'><type name='standard'/>"
				+ "</resource>"
				+ "<resource name='"
				+ elementsPath
				+ "/Xml.xml'><type name='standard'/>"
				+ "</resource>"
				+ "<resource name='"
				+ elementsPath
				+ "/buildr.rb'><type name='standard'/>"
				+ "</resource>"
				+ "<resource name='"
				+ elementsPath
				+ "/dummy.jar'><type name='archive'/></resource>"
				+ "</rat-report>"));
		assertTrue("Is well formed", XmlUtils.isWellFormedXml(output));
		assertEquals(
				"Binary files",
				Integer.valueOf(1),
				statistic.getDocumentCategoryMap().get(
						MetaData.RAT_DOCUMENT_CATEGORY_VALUE_BINARY));
		assertEquals(
				"Notice files",
				Integer.valueOf(2),
				statistic.getDocumentCategoryMap().get(
						MetaData.RAT_DOCUMENT_CATEGORY_VALUE_NOTICE));
		assertEquals(
				"Standard files",
				Integer.valueOf(5),
				statistic.getDocumentCategoryMap().get(
						MetaData.RAT_DOCUMENT_CATEGORY_VALUE_STANDARD));
		assertEquals(
				"Archives",
				Integer.valueOf(1),
				statistic.getDocumentCategoryMap().get(
						MetaData.RAT_DOCUMENT_CATEGORY_VALUE_ARCHIVE));
	}
}
