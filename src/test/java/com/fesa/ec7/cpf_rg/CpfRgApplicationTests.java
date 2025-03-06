package com.fesa.ec7.cpf_rg;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.fesa.ec7.cpf_rg.model.AFD;
import com.fesa.ec7.cpf_rg.model.Validator;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class CpfRgApplicationTests {

	@Test
	void contextLoads() {
	}

	@Test
	void CPFValido() {
		List<String> ids = Arrays.asList(
				"123.456.789-10", "12345678910", "123456789-10");

		assertThat(ids).allMatch(id -> {
			AFD automato = new AFD();
			automato.createAutomaton();
			var res = Validator.validar(id, automato);
			System.out.println(res);
			return res == "CPF";
		}, "Todos os CPFs deveriam ser válidos.");
	}

	@Test
	void CPFInvalido() {
		List<String> ids = Arrays.asList(
				"123456.789-09",
				"123.456.789-0", 
				"123.456.789-",
				"123.456.789-09A", 
				"123.456.789-09-",
				"123.456.789-09.123");

		assertThat(ids).allMatch(id -> {
			AFD automato = new AFD();
			automato.createAutomaton();
			var res = Validator.validar(id, automato);
			System.out.println(res);
			return res == "Inválido";
		}, "Todos os CPFs deveriam ser inválidos.");
	}

	@Test
	void RGValido() {
		List<String> ids = Arrays.asList(
				"12.345.678-9",
				"123456789",
				"12345678-9");

		assertThat(ids).allMatch(id -> {
			AFD automato = new AFD();
			automato.createAutomaton();
			var res = Validator.validar(id, automato);
			System.out.println(res);
			return res == "RG";
		}, "Todos os RGs deveriam ser válidos.");
	}

	@Test
	void RGInvalido() {
		List<String> ids = Arrays.asList(
				"12345.678-9",
				"12.345.678-",
				"12.345.678",
				"12.345.678-9A",
				"12.345.678-9-",
				"12.345.678-9.123");

		assertThat(ids).allMatch(id -> {
			AFD automato = new AFD();
			automato.createAutomaton();
			var res = Validator.validar(id, automato);
			System.out.println(res);
			return res == "Inválido";
		}, "Todos os RGs deveriam ser inválidos.");
	}

}
