package uk.gov.digital.ho.hocs.entrypoint

import org.springframework.web.bind.annotation.*
import uk.gov.digital.ho.hocs.entrypoint.dto.GroupDto
import uk.gov.digital.ho.hocs.services.group.Team
import uk.gov.digital.ho.hocs.services.group.TeamService
import javax.validation.Valid

@RestController
class TeamResource(
    val teamService: TeamService
) {

    @PostMapping("/teams")
    fun create(@Valid @RequestBody groupRequest: GroupDto): Team =
        teamService.create(Team(name = groupRequest.id))

    @GetMapping("/teams/{teamName}")
    fun get(@PathVariable teamName: String) : Team = teamService.get(teamName)

}
