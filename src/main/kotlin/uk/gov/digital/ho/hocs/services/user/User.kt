package uk.gov.digital.ho.hocs.services.user

import com.fasterxml.jackson.annotation.JsonCreator
import org.keycloak.representations.idm.UserRepresentation
import uk.gov.digital.ho.hocs.entrypoint.dto.UserDto

data class User(
    var id: String? = null,
    val username: String? = null,
    val email: String? = null,
    val firstName: String? = null,
    val lastName: String? = null,
    val enabled: Boolean? = null
) {

    companion object {
        fun from(userDto: UserDto) = User(
            username = userDto.username, email = userDto.email,
            firstName = userDto.firstName, lastName = userDto.lastName, enabled = userDto.enabled
        )
    }

    constructor(
        userDto: UserDto
    ) :
        this(
            id = null, username = userDto.username,
            email = userDto.email, firstName = userDto.firstName,
            lastName = userDto.lastName, enabled = userDto.enabled
        )
    constructor(userRepresentation: UserRepresentation) :
        this(
            id = userRepresentation.id,
            username = userRepresentation.username,
            email = userRepresentation.email,
            firstName = userRepresentation.firstName,
            lastName = userRepresentation.lastName,
            enabled = userRepresentation.isEnabled
        )
    @JsonCreator private constructor() :
        this(null, null, null, null, null, null)

    fun toUserRepresentation(enabled: Boolean): UserRepresentation {
        val newUser = UserRepresentation()

        newUser.username = username
        newUser.email = email
        newUser.firstName = firstName
        newUser.lastName = lastName
        newUser.isEnabled = enabled

        return newUser
    }
}
