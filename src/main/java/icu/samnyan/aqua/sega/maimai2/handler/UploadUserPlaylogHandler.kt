package icu.samnyan.aqua.sega.maimai2.handler

import ext.millis
import icu.samnyan.aqua.sega.maimai2.model.Mai2UserDataRepo
import icu.samnyan.aqua.sega.maimai2.model.Mai2UserPlaylogRepo
import icu.samnyan.aqua.sega.general.BaseHandler
import icu.samnyan.aqua.sega.maimai2.model.request.UploadUserPlaylog
import icu.samnyan.aqua.sega.maimai2.model.userdata.UserPlaylog
import icu.samnyan.aqua.sega.util.jackson.BasicMapper
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component
import kotlin.jvm.optionals.getOrNull

/**
 * @author samnyan (privateamusement@protonmail.com)
 */
@Component("Maimai2UploadUserPlaylogHandler")
class UploadUserPlaylogHandler(
    private val userDataRepository: Mai2UserDataRepo,
    private val playlogRepo: Mai2UserPlaylogRepo,
    private val mapper: BasicMapper
) : BaseHandler {
    data class BacklogEntry(val time: Long, val playlog: UserPlaylog)
    companion object {
        @JvmStatic
        val playBacklog = mutableMapOf<Long, MutableList<BacklogEntry>>()
    }

    override fun handle(request: Map<String, Any>): String {
        val req = mapper.convert(request, UploadUserPlaylog::class.java)

        // Save if the user is registered
        val u = userDataRepository.findByCardExtId(req.userId).getOrNull()
        if (u != null) playlogRepo.save(req.userPlaylog.apply { user = u })

        // If the user hasn't registered (first play), save the playlog to a backlog
        else {
            playBacklog.putIfAbsent(req.userId, mutableListOf())
            playBacklog[req.userId]?.apply {
                add(BacklogEntry(millis(), req.userPlaylog))
                if (size > 6) clear()  // Prevent abuse
            }
        }

        return """{"returnCode":1,"apiName":"com.sega.maimai2servlet.api.UploadUserPlaylogApi"}"""
    }

    @Scheduled(fixedDelay = 60_000)
    fun cleanBacklog() {
        // Clean all backlog entries that are older than 5 minutes
        val now = millis()
        playBacklog.filter { (_, v) -> v.isEmpty() || v[0].time - now > 300_000 }.toList()
            .forEach { (k, _) -> playBacklog.remove(k) }
    }
}
