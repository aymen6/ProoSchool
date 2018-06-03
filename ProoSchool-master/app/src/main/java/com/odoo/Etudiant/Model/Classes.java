package com.odoo.Etudiant.Model;

import android.content.Context;
import android.net.Uri;

import com.odoo.core.orm.OModel;
import com.odoo.core.orm.fields.OColumn;
import com.odoo.core.orm.fields.types.OText;
import com.odoo.core.orm.fields.types.OVarchar;
import com.odoo.core.support.OUser;

/**
 * Created by Aymen on 13/03/2018.
 */

public class Classes extends OModel {
    public static final String AUTHORITY = "com.odoo.Etudiant.Model.proschool_classes";

    public static final String TAG = Classes.class.getSimpleName();
    OColumn code = new OColumn("Code", OVarchar.class).setRequired();


    public Classes(Context context, OUser user) {
        super(context, "proschool.classes", user);
    }

    @Override
    public Uri uri() {
        return buildURI(AUTHORITY);
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
}
