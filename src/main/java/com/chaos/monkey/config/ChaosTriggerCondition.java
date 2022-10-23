package com.chaos.monkey.config;

import com.chaos.monkey.ChaosTriggerParamMapRepository;
import de.codecentric.spring.boot.chaos.monkey.configuration.toggles.ChaosToggles;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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

/**
 * TODO exception 주입시 여기 코드 살리면 400에러..
 */

@Slf4j
@Component
@RequiredArgsConstructor
public class ChaosTriggerCondition implements ChaosToggles {

    private static final String TOGGLE_KEYWORD = "bankAccountNumber";

    private final ChaosTriggerParamMapRepository repository;

    @Override
    public boolean isEnabled(String toggleName) {
        log.info("### check trigger");
        if (Objects.isNull(RequestContextHolder.getRequestAttributes())) {
            log.info("### not exist request");
            return false;
        }

        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        if (HttpMethod.GET.name().equals(request.getMethod())) {
            boolean isExist = isExistWhenGet(request);
            log.info("### get request isExist: {}", isExist);
            return isExist;
        } else if (HttpMethod.POST.name().equals(request.getMethod())) {
            boolean isExist = isExistWhenPost(request);
            log.info("### post request isExist: {}", isExist);
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
