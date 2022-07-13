package uk.gov.digital.ho.hocs.services.user

import org.springframework.stereotype.Service
import uk.gov.digital.ho.hocs.repository.UserRepository

@Service
class UserService(
    private val userRepository: UserRepository
) {

    fun getAllUsers(): List<User> = userRepository.getAllUsers().map { User(it) }

    fun create(user: User): User = userRepository.createUser(user.toUserRepresentation(true))
        .let { user.id = it; return user }

    fun delete(userId: String) = userRepository.deleteUser(userId)

    fun get(userId: String): User = getAllUsers().first { it.id == userId }
}
