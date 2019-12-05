package ${package}.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupJpaRepository extends JpaRepository<CGroup, Integer> {
}
