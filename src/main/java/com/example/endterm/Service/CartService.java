package com.example.endterm.Service;

import com.example.endterm.Model.Cart;
import com.example.endterm.Model.CartItem;
import com.example.endterm.Model.Client;
import com.example.endterm.Repository.CartRepository;
import com.example.endterm.Strategy.DiscountStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class CartService {

    private final CartRepository cartRepository;
    private Client client;
    private final DiscountStrategyService discountStrategyService; // Добавленная зависимость

    @Autowired
    public CartService(CartRepository cartRepository, DiscountStrategyService discountStrategyService) {
        this.cartRepository = cartRepository;
        this.discountStrategyService = discountStrategyService; // Инициализация в конструкторе
    }

    public List<Cart> findAllCarts() {
        return cartRepository.findAll();
    }

    public Optional<Cart> findCartById(Long id) {
        return cartRepository.findById(id);
    }

    public Cart saveCart(Cart cart) {
        return cartRepository.save(cart);
    }

    public void deleteCart(Long id) {
        cartRepository.deleteById(id);
    }

    // Метод для рассчета итоговой суммы с учетом скидки
    public BigDecimal calculateTotalWithDiscount(Cart cart) {
        BigDecimal totalAmount = cart.getItems().stream()
                .map(CartItem::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        // Получаем подходящую стратегию скидок для текущей суммы корзины
        DiscountStrategy discountStrategy = discountStrategyService.getDiscountStrategy(totalAmount);

        // Применяем стратегию скидки
        return discountStrategy.applyDiscount(totalAmount);
    }

    public Cart createCartForClient(Client savedClient) {
        Cart cart = new Cart();
        cart.setClient(client);
        return cartRepository.save(cart);
    }


    // ... Остальные методы класса
}