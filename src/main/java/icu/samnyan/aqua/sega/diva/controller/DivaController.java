package icu.samnyan.aqua.sega.diva.controller;

import icu.samnyan.aqua.sega.diva.handler.boot.AttendHandler;
import icu.samnyan.aqua.sega.diva.handler.boot.GameInitHandler;
import icu.samnyan.aqua.sega.diva.handler.card.*;
import icu.samnyan.aqua.sega.diva.handler.databank.*;
import icu.samnyan.aqua.sega.diva.handler.ingame.*;
import icu.samnyan.aqua.sega.diva.handler.operation.PingHandler;
import icu.samnyan.aqua.sega.diva.handler.user.*;
import icu.samnyan.aqua.sega.diva.model.request.BaseRequest;
import icu.samnyan.aqua.sega.diva.model.request.boot.GameInitRequest;
import icu.samnyan.aqua.sega.diva.model.request.card.CardProcedureRequest;
import icu.samnyan.aqua.sega.diva.model.request.card.ChangeNameRequest;
import icu.samnyan.aqua.sega.diva.model.request.card.ChangePasswdRequest;
import icu.samnyan.aqua.sega.diva.model.request.card.RegistrationRequest;
import icu.samnyan.aqua.sega.diva.model.request.databank.BannerDataRequest;
import icu.samnyan.aqua.sega.diva.model.request.databank.PsRankingRequest;
import icu.samnyan.aqua.sega.diva.model.request.ingame.*;
import icu.samnyan.aqua.sega.diva.model.request.user.PdUnlockRequest;
import icu.samnyan.aqua.sega.diva.model.request.user.PreStartRequest;
import icu.samnyan.aqua.sega.diva.model.request.user.SpendCreditRequest;
import icu.samnyan.aqua.sega.diva.model.request.user.StartRequest;
import icu.samnyan.aqua.sega.diva.util.DivaMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author samnyan (privateamusement@protonmail.com)
 */
@RestController
@RequestMapping("/diva")
public class DivaController {

    private static final Logger logger = LoggerFactory.getLogger(DivaController.class);

    private final GameInitHandler gameInitHandler;
    private final AttendHandler attendHandler;

    private final CardProcedureHandler cardProcedureHandler;
    private final ChangeNameHandler changeNameHandler;
    private final ChangePasswdHandler changePasswdHandler;
    private final InitPasswdHandler initPasswdHandler;
    private final RegistrationHandler registrationHandler;

    /**
     * Databank
     */
    private final BannerInfoHandler bannerInfoHandler;
    private final BannerDataHandler bannerDataHandler;
    private final CmPlyInfoHandler cmPlyInfoHandler;
    private final ContestInfoHandler contestInfoHandler;
    private final CstmzItmCtlgHandler cstmzItmCtlgHandler;
    private final CstmzItmNgMdlListHandler cstmzItmNgMdlListHandler;
    private final FestaInfoHandler festaInfoHandler;
    private final NgWordHandler ngWordHandler;
    private final NvRankingHandler nvRankingHandler;
    private final PsRankingHandler psRankingHandler;
    private final PstdHCtrlHandler pstdHCtrlHandler;
    private final PstdItemNgLstHandler pstdItemNgLstHandler;
    private final PvDefChrLstHandler pvDefChrLstHandler;
    private final PvListHandler pvListHandler;
    private final PvNgMdlLstHandler pvNgMdlLstHandler;
    private final QstInfHandler qstInfHandler;
    private final RmtWpLstHandler rmtWpLstHandler;
    private final ShopCatalogHandler shopCatalogHandler;

    private final BuyCstmzItmHandler buyCstmzItmHandler;
    private final BuyModuleHandler buyModuleHandler;
    private final GetPvPdHandler getPvPdHandler;
    private final ShopExitHandler shopExitHandler;
    private final StageResultHandler stageResultHandler;
    private final StageStartHandler stageStartHandler;
    private final StoreSsHandler storeSsHandler;

    private final PingHandler pingHandler;

    private final EndHandler endHandler;
    private final PdUnlockHandler pdUnlockHandler;
    private final PreStartHandler preStartHandler;
    private final SpendCreditHandler spendCreditHandler;
    private final StartHandler startHandler;

    private final DivaMapper mapper;

    public DivaController(GameInitHandler gameInitHandler, AttendHandler attendHandler, CardProcedureHandler cardProcedureHandler, ChangeNameHandler changeNameHandler, ChangePasswdHandler changePasswdHandler, InitPasswdHandler initPasswdHandler, RegistrationHandler registrationHandler, BannerInfoHandler bannerInfoHandler, BannerDataHandler bannerDataHandler, CmPlyInfoHandler cmPlyInfoHandler, ContestInfoHandler contestInfoHandler, CstmzItmCtlgHandler cstmzItmCtlgHandler, CstmzItmNgMdlListHandler cstmzItmNgMdlListHandler, FestaInfoHandler festaInfoHandler, NgWordHandler ngWordHandler, NvRankingHandler nvRankingHandler, PsRankingHandler psRankingHandler, PstdHCtrlHandler pstdHCtrlHandler, PstdItemNgLstHandler pstdItemNgLstHandler, PvDefChrLstHandler pvDefChrLstHandler, PvListHandler pvListHandler, PvNgMdlLstHandler pvNgMdlLstHandler, QstInfHandler qstInfHandler, RmtWpLstHandler rmtWpLstHandler, ShopCatalogHandler shopCatalogHandler, BuyCstmzItmHandler buyCstmzItmHandler, BuyModuleHandler buyModuleHandler, GetPvPdHandler getPvPdHandler, ShopExitHandler shopExitHandler, StageResultHandler stageResultHandler, StageStartHandler stageStartHandler, StoreSsHandler storeSsHandler, PingHandler pingHandler, EndHandler endHandler, PdUnlockHandler pdUnlockHandler, PreStartHandler preStartHandler, SpendCreditHandler spendCreditHandler, StartHandler startHandler, DivaMapper mapper) {
        this.gameInitHandler = gameInitHandler;
        this.attendHandler = attendHandler;
        this.cardProcedureHandler = cardProcedureHandler;
        this.changeNameHandler = changeNameHandler;
        this.changePasswdHandler = changePasswdHandler;
        this.initPasswdHandler = initPasswdHandler;
        this.registrationHandler = registrationHandler;
        this.bannerInfoHandler = bannerInfoHandler;
        this.bannerDataHandler = bannerDataHandler;
        this.cmPlyInfoHandler = cmPlyInfoHandler;
        this.contestInfoHandler = contestInfoHandler;
        this.cstmzItmCtlgHandler = cstmzItmCtlgHandler;
        this.cstmzItmNgMdlListHandler = cstmzItmNgMdlListHandler;
        this.festaInfoHandler = festaInfoHandler;
        this.ngWordHandler = ngWordHandler;
        this.nvRankingHandler = nvRankingHandler;
        this.psRankingHandler = psRankingHandler;
        this.pstdHCtrlHandler = pstdHCtrlHandler;
        this.pstdItemNgLstHandler = pstdItemNgLstHandler;
        this.pvDefChrLstHandler = pvDefChrLstHandler;
        this.pvListHandler = pvListHandler;
        this.pvNgMdlLstHandler = pvNgMdlLstHandler;
        this.qstInfHandler = qstInfHandler;
        this.rmtWpLstHandler = rmtWpLstHandler;
        this.shopCatalogHandler = shopCatalogHandler;
        this.buyCstmzItmHandler = buyCstmzItmHandler;
        this.buyModuleHandler = buyModuleHandler;
        this.getPvPdHandler = getPvPdHandler;
        this.shopExitHandler = shopExitHandler;
        this.stageResultHandler = stageResultHandler;
        this.stageStartHandler = stageStartHandler;
        this.storeSsHandler = storeSsHandler;
        this.pingHandler = pingHandler;
        this.endHandler = endHandler;
        this.pdUnlockHandler = pdUnlockHandler;
        this.preStartHandler = preStartHandler;
        this.spendCreditHandler = spendCreditHandler;
        this.startHandler = startHandler;
        this.mapper = mapper;
    }


    @PostMapping(value = "", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String formRequest(HttpServletRequest request) throws IOException {
        String bodyStr = new String(request.getInputStream().readAllBytes());
        Map<String, Object> body = parse(bodyStr);

        String command = (String) body.getOrDefault("cmd", "");

        logger.info("{}: {}", command, body.toString());

        switch (command) {

            // Boot
            case "game_init":
                return gameInitHandler.handle(mapper.convert(body, GameInitRequest.class));
            case "attend":
                return attendHandler.handle(mapper.convert(body, GameInitRequest.class));

            //
            case "test":
                return gameInitHandler.handle(mapper.convert(body, BaseRequest.class));

            // Databank
            case "nv_ranking":
                return nvRankingHandler.handle(mapper.convert(body, BaseRequest.class));
            case "ps_ranking":
                return psRankingHandler.handle(mapper.convert(body, PsRankingRequest.class));
            case "pv_list":
                return pvListHandler.handle(mapper.convert(body, BaseRequest.class));
            case "ng_word":
                return ngWordHandler.handle(mapper.convert(body, BaseRequest.class));
            case "rmt_wp_list":
                return rmtWpLstHandler.handle(mapper.convert(body, BaseRequest.class));
            case "festa_info":
                return festaInfoHandler.handle(mapper.convert(body, BaseRequest.class));
            case "contest_info":
                return contestInfoHandler.handle(mapper.convert(body, BaseRequest.class));
            case "pv_def_chr_list":
                return pvDefChrLstHandler.handle(mapper.convert(body, BaseRequest.class));
            case "pv_ng_mdl_list":
                return pvNgMdlLstHandler.handle(mapper.convert(body, BaseRequest.class));
            case "cstmz_itm_ng_mdl_list":
                return cstmzItmNgMdlListHandler.handle(mapper.convert(body, BaseRequest.class));
            case "banner_info":
                return bannerInfoHandler.handle(mapper.convert(body, BaseRequest.class));
            case "banner_data":
                return bannerDataHandler.handle(mapper.convert(body, BannerDataRequest.class));
            case "cm_ply_info":
                return cmPlyInfoHandler.handle(mapper.convert(body, BaseRequest.class));
            case "qst_inf":
                return qstInfHandler.handle(mapper.convert(body, BaseRequest.class));
            case "pstd_h_ctrl":
                return pstdHCtrlHandler.handle(mapper.convert(body, BaseRequest.class));
            case "pstd_item_ng_lst":
                return pstdItemNgLstHandler.handle(mapper.convert(body, BaseRequest.class));
            case "shop_catalog":
                return shopCatalogHandler.handle(mapper.convert(body, BaseRequest.class));
            case "cstmz_itm_ctlg":
                return cstmzItmCtlgHandler.handle(mapper.convert(body, BaseRequest.class));

            case "card_procedure":
                return cardProcedureHandler.handle(mapper.convert(body, CardProcedureRequest.class));
            case "registration":
                return registrationHandler.handle(mapper.convert(body, RegistrationRequest.class));
            case "init_passwd":
                return initPasswdHandler.handle(mapper.convert(body, GameInitRequest.class));
            case "change_passwd":
                return changePasswdHandler.handle(mapper.convert(body, ChangePasswdRequest.class));
            case "change_name":
                return changeNameHandler.handle(mapper.convert(body, ChangeNameRequest.class));

            case "pre_start":
                return preStartHandler.handle(mapper.convert(body, PreStartRequest.class));
            case "start":
                return startHandler.handle(mapper.convert(body, StartRequest.class));
            case "pd_unlock":
                return pdUnlockHandler.handle(mapper.convert(body, PdUnlockRequest.class));
            case "spend_credit":
                return spendCreditHandler.handle(mapper.convert(body, SpendCreditRequest.class));
            case "no_card_end":
                return gameInitHandler.handle(mapper.convert(body, GameInitRequest.class));
            case "end":
                return endHandler.handle(mapper.convert(body, StageResultRequest.class));

            case "get_pv_pd":
                return getPvPdHandler.handle(mapper.convert(body, GetPvPdRequest.class));
            case "buy_module":
                return buyModuleHandler.handle(mapper.convert(body, BuyModuleRequest.class));
            case "buy_cstmz_itm":
                return buyCstmzItmHandler.handle(mapper.convert(body, BuyCstmzItmRequest.class));
            case "shop_exit":
                return shopExitHandler.handle(mapper.convert(body, ShopExitRequest.class));
            case "stage_start":
                return stageStartHandler.handle(mapper.convert(body, StageStartRequest.class));
            case "stage_result":
                return stageResultHandler.handle(mapper.convert(body, StageResultRequest.class));
            case "store_ss":
                return gameInitHandler.handle(mapper.convert(body, GameInitRequest.class));

            default:
                return "stat=0";

        }
    }

    @PostMapping(value = "", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String fileRequest(@RequestParam String query, @RequestParam(required = false) MultipartFile bin) throws IOException, ServletException {

        Map<String, Object> body = parse(query);

        String command = (String) body.getOrDefault("cmd", "");

        logger.info("{}: {}", command, body.toString());

        switch (command) {
            case "ping":
                return pingHandler.handle(mapper.convert(body, BaseRequest.class));
            case "investigate":
                return gameInitHandler.handle(mapper.convert(body, BaseRequest.class));
            case "store_ss":
                return storeSsHandler.handle(mapper.convert(body, StoreSsRequest.class), bin);
            default:
                return "stat=1";
        }
    }

    private Map<String, Object> parse(String form) {
        String[] kvps = form.split("&");
        Map<String, Object> body = new LinkedHashMap<>();
        for (String kvp :
                kvps) {
            String[] k = kvp.split("=");
            k[1] = URLDecoder.decode(k[1], StandardCharsets.UTF_8);
            Object value;
            if (k[1].contains(",")) {
                value = deArray(k[1]);
            } else {
                value = k[1];
            }
            body.put(k[0], value);
        }
        return body;
    }

    private Object deArray(String input) {
        if (!input.contains(",")) return input;
        return Arrays.stream(input.split(",")).map(x -> URLDecoder.decode(x, StandardCharsets.UTF_8))
                .map(this::deArray).collect(Collectors.toList());
    }
}
