package com.odoo.Etudiant.providers;

import com.odoo.Etudiant.Model.typee;
import com.odoo.core.orm.provider.BaseModelProvider;

/**
 * Created by Aymen on 08/03/2018.
 */

public class TypeeProvider extends BaseModelProvider {
    public static final String TAG = TypeeProvider.class.getSimpleName();

    @Override
    public String authority() {
        return typee.AUTHORITY;
    }
}