package transfer.money.conversion.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import transfer.money.conversion.domain.Owner;
@Repository
public interface OwnerRepository extends JpaRepository<Owner, Long> {
    Optional<Owner> findByImmatriculationSocialNumber(String immatriculationSocialNumber);
}
