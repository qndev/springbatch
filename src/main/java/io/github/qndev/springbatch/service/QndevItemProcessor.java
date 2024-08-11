package io.github.qndev.springbatch.service;

import io.github.qndev.springbatch.entity.Qndev;
import io.github.qndev.springbatch.entity.QndevWriter;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.lang.Nullable;

import java.util.Date;

public class QndevItemProcessor implements ItemProcessor<Qndev, QndevWriter> {

    private Log log = LogFactory.getLog(this.getClass());

    @Nullable
    @Override
    public QndevWriter process(Qndev qndev) {
        QndevWriter qndevWriter = new QndevWriter();
        qndevWriter.setDescription(qndev.getId() + "-" + qndev.getName() + "-" + qndev.getDescription());
        qndevWriter.setCreatedDate(new Date());
        qndevWriter.setUpdatedDate(new Date());
        log.error("+++++++++++++++++ " + qndevWriter.getDescription() + " +++++++++++++++++");
        return qndevWriter;
    }

}
