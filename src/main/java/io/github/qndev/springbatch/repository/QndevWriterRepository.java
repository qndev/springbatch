package io.github.qndev.springbatch.repository;

import io.github.qndev.springbatch.entity.QndevWriter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QndevWriterRepository extends JpaRepository<QndevWriter, Long> {
}
