package com.chaos.monkey;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SendController {

    private SendService sendService;

    public SendController(SendService sendService) {
        this.sendService = sendService;
    }

    @PostMapping("/send")
    public String send(@RequestBody SendRequest sendRequest) {
        return sendService.send(sendRequest.getAmount());
    }
}

class SendRequest {
    private int amount;

    public SendRequest() {
    }

    public int getAmount() {
        return amount;
    }
}

