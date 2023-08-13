package io.juancrrn.balancerbankingconnector.restserver

import io.juancrrn.balancerbankingconnector.commands.CreateLinkTokenCommand
import io.juancrrn.balancerbankingconnector.usecases.UseCaseDispatcher
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.*

@RestController
@RequestMapping("link-tokens")
class LinkTokenController(
    private val dispatcher: UseCaseDispatcher,
) {

    // TODO: autogenerate API
    @PostMapping("/create")
    suspend fun abc(@RequestBody body: HashMap<String, String>): String {
        val command = CreateLinkTokenCommand(
            UUID.fromString(body["userId"]), // TODO: actually associate with a user and store in DB
        )

        return dispatcher.dispatch(command).token
    }
}
