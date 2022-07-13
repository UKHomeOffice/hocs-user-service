package uk.gov.digital.ho.hocs.repository

import org.keycloak.admin.client.Keycloak
import org.keycloak.admin.client.resource.GroupsResource
import org.keycloak.admin.client.resource.RealmResource
import org.keycloak.representations.idm.GroupRepresentation
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Repository
import uk.gov.digital.ho.hocs.core.exceptions.TeamNotFoundException
import uk.gov.digital.ho.hocs.services.group.Team
import uk.gov.digital.ho.hocs.services.user.User

@Repository
class TeamRepository(
    keycloak: Keycloak,
    @Value("\${keycloak.realm}") private val realm: String)
    : KeycloakRepository() {

    private final val keycloakRealm: RealmResource = keycloak.realm(realm)
    val groupsResource: GroupsResource = keycloakRealm.groups()

    fun createGroup(group: GroupRepresentation): String {
        return handleKeycloakRequest { groupsResource.add(group) } as String
    }

    fun getGroup(groupName: String): Team {
        val groupByPath = keycloakRealm.getGroupByPath(groupName) ?: throw TeamNotFoundException()

        val groupResource = groupsResource.group(groupByPath.id)

        val groupUsers: List<User> =
            groupResource.members(0, -1)
                .map { User(it) }

        return Team(groupResource.toRepresentation(), groupUsers)
    }

}
