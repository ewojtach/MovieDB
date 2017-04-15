package wojtach.ewa.moviedb.user.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Created by ewojtach on 07/04/2017.
 */
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
class UserAccount {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Getter private long id;

    @Getter private String name;
    @Getter private String password;

}
