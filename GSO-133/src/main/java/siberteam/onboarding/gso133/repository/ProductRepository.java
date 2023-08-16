package siberteam.onboarding.gso133.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import siberteam.onboarding.gso133.domain.ProductEntity;

public interface ProductRepository extends JpaRepository<ProductEntity, Integer> {
}
