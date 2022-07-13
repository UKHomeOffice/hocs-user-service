package uk.gov.uk.digital.ho.hocs.services.group

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.mockito.kotlin.any
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever
import uk.gov.digital.ho.hocs.repository.TeamRepository
import uk.gov.digital.ho.hocs.services.group.Team
import uk.gov.digital.ho.hocs.services.group.TeamService
import java.util.UUID

private const val uuidconstant = "uuidhere"

class TeamServiceTest {
    private val teamRepository: TeamRepository = mock()
    private val teamService: TeamService = TeamService(teamRepository)

    @Nested
    inner class CreateTeamsTests {

        @Test
        fun `It should create a valid team`() {
            whenever(teamRepository.createGroup(any())).thenReturn(uuidconstant)

            val team = Team(name = UUID.randomUUID())

            val actual = teamService.create(team)
            assertEquals(uuidconstant, actual.id)
        }
    }
}
