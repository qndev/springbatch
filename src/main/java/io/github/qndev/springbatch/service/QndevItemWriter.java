package io.github.qndev.springbatch.service;

import io.github.qndev.springbatch.entity.QndevWriter;
import io.github.qndev.springbatch.repository.QndevWriterRepository;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.batch.item.ItemWriter;

import java.util.List;

public class QndevItemWriter implements ItemWriter<QndevWriter> {

    private Log log = LogFactory.getLog(this.getClass());
    private final QndevWriterRepository qndevWriterRepository;

    public QndevItemWriter(QndevWriterRepository qndevWriterRepository) {
        this.qndevWriterRepository = qndevWriterRepository;
    }

    @Override
    public void write(List<? extends QndevWriter> list) {
        log.error("+++++++++++++++++ QndevItemWriter: " + list.size() + " +++++++++++++++++");
        // for (QndevWriter qndevWriter : list) {
        //     if (qndevWriter.getDescription().equals("180-Qndev-Spring Batch")) {
        //         throw new RuntimeException("For exception testing.");
        //     }
        // }
        this.qndevWriterRepository.saveAll(list);
        // throw new RuntimeException("For exception testing.");
    }

}
