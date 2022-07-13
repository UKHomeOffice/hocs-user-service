package uk.gov.digital.ho.hocs.core.exceptions

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

@ControllerAdvice
class ExceptionControllerAdvice {

    @ExceptionHandler
    fun handleUserNotFoundException(ex: UserNotFoundException): ResponseEntity<Void> =
        ResponseEntity.notFound().build()

    @ExceptionHandler
    fun handleTeamNotFoundException(ex: TeamNotFoundException): ResponseEntity<Void> =
        ResponseEntity.notFound().build()
}
