package wojtach.ewa.moviedb.user.domain;

import lombok.Builder;
import lombok.Getter;

/**
 * Created by ewojtach on 07/04/2017.
 */

@Builder
public class UserAccountDto {

    @Getter private String name;

    @Getter private String password;


}
