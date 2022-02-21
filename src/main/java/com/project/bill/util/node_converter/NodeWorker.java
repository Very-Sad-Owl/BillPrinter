package com.project.bill.util.node_converter;

import by.epam.training.jwd.task03.entity.Node;
import com.project.bill.model.AbstractModel;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public abstract class NodeWorker<E extends AbstractModel> {

    public abstract E nodeToModel(Node node);
    public abstract Node modelToNode(E model);

    public void nodeToList(Node rootNode, List<E> models) {
        if(rootNode.getChildNodes() == null) {
            models.add(nodeToModel(rootNode)
            );
        } else {
            for(Node childNode : rootNode.getChildNodes()) {
                nodeToList(childNode, models);
            }
        }
    }

}
