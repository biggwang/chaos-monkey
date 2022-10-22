package com.chaos.monkey;

import de.codecentric.spring.boot.chaos.monkey.configuration.toggles.ChaosToggles;
import org.apache.commons.io.IOUtils;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Supplier;

@Component
public class ChaosTriggerCondition implements ChaosToggles {

    private final ChaosTriggerParamMapRepository repository;
    private static final String TOGGLE_KEYWORD = "bankAccountNumber";

    public ChaosTriggerCondition(ChaosTriggerParamMapRepository repository) {
        this.repository = repository;
    }

    @Override
    public boolean isEnabled(String toggleName) {
        System.out.println("### check trigger");
        if (Objects.isNull(RequestContextHolder.getRequestAttributes())) {
            System.out.println("### not exist request");
            return false;
        }

        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        if (HttpMethod.GET.name().equals(request.getMethod())) {
            boolean isExist = isExistWhenGet(request);
            System.out.println("### get request isExist:" + isExist);
            return isExist;
        } else if (HttpMethod.POST.name().equals(request.getMethod())) {
            boolean isExist = isExistWhenPost(request);
            System.out.println("### post request isExist:" + isExist);
            return isExist;
        } else {
            return false;
        }
    }

    private boolean isExistWhenGet(HttpServletRequest request) {
        String requestValue = Optional.ofNullable(request.getParameter(TOGGLE_KEYWORD)).orElse("");
        return repository.isExist(requestValue);
    }

    private boolean isExistWhenPost(HttpServletRequest request) {
        String requestValue = null;
        try {
            requestValue = IOUtils.toString(request.getInputStream(), StandardCharsets.UTF_8);
        } catch (IOException e) {
            return false;
        }
        return repository.isExist(requestValue);
    }

    private boolean isTrigger(Supplier<String> func) {
        String requestValue = func.get();
        return repository.isExist(requestValue);
    }
}
