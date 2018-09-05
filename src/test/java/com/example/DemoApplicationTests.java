package com.example;

import com.example.model.Permission;
import com.example.model.Role;
import com.example.repository.RoleRepository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {

	@Test
	public void contextLoads() {
	}

	@Autowired
	private RoleRepository roleRepository;

	@Test
	public void checkIfRoleIsNull() {
		//Role userRole = roleRepository.findByRole("ADMIN");
		Role role = roleRepository.findOne(1);

		assertEquals("ADMIN", role.getRole());

		//System.out.println(roleRepository.findByRole());
		//assertEquals("ADMIN", role.getRole());
	}
}
