package org.eshishkin.edu.demographql.service;

import lombok.RequiredArgsConstructor;
import org.eshishkin.edu.demographql.model.CreateMerchantRequest;
import org.eshishkin.edu.demographql.persistence.model.MerchantEntity;
import org.eshishkin.edu.demographql.persistence.repository.MerchantRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class MerchantService {
    private final MerchantRepository merchantRepository;

    public Mono<MerchantEntity> create(CreateMerchantRequest request) {
        return Mono.fromSupplier(() -> {
            var merchant = new MerchantEntity();
            merchant.setName(request.getName());
            merchant.setLocatedIn(request.getLocatedIn());
            return merchantRepository.save(merchant);
        });
    }

    public Flux<MerchantEntity> find(List<Long> ids) {
        return Flux.fromStream(() -> ids.isEmpty() ? Stream.empty() : merchantRepository.findAllById(ids).stream());
    }
}
