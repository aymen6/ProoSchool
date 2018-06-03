package com.odoo.Etudiant.Model;

import android.content.Context;
import android.net.Uri;

import com.odoo.core.orm.OModel;
import com.odoo.core.orm.fields.OColumn;
import com.odoo.core.orm.fields.types.OVarchar;
import com.odoo.core.support.OUser;

/**
 * Created by Aymen on 31/05/2018.
 */

public class Classroom extends OModel {
    public static final String AUTHORITY = "com.odoo.Etudiant.Model.proschool_classroom";
    public static final String TAG = Classroom.class.getSimpleName();

    OColumn name = new OColumn("name", OVarchar.class).setRequired();

    public Classroom(Context context, OUser user) {
        super(context, "proschool.classroom", user);
    }


    @Override
    public boolean allowCreateRecordOnServer() {
        return true;
    }

    @Override
    public boolean allowUpdateRecordOnServer() {
        return true;
    }

    @Override
    public boolean checkForWriteDate() {
        return true;
    }

    @Override
    public Uri uri() {
        return buildURI(AUTHORITY);
    }

}