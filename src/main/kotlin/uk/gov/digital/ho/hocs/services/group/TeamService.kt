package uk.gov.digital.ho.hocs.services.group

import org.springframework.stereotype.Service
import uk.gov.digital.ho.hocs.repository.TeamRepository

@Service
class TeamService(
    private val teamRepository: TeamRepository) {

    fun create(group: Team) : Team = teamRepository.createGroup(group.toGroupRepresentation())
        .let { group.id = it; return group }

    fun get(groupName: String) : Team = teamRepository.getGroup(groupName)

}
