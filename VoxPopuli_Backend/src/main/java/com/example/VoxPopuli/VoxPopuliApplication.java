package com.example.VoxPopuli;

import com.example.VoxPopuli.model.Poll;
import com.example.VoxPopuli.model.PollOption;
import com.example.VoxPopuli.model.User;
import com.example.VoxPopuli.repository.PollOptionRepository;
import com.example.VoxPopuli.repository.PollRepository;
import com.example.VoxPopuli.repository.UserRepository;
import com.example.VoxPopuli.repository.mappers.PollOptionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;
import java.util.Optional;

@SpringBootApplication
public class VoxPopuliApplication implements CommandLineRunner {

	@Autowired
	UserRepository userRepo;

	@Autowired
	PollRepository pollRepo;

	@Autowired
	PollOptionRepository pollOptionRepo;

	public static void main(String[] args) {
		SpringApplication.run(VoxPopuliApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

	}
}
