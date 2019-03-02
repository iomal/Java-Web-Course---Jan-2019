package chushka.utils;

import org.modelmapper.ModelMapper;

import java.util.*;

public class DtoUtils {
    private static final ModelMapper INSTANCE = new ModelMapper();

    private DtoUtils() {
        throw new InstantiationError("Must not instantiate this class");
    }

    public static <S, T> T map(S source, Class<T> targetClass) {
        return INSTANCE.map(source, targetClass);
    }

    public static <S, T> void mapTo(S source, T dist) {
        INSTANCE.map(source, dist);
    }

    public static <S, T> List<T> mapList(List<S> source, Class<T> targetClass) {
        List<T> list = new ArrayList<T>();
        for (int i = 0; i < source.size(); i++) {
            T target = INSTANCE.map(source.get(i), targetClass);
            list.add(target);
        }

        return list;
    }
}
