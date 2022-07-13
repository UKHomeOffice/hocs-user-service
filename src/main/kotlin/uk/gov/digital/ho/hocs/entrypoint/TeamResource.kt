package uk.gov.digital.ho.hocs.entrypoint

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import uk.gov.digital.ho.hocs.entrypoint.dto.GroupDto
import uk.gov.digital.ho.hocs.services.group.Team
import uk.gov.digital.ho.hocs.services.group.TeamService
import java.util.UUID
import javax.validation.Valid

@RestController
class TeamResource(
    val teamService: TeamService
) {

    @PostMapping("/teams")
    fun create(@Valid @RequestBody groupRequest: GroupDto): Team =
        teamService.create(Team(name = groupRequest.id))

    @GetMapping("/teams/{teamUuid}")
    fun get(@PathVariable teamUuid: UUID): Team = teamService.get(teamUuid)
}
