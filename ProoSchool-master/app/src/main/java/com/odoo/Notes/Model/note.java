package com.odoo.Notes.Model;

import android.content.Context;
import android.net.Uri;

import com.odoo.Etudiant.Model.Classes;
import com.odoo.base.addons.res.ResPartner;
import com.odoo.core.orm.OModel;
import com.odoo.core.orm.fields.OColumn;
import com.odoo.core.orm.fields.types.OFloat;
import com.odoo.core.orm.fields.types.OVarchar;
import com.odoo.core.support.OUser;

/**
 * Created by Aymen on 31/05/2018.
 */

public class note  extends OModel {

    public static final String TAG = note.class.getSimpleName();
    public static final String AUTHORITY = "com.odoo.Notes.Model.proschool_student_gradebook";
    OColumn average = new OColumn("average", OFloat.class);
    OColumn student_id = new OColumn("student_id", ResPartner.class,
            OColumn.RelationType.ManyToOne);



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

    public note(Context context, OUser user) {
        super(context, "proschool.student.gradebook", user);
        //    setHasMailChatter(true);

    }


    @Override
    public Uri uri() {
        return buildURI(AUTHORITY);
    }

}
