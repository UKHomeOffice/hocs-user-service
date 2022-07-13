package uk.gov.digital.ho.hocs.repository

import org.keycloak.admin.client.CreatedResponseUtil
import javax.ws.rs.core.Response

open class KeycloakRepository {

    protected fun handleKeycloakRequest(keycloakFunction: () -> Response): Any? =
        keycloakFunction().let {
            return when (it.statusInfo) {
                Response.Status.CREATED -> {
                    CreatedResponseUtil.getCreatedId(it)
                }
                else -> {
                    null
                }
            }
        }
}
