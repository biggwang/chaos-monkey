package com.chaos.monkey;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class HolderController {

    private final HolderService holderService;

    @GetMapping("/holder")
    public String holder(@RequestParam(required = false) String bankAccountNumber) {
        return holderService.getName();
    }

}
