package dev.kishore.stripepaymentgateway.service;


import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentLink;
import com.stripe.model.Price;
import com.stripe.param.PaymentLinkCreateParams;
import com.stripe.param.PriceCreateParams;
import org.springframework.stereotype.Service;
@Service

public class StripePaymentServiceImpl implements PaymentService {

    @Override
     public String makePayment(String orderId, Long amount) throws StripeException {
        Stripe.apiKey = "sk_test_51POg8w2Mba4871ciIoQ9UnZ00XSv4JKH7o81PeOrEvlBQluOijyVulAtRs8IWje79i6qVSF9vXbasFsUe5Qo4ncR00WrtZ49ze";

        PriceCreateParams params =
                PriceCreateParams.builder()
                        .setCurrency("usd")
                        .setUnitAmount(1000L)

                        .setProductData(
                                PriceCreateParams.ProductData.builder().setName("Gold Plan").build()
                        )
                        .build();

        Price price = Price.create(params);
        Stripe.apiKey = "sk_test_51POg8w2Mba4871ciIoQ9UnZ00XSv4JKH7o81PeOrEvlBQluOijyVulAtRs8IWje79i6qVSF9vXbasFsUe5Qo4ncR00WrtZ49ze";

        PaymentLinkCreateParams paymentParams =
                PaymentLinkCreateParams.builder()
                        .addLineItem(
                                PaymentLinkCreateParams.LineItem.builder()
                                        .setPrice(price.getId())
                                        .setQuantity(1L)
                                        .build()
                        )
                        .build();

        PaymentLink paymentLink = PaymentLink.create(paymentParams);
        return paymentLink.getUrl();
    }
}
