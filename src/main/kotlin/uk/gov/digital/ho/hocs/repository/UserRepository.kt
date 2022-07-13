package uk.gov.digital.ho.hocs.repository

import org.keycloak.admin.client.Keycloak
import org.keycloak.admin.client.resource.UsersResource
import org.keycloak.representations.idm.UserRepresentation
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Repository

@Repository
class UserRepository(
    keycloak: Keycloak,
    @Value("\${keycloak.realm}") private val realm: String)
    : KeycloakRepository() {

        val keycloakUserResource: UsersResource = keycloak.realm(realm).users()

        fun createUser(user: UserRepresentation) : String =
            handleKeycloakRequest { keycloakUserResource.create(user) } as String

        fun deleteUser(userId: String) {
            handleKeycloakRequest { keycloakUserResource.delete(userId) }
        }

        fun getAllUsers() : List<UserRepresentation> =
            keycloakUserResource.list(0, -1)

    fun getAllUsersPaginated() : List<UserRepresentation> {
        val totalUserCount = keycloakUserResource.count()
        val users: ArrayList<UserRepresentation> = arrayListOf()

        var i = 0
        while (i < totalUserCount) {
            users.addAll(
                keycloakUserResource.list(i, BATCH_SIZE)
            )
            i += BATCH_SIZE
        }

        return users
    }

}
