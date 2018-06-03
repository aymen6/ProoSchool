package com.odoo.Etudiant.providers;

import com.odoo.Etudiant.Model.Sanction;
import com.odoo.core.orm.provider.BaseModelProvider;

/**
 * Created by Aymen on 08/03/2018.
 */

public class SanctionProvider extends BaseModelProvider {
    public static final String TAG = SanctionProvider.class.getSimpleName();

    @Override
    public String authority() {
        return Sanction.AUTHORITY;
    }
}