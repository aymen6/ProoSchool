package com.odoo.Avis.model;

import android.content.Context;
import android.net.Uri;

import com.odoo.Etudiant.Model.Classes;
import com.odoo.base.addons.res.ResPartner;
import com.odoo.core.orm.OModel;
import com.odoo.core.orm.fields.OColumn;
import com.odoo.core.orm.fields.types.ODate;
import com.odoo.core.orm.fields.types.ODateTime;
import com.odoo.core.orm.fields.types.OText;
import com.odoo.core.orm.fields.types.OVarchar;
import com.odoo.core.support.OUser;

/**
 * Created by Aymen on 10/04/2018.
 */

public class ParentAvis extends OModel {
    public static final String TAG = ParentAvis.class.getSimpleName();

    public static final String AUTHORITY = "com.odoo.Avis.model.proschool_parent_message";

    OColumn parent_id = new OColumn("student", ResPartner.class, OColumn.RelationType.ManyToOne);
    OColumn message = new OColumn("message", OText.class).setRequired();
    OColumn name = new OColumn("name", OVarchar.class);
    OColumn date = new OColumn("date", ODateTime.class).setRequired();
    OColumn student_id = new OColumn("student", ResPartner.class, OColumn.RelationType.ManyToOne);
    OColumn class_id = new OColumn("class_id", Classes.class, OColumn.RelationType.ManyToOne);


    public ParentAvis(Context context, OUser user) {
        super(context, "proschool.parent.message", user);
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