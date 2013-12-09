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

package org.apache.rat.report.claim.util;

import java.io.IOException;

import org.apache.rat.api.Document;
import org.apache.rat.document.impl.FileDocument;
import org.apache.rat.test.utils.Resources;
import org.junit.Test;

/**
 * The Class LicenseAddingReportTest.
 */
public class LicenseAddingReportTest {

	/**
	 * Test report.
	 * 
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	@Test
	public void testReport() throws IOException {
		boolean pForced = false;
		String pCopyrightMsg = "";
		LicenseAddingReport licenseAddingReport = new LicenseAddingReport(
				pCopyrightMsg, pForced);
		Document document = new FileDocument(
				Resources.getResourceFile("elements/Source.java"));
		licenseAddingReport.report(document);
		//Delete File report.
		Resources.getResourceFile("elements/Source.java.new").delete();
	}
}