package uk.gov.digital.ho.hocs.services.group

import org.apache.commons.codec.binary.Base64
import org.keycloak.representations.idm.GroupRepresentation
import uk.gov.digital.ho.hocs.services.user.User
import java.nio.ByteBuffer
import java.util.UUID

data class Team(
    var id: String? = null,
    val name: UUID,
    var users: List<User> = emptyList()
) {

    companion object {
        fun uuidToBase64String(uuid: UUID): String {
            val uuidBytes = ByteBuffer.wrap(ByteArray(16))
            uuidBytes.putLong(uuid.mostSignificantBits)
            uuidBytes.putLong(uuid.leastSignificantBits)
            return Base64.encodeBase64URLSafeString(uuidBytes.array())
        }

        private fun base64StringToUUID(base64UUIDString: String): UUID =
            Base64.decodeBase64(base64UUIDString)
                .let { ByteBuffer.wrap(it) }
                .let { UUID(it.long, it.long) }

        fun from(groupRepresentation: GroupRepresentation, users: List<User>) =
            Team(id = groupRepresentation.id, name = base64StringToUUID(groupRepresentation.name), users = users)
    }

    fun toGroupRepresentation() =
        GroupRepresentation().also { it.name = uuidToBase64String(name) }
}
