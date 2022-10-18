package com.chaos.monkey;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RemittanceController {

    @GetMapping("/remittance")
    public String remittance() {
        return "송금";
    }

}
