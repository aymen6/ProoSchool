package com.odoo.Etudiant.providers;

import com.odoo.Etudiant.Model.Classroom;
import com.odoo.core.orm.provider.BaseModelProvider;

/**
 * Created by Aymen on 01/06/2018.
 */



  public class ClassroomProvider extends BaseModelProvider {
    public static final String TAG = ClassroomProvider.class.getSimpleName();

    @Override
    public String authority() {
        return Classroom.AUTHORITY;
    }
}
