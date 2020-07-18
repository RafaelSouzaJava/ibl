package com.rafael.ibltelecom.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rafael.ibltelecom.domain.Bairro;
import com.rafael.ibltelecom.domain.Categoria;
import com.rafael.ibltelecom.domain.CategoriaCombo;
import com.rafael.ibltelecom.domain.ClienteCombo;
import com.rafael.ibltelecom.domain.ClientePlano;
import com.rafael.ibltelecom.domain.Combo;
import com.rafael.ibltelecom.domain.Endereco;
import com.rafael.ibltelecom.domain.Logradouro;
import com.rafael.ibltelecom.domain.Plano;
import com.rafael.ibltelecom.domain.Post;
import com.rafael.ibltelecom.domain.enums.FormaPagamento;
import com.rafael.ibltelecom.domain.enums.TipoCliente;
import com.rafael.ibltelecom.domain.enums.Vencimento;
import com.rafael.ibltelecom.repositories.BairroRepository;
import com.rafael.ibltelecom.repositories.CategoriaComboRepository;
import com.rafael.ibltelecom.repositories.CategoriaRepository;
import com.rafael.ibltelecom.repositories.ClienteComboRepository;
import com.rafael.ibltelecom.repositories.ClientePlanoRepository;
import com.rafael.ibltelecom.repositories.ComboRepository;
import com.rafael.ibltelecom.repositories.EnderecoRepository;
import com.rafael.ibltelecom.repositories.LogradouroRepository;
import com.rafael.ibltelecom.repositories.PlanoRepository;
import com.rafael.ibltelecom.repositories.PostRepository;

@Service
public class DBService {

	@Autowired
	CategoriaRepository categoriaRepository;

	@Autowired
	CategoriaComboRepository categoriaComboRepository;

	@Autowired
	PlanoRepository planosRepository;

	@Autowired
	ComboRepository comboRepository;

	@Autowired
	PostRepository postRepository;

	@Autowired
	BairroRepository bairroRepository;

	@Autowired
	LogradouroRepository logradouroRepository;

	@Autowired
	ClientePlanoRepository clienteRepository;

	@Autowired
	EnderecoRepository enderecoRepository;

	@Autowired
	ClienteComboRepository clienteComboRepository;

	public void instantiateTestDatabase() throws ParseException {
		Categoria internet = new Categoria(null, "Internet");
		Categoria celular = new Categoria(null, "Internet Movel");

		CategoriaCombo combos = new CategoriaCombo(null, "Internet Banda Larga + Internet Movel");

		categoriaRepository.saveAll(Arrays.asList(internet, celular));

		categoriaComboRepository.saveAll(Arrays.asList(combos));

		Plano p1 = new Plano(null, "Plano Bronze", "40 Megas", "20 Megas", 59.90, 70.00, null, " 1 Ano", null);
		Plano p2 = new Plano(null, "Plano Prata", "50 Megas", "25 Megas", 69.90, 70.00, null, "1 Ano", null);
		Plano p3 = new Plano(null, "Plano Ouro", "100 Megas", "50 Megas", 74.90, 70.00, null, "1 Ano", null);
		Plano p4 = new Plano(null, "Plano Platina", "150 Megas", "75 Megas", 89.90, 70.00, null, "1 Ano", null);
		Plano p5 = new Plano(null, "Plano Diamante", "200 Megas", "100 Megas", 99.90, 70.00,
				"3 primeiro meses no valor de 69,90, após o 3° més retorna para 99.90", "1 Ano", null);

		Plano mov1 = new Plano(null, "Plano Bronze Movel", "8 GB", null, 59.90, null, "4 GB + 500 MB Bônus", "1 Ano",
				"+ 4 GB Para Acessar APPS");
		Plano mov2 = new Plano(null, "Plano Prata Movel", "10 GB*", null, 69.90, null, "5 GB + 500 MB Bônus", " 1 Ano",
				"+ 5 GB Para Acessar APPS");
		Plano mov3 = new Plano(null, "Plano Ouro Movel", "15 GB*", null, 79.90, null, "7.5 GB + 500 MB Bônus", "1 Ano",
				"+ 7.5 GB Para Acessar APPS");

		internet.getPlanos().addAll(Arrays.asList(p1, p2, p3, p4, p5));
		celular.getPlanos().addAll(Arrays.asList(mov1, mov2, mov3));

		p1.getCategorias().addAll(Arrays.asList(internet));
		p2.getCategorias().addAll(Arrays.asList(internet));
		p3.getCategorias().addAll(Arrays.asList(internet));
		p4.getCategorias().addAll(Arrays.asList(internet));
		p5.getCategorias().addAll(Arrays.asList(internet));

		mov1.getCategorias().addAll(Arrays.asList(celular));
		mov2.getCategorias().addAll(Arrays.asList(celular));
		mov3.getCategorias().addAll(Arrays.asList(celular));

		planosRepository.saveAll(Arrays.asList(p1, p2, p3, p4, p5, mov1, mov2, mov3));

		Combo combo1 = new Combo(null, p1, mov2, 149.90, null, "1 Ano");
		Combo combo2 = new Combo(null, p3, mov1, 169.90, null, "1 Ano");
		Combo combo3 = new Combo(null, p5, mov3, 189.90, null, "1 Ano");

		combos.getCombos().addAll(Arrays.asList(combo1, combo2, combo3));

		combo1.getCategorias().addAll(Arrays.asList(combos));
		combo2.getCategorias().addAll(Arrays.asList(combos));
		combo3.getCategorias().addAll(Arrays.asList(combos));

		comboRepository.saveAll(Arrays.asList(combo1, combo2, combo3));

		Post post1 = new Post();
		post1.setAutor("Rafael Souza");
		post1.setData(LocalDate.now());
		post1.setTitulo("Manutenção de rede externa");
		post1.setTexto(
				"Esta ocorrendo uma manutenção que cobre as aproximidades da rua 1 do Presidente Vargas, a manutenção é devido a quedas de cabos que ocorreu no local, previsão para termino até as 15:00 horas");

		Post post2 = new Post();
		post2.setAutor("Direção da IBL Telecom");
		post2.setData(LocalDate.now());
		post2.setTitulo("Manutenção de madrugada para Atualização de Sistema");
		post2.setTexto(
				"Durante o periodo da madrugada da data 05/07/2020 vai esta ocorrendo uma atualização no Sistema para melhoria de navegação de todos os usuarios de internet da IBL Telecom, a manutenção vai esta ocorrendo entre as 02:00 até as 04:00 horas da manha");

		postRepository.saveAll(Arrays.asList(post1, post2));

		// --------------------------------------------------------------------

		Bairro bairro1 = new Bairro(null, "LOTEAMENTO PARQUE SANTANA");
		Bairro bairro2 = new Bairro(null, "CONJ ESPERANÇA");
		Bairro bairro3 = new Bairro(null, "Conjunto Sítio Córrego");
		Bairro bairro4 = new Bairro(null, "JARDIM JATOBA");
		Bairro bairro5 = new Bairro(null, "LOTEAMENTO SÃO MATEUS");
		Bairro bairro6 = new Bairro(null, "PARQUE PRESIDENTE VARGAS");
		Bairro bairro7 = new Bairro(null, "PARQUE SANTA ROSA");
		Bairro bairro8 = new Bairro(null, "NOVO MONDUBIM");
		Bairro bairro9 = new Bairro(null, "ARACAPE");
		Bairro bairro10 = new Bairro(null, "MONDUBIM");

		bairroRepository.saveAll(Arrays.asList(bairro1, bairro2, bairro3, bairro4, bairro5, bairro6, bairro7, bairro8,
				bairro9, bairro10));

		// -----------------------------------------------------------------------------

		// Ruas do Parque Santana ID 1 ATÉ 40

		Logradouro logradouro1 = new Logradouro(null, "RUA FRANCISCO BENTO", bairro1);
		Logradouro logradouro2 = new Logradouro(null, "RUA DR. JOSE DUTRA", bairro1);
		Logradouro logradouro3 = new Logradouro(null, "RUA DONA LIBANIA RIBEIRO DE MELO NUNES", bairro1);
		Logradouro logradouro4 = new Logradouro(null, "RUA TAINA PIRES BRILHANTE", bairro1);
		Logradouro logradouro5 = new Logradouro(null, "RUA 10", bairro1);
		Logradouro logradouro6 = new Logradouro(null, "RUA 01", bairro1);
		Logradouro logradouro7 = new Logradouro(null, "RUA CESAR LEITE", bairro1);
		Logradouro logradouro8 = new Logradouro(null, "RUA 08", bairro1);
		Logradouro logradouro9 = new Logradouro(null, "RUA 09", bairro1);
		Logradouro logradouro10 = new Logradouro(null, "RUA 05", bairro1);
		Logradouro logradouro11 = new Logradouro(null, "RUA 10", bairro1);
		Logradouro logradouro12 = new Logradouro(null, "RUA 15", bairro1);
		Logradouro logradouro13 = new Logradouro(null, "RUA 14", bairro1);
		Logradouro logradouro14 = new Logradouro(null, "RUA 02", bairro1);
		Logradouro logradouro15 = new Logradouro(null, "RUA 03", bairro1);
		Logradouro logradouro16 = new Logradouro(null, "RUA 18", bairro1);
		Logradouro logradouro17 = new Logradouro(null, "RUA 20", bairro1);
		Logradouro logradouro18 = new Logradouro(null, "RUA 16", bairro1);
		Logradouro logradouro19 = new Logradouro(null, "RUA 13", bairro1);
		Logradouro logradouro20 = new Logradouro(null, "RUA 12", bairro1);
		Logradouro logradouro21 = new Logradouro(null, "RUA 19", bairro1);
		Logradouro logradouro22 = new Logradouro(null, "RUA 06", bairro1);
		Logradouro logradouro23 = new Logradouro(null, "RUA SAMUEL PIRES RIBEIRO", bairro1);
		Logradouro logradouro24 = new Logradouro(null, "RUA CARLOS MAGNO", bairro1);
		Logradouro logradouro25 = new Logradouro(null, "RUA MARCILIA COSTA FREIRE", bairro1);
		Logradouro logradouro26 = new Logradouro(null, "RUA GABRIEL PIRES RIBEIRO", bairro1);
		Logradouro logradouro27 = new Logradouro(null, "RUA OLIMPIO RIBEIRO ", bairro1);
		Logradouro logradouro28 = new Logradouro(null, "RUA RAQUEL PIRES RIBEIRO", bairro1);
		Logradouro logradouro29 = new Logradouro(null, "RUA PEDRO RIBEIRO", bairro1);
		Logradouro logradouro30 = new Logradouro(null, "RUA MORRAIS CORREIA", bairro1);
		Logradouro logradouro31 = new Logradouro(null, "RUA ROSSELI FERREIRA", bairro1);
		Logradouro logradouro32 = new Logradouro(null, "RUA BENTA GURGEL", bairro1);
		Logradouro logradouro33 = new Logradouro(null, "RUA BOM JESUS DOS AFLITOS", bairro1);
		Logradouro logradouro34 = new Logradouro(null, "TRAVESSA VITALINO", bairro1);
		Logradouro logradouro35 = new Logradouro(null, "RUA PAULANE", bairro1);
		Logradouro logradouro36 = new Logradouro(null, "RUA B", bairro1);
		Logradouro logradouro37 = new Logradouro(null, "RUA A", bairro1);
		Logradouro logradouro38 = new Logradouro(null, "RUA QUEIROS DE LIMA", bairro1);
		Logradouro logradouro39 = new Logradouro(null, "RUA CAMILA BEZERRA", bairro1);
		Logradouro logradouro40 = new Logradouro(null, "AVENIDA 01", bairro1);

		// RUAS DO CONJ ESPERANÇA ID: 41 ATÉ 56

		Logradouro logradouro41 = new Logradouro(null, "AVENIDA CONTORNO SUL", bairro2);
		Logradouro logradouro42 = new Logradouro(null, "TRAVESSA DONA RITA", bairro2);
		Logradouro logradouro43 = new Logradouro(null, "RUA MARIA PEREIRA FIRMO", bairro2);
		Logradouro logradouro44 = new Logradouro(null, "RUA 109", bairro2);
		Logradouro logradouro45 = new Logradouro(null, "RUA 108", bairro2);
		Logradouro logradouro46 = new Logradouro(null, "RUA SAO FRANCISCO", bairro2);
		Logradouro logradouro47 = new Logradouro(null, "RUA PROFESSOR CABRAL", bairro2);
		Logradouro logradouro48 = new Logradouro(null, "AVENIDA A", bairro2);
		Logradouro logradouro49 = new Logradouro(null, "AVENIDA B", bairro2);
		Logradouro logradouro50 = new Logradouro(null, "AVENIDA PENETRAÇÃO NORTE-SUL", bairro2);
		Logradouro logradouro51 = new Logradouro(null, "RUA 111", bairro2);
		Logradouro logradouro52 = new Logradouro(null, "AVENIDA B", bairro2);
		Logradouro logradouro53 = new Logradouro(null, "Rua 112", bairro2);
		Logradouro logradouro54 = new Logradouro(null, "AVENIDA TENENTE ANDERSON", bairro2);
		Logradouro logradouro55 = new Logradouro(null, "RUA SAO LUCAS", bairro2);
		Logradouro logradouro56 = new Logradouro(null, "RUA SAO FRANCISCO", bairro2);

		// RUA DO CONJ SITIO CORREGO ID: 57 ATÉ 70

		Logradouro logradouro57 = new Logradouro(null, "Rua 9", bairro3);
		Logradouro logradouro58 = new Logradouro(null, "Rua 4", bairro3);
		Logradouro logradouro59 = new Logradouro(null, "RUA 5", bairro3);
		Logradouro logradouro60 = new Logradouro(null, "RUA FRANCIMAR PEREIRA DOS SANTOS", bairro3);
		Logradouro logradouro61 = new Logradouro(null, "ALAMEDA VERDE 01", bairro3);
		Logradouro logradouro62 = new Logradouro(null, "ALAMEDA VERDE 8", bairro3);
		Logradouro logradouro63 = new Logradouro(null, "ALAMEDA VERDE 6", bairro3);
		Logradouro logradouro64 = new Logradouro(null, "RUA 7", bairro3);
		Logradouro logradouro65 = new Logradouro(null, "RUA 2", bairro3);
		Logradouro logradouro66 = new Logradouro(null, "ALAMEDA VERDE 7", bairro3);
		Logradouro logradouro67 = new Logradouro(null, "ALAMEDA VERDE 2", bairro3);
		Logradouro logradouro68 = new Logradouro(null, "RUA 3", bairro3);
		Logradouro logradouro69 = new Logradouro(null, "RUA 8", bairro3);
		Logradouro logradouro70 = new Logradouro(null, "RUA 1", bairro3);

		// RUA DO JARDIM JATOBA ID: 71 ATÉ 100

		Logradouro logradouro71 = new Logradouro(null, "RUA JULIO COLF", bairro4);
		Logradouro logradouro72 = new Logradouro(null, "RUA JOSÉ ARÃO", bairro4);
		Logradouro logradouro73 = new Logradouro(null, "RUA SÃO FRANCISCO", bairro4);
		Logradouro logradouro74 = new Logradouro(null, "RUA PAZ", bairro4);
		Logradouro logradouro75 = new Logradouro(null, "RUA ADALBERTO MALVEIRA", bairro4);
		Logradouro logradouro76 = new Logradouro(null, "AVENIDA SIQUEIRA CAMPOS", bairro4);
		Logradouro logradouro77 = new Logradouro(null, "RUA ESTRADA DO JATOBÁ", bairro4);
		Logradouro logradouro78 = new Logradouro(null, "RUA VINICIUS DE MORAES", bairro4);
		Logradouro logradouro79 = new Logradouro(null, "RUA VIÇOSA MAIA", bairro4);
		Logradouro logradouro80 = new Logradouro(null, "RUA PROFESSOR MANOEL DE CASTRO", bairro4);
		Logradouro logradouro81 = new Logradouro(null, "RUA JACANAU", bairro4);
		Logradouro logradouro82 = new Logradouro(null, "RUA SÃO VICENTE", bairro4);
		Logradouro logradouro83 = new Logradouro(null, "RUA PEREIRA SILVA", bairro4);
		Logradouro logradouro84 = new Logradouro(null, "RUA VINICIUS DE MORAIS", bairro4);
		Logradouro logradouro85 = new Logradouro(null, "RUA SANTO ANTONIO", bairro4);
		Logradouro logradouro86 = new Logradouro(null, "RUA MONTEIRO LOBATO", bairro4);
		Logradouro logradouro87 = new Logradouro(null, "RUA SÃO PAULO", bairro4);
		Logradouro logradouro88 = new Logradouro(null, "RUA PEREIRA SILVA", bairro4);
		Logradouro logradouro89 = new Logradouro(null, "AVENIDA MACIEL BEZERRA", bairro4);
		Logradouro logradouro90 = new Logradouro(null, "RUA:PADRE FIALHO", bairro4);
		Logradouro logradouro91 = new Logradouro(null, "RUA POSSIDONIO SOUZA CAMPOS", bairro4);
		Logradouro logradouro92 = new Logradouro(null, "RUA ADALBERTO MALVEIRA", bairro4);
		Logradouro logradouro93 = new Logradouro(null, "RUA LEANDRO HENRIQUE", bairro4);
		Logradouro logradouro94 = new Logradouro(null, "RUA MARCIEL LUZ", bairro4);
		Logradouro logradouro95 = new Logradouro(null, "RUA PATATIVA DO ASSARÉ", bairro4);
		Logradouro logradouro96 = new Logradouro(null, "RUA ERIVEU RAMOS", bairro4);
		Logradouro logradouro97 = new Logradouro(null, "RUA BOA VISTA", bairro4);
		Logradouro logradouro98 = new Logradouro(null, "RUA MIRACEL", bairro4);
		Logradouro logradouro99 = new Logradouro(null, "RUA IRINEU MACHADO", bairro4);
		Logradouro logradouro100 = new Logradouro(null, "RUA ADAHIL BARRETO", bairro4);

		// RUA DO LOTEAMENTO SÃO MATEUS

		Logradouro logradouro101 = new Logradouro(null, "AVENIDA 9", bairro5);
		Logradouro logradouro102 = new Logradouro(null, "RUA 4", bairro5);
		Logradouro logradouro103 = new Logradouro(null, "RUA 5", bairro5);

		// RUA DO PARQUE PRESIDENTE VARGAS ID: 104 ATÉ 130

		Logradouro logradouro104 = new Logradouro(null, "RUA PEDRO CABRAL", bairro6);
		Logradouro logradouro105 = new Logradouro(null, "RUA ANTôNIO MONTEIRO", bairro6);
		Logradouro logradouro106 = new Logradouro(null, "RUA 4", bairro6);
		Logradouro logradouro107 = new Logradouro(null, "RUA ANTONIO MONTEIRO", bairro6);
		Logradouro logradouro108 = new Logradouro(null, "RUA DAS CEREJEIRAS", bairro6);
		Logradouro logradouro109 = new Logradouro(null, "RUA 1", bairro6);
		Logradouro logradouro110 = new Logradouro(null, "RUA ANTôNIO CHACON", bairro6);
		Logradouro logradouro111 = new Logradouro(null, "RUA SãO FIDéLIS", bairro6);
		Logradouro logradouro112 = new Logradouro(null, "RUA 2", bairro6);
		Logradouro logradouro113 = new Logradouro(null, "RUA 5", bairro6);
		Logradouro logradouro114 = new Logradouro(null, "RUA LUCINEIDE GOMES", bairro6);
		Logradouro logradouro115 = new Logradouro(null, "RUA DAS CEREJEIRAS", bairro6);
		Logradouro logradouro116 = new Logradouro(null, "RUA 3", bairro6);
		Logradouro logradouro117 = new Logradouro(null, "RUA 1 (LOT GRANJA PALESTINA)", bairro6);
		Logradouro logradouro118 = new Logradouro(null, "TRAVESSA LUCINEIDE GOMES", bairro6);
		Logradouro logradouro119 = new Logradouro(null, "RUA MARTINS DE LIMA", bairro6);
		Logradouro logradouro120 = new Logradouro(null, "RUA LIMA CAMPOS", bairro6);
		Logradouro logradouro121 = new Logradouro(null, "RUA VICENTE CELESTINO", bairro6);
		Logradouro logradouro122 = new Logradouro(null, "RUA CARLOS TERTULIANO", bairro6);
		Logradouro logradouro123 = new Logradouro(null, "RUA JACARANDA", bairro6);
		Logradouro logradouro124 = new Logradouro(null, "RUA INA BRITO", bairro6);
		Logradouro logradouro125 = new Logradouro(null, "RUA 06", bairro6);
		Logradouro logradouro126 = new Logradouro(null, "RUA FRANCISCO ALMEIDA", bairro6);
		Logradouro logradouro127 = new Logradouro(null, "TRAVESSA CARLOS TERTULIANO", bairro6);
		Logradouro logradouro128 = new Logradouro(null, "RUA MARTINS DE LIMA", bairro6);
		Logradouro logradouro129 = new Logradouro(null, "RUA UMARIZEIRA", bairro6);
		Logradouro logradouro130 = new Logradouro(null, "TRAVESSA TULIPA NEGRA", bairro6);

		// RUA DO PARQUE SANTA ROSA ID: 131 ATÉ 138

		Logradouro logradouro131 = new Logradouro(null, "RUA GETULIO VARGAS", bairro7);
		Logradouro logradouro132 = new Logradouro(null, "RUA OSóRIO CORREIA", bairro7);
		Logradouro logradouro133 = new Logradouro(null, "RUA PACAVIRA", bairro7);
		Logradouro logradouro134 = new Logradouro(null, "RUA VITORIA REGIA", bairro7);
		Logradouro logradouro135 = new Logradouro(null, "RUA TULIPA NEGRA", bairro7);
		Logradouro logradouro136 = new Logradouro(null, "RUA PEDRO CABRAL", bairro7);
		Logradouro logradouro137 = new Logradouro(null, "RUA EDUARDO ARAUJO", bairro7);
		Logradouro logradouro138 = new Logradouro(null, "RUA PROFESSOR CABRAL", bairro7);

		// RUA DO NOVO MONDUBIM ID: 139 ATÉ 144

		Logradouro logradouro139 = new Logradouro(null, "RUA LUíS DE PONTES", bairro8);
		Logradouro logradouro140 = new Logradouro(null, "RUA 101", bairro8);
		Logradouro logradouro141 = new Logradouro(null, "RUA JOAQUIM ALFREDO", bairro8);
		Logradouro logradouro142 = new Logradouro(null, "RUA 102", bairro8);
		Logradouro logradouro143 = new Logradouro(null, "RUA ALFREDO MAMEDE", bairro8);
		Logradouro logradouro144 = new Logradouro(null, "Rua 103", bairro8);

		// RUA DO ARACAPE

		Logradouro logradouro145 = new Logradouro(null, "TRAVESSA MENINO JESUS", bairro9);
		Logradouro logradouro146 = new Logradouro(null, "RUA B", bairro9);
		Logradouro logradouro147 = new Logradouro(null, "RUA E", bairro9);
		Logradouro logradouro148 = new Logradouro(null, "RUA D", bairro9);
		Logradouro logradouro149 = new Logradouro(null, "RUA C", bairro9);

		// ----------------------------------------------------------------------------

		bairro1.getLogradouros().addAll(Arrays.asList(logradouro1, logradouro2, logradouro3, logradouro4, logradouro5,
				logradouro6, logradouro7, logradouro8, logradouro9, logradouro10, logradouro11, logradouro12,
				logradouro13, logradouro14, logradouro15, logradouro16, logradouro17, logradouro18, logradouro19,
				logradouro20, logradouro21, logradouro22, logradouro23, logradouro24, logradouro25, logradouro26,
				logradouro27, logradouro28, logradouro29, logradouro30, logradouro31, logradouro32, logradouro33,
				logradouro34, logradouro35, logradouro36, logradouro37, logradouro38, logradouro39, logradouro40));

		bairro2.getLogradouros()
				.addAll(Arrays.asList(logradouro41, logradouro42, logradouro43, logradouro44, logradouro45,
						logradouro46, logradouro47, logradouro48, logradouro49, logradouro50, logradouro51,
						logradouro52, logradouro53, logradouro54, logradouro55, logradouro56));

		bairro3.getLogradouros()
				.addAll(Arrays.asList(logradouro57, logradouro58, logradouro59, logradouro60, logradouro61,
						logradouro62, logradouro63, logradouro64, logradouro65, logradouro66, logradouro67,
						logradouro68, logradouro69, logradouro70));

		bairro4.getLogradouros().addAll(Arrays.asList(logradouro71, logradouro72, logradouro73, logradouro74,
				logradouro75, logradouro76, logradouro77, logradouro78, logradouro79, logradouro80, logradouro81,
				logradouro82, logradouro83, logradouro84, logradouro85, logradouro86, logradouro87, logradouro88,
				logradouro89, logradouro90, logradouro91, logradouro92, logradouro93, logradouro94, logradouro95,
				logradouro96, logradouro97, logradouro98, logradouro99, logradouro100));

		bairro5.getLogradouros().addAll(Arrays.asList(logradouro101, logradouro102, logradouro103));

		bairro6.getLogradouros()
				.addAll(Arrays.asList(logradouro104, logradouro105, logradouro106, logradouro107, logradouro108,
						logradouro109, logradouro110, logradouro111, logradouro112, logradouro113, logradouro114,
						logradouro115, logradouro116, logradouro117, logradouro118, logradouro119, logradouro120,
						logradouro121, logradouro122, logradouro123, logradouro124, logradouro125, logradouro126,
						logradouro127, logradouro128, logradouro129, logradouro130));

		bairro7.getLogradouros().addAll(Arrays.asList(logradouro131, logradouro132, logradouro133, logradouro134,
				logradouro135, logradouro136, logradouro137, logradouro138));

		bairro8.getLogradouros().addAll(Arrays.asList(logradouro139, logradouro140, logradouro141, logradouro142,
				logradouro143, logradouro144));

		bairro9.getLogradouros()
				.addAll(Arrays.asList(logradouro145, logradouro146, logradouro147, logradouro148, logradouro149));

		logradouroRepository.saveAll(Arrays.asList(logradouro1, logradouro2, logradouro3, logradouro4, logradouro5,
				logradouro6, logradouro7, logradouro8, logradouro9, logradouro10, logradouro11, logradouro12,
				logradouro13, logradouro14, logradouro15, logradouro16, logradouro17, logradouro18, logradouro19,
				logradouro20, logradouro21, logradouro22, logradouro23, logradouro24, logradouro25, logradouro26,
				logradouro27, logradouro28, logradouro29, logradouro30, logradouro31, logradouro32, logradouro33,
				logradouro34, logradouro35, logradouro36, logradouro37, logradouro38, logradouro39, logradouro40,
				logradouro41, logradouro42, logradouro43, logradouro44, logradouro45, logradouro46, logradouro47,
				logradouro48, logradouro49, logradouro50, logradouro51, logradouro52, logradouro53, logradouro54,
				logradouro55, logradouro56, logradouro57, logradouro58, logradouro59, logradouro60, logradouro61,
				logradouro62, logradouro63, logradouro64, logradouro65, logradouro66, logradouro67, logradouro68,
				logradouro69, logradouro70, logradouro71, logradouro72, logradouro73, logradouro74, logradouro75,
				logradouro76, logradouro77, logradouro78, logradouro79, logradouro80, logradouro81, logradouro82,
				logradouro83, logradouro84, logradouro85, logradouro86, logradouro87, logradouro88, logradouro89,
				logradouro90, logradouro91, logradouro92, logradouro93, logradouro94, logradouro95, logradouro96,
				logradouro97, logradouro98, logradouro99, logradouro100, logradouro101, logradouro102, logradouro103,
				logradouro104, logradouro105, logradouro106, logradouro107, logradouro108, logradouro109, logradouro110,
				logradouro111, logradouro112, logradouro113, logradouro114, logradouro115, logradouro116, logradouro117,
				logradouro118, logradouro119, logradouro120, logradouro121, logradouro122, logradouro123, logradouro124,
				logradouro125, logradouro126, logradouro127, logradouro128, logradouro129, logradouro130, logradouro131,
				logradouro132, logradouro133, logradouro134, logradouro135, logradouro136, logradouro137, logradouro138,
				logradouro139, logradouro140, logradouro141, logradouro142, logradouro143, logradouro144, logradouro145,
				logradouro146, logradouro147, logradouro148, logradouro149));

		// ----------------------------------------------------------------------------------------------------------------
		SimpleDateFormat data = new SimpleDateFormat("dd/MM/yyyy");

		ClientePlano cli1 = new ClientePlano(null, "Maria Silva", "15151651", "97525464049", data.parse("05/07/2020"),
				"mariasilva1@gmail.com", TipoCliente.PESSOAFISICA, logradouro28, p3, FormaPagamento.CARTAO,
				Vencimento.CINCO);
		cli1.getTelefones().addAll(Arrays.asList("32967050", "98504271"));

		Endereco endereco1 = new Endereco(null, "300", null, "60540210", cli1, null);

		ClientePlano cli2 = new ClientePlano(null, "Roberto Silva", "546561", "77322498011", data.parse("05/04/2020"),
				"roberto@gmail.com", TipoCliente.PESSOAJURIDICA, logradouro119, p1, FormaPagamento.AVISTA,
				Vencimento.DEZ);
		cli2.getTelefones().addAll(Arrays.asList("34509040", "986704599"));

		Endereco endereco2 = new Endereco(null, "2541", null, "60000000", cli2, null);

		clienteRepository.saveAll(Arrays.asList(cli1, cli2));

		ClienteCombo clicombo1 = new ClienteCombo(null, "Luiz Felipe", "16565445", "80691295069",
				data.parse("05/07/2020"), "luiz2@gmail.com", logradouro139, TipoCliente.PESSOAFISICA,
				FormaPagamento.AVISTA, Vencimento.CINCO, combo1);
		clicombo1.getTelefones().addAll(Arrays.asList("35452211", "45806655"));

		Endereco endereco3 = new Endereco(null, "600", null, "60457062", null, clicombo1);

		clienteComboRepository.saveAll(Arrays.asList(clicombo1));

		enderecoRepository.saveAll(Arrays.asList(endereco1, endereco2, endereco3));
	}

}
