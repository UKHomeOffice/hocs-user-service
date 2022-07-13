package uk.gov.digital.ho.hocs.core.exceptions

class TeamNotFoundException : RuntimeException(MESSAGE)

private const val MESSAGE = "Group not found."
