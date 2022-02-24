package org.eshishkin.edu.demographql.persistence.repository;

import org.eshishkin.edu.demographql.persistence.model.MerchantEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MerchantRepository extends JpaRepository<MerchantEntity, Long> {
}