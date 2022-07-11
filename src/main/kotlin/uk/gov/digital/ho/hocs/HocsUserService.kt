package uk.gov.digital.ho.hocs

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class HocsUserService

fun main(args: Array<String>) {
	runApplication<HocsUserService>(*args)
}
