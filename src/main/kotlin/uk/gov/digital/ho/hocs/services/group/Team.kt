package uk.gov.digital.ho.hocs.services.group

import com.fasterxml.jackson.annotation.JsonCreator
import org.keycloak.representations.idm.GroupRepresentation
import uk.gov.digital.ho.hocs.services.user.User

data class Team(
    var id: String?,
    val name: String?,
    var users: List<User>
) {
    constructor(
        name: String,
    ) :
            this(id = null, name = name, users = listOf())
    constructor(
        groupRepresentation: GroupRepresentation,
        users: List<User>
    ) :
            this(id = groupRepresentation.id, name = groupRepresentation.name, users = users)

    @JsonCreator
    private constructor() : this(id = null, name = null, users = listOf())

    fun toGroupRepresentation(): GroupRepresentation {
        val newGroup = GroupRepresentation()

        newGroup.name = name

        return newGroup
    }
}
