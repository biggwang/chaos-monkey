package com.chaos.monkey;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SendService {

    private final SimpleApiAdapter simpleApiAdapter;

    public String send(SendRequest sendRequest) throws Exception {
        int count = simpleApiAdapter.getData(sendRequest.getReceiverName());
        return String.format("[%d] %s 에게  %d 원 송금 완료", count, sendRequest.getReceiverName(), sendRequest.getAmount());
    }
}
