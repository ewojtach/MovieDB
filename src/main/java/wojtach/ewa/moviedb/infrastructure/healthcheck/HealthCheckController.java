package wojtach.ewa.moviedb.infrastructure.healthcheck;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import wojtach.ewa.moviedb.user.domain.UserAccountDto;
import wojtach.ewa.moviedb.user.domain.UserAccountFacade;

import java.util.List;

/**
 * Created by ewa on 15.04.2017.
 */

@RestController
@AllArgsConstructor
class HealthCheckController {

    @GetMapping("api/healthcheck")
    String getUserAccounts(){
        return "OK";
    }
}
