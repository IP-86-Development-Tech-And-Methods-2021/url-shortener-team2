package edu.kpi.developmentmethods.views;


import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.rules.SecurityRule;

import java.security.Principal;

/**
 * Пример контроллера, который доступен только для аутентифицированых пользователей
 */
@Controller
@Secured(SecurityRule.IS_AUTHENTICATED)
public class ProtectedController {
    /**
     * @param principal — пользователь, который аутентифицирован в системе
     * @return email аутентифицированого пользователя
     */
    @Get(
            value = "/me",
            produces = MediaType.TEXT_PLAIN
    )
    public String me(Principal principal) {
        return principal.getName();
    }
}
