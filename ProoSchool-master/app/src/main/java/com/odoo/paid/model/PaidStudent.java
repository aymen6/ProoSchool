package com.odoo.paid.model;

import android.content.Context;
import android.net.Uri;

import com.odoo.Etudiant.Model.Classes;
import com.odoo.Etudiant.Model.typee;
import com.odoo.base.addons.res.ResPartner;
import com.odoo.core.orm.OModel;
import com.odoo.core.orm.fields.OColumn;
import com.odoo.core.orm.fields.types.ODateTime;
import com.odoo.core.orm.fields.types.OText;
import com.odoo.core.orm.fields.types.OVarchar;
import com.odoo.core.support.OUser;

/**
 * Created by Aymen on 13/04/2018.
 */

public class PaidStudent extends OModel {

    public static final String TAG = PaidStudent.class.getSimpleName();
    public static final String AUTHORITY = "com.odoo.paid.model.pos_order.line";

    OColumn message = new OColumn("message", OText.class).setRequired();
    OColumn date = new OColumn("date", ODateTime.class).setRequired();
    OColumn type_id = new OColumn("type_id", typee.class, OColumn.RelationType.ManyToOne);
    OColumn name = new OColumn("name", OVarchar.class).setSize(100);
    OColumn student_id = new OColumn("name", ResPartner.class, OColumn.RelationType.ManyToOne);
    OColumn class_id = new OColumn("class_id", Classes.class, OColumn.RelationType.ManyToOne);
    OColumn create_date = new OColumn("create_date", ODateTime.class).setRequired();

    public PaidStudent(Context context, OUser user) {
        super(context, "pos.order.line", user);
        //    setHasMailChatter(true);
    }

    @Override
    public Uri uri() {
        return buildURI(AUTHORITY);
    }

}
