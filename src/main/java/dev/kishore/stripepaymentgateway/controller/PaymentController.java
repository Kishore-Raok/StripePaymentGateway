package dev.kishore.stripepaymentgateway.controller;

import com.stripe.exception.StripeException;
import dev.kishore.stripepaymentgateway.dtos.PaymentRequestDto;
import dev.kishore.stripepaymentgateway.service.PaymentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PaymentController {
    PaymentService ps;

    public PaymentController(PaymentService ps) {
        this.ps = ps;
    }

    @PostMapping("/payment")
    public ResponseEntity<String> createPaymentLink(@RequestBody PaymentRequestDto paymentRequestDto) {
        try {

        String paymentLink = ps.makePayment(paymentRequestDto.getOrderId(), paymentRequestDto.getPaymentAmount());
        return new ResponseEntity<>(paymentLink, HttpStatus.OK);
        } catch (StripeException e) {
            // Log the exception and return an error response
            e.printStackTrace();
            return new ResponseEntity<>("Failed to create payment link", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }




}
