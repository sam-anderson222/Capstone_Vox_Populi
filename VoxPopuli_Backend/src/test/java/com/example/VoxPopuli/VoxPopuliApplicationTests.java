package com.example.VoxPopuli;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

@SpringBootTest
class VoxPopuliApplicationTests {
	

	@Autowired
	private JdbcTemplate jdbcTemplate; // Used to access and reset the sql database.

	@BeforeEach
	void setUp() {
		try {
			jdbcTemplate.execute("{CALL set_known_good_state()}");
		} catch (Exception ex) {
			System.out.println("Error");
		}
	}
}
