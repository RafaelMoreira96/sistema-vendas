package com.sistemavenda.tcc;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.sistemavenda.tcc.domain.Cliente;
import com.sistemavenda.tcc.domain.Contato;
import com.sistemavenda.tcc.domain.Endereco;
import com.sistemavenda.tcc.repositories.ClienteRepository;
import com.sistemavenda.tcc.repositories.ContatoRepository;
import com.sistemavenda.tcc.repositories.EnderecoRepository;

@SpringBootApplication
public class TccApplication implements CommandLineRunner{

	@Autowired
	private ClienteRepository cliR;
	@Autowired
	private EnderecoRepository eR;
	@Autowired
	private ContatoRepository contR;

	public static void main(String[] args) {
		SpringApplication.run(TccApplication.class, args);
	}

	public void run(String... args) throws Exception{
		Endereco e = new Endereco(null, "79640-450", "Rua dos patetas", "1234", null, "Aquele lá", "Três Lagoas", "Mato Grosso do Sul");
		Contato c = new Contato(null, "67991784885", "Celular");
		List<Contato> contato = new ArrayList<>();
		contato.add(c);
		Cliente c1 = new Cliente(null, "24.901.784/0001-97", "828.178.770-80", "Rafael M", e, contato);

		eR.save(e);
		contR.save(c);
		cliR.save(c1);
	}

}
