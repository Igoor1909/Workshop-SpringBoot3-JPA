package com.educandoweb.course.config;

import java.time.Instant;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.educandoweb.course.entities.Category;
import com.educandoweb.course.entities.Order;
import com.educandoweb.course.entities.OrderItem;
import com.educandoweb.course.entities.Payment;
import com.educandoweb.course.entities.Product;
import com.educandoweb.course.entities.User;
import com.educandoweb.course.entities.enums.OrderStatus;
import com.educandoweb.course.repositores.CategoryRepository;
import com.educandoweb.course.repositores.OrderItemRepository;
import com.educandoweb.course.repositores.OrderRepository;
import com.educandoweb.course.repositores.ProductRepository;
import com.educandoweb.course.repositores.UserRepository;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner {

	@Autowired
	private UserRepository userRepository;
	// instanciando o repositoruo de usuarios
	
	@Autowired
	private OrderRepository orderRepository;
	// instanciando o repositorio de pedidos
	
	@Autowired
	private CategoryRepository categoryRepository;
	// instanciando o repositorio de categorias
	
	@Autowired
	private ProductRepository productRepository;
	// instanciando o repositorio de produtos
	
	@Autowired
	private OrderItemRepository orderItemRepository;
	// instanciando o repositorio de produtos vinculados a vendas

	@Override
	public void run(String... args) throws Exception {
		User u1 = new User(null, "Maria Brown", "maria@gmail.com", "988888", "123456");
		User u2 = new User(null, "alex", "alex@gmail.com", "984389344", "12345");
		// adicionando alguns usuarios
		
		Order o1 = new Order(null, Instant.parse("2019-06-20T19:53:07Z"), OrderStatus.PAID, u1);
		Order o2 = new Order(null, Instant.parse("2019-07-21T03:42:10Z"), OrderStatus.WAITING_PAYMENT, u2);
		Order o3 = new Order(null, Instant.parse("2019-07-22T15:21:22Z"), OrderStatus.CANCELED, u1); 
		// adicionando alguns pedidos
		
		Category cat1 = new Category(null, "Electronics");
		Category cat2 = new Category(null, "Books");
		Category cat3 = new Category(null, "Computers"); 
		// adicionando algumas categorias
		
		Product p1 = new Product(null, "The Lord of the Rings", "Lorem ipsum dolor sit amet, consectetur.", 90.5, "");
		Product p2 = new Product(null, "Smart TV", "Nulla eu imperdiet purus. Maecenas ante.", 2190.0, "");
		Product p3 = new Product(null, "Macbook Pro", "Nam eleifend maximus tortor, at mollis.", 1250.0, "");
		Product p4 = new Product(null, "PC Gamer", "Donec aliquet odio ac rhoncus cursus.", 1200.0, "");
		Product p5 = new Product(null, "Rails for Dummies", "Cras fringilla convallis sem vel faucibus.", 100.99, ""); 
		// adicionando alguns produtos

		userRepository.saveAll(Arrays.asList(u1,u2));
		orderRepository.saveAll(Arrays.asList(o1,o2,o3));
		categoryRepository.saveAll(Arrays.asList(cat1, cat2, cat3));
		productRepository.saveAll(Arrays.asList(p1, p2, p3, p4, p5));
		// salvando todos os objetos em seus respectivos repositorios por meio de listas
		
		OrderItem oi1 = new OrderItem(o1, p1, 2, p1.getPrice());
		OrderItem oi2 = new OrderItem(o1, p3, 1, p3.getPrice());
		OrderItem oi3 = new OrderItem(o2, p3, 2, p3.getPrice());
		OrderItem oi4 = new OrderItem(o3, p5, 2, p5.getPrice()); 
		// trabalhando com chaves estrangeiras vinculados produtos e pedidos
		
		
		p1.getCategories().add(cat2);
		p2.getCategories().add(cat1);
		p2.getCategories().add(cat3);
		p3.getCategories().add(cat3);
		p4.getCategories().add(cat3);
		p5.getCategories().add(cat2);
		// adicionando as categorias cadastradas aos produtos
		
		productRepository.saveAll(Arrays.asList(p1,p2,p3,p4,p5));
		orderItemRepository.saveAll(Arrays.asList(oi1, oi2, oi3, oi4));
		// salvando as informações anteriores dentro dos seus respectivos repositorios
		
		Payment pay1 =  new Payment(null,Instant.parse("2019-06-20T21:53:07Z"), o1 );
		o1.setPayment(pay1);
		// adicionando uma tabela de pagamentos e instanciando o objeto responsavel por salvar suas informações
		
		orderRepository.save(o1);
		// salvando as informações enviando ao respositorio responsavel
		
		
		
		
		
	}
	
	
	
}
