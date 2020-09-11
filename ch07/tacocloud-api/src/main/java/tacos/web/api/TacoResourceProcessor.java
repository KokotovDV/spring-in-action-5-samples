package tacos.web.api;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.hateoas.server.EntityLinks;
import org.springframework.hateoas.server.RepresentationModelProcessor;
import org.springframework.stereotype.Component;
import tacos.Taco;

/**
 * @author Dmitry Kokotov
 */
@Component
public class TacoResourceProcessor implements RepresentationModelProcessor <PagedModel<EntityModel<Taco>>>{

    private final EntityLinks entityLinks;

    public TacoResourceProcessor(EntityLinks entityLinks) {
        this.entityLinks = entityLinks;
    }

    @Override
    public PagedModel<EntityModel<Taco>> process(PagedModel<EntityModel<Taco>> model) {
            model.add(entityLinks
                            .linkFor(Taco.class)
                            .slash("recent")
                            .withRel("recents"));
            return model;

    }

}
