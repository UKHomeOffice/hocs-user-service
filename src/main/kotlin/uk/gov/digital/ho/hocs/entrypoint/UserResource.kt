package uk.gov.digital.ho.hocs.entrypoint

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import uk.gov.digital.ho.hocs.entrypoint.dto.UserDto
import uk.gov.digital.ho.hocs.services.group.TeamService
import uk.gov.digital.ho.hocs.services.user.User
import uk.gov.digital.ho.hocs.services.user.UserService

@RestController
class UserResource(
    val userService: UserService,
    val teamService: TeamService
) {

    @PostMapping("/users")
    fun create(@RequestBody userRequest: UserDto) =
        UserDto(userService.create(User.from(userRequest)))

    @DeleteMapping("/users/{userId}")
    fun delete(@PathVariable("userId") user: String): ResponseEntity<Void> =
        userService.delete(user)
            .run { return ResponseEntity<Void>(HttpStatus.NO_CONTENT) }

    @GetMapping("/users/{userId}")
    fun get(@PathVariable("userId") user: String) =
        UserDto(userService.get(user))

    @GetMapping("/users")
    fun get(): List<UserDto> =
        userService.getAllUsers()
            .map { UserDto(it) }
}
