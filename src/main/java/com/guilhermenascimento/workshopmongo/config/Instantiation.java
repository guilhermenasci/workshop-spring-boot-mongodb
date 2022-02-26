package com.guilhermenascimento.workshopmongo.config;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.guilhermenascimento.workshopmongo.domain.Post;
import com.guilhermenascimento.workshopmongo.domain.User;
import com.guilhermenascimento.workshopmongo.dto.AuthorDTO;
import com.guilhermenascimento.workshopmongo.repository.PostRepository;
import com.guilhermenascimento.workshopmongo.repository.UserRepository;

@Configuration
public class Instantiation implements CommandLineRunner {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PostRepository postRepository;

	@Override
	public void run(String... arg0) throws Exception {

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		sdf.setTimeZone(TimeZone.getTimeZone("GMT"));

		userRepository.deleteAll();
		postRepository.deleteAll();

		User maria = new User(null, "Maria Brown", "maria@gmail.com");
		User alex = new User(null, "Alex Green", "alex@gmail.com");
		User bob = new User(null, "Bob Grey", "bob@gmail.com");
		
		userRepository.saveAll(Arrays.asList(maria,alex,bob));

		Post post1 = new Post(null, sdf.parse("25/02/2022"), "Let's Travel", "I'll travel to São Paulo. Thanks!", new AuthorDTO(maria));
		Post post2 = new Post(null, sdf.parse("25/02/2022"), "Good Morning", "I wake up happy today!", new AuthorDTO(maria));

		postRepository.saveAll(Arrays.asList(post1, post2));
		
		maria.getPosts().addAll(Arrays.asList(post1,post2));
		userRepository.save(maria);

	}
}
  