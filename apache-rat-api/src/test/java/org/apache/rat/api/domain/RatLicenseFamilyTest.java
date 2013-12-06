/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 *  to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 *  with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.apache.rat.api.domain;

import static org.apache.rat.api.domain.RatLicenseFamily.APACHE;
import static org.apache.rat.api.domain.RatLicenseFamily.GPL1;
import static org.apache.rat.api.domain.RatLicenseFamily.GPL2;
import static org.apache.rat.api.domain.RatLicenseFamily.GPL3;
import static org.apache.rat.api.domain.RatLicenseFamily.W3C;
import static org.apache.rat.api.domain.RatLicenseFamily.W3C_DOCUMENTATION;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class RatLicenseFamilyTest {

    @Test
    public void testW3CLicenseFamilyCategory() {
        assertThat(W3C.getCategory(), is("W3C  "));
    }

    @Test
    public void testW3CLicenseFamilyName() {
        assertThat(W3C.getName(), is("W3C Software Copyright"));
    }

    @Test
    public void testW3CLicenseFamilyNotes() {
        assertThat(
                W3C.getNotes(),
                is("Note that W3C requires a NOTICE. All modifications require notes. See http://www.w3.org/Consortium/Legal/2002/copyright-software-20021231."));
    }

    @Test
    public void testW3CDocLicenseFamilyCategory() {
        assertThat(W3C_DOCUMENTATION.getCategory(), is("W3CD "));
    }

    @Test
    public void testW3CDocLicenseFamilyName() {
        assertThat(W3C_DOCUMENTATION.getName(), is("W3C Document Copyright"));
    }

    @Test
    public void testW3CDocLicenseFamilyNotes() {
        assertThat(
                W3C_DOCUMENTATION.getNotes(),
                is("Note that W3CD does not allow modifications. See http://www.w3.org/Consortium/Legal/2002/copyright-documents-20021231."));
    }

	@Test
	public void testAPACHELicenseFamilyCategory() {
		assertThat(APACHE.getCategory(), is("AL   "));
	}

	@Test
	public void testAPACHELicenseFamilyName() {
		assertThat(APACHE.getName(), is("Apache License Version 2.0"));
	}

	@Test
	public void testAPACHELicenseFamilyNotes() {
		assertThat(
				APACHE.getNotes(),
				is("Note that APACHE requires a NOTICE. All modifications require notes. See http://www.apache.org/licenses/LICENSE-2.0."));
	}

	@Test
	public void testGPL1LicenseFamilyCategory() {
		assertThat(GPL1.getCategory(), is("GPL1 "));
	}

	@Test
	public void testGPL1LicenseFamilyName() {
		assertThat(GPL1.getName(), is("GNU General Public License, version 1"));
	}

	@Test
	public void testGPL1LicenseFamilyNotes() {
		assertThat(
				GPL1.getNotes(),
				is("Note that GPL1 requires a NOTICE. All modifications require notes. See http://www.gnu.org/licenses/gpl-1.0.html."));
	}

	@Test
	public void testGPL2LicenseFamilyCategory() {
		assertThat(GPL2.getCategory(), is("GPL2 "));
	}

	@Test
	public void testGPL2LicenseFamilyName() {
		assertThat(GPL2.getName(), is("GNU General Public License, version 2"));
	}

	@Test
	public void testGPL2LicenseFamilyNotes() {
		assertThat(
				GPL2.getNotes(),
				is("Note that GPL2 requires a NOTICE. All modifications require notes. See http://www.gnu.org/licenses/gpl-2.0.html."));
	}
	
	@Test
	public void testGPL3LicenseFamilyCategory() {
		assertThat(GPL3.getCategory(), is("GPL3 "));
	}

	@Test
	public void testGPL3LicenseFamilyName() {
		assertThat(GPL3.getName(), is("GNU General Public License, version 3"));
	}

	@Test
	public void testGPL3LicenseFamilyNotes() {
		assertThat(
				GPL3.getNotes(),
				is("Note that GPL3 requires a NOTICE. All modifications require notes. See http://www.gnu.org/licenses/gpl-3.0.html."));
	}
}
