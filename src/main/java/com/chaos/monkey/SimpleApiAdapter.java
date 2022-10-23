package com.chaos.monkey;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Slf4j
@Component
@RequiredArgsConstructor
public class SimpleApiAdapter {

    private final RestTemplate restTemplate;

    public int getData(String holder) throws Exception {
        log.info("### ---> request: {}", holder);
        Response response = Optional.ofNullable(restTemplate.getForObject(String.format("https://api.agify.io/?name=%s", holder), Response.class))
                .orElseThrow(Exception::new);
        log.info("### <--- response: {}", response.toString());
        return response.getCount();
    }
}

@Getter
@NoArgsConstructor
class Response {
    private int count;
}
