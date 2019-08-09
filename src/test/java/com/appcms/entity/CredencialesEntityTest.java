package com.appcms.entity;

import org.junit.Before;
import org.junit.Test;

public class CredencialesEntityTest {
	
	private CredencialesEntity credencialesEntity;
	
	
	@Before
	public void setUp() throws Exception {
		credencialesEntity=new CredencialesEntity();
		credencialesEntity.setUserName("TESTUSER");
		//credencialesEntity.setScotiauser("TESTSCOTIAUSER");
		credencialesEntity.setTOKENONE("546546524165%$/KJNKDKJDNKJD");
		credencialesEntity.setTOKENTWO("HJJJNHKKJU0546546524165%$/KJNKDKJDNKJD");
	}

	@Test
	public final void testGetUserName() throws Exception {
		// TODO
		throw new RuntimeException("not yet implemented");
	}

	@Test
	public final void testGetPassword() throws Exception {
		// TODO
		throw new RuntimeException("not yet implemented");
	}

	@Test
	public final void testGetTOKENONE() throws Exception {
		// TODO
		throw new RuntimeException("not yet implemented");
	}

	@Test
	public final void testGetTOKENTWO() throws Exception {
		// TODO
		throw new RuntimeException("not yet implemented");
	}

	@Test
	public final void testGetScotiauser() throws Exception {
		// TODO
		throw new RuntimeException("not yet implemented");
	}

}
