// Copyright 2015 Denis Itskovich
// Refer to LICENSE.txt for license details
package com.slimgears.slimorm.android.core;

import android.database.sqlite.SQLiteDatabase;

import com.slimgears.slimorm.core.interfaces.RepositorySession;
import com.slimgears.slimorm.core.interfaces.entities.Entity;
import com.slimgears.slimorm.core.interfaces.entities.EntityType;
import com.slimgears.slimorm.core.internal.interfaces.SessionEntityServiceProvider;
import com.slimgears.slimorm.core.internal.interfaces.TransactionProvider;
import com.slimgears.slimorm.core.internal.sql.AbstractSqlSessionServiceProvider;
import com.slimgears.slimorm.core.internal.sql.SqlCommandExecutor;
import com.slimgears.slimorm.core.internal.sql.SqlOrmServiceProvider;
import com.slimgears.slimorm.core.internal.sql.SqlSessionEntityServiceProvider;

import java.io.Closeable;
import java.io.IOException;

/**
 * Created by Denis on 15-Apr-15
 * <File Description>
 */
public class SqliteSessionServiceProvider extends AbstractSqlSessionServiceProvider {
    private final SQLiteDatabase database;
    private final Closeable closer;

    public SqliteSessionServiceProvider(SqlOrmServiceProvider serviceProvider, SQLiteDatabase db, Closeable closer) {
        super(serviceProvider);
        this.database = db;
        this.closer = closer;
    }

    @Override
    protected SqlCommandExecutor createCommandExecutor() {
        return new SqliteCommandExecutor(database, this);
    }

    @Override
    protected TransactionProvider createTransactionProvider() {
        return new SqliteTransactionProvider(database);
    }

    @Override
    protected <TKey, TEntity extends Entity<TKey>> SessionEntityServiceProvider<TKey, TEntity> createEntityServiceProvider(EntityType<TKey, TEntity> entityType) {
        return new SqliteSessionEntityServiceProvider<>(database, this, entityType);
    }

    @Override
    public void close() throws IOException {
        if (closer != null) closer.close();
    }
}
