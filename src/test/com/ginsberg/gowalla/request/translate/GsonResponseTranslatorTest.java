/**
 * Copyright (c) 2010, Todd Ginsberg
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 * 
 *    * Redistributions of source code must retain the above copyright
 *      notice, this list of conditions and the following disclaimer.
 *    * Redistributions in binary form must reproduce the above copyright
 *      notice, this list of conditions and the following disclaimer in the
 *      documentation and/or other materials provided with the distribution.
 *    * Neither the name of Todd Ginsberg, or Gowalla nor the
 *      names of any contributors may be used to endorse or promote products
 *      derived from this software without specific prior written permission.
 * 
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL <COPYRIGHT HOLDER> BE LIABLE FOR ANY
 * DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 * 
 *  Also, please use this for Good and not Evil.  
 */
package com.ginsberg.gowalla.request.translate;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.ginsberg.gowalla.dto.FullCategory;

/**
 * @author Todd Ginsberg
 *
 */
public class GsonResponseTranslatorTest {

	private ResponseTranslator gson = null;
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		gson = new GsonResponseTranslator();
	}
	
	@Test
	public void testCategoryBasic() throws Exception {
		final FullCategory c = gson.translateCategory(getFile("testCategoryBasic.json"));
		assertEquals("Name should match.", "CategoryName", c.getName());
		assertEquals("Id should match.", 999, c.getId());
	}
	
	@Test
	public void testCategoryWithSubcategories() throws Exception {
		final FullCategory c = gson.translateCategory(getFile("testCategoryWithSubcategories.json"));
		assertEquals("Name should match.", "Parent", c.getName());
		assertEquals("Id should match.", 999, c.getId());
		assertEquals("Should have 2 subcategories.", 2, c.getSubcategories().size());
		assertEquals("First Subcat", 9991, c.getSubcategories().get(0).getId());
		assertEquals("Second Subcat", 9992, c.getSubcategories().get(1).getId());
	}
	
	@Test
	public void testAllCategories() throws Exception {
		List<FullCategory> categories = gson.translateCategories(getFile("testAllCategories.json"));
		assertNotNull(categories);
		assertEquals(9, categories.size());
		assertNotNull(categories.get(0).getSubcategories());
		assertTrue(categories.get(0).getSubcategories().size() > 0);
		// TODO: Assert more about this (find subcats, etc).
	}
	
	/**
	 * Read JSON data from a file.
	 */
	private String getFile(final String file) throws Exception {
        final StringBuilder buf = new StringBuilder();
        InputStream in = null;
        String line;

        try {
        	in = getClass().getClassLoader().getResourceAsStream("json/" + file);
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            while ((line = reader.readLine()) != null) {
            	buf.append(line).append("\n");
            }
        } finally {
            in.close();
        }
        return buf.toString();


	}

}
