package ru.kpfu.itis.kashapova.converter;

import lombok.extern.java.Log;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.core.convert.converter.GenericConverter;
import org.springframework.stereotype.Component;
import ru.kpfu.itis.kashapova.entity.Ad;
import ru.kpfu.itis.kashapova.entity.Resume;
import ru.kpfu.itis.kashapova.entity.Team;
import ru.kpfu.itis.kashapova.entity.User;
import ru.kpfu.itis.kashapova.form.AdForm;
import ru.kpfu.itis.kashapova.form.ResumeForm;
import ru.kpfu.itis.kashapova.form.SignUpUserForm;
import ru.kpfu.itis.kashapova.form.TeamForm;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;
import java.util.logging.Level;

@Component
@Log
public class FormToEntityConverter implements GenericConverter {

    @Override
    public Set<ConvertiblePair> getConvertibleTypes() {
        Set<ConvertiblePair> set = new HashSet<>();
        set.add(new ConvertiblePair(AdForm.class, Ad.class));
        set.add(new ConvertiblePair(ResumeForm.class, Resume.class));
        set.add(new ConvertiblePair(SignUpUserForm.class, User.class));
        set.add(new ConvertiblePair(TeamForm.class, Team.class));
        return set;
    }

    @Override
    public Object convert(Object source, TypeDescriptor sourceType, TypeDescriptor targetType) {
        Field[] fields = sourceType.getType().getDeclaredFields();
        Object to;
        try {
            to = targetType.getType().getConstructor().newInstance();
        } catch (InstantiationException | IllegalAccessException
                | InvocationTargetException | NoSuchMethodException e) {
            throw new IllegalStateException(e);
        }for (Field field: fields) {
            String name = String.valueOf(field.getName().charAt(0)).toUpperCase(Locale.ROOT) + field.getName().substring(1);
            Method getter;
            try {
                getter = sourceType.getType().getMethod("get" + name);
            } catch (NoSuchMethodException e) {
                log.log(Level.WARNING, "can't find getter for " + field.getName()
                        + " for type " + sourceType.getType().getName());
                continue;
            }
            try {
                targetType.getType().getMethod("set" + name, field.getType())
                        .invoke(to, getter.invoke(source));
            } catch (InvocationTargetException | IllegalAccessException e) {
                throw new IllegalStateException(e);
            } catch (NoSuchMethodException e) {
                log.log(Level.WARNING, "can't find setter for " + field.getName()
                        + " for type " + targetType.getType().getName());
            }
        }
        return to;
    }
}
