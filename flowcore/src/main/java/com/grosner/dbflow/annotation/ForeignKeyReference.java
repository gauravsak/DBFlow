package com.grosner.dbflow.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Author: andrewgrosner
 * Contributors: { }
 * Description:
 */
@Retention(RetentionPolicy.SOURCE)
public @interface ForeignKeyReference {

    String columnName();

    /**
     * Needs to match both tables!
     *
     * @return
     */
    Class<?> columnType();

    String foreignColumnName();
}
