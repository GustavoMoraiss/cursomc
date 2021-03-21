package com.gustavoweb.cursomc;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.gustavoweb.cursomc.domain.Categoria;
import com.gustavoweb.cursomc.domain.Cidade;
import com.gustavoweb.cursomc.domain.Cliente;
import com.gustavoweb.cursomc.domain.Endereco;
import com.gustavoweb.cursomc.domain.Estado;
import com.gustavoweb.cursomc.domain.ItemPedido;
import com.gustavoweb.cursomc.domain.Pagamento;
import com.gustavoweb.cursomc.domain.PagamentoComBoleto;
import com.gustavoweb.cursomc.domain.PagamentoComCartao;
import com.gustavoweb.cursomc.domain.Pedido;
import com.gustavoweb.cursomc.domain.Produto;
import com.gustavoweb.cursomc.domain.enums.EstadoPagamento;
import com.gustavoweb.cursomc.domain.enums.TipoCliente;
import com.gustavoweb.cursomc.repositories.CategoriaRepository;
import com.gustavoweb.cursomc.repositories.CidadeRepository;
import com.gustavoweb.cursomc.repositories.ClienteRepository;
import com.gustavoweb.cursomc.repositories.EnderecoRepository;
import com.gustavoweb.cursomc.repositories.EstadoRepository;
import com.gustavoweb.cursomc.repositories.ItemPedidoRepository;
import com.gustavoweb.cursomc.repositories.PagamentoRepository;
import com.gustavoweb.cursomc.repositories.PedidoRepository;
import com.gustavoweb.cursomc.repositories.ProdutoRepository;

@SpringBootApplication
public class CursomcApplication implements CommandLineRunner {

	@Autowired
	private CategoriaRepository categoriaRepository;

	@Autowired
	private ProdutoRepository produtoRepository;

	@Autowired
	private EstadoRepository estadoRepository;

	@Autowired
	private CidadeRepository cidadeRepository;

	@Autowired
	private ClienteRepository clienteRepository;

	@Autowired
	private EnderecoRepository enderecoRepository;

	@Autowired
	private PedidoRepository pedidoRepository;

	@Autowired
	private PagamentoRepository pagamentoRepository;
	
	@Autowired
	private ItemPedidoRepository itemPedidoRepository;

	public static void main(String[] args) {
		SpringApplication.run(CursomcApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");

		Categoria cat1 = new Categoria(null, "Informática");
		Categoria cat2 = new Categoria(null, "Escritório");
		Categoria cat3 = new Categoria(null, "Firma");
		Categoria cat4 = new Categoria(null, "Super-Mercado");
		Categoria cat5 = new Categoria(null, "Contabilidade");
		Categoria cat6 = new Categoria(null, "Pet-shop");
		Categoria cat7 = new Categoria(null, "Shopping");
		Categoria cat8 = new Categoria(null, "Tabacaria");
		Categoria cat9 = new Categoria(null, "Moto-peças");

		Produto p1 = new Produto(null, "Computador", 2000.00);
		Produto p2 = new Produto(null, "Impressora", 800.00);
		Produto p3 = new Produto(null, "Mouse", 80.00);

		cat1.getProdutos().addAll(Arrays.asList(p1, p2, p3));
		cat2.getProdutos().addAll(Arrays.asList(p2));

		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat2, cat1));
		p3.getCategorias().addAll(Arrays.asList(cat1));

		Estado est1 = new Estado(null, "Minas Gerais");
		Estado est2 = new Estado(null, "São Paulo");
		Estado est3 = new Estado(null, "Rio de Janeiro");

		Cidade c1 = new Cidade(null, "Uberlândia", est1);
		Cidade c2 = new Cidade(null, "Santo André", est2);
		Cidade c4 = new Cidade(null, "Mauá", est2);
		Cidade c3 = new Cidade(null, "Barra da Tijuca", est3);

		est1.getCidades().addAll(Arrays.asList(c1));
		est2.getCidades().addAll(Arrays.asList(c2, c4));
		est3.getCidades().addAll(Arrays.asList(c3));

		Cliente cli1 = new Cliente(null, "Gustavo Morais", "gustavo@gmail.com", "46329223823",
				TipoCliente.PESSOAFISICA);
		cli1.getTelefones().addAll(Arrays.asList("985355789", "44572390"));

		Cliente cli2 = new Cliente(null, "Bruna Severo", "gustavo@gmail.com", "46329224444", TipoCliente.PESSOAFISICA);
		cli2.getTelefones().addAll(Arrays.asList("980005555", "44578585"));

		Endereco e1 = new Endereco(null, "Rua hermes fontes", "81", "perto do campo", "Jardim Aclimação", "09170770",
				cli1, c1);
		Endereco e2 = new Endereco(null, "Avenida Perimetral", "90", "avenida principal", "Centro S.A.", "09170777",
				cli2, c2);

		cli1.getEnderecos().addAll(Arrays.asList(e1));
		cli2.getEnderecos().addAll(Arrays.asList(e2));

		Pedido ped1 = new Pedido(null, sdf.parse("30/09/2020 10:32"), cli1, e1);
		Pedido ped2 = new Pedido(null, sdf.parse("10/12/2020 10:32"), cli2, e2);

		Pagamento pag1 = new PagamentoComCartao(null, EstadoPagamento.QUITADO, ped1, 6);
		ped1.setPagamento(pag1);
		Pagamento pag2 = new PagamentoComBoleto(null, EstadoPagamento.PENDENTE, ped2, sdf.parse("10/03/2021 00:32"),
				null);
		ped2.setPagamento(pag2);

		cli1.getPedidos().addAll(Arrays.asList(ped1, ped2));
		
		ItemPedido ip1 = new ItemPedido(ped1, p1, 0.00, 1, 2000.0);
		ItemPedido ip2 = new ItemPedido(ped1, p3, 0.00, 2, 80.0);
		ItemPedido ip3 = new ItemPedido(ped2, p2, 100.00, 5, 4000.0);
		
		ped1.getItens().addAll(Arrays.asList(ip1, ip2));
		ped2.getItens().addAll(Arrays.asList(ip3));
		
		p1.getItens().addAll(Arrays.asList(ip1));
		p2.getItens().addAll(Arrays.asList(ip3));
		p3.getItens().addAll(Arrays.asList(ip3));

		categoriaRepository.saveAll(Arrays.asList(cat1, cat2, cat3, cat4, cat5, cat6, cat7, cat8, cat9));
		produtoRepository.saveAll(Arrays.asList(p1, p2, p3));
		estadoRepository.saveAll(Arrays.asList(est1, est2, est3));
		cidadeRepository.saveAll(Arrays.asList(c1, c2, c3));
		clienteRepository.saveAll(Arrays.asList(cli1, cli2));
		enderecoRepository.saveAll(Arrays.asList(e1, e2));
		pedidoRepository.saveAll(Arrays.asList(ped1, ped2));
		pagamentoRepository.saveAll(Arrays.asList(pag1, pag2));
		itemPedidoRepository.saveAll(Arrays.asList(ip1, ip2, ip3));

	}

}
