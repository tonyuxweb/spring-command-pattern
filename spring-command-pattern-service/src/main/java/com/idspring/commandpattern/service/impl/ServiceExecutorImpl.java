package com.idspring.commandpattern.service.impl;

import com.idspring.commandpattern.model.service.ServiceRequest;
import com.idspring.commandpattern.service.Command;
import com.idspring.commandpattern.service.ServiceExecutor;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

/**
 * @author Eko Kurniawan Khannedy
 * @since 30/06/17
 */
@Component
public class ServiceExecutorImpl implements ServiceExecutor, ApplicationContextAware {

    private ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @Override
    public <T, R extends ServiceRequest> Mono<T> execute(Class<? extends Command<T, R>> commandClass, R request) {
        Command<T, R> command = applicationContext.getBean(commandClass);
        return command.execute(request);
    }
}
