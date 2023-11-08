package com.example.endterm.Controller;

import com.example.endterm.Model.CartItem;
import com.example.endterm.Service.CartItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cart-items")
public class CartItemController {

    private final CartItemService cartItemService;

    @Autowired
    public CartItemController(CartItemService cartItemService) {
        this.cartItemService = cartItemService;
    }

    @GetMapping
    public List<CartItem> getAllCartItems() {
        return cartItemService.findAllCartItems();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CartItem> getCartItemById(@PathVariable Long id) {
        return cartItemService.findCartItemById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public CartItem createCartItem(@RequestBody CartItem cartItem) {
        return cartItemService.saveCartItem(cartItem);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CartItem> updateCartItem(@PathVariable Long id, @RequestBody CartItem cartItemDetails) {
        return cartItemService.findCartItemById(id)
                .map(cartItem -> {
                    cartItem.setCart(cartItemDetails.getCart());
                    cartItem.setQuantity(cartItemDetails.getClothingItem());
                    cartItem.setQuantity(cartItemDetails.getQuantity());
                    CartItem updatedCartItem = cartItemService.saveCartItem(cartItem);
                    return ResponseEntity.ok(updatedCartItem);
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCartItem(@PathVariable Long id) {
        return cartItemService.findCartItemById(id)
                .map(cartItem -> {
                    cartItemService.deleteCartItem(id);
                    return ResponseEntity.ok().<Void>build();
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}