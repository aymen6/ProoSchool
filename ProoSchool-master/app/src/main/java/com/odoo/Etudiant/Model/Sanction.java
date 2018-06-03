package com.odoo.Etudiant.Model;

import android.content.Context;
import android.net.Uri;
import android.util.Log;

import com.odoo.base.addons.res.ResPartner;
import com.odoo.core.orm.ODataRow;
import com.odoo.core.orm.OModel;
import com.odoo.core.orm.OValues;
import com.odoo.core.orm.fields.OColumn;
import com.odoo.core.orm.fields.types.ODateTime;
import com.odoo.core.orm.fields.types.OSelection;
import com.odoo.core.orm.fields.types.OText;
import com.odoo.core.orm.fields.types.OVarchar;
import com.odoo.core.support.OUser;
import com.odoo.core.utils.ODateUtils;
import com.odoo.core.utils.OdooRecordUtils;

import java.util.List;


/**
 * Created by Aymen on 15/03/2018.
 */

public class Sanction extends OModel {

    public static final String TAG = Sanction.class.getSimpleName();
    public static final String AUTHORITY = "com.odoo.Etudiant.Model.proschool_sanction";
    OColumn message = new OColumn("message", OText.class).setRequired();
    OColumn date = new OColumn("date", ODateTime.class).setRequired();
    OColumn type_id = new OColumn("type_id", typee.class, OColumn.RelationType.ManyToOne);
    OColumn name = new OColumn("name", OVarchar.class).setSize(100);
    OColumn student_id = new OColumn("student_id", ResPartner.class, OColumn.RelationType.ManyToOne);
    OColumn class_id = new OColumn("class_id", Classes.class, OColumn.RelationType.ManyToOne);
    OColumn company_name = new OColumn("Company Name", OVarchar.class).setSize(100);



    OColumn state = new OColumn("State", OSelection.class)
            .addSelection("draft", "Draft")
            .addSelection("confirm", "Confirmed")
            .addSelection("close", "Canceled")
            .addSelection("done", "Done");

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

    public Sanction(Context context, OUser user) {
        super(context, "proschool.sanction", user);
        //    setHasMailChatter(true);

    }


    @Override
    public Uri uri() {
        return buildURI(AUTHORITY);
    }

}
