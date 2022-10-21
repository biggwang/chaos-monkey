package com.chaos.monkey;

import org.springframework.stereotype.Service;

@Service
public class SendService {

    public String send(int amount) {
        return String.format("%d 원 송금 완료", amount);
    }
}
