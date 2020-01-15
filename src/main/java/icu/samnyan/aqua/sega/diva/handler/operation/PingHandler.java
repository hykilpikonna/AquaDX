package icu.samnyan.aqua.sega.diva.handler.operation;

import icu.samnyan.aqua.sega.diva.handler.BaseHandler;
import icu.samnyan.aqua.sega.diva.model.request.BaseRequest;
import icu.samnyan.aqua.sega.diva.model.response.operation.PingResponse;
import icu.samnyan.aqua.sega.diva.util.DivaMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @author samnyan (privateamusement@protonmail.com)
 */
@Component
public class PingHandler extends BaseHandler {

    private static final Logger logger = LoggerFactory.getLogger(PingHandler.class);

    public PingHandler(DivaMapper mapper) {
        super(mapper);
    }

    public String handle(BaseRequest request) {

        PingResponse response = new PingResponse(
                request.getCmd(),
                request.getReq_id(),
                "ok"
        );

        String resp = this.build(mapper.toMap(response));
        logger.info("Response: {}", resp);

        return resp;
    }
}
