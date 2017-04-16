package wojtach.ewa.moviedb.user.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by ewojtach on 07/04/2017.
 */

@Builder
public class UserAccountDto {

    @Getter private long id;

    @Getter private String name;

    @Getter @Setter private String password;

}
