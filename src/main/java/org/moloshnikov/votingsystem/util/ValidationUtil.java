package org.moloshnikov.votingsystem.util;

import org.moloshnikov.votingsystem.HasId;
import org.moloshnikov.votingsystem.model.AbstractBaseEntity;
import org.moloshnikov.votingsystem.model.Menu;
import org.moloshnikov.votingsystem.util.exception.NotFoundException;

import javax.validation.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Set;

public class ValidationUtil {

    public static final LocalTime DEAD_LINE = LocalTime.of(23, 59);

    private static final Validator validator;


    static {
        //  From Javadoc: implementations are thread-safe and instances are typically cached and reused.
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        //  From Javadoc: implementations of this interface must be thread-safe
        validator = factory.getValidator();
    }

    private ValidationUtil() {
    }

    public static <T> void validate(T bean) {
        // https://alexkosarev.name/2018/07/30/bean-validation-api/
        Set<ConstraintViolation<T>> violations = validator.validate(bean);
        if (!violations.isEmpty()) {
            throw new ConstraintViolationException(violations);
        }
    }

    public static <T> T checkNotFoundWithId(T object, int id) {
        checkNotFoundWithId(object != null, id);
        return object;
    }

//    public static<T> T checkNotFoundWithDate(T object, LocalDate date){
//        checkNotFoundWithDate(object != null, date);
//        return object;
//    }

    public static void checkNotFoundWithDate(boolean found, LocalDate date) {
        checkNotFound(found, "date=" + date);
    }


    public static void checkNotFoundWithId(boolean found, int id) {
        checkNotFound(found, "id=" + id);
    }

    public static <T> T checkNotFound(T object, String msg) {
        checkNotFound(object != null, msg);
        return object;
    }

    public static void checkNotFound(boolean found, String msg) {
        if (!found) {
            throw new NotFoundException("Not found entity with " + msg);
        }
    }

    public static void checkNew(AbstractBaseEntity entity) {
        if (!entity.isNew()) {
            throw new IllegalArgumentException(entity + " must be new (id=null)");
        }
    }

    public static void assureIdConsistent(HasId bean, int id) {
//      conservative when you reply, but accept liberally (http://stackoverflow.com/a/32728226/548473)
        if (bean.isNew()) {
            bean.setId(id);
        } else if (bean.id() != id) {
            throw new IllegalArgumentException(bean + " must be with id=" + id);
        }
    }

    public static void checkDate(Menu menu) {
        if (menu.getDate() == null) {
            menu.setDate(LocalDate.now());
        }
    }

    public static void checkDeadLine(LocalTime taken) {
        if (taken.isAfter(DEAD_LINE)) {
            throw new NotFoundException(String.format("Sorry, after %s you cannot vote ", DEAD_LINE));
        }
    }
}
