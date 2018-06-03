package com.odoo.Etudiant.providers;

import com.odoo.Etudiant.Model.Classes;
import com.odoo.core.orm.provider.BaseModelProvider;

/**
 * Created by Aymen on 08/03/2018.
 */

public class ClassesProvider extends BaseModelProvider {
    public static final String TAG = ClassesProvider.class.getSimpleName();

    @Override
    public String authority() {
        return Classes.AUTHORITY;
    }
}