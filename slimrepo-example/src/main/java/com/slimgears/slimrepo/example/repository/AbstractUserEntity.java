// Copyright 2015 Denis Itskovich
// Refer to LICENSE.txt for license details
package com.slimgears.slimrepo.example.repository;

import android.os.Bundle;
import com.slimgears.slimrepo.core.annotations.GenerateEntity;

/**
 * Created by Denis on 22-Apr-15
 *
 */
@GenerateEntity
public class AbstractUserEntity {
    int id;
    String firstName;
    String lastName;
    int age;
    AbstractCountryEntity country;
    Bundle status;

    public String getFullName() {
        return lastName + ", " + firstName;
    }
}
