package ua.com.alevel.network.persistence.listener;

import org.joda.time.LocalDate;
import org.joda.time.Years;
import ua.com.alevel.network.persistence.entity.user.Personal;

import javax.persistence.PostLoad;
import javax.persistence.PostPersist;
import javax.persistence.PostUpdate;

/**
 * @author Iehor Funtusov, created 24/12/2020 - 9:40 AM
 */

public class AgeByBirthDayGenerationListener {

    @PostLoad
    @PostPersist
    @PostUpdate
    public void generateAgeByBirthDay(Personal user) {
        if (user.getBirthDay() == null) {
            user.setAge(null);
            return;
        }
        Years years = Years.yearsBetween(new LocalDate(user.getBirthDay()), new LocalDate());
        user.setAge(years.getYears());
    }
}
