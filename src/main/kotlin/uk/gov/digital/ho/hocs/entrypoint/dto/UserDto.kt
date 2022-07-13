package uk.gov.digital.ho.hocs.entrypoint.dto

import uk.gov.digital.ho.hocs.services.user.User

data class UserDto(val username: String?,
                   val email: String?,
                   val firstName: String?,
                   val lastName: String?,
                   val enabled: Boolean?) {
    constructor(user: User) :
            this(username = user.username, email = user.email,
                firstName = user.firstName, lastName = user.lastName,
                enabled = user.enabled)
}
