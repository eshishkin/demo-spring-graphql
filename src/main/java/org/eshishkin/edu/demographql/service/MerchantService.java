package org.eshishkin.edu.demographql.service;

import lombok.RequiredArgsConstructor;
import org.eshishkin.edu.demographql.model.CreateMerchantRequest;
import org.eshishkin.edu.demographql.persistence.model.MerchantEntity;
import org.eshishkin.edu.demographql.persistence.repository.MerchantRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class MerchantService {
    private final MerchantRepository userRepository;

    public Mono<MerchantEntity> create(CreateMerchantRequest request) {
        return Mono.fromSupplier(() -> {
            var merchant = new MerchantEntity();
            merchant.setName(request.getName());
            merchant.setLocatedIn(request.getLocatedIn());
            return userRepository.save(merchant);
        });
    }
}
