package de.mineformers.gui.util;

import com.google.common.collect.Lists;
import com.sun.istack.internal.NotNull;
import com.sun.istack.internal.Nullable;

import java.lang.reflect.Field;
import java.util.Iterator;
import java.util.List;

/**
 * GUISystem
 * </p>
 * ReflectionHelper
 *
 * @author PaleoCrafter
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class ReflectionHelper {

    public static Iterable<Field> getFieldsUpTo(@NotNull Class<?> startClass,
                                                @Nullable Class<?> exclusiveParent) {

        List<Field> currentClassFields = Lists.newArrayList(startClass.getDeclaredFields());
        Class<?> parentClass = startClass.getSuperclass();

        if (parentClass != null &&
                (exclusiveParent == null || !(parentClass.equals(exclusiveParent)))) {
            List<Field> parentClassFields =
                    (List<Field>) getFieldsUpTo(parentClass, exclusiveParent);
            currentClassFields.addAll(parentClassFields);
        }

        return currentClassFields;
    }

    public static Field getFieldFull(String fieldName,
                                     @NotNull Class<?> clazz,
                                     @Nullable Class<?> exclusiveParent) {
        Iterator<Field> fields = getFieldsUpTo(clazz, exclusiveParent).iterator();
        while (fields.hasNext()) {
            Field field = fields.next();
            if (field.getName().equals(fieldName)) {
                return field;
            }
        }

        return null;
    }

}
