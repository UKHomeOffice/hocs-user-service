package uk.gov.digital.ho.hocs.services.group

import org.springframework.stereotype.Service
import uk.gov.digital.ho.hocs.repository.TeamRepository
import java.util.UUID

@Service
class TeamService(
    private val teamRepository: TeamRepository
) {

    fun create(group: Team): Team = teamRepository.createGroup(group.toGroupRepresentation())
        .let { group.id = it; return group }

    fun get(groupName: UUID): Team =
        teamRepository.getGroup(Team.uuidToBase64String(groupName))
}
