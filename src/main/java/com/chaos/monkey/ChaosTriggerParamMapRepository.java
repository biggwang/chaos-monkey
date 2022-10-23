package com.chaos.monkey;

import lombok.NoArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;

@Repository
public class ChaosTriggerParamMapRepository {

    private final List<String> list = Arrays.asList("루피", "11112222");

    public boolean isExist(String key) {
        return list.stream().anyMatch(key::contains);
    }
}
