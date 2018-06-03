package com.odoo.Etudiant.Model;

import android.content.Context;
import android.net.Uri;

import com.odoo.core.orm.OModel;
import com.odoo.core.orm.fields.OColumn;
import com.odoo.core.orm.fields.types.OVarchar;
import com.odoo.core.support.OUser;

/**
 * Created by Aymen on 14/03/2018.
 */

public class typee extends OModel {
    public static final String TAG = typee.class.getSimpleName();
    public static final String AUTHORITY = "com.odoo.Etudiant.Model.proschool_sanction_type";

    OColumn name = new OColumn("name", OVarchar.class);

    public typee(Context context, OUser user) {
        super(context, "proschool.sanction.type", user);
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
