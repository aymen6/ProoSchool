package com.odoo.Etudiant.providers;

 import com.odoo.Etudiant.Model.matiere;
import com.odoo.core.orm.provider.BaseModelProvider;

/**
 * Created by Aymen on 01/06/2018.
 */

public class MatiereProvider extends BaseModelProvider {
    public static final String TAG = MatiereProvider.class.getSimpleName();

    @Override
    public String authority() {
        return matiere.AUTHORITY;
    }
}