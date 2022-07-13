package uk.gov.digital.ho.hocs.entrypoint

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import uk.gov.digital.ho.hocs.entrypoint.dto.UserDto
import uk.gov.digital.ho.hocs.services.group.TeamService
import uk.gov.digital.ho.hocs.services.user.UserService
import uk.gov.digital.ho.hocs.services.user.User

@RestController
class UserResource(
    val userService: UserService,
    val teamService: TeamService
) {

    @PostMapping("/users")
    fun create(@RequestBody userRequest: UserDto): UserDto =
        User(userRequest)
            .let {
                val user =  userService.create(it)
                return UserDto(user)
            }

    @DeleteMapping("/users/{userId}")
    fun delete(@PathVariable("userId") user: String): ResponseEntity<Void> =
        userService.delete(user)
            .let { return ResponseEntity<Void>(HttpStatus.NO_CONTENT) }

    @GetMapping("/users/{userId}")
    fun get(@PathVariable("userId") user: String): UserDto =
        userService.get(user)
            .let { return UserDto(it) }


    @GetMapping("/users")
    fun get(): List<UserDto> =
        userService.getAllUsers()
            .map { UserDto(it) }



}
