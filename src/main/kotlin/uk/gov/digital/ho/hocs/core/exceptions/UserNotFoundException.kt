package uk.gov.digital.ho.hocs.core.exceptions

class UserNotFoundException : RuntimeException(MESSAGE)

private const val MESSAGE = "User not found."
