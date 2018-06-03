package com.odoo.Calendrier.model;

import android.content.Context;
import android.net.Uri;

import com.odoo.Etudiant.Model.Classes;
import com.odoo.Etudiant.Model.Classroom;
import com.odoo.Etudiant.Model.matiere;
 import com.odoo.base.addons.res.ResUsers;
import com.odoo.core.orm.OModel;
import com.odoo.core.orm.fields.OColumn;
import com.odoo.core.orm.fields.types.ODateTime;
 import com.odoo.core.support.OUser;

/**
 * Created by Aymen on 30/05/2018.
 */

public class Calend extends OModel {
    public static final String TAG = com.odoo.Calendrier.model.Calend.class.getSimpleName();

    public static final String AUTHORITY = "com.odoo.Calendrier.model.proschool_timetable_session";


    OColumn start_time = new OColumn("start_time", ODateTime.class);
    OColumn end_time = new OColumn("end_time", ODateTime.class);
    OColumn teacher_id = new OColumn("teacher_id", ResUsers.class, OColumn.RelationType.ManyToOne);
    OColumn class_id = new OColumn("class_id", Classes.class, OColumn.RelationType.ManyToOne);
    OColumn classroom_id = new OColumn("classroom_id",Classroom.class, OColumn.RelationType.ManyToOne).setRequired();
    OColumn subject_id = new OColumn("name", matiere.class, OColumn.RelationType.ManyToOne).setRequired();


    public Calend(Context context, OUser user) {
        super(context,"proschool.timetable.session", user);
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