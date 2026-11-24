package mylab.order.di.xml;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = "classpath:mylab-order-di.xml")
public class OrderSpringTest {

    @Autowired
    private ShoppingCart shoppingCart;

    @Autowired
    private OrderService orderService;

    // ShoppingCart Bean Test
    @Test
    public void testShoppingCart() {

        // A. shoppingCart 객체가 Null이 아닌지 확인
        assertNotNull(shoppingCart);

        // B. Product 목록의 크기 확인
        assertEquals(2, shoppingCart.getProducts().size());

        // C. 첫 번째 Product 이름 확인
        assertEquals("노트북", shoppingCart.getProducts().get(0).getName());

        // D. 두 번째 Product 이름 확인
        assertEquals("스마트폰", shoppingCart.getProducts().get(1).getName());
    }

    // OrderService Bean Test
    @Test
    public void testOrderService() {

        // A. orderService 객체가 Null이 아닌지 확인
        assertNotNull(orderService);

        // B. ShoppingCart 객체가 Null이 아닌지 확인
        assertNotNull(orderService.getShoppingCart());

        // C. 주문 총액 확인
        assertEquals(950000.0, orderService.calculateOrderTotal(), 0.001);
    }
}