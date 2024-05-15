package com.navi.loan.audit;

import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class AuditorAwareImpl implements AuditorAware {
    /**
     * @return
     */
    @Override
    public Optional getCurrentAuditor() {
        return Optional.of("LOAN_MS");
    }
}
