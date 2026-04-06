package com.example.cart.cart;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

public class AddToCartRequest {

    @NotNull
    @Schema(description = "Product id to add to the new cart", example = "101")
    private Long pid;

    public AddToCartRequest() {
    }

    public AddToCartRequest(Long pid) {
        this.pid = pid;
    }

    public Long getPid() {
        return pid;
    }

    public void setPid(Long pid) {
        this.pid = pid;
    }
}
