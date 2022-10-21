package com.chaos.monkey;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HolderController {

    @GetMapping("/holder")
    public String holder() {
        return "홍길동";
    }

}
