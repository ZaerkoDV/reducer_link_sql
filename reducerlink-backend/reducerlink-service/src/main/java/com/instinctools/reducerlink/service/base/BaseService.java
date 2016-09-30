package com.instinctools.reducerlink.service.base;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import javax.mail.internet.MimeMessage;
import org.apache.velocity.app.VelocityEngine;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import com.instinctools.reducerlink.service.support.AppException;
import com.instinctools.reducerlink.service.support.ObjUtils;
import com.instinctools.reducerlink.service.support.TransformUtils;
import com.instinctools.reducerlink.service.support.VelocityEngineUtils;

public class BaseService {
    private static final Logger logger = LoggerFactory.getLogger(BaseService.class);
    public static final String ERROR_NOT_FOUND = "notFound";

    @Autowired
    private MessageSource messageSource;

    @Autowired
    private VelocityEngine velocityEngine;

    @Autowired
    private JavaMailSender mailSender;

    protected <S, D> List<D> mapList(Collection<S> collection, Function<S, D> mapper) {
        return TransformUtils.mapList(collection, mapper);
    }

    protected Map<String, Object> toMap(Object... args) {
        return TransformUtils.toMap(args);
    }

    protected String tran(String code) {
        return tran(code, null);
    }

    protected String tran(String code, Object[] args) {
        return messageSource.getMessage(code, args, code, LocaleContextHolder.getLocale());
    }

    protected int computePages(long totalRecords, int pageSize) {
        if (pageSize < 1) {
            pageSize = 1;
        }

        return (int)((totalRecords + (long)pageSize - 1L) / (long)pageSize);
    }

    protected <T> T ensureFound(T entity) {
        if (entity == null) {
            throw new AppException(ERROR_NOT_FOUND);
        }

        return entity;
    }

    protected void sendMail(String toEmailAddress, String fromEmailAddress, String templateLocation, Map<String, Object> model) {
        final String text = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine, templateLocation, model);
        final String subject = ObjUtils.asString(model, "subject");

        logger.info("sendMail : to = \"" + String.valueOf(toEmailAddress) + "\"");
        logger.info("sendMail : from = \"" + String.valueOf(fromEmailAddress) + "\"");
        logger.info("sendMail : subject = \"" + subject + "\"");
        logger.info("sendMail : text = \"" + text + "\"");

        MimeMessagePreparator preparator = new MimeMessagePreparator() {
            @Override
            public void prepare(MimeMessage mimeMessage) throws Exception {
                MimeMessageHelper message = new MimeMessageHelper(mimeMessage, true, "UTF-8");
                message.setTo(toEmailAddress);
                message.setFrom(fromEmailAddress); // TODO: probably real email address should be set
                message.setReplyTo(fromEmailAddress);
                message.setSubject(subject);
                message.setText(text, true);
            }
        };

        mailSender.send(preparator);
    }
}