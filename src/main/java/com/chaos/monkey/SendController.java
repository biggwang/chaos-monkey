package com.chaos.monkey;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class SendController {

    private final SendService sendService;

    @PostMapping("/send")
    public String send(@RequestBody SendRequest sendRequest) throws Exception {
        return sendService.send(sendRequest);
    }
}


