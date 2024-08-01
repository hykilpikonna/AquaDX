package icu.samnyan.aqua.api.controller.sega.manage;

import icu.samnyan.aqua.api.model.MessageResponse;
import icu.samnyan.aqua.api.model.req.sega.diva.ModuleEntry;
import icu.samnyan.aqua.api.model.req.sega.diva.PvListEntry;
import icu.samnyan.aqua.api.model.req.sega.diva.PvListRequest;
import icu.samnyan.aqua.sega.diva.dao.gamedata.*;
import icu.samnyan.aqua.sega.diva.model.common.Difficulty;
import icu.samnyan.aqua.sega.diva.model.common.Edition;
import icu.samnyan.aqua.sega.diva.model.gamedata.*;
import icu.samnyan.aqua.sega.general.dao.PropertyEntryRepository;
import icu.samnyan.aqua.sega.general.model.PropertyEntry;
import lombok.AllArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author samnyan (privateamusement@protonmail.com)
 */
@RestController
@RequestMapping("api/manage/diva/")
@ConditionalOnProperty(prefix = "aquaviewer.api", name = "enabled", havingValue = "true")
@AllArgsConstructor
public class ApiDivaManageController {

    private final PvEntryRepository pvEntryRepository;
    private final DivaModuleRepository moduleRepository;
    private final DivaCustomizeRepository customizeRepository;
    private final FestaRepository festaRepository;
    private final ContestRepository contestRepository;
    private final PropertyEntryRepository propertyEntryRepository;

    @PostMapping("pvList")
    public List<PvEntry> updatePvList(@RequestBody PvListRequest request) {
        request.getEasy().forEach(x -> savePv(x, Difficulty.EASY));
        request.getNormal().forEach(x -> savePv(x, Difficulty.NORMAL));
        request.getHard().forEach(x -> savePv(x, Difficulty.HARD));
        request.getExtreme().forEach(x -> savePv(x, Difficulty.EXTREME));
        return pvEntryRepository.findAll();
    }

    @PostMapping("module")
    public List<DivaModule> updateModuleList(@RequestBody List<ModuleEntry> request) {
        List<DivaModule> moduleList = new ArrayList<>();
        request.forEach(x -> moduleList.add(new DivaModule(x.getID(), x.getName(), x.getPrice(), x.getReleaseDate(), x.getEndDate(), x.getSortOrder())));
        return moduleRepository.saveAll(moduleList);
    }

    @PostMapping("item")
    public List<DivaCustomize> updateItemList(@RequestBody List<ModuleEntry> request) {
        List<DivaCustomize> itemList = new ArrayList<>();
        request.forEach(x -> itemList.add(new DivaCustomize(x.getID(), x.getName(), x.getPrice(), x.getReleaseDate(), x.getEndDate(), x.getSortOrder())));
        return customizeRepository.saveAll(itemList);
    }

    private void savePv(PvListEntry x, Difficulty difficulty) {
        pvEntryRepository.save(new PvEntry(x.getPVID(),
                difficulty,
                x.getVersion(),
                Edition.fromValue(x.getEdition()),
                x.getAdvDemo().getStart(),
                x.getAdvDemo().getEnd(),
                x.getPlayable().getStart(),
                x.getPlayable().getEnd()
        ));
    }

    @GetMapping("festa")
    public List<Festa> getFesta() {
        return festaRepository.findAll();
    }

    @PutMapping("festa")
    public Festa updateFesta(@RequestBody Festa festa) {
        return festaRepository.save(festa);
    }

    @DeleteMapping("festa/{id}")
    public MessageResponse getFesta(@PathVariable int id) {
        festaRepository.deleteById(id);
        return new MessageResponse("Deleted " + id);
    }

    @GetMapping("contest")
    public List<Contest> getContest() {
        return contestRepository.findAll();
    }

    @PutMapping("contest")
    public Contest updateContest(@RequestBody Contest contest) {
        return contestRepository.save(contest);
    }

    @DeleteMapping("contest/{id}")
    public MessageResponse deleteContest(@PathVariable int id) {
        contestRepository.deleteById(id);
        return new MessageResponse("Deleted " + id);
    }

    @GetMapping("news")
    public Optional<PropertyEntry> getNews() {
        return propertyEntryRepository.findByPropertyKey("diva_news");
    }

    @PutMapping("news")
    public PropertyEntry updateNews(@RequestBody PropertyEntry property) {
        PropertyEntry entry = propertyEntryRepository.findByPropertyKey("diva_news")
                .orElseGet(() -> new PropertyEntry("diva_news"));
        entry.setPropertyValue(property.getPropertyValue());
        return propertyEntryRepository.save(entry);
    }

    @GetMapping("warning")
    public Optional<PropertyEntry> getWarning() {
        return propertyEntryRepository.findByPropertyKey("diva_warning");
    }

    @PutMapping("warning")
    public PropertyEntry updateWarning(@RequestBody PropertyEntry property) {
        PropertyEntry entry = propertyEntryRepository.findByPropertyKey("diva_warning")
                .orElseGet(() -> new PropertyEntry("diva_warning"));
        entry.setPropertyValue(property.getPropertyValue());
        return propertyEntryRepository.save(entry);
    }

    @GetMapping("module")
    public List<DivaModule> getModule() {
        return moduleRepository.findAll();
    }

    @GetMapping("customize")
    public List<DivaCustomize> getCustomize() {
        return customizeRepository.findAll();
    }
}
