package com.hassan.ecommerce.shop_service.service;

import com.hassan.ecommerce.shop_service.dto.AddToCartRequest;
import com.hassan.ecommerce.shop_service.dto.ProductResponse;
import com.hassan.ecommerce.shop_service.entity.Cart;
import com.hassan.ecommerce.shop_service.entity.CartItem;
import com.hassan.ecommerce.shop_service.exception.CartItemNotFoundException;
import com.hassan.ecommerce.shop_service.feign.InventoryClient;
import com.hassan.ecommerce.shop_service.repository.CartItemRepository;
import com.hassan.ecommerce.shop_service.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class CartService {
    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private CartItemRepository cartItemRepository;

    @Autowired
    private InventoryClient inventoryClient;


    private Cart getOrCreateCart(Long userId) {

        return cartRepository.findByUserId(userId)
                .orElseGet(() -> cartRepository.save(new Cart(userId)));
    }


    public Cart addToCart(Long userId, AddToCartRequest request){
        // Get the user's cart or create a new one
        Cart cart = getOrCreateCart(userId);

        // Get product information from Inventory Service
        ProductResponse productResponse = inventoryClient.getProductById(request.getProductId());

        // Check if the product already exists in the cart
        CartItem cartItem = cart.getItems()
                .stream()
                .filter(i -> i.getProductId().equals(request.getProductId()))
                .findFirst()
                .orElse(null);

        if (cartItem != null){
            // check availability of this product
            int newQuantity = cartItem.getQuantity() + request.getQuantity();

            Boolean available = inventoryClient.isAvailable(
                    request.getProductId(),
                    newQuantity
            );

            if (!available) {
                throw new IllegalArgumentException("Insufficient stock.");
            }

            cartItem.setQuantity(newQuantity);
        }
        else {
            // Create new cart item
            cartItem = new CartItem(
                    productResponse.getId(),
                    request.getQuantity(),
                    productResponse.getPrice());

            cart.addItem(cartItem);
        }
        cartRepository.save(cart);
        return cart;
    }


    public Cart getCart(Long userId){
        return  getOrCreateCart(userId);
    }


    public Cart updateQuantity(Long userId,
                               Long itemId,
                               Integer quantity) {

        if (quantity <= 0) {
            throw new IllegalArgumentException("Quantity must be greater than zero.");
        }

        Cart cart = getOrCreateCart(userId);

        CartItem cartItem = cart.getItems()
                .stream()
                .filter(item -> item.getId().equals(itemId))
                .findFirst()
                .orElseThrow(() ->
                        new CartItemNotFoundException("Cart item not found."));

        Boolean available = inventoryClient.isAvailable(
                cartItem.getProductId(),
                quantity
        );

        if (!available) {
            throw new IllegalArgumentException("Insufficient stock.");
        }

        cartItem.setQuantity(quantity);

        return cartRepository.save(cart);
    }


    public Cart removeItem(Long userId, Long itemId) {
        Cart cart = getOrCreateCart(userId);

        CartItem cartItem = cart.getItems()
                .stream()
                .filter(item -> item.getId().equals(itemId))
                .findFirst()
                .orElseThrow(() ->
                        new CartItemNotFoundException("Cart item not found."));

        cart.removeItem(cartItem);

        cartRepository.save(cart);
        return cart;
    }

    public void clearCart(Long userId) {

        Cart cart = getOrCreateCart(userId);
        cart.clearItems();

        cartRepository.save(cart);
    }

}


