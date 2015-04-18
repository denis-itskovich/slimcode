// Copyright 2015 Denis Itskovich
// Refer to LICENSE.txt for license details
package com.slimgears.slimorm.core.interfaces.conditions;

/**
 * Created by Denis on 02-Apr-15
 * <File Description>
 */
public interface Condition<TEntity> {
    PredicateType getType();
    Condition<TEntity> and(Condition<TEntity> other);
    Condition<TEntity> or(Condition<TEntity> other);
}
