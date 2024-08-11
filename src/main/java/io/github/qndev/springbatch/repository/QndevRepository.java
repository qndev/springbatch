package io.github.qndev.springbatch.repository;

import io.github.qndev.springbatch.entity.Qndev;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QndevRepository extends JpaRepository<Qndev, Long> {
    Page<Qndev> findAll(Pageable pageable);
}
