package com.jl.service.parallelstreams;

import com.jl.domain.checkout.Cart;
import com.jl.domain.checkout.CheckoutResponse;
import com.jl.domain.checkout.CheckoutStatus;
import com.jl.service.PriceValidatorService;
import com.jl.service.parallelstreams.CheckoutService;
import com.jl.util.DataSet;
import org.hamcrest.core.IsEqual;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;


public class CheckoutServiceTest {

    private final PriceValidatorService priceValidatorService = new PriceValidatorService();
    private final CheckoutService checkoutService = new CheckoutService(priceValidatorService);

    @Test
    public void check_out_6_items() {

        Cart cart = DataSet.createCart(6);

        CheckoutResponse response = checkoutService.checkout(cart);

        assertThat(response.getCheckoutStatus(), IsEqual.equalTo(CheckoutStatus.SUCCESS));
    }

    @Test
    public void check_out_11_items() {
        Cart cart = DataSet.createCart(11);

        CheckoutResponse response = checkoutService.checkout(cart);

        assertThat(response.getCheckoutStatus(), IsEqual.equalTo(CheckoutStatus.FAILURE));
    }

    @Test
    public void check_out_12_items() {
        Cart cart = DataSet.createCart(12);

        CheckoutResponse response = checkoutService.checkout(cart);

        assertThat(response.getCheckoutStatus(), IsEqual.equalTo(CheckoutStatus.FAILURE));
    }


    @Test
    public void check_out_22_items() {
        Cart cart = DataSet.createCart(22);

        CheckoutResponse response = checkoutService.checkout(cart);

        assertThat(response.getCheckoutStatus(), IsEqual.equalTo(CheckoutStatus.FAILURE));
    }

}