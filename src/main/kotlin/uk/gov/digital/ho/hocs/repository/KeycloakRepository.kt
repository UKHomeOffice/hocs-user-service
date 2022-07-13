package uk.gov.digital.ho.hocs.repository

import org.keycloak.admin.client.CreatedResponseUtil
import javax.ws.rs.core.Response

open class KeycloakRepository {

    protected val BATCH_SIZE = 100

    protected fun handleKeycloakRequest(keycloakFunction: () -> Response): Any {
        val response = keycloakFunction()

        when (response.statusInfo) {
            Response.Status.CREATED -> {
                return CreatedResponseUtil.getCreatedId(response)
            }
        }

        return "test"
    }

}
