package com.project.bill.repository.impl;

import by.epam.training.jwd.task03.service.NodeTreeBuilder;
import com.project.bill.util.MessageLocaleService;
import com.project.bill.util.node_converter.WorkerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
public abstract class AbstractRepository {
    protected NodeTreeBuilder nodeTreeBuilder;
    protected WorkerFactory workerFactory;
    protected Environment env;
    protected MessageLocaleService locale;

    @Autowired
    public void setMessageLocaleService(MessageLocaleService locale) {
        this.locale = locale;
    }

    @Autowired
    public final void setEnv(Environment env){this.env = env;}

    @Autowired
    public final void setNodeTreeBuilder(NodeTreeBuilder nodeTreeBuilder) {
        this.nodeTreeBuilder = nodeTreeBuilder;
    }

    @Autowired
    public final void setWorkerFactory(WorkerFactory workerFactory){this.workerFactory = workerFactory;}

}
