package wojtach.ewa.moviedb.user.domain;

import lombok.Builder;
import lombok.Getter;

/**
 * Created by ewojtach on 07/04/2017.
 */
@Builder
class UserAccount {

    @Getter private long id;
    @Getter private String name;
    @Getter private String password;

}
