package com.example.endterm.Controller;

import com.example.endterm.Model.Cart;
import com.example.endterm.Service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/carts")
public class CartController {

    private final CartService cartService;

    @Autowired
    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @GetMapping
    public List<Cart> getAllCarts() {
        return cartService.findAllCarts();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cart> getCartById(@PathVariable Long id) {
        return cartService.findCartById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Cart createCart(@RequestBody Cart cart) {
        return cartService.saveCart(cart);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cart> updateCart(@PathVariable Long id, @RequestBody Cart cartDetails) {
        return cartService.findCartById(id)
                .map(cart -> {
                    cart.setClient(cartDetails.getClient());
                    Cart updatedCart = cartService.saveCart(cart);
                    return ResponseEntity.ok(updatedCart);
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCart(@PathVariable Long id) {
        return cartService.findCartById(id)
                .map(cart -> {
                    cartService.deleteCart(id);
                    return ResponseEntity.ok().<Void>build();
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}