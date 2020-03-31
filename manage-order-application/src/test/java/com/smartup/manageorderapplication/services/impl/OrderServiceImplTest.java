package com.smartup.manageorderapplication.services.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.smartup.manageorderapplication.dto.ClientDto;
import com.smartup.manageorderapplication.dto.ItemOrderDto;
import com.smartup.manageorderapplication.dto.OrderDto;
import com.smartup.manageorderapplication.dto.ProductDto;
import com.smartup.manageorderapplication.entities.Client;
import com.smartup.manageorderapplication.entities.ItemOrder;
import com.smartup.manageorderapplication.entities.Order;
import com.smartup.manageorderapplication.entities.Product;
import com.smartup.manageorderapplication.exceptions.OrderIdsInputInvalidException;
import com.smartup.manageorderapplication.exceptions.OrderUnavailableException;
import com.smartup.manageorderapplication.exceptions.ShippingCityUnavailableException;
import com.smartup.manageorderapplication.repositories.OrderRepository;
import com.smartup.manageorderapplication.utils.mappers.OrderMapper;

@RunWith(MockitoJUnitRunner.class)
public class OrderServiceImplTest {

	@Mock
	private OrderRepository repository;
	
	@Mock
	private OrderMapper orderMapper;
	
	@InjectMocks
	private OrderServiceImpl service;
	
	private List<Order> lastOrders;
	private OrderDto inDto;
	private Client c1;
	private Product p1;
	private Product p2;
	private Order o1;
	private Order o2;
	
	@Before
	public void setUp () {
		List<ItemOrderDto> invalidsItems = new ArrayList<ItemOrderDto>();
		ProductDto invalidProduct = ProductDto.builder().id(1L).build();
		ItemOrderDto invalidItem = ItemOrderDto.builder().amount(2L).product(invalidProduct).build();
		invalidsItems.add(invalidItem);
		inDto = OrderDto.builder()
			.address("Calle Madrid")
			.postalCode(00000)
			.shippingCity("Madrid")
			.client(ClientDto.builder().id(1L).build())
			.items(invalidsItems)
			.build();
		c1 = getClient(1L,"00000000A", "Alex","A");
		p1 = getProduct(1L, "Samsung", "S10", "Samsung 10", 700.0, 21.0);
		p2 = getProduct(2L, "Samsung", "S9", "Samsung 9", 500.0, 21.0);
		o1 = getOrder(1L);
		o2 = getOrder(2L);
		lastOrders = getLastOrders();
		
	}
	
	@After
	public void tearDown () {
		List<ItemOrderDto> invalidsItems = new ArrayList<ItemOrderDto>();
		ProductDto invalidProduct = ProductDto.builder().id(1L).build();
		ItemOrderDto invalidItem = ItemOrderDto.builder().amount(2L).product(invalidProduct).build();
		invalidsItems.add(invalidItem);
		inDto = OrderDto.builder()
			.address("Calle Madrid")
			.postalCode(00000)
			.shippingCity("Madrid")
			.client(ClientDto.builder().id(1L).build())
			.items(invalidsItems)
			.build(); 
	}
	
	@Test
	public void shouldThrowOrderIdsInputInvalidExceptionWhenInvalidClientIdInDto() {
		inDto.getClient().setId(null);
		assertThrows(OrderIdsInputInvalidException.class, () -> service.createOrder(inDto));
		
	}
	
	@Test
	public void shouldThrowOrderIdsInputInvalidExceptionWhenInvalidProductIdsInDto() {
		inDto.getItems().stream().forEach(item -> item.getProduct().setId(null));
		assertThrows(OrderIdsInputInvalidException.class, () -> service.createOrder(inDto));
	}
		
	
	@Test
	public void shouldThrowShippingCityUnavailableExceptionWhenInvalidShippingCity() {
		inDto.setShippingCity("Zurich");
		assertThrows(ShippingCityUnavailableException.class, () -> service.createOrder(inDto));
	}
		
	
	@Test
	public void shouldThrowOrderUnavailableExceptionWhenDuplicateOrdersIntoTenMinutes() {
		when(repository.findByClientIdAndOrderDateBetweenOrderByOrderDate(Mockito.isA(Long.class), Mockito.isA(LocalDateTime.class), Mockito.isA(LocalDateTime.class))).thenReturn(lastOrders);
		when(orderMapper.dto2Entity(Mockito.any((OrderDto.class)))).thenReturn(o1);
		assertThrows(OrderUnavailableException.class, () -> service.createOrder(inDto));
	}
	
	@Test
	public void shouldCreateOrderWhenValidInDto() {
		when(repository.findByClientIdAndOrderDateBetweenOrderByOrderDate(Mockito.isA(Long.class), Mockito.isA(LocalDateTime.class), Mockito.isA(LocalDateTime.class))).thenReturn(lastOrders);
		when(orderMapper.dto2Entity(Mockito.any((OrderDto.class)))).thenReturn(o2);
		when(repository.saveAndFlush(Mockito.isA(Order.class))).thenReturn(o2);
		when(orderMapper.entity2Dto(Mockito.any((Order.class)))).thenReturn(inDto);
		OrderDto response = service.createOrder(inDto);
		assertEquals(inDto.getId(), response.getId());
	}
	
	private List<Order>  getLastOrders(){
		List<Order> orders = new ArrayList<Order>();
		orders.add(o1);
		return orders;
	}
	
	private Order getOrder(Long amount) {
		List<ItemOrder> items = new ArrayList<>();
		ItemOrder item1 = ItemOrder.builder().amount(amount).product(p1).build();
		ItemOrder item2 = ItemOrder.builder().amount(amount).product(p2).build();		
		items.add(item1);
		items.add(item2);
		return Order.builder().client(c1).items(items).build();
	}
	
	private Client getClient (Long id, String dni, String name, String lastname) {
		return Client.builder().id(id).dni(dni).name(name).lastname(lastname).build();
	}
	private Product getProduct (Long id, String brand, String model, String name, Double price, Double iva) {
		return Product.builder().id(id).brand(brand).model(model).name(name).price(price).iva(iva).build();
	}
	
}
