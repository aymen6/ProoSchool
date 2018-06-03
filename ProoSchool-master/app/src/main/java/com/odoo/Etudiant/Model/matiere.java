package com.odoo.Etudiant.Model;

import android.content.Context;
import android.net.Uri;

import com.odoo.core.orm.OModel;
import com.odoo.core.orm.fields.OColumn;
import com.odoo.core.orm.fields.types.OVarchar;
import com.odoo.core.support.OUser;


public class matiere extends OModel {

    public static final String TAG = matiere.class.getSimpleName();
    public static final String AUTHORITY = "com.odoo.Etudiant.Model.proschool_subject";
    OColumn name = new OColumn("name", OVarchar.class).setRequired();


    public matiere(Context context, OUser user) {
        super(context, "proschool.subject", user);
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
