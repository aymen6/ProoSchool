package com.odoo.HistoryPay.model;

import android.content.Context;
import android.net.Uri;

import com.odoo.Etudiant.Model.Classes;
import com.odoo.Etudiant.Model.Sanction;
import com.odoo.base.addons.res.ResPartner;
import com.odoo.core.orm.OModel;
import com.odoo.core.orm.fields.OColumn;
import com.odoo.core.orm.fields.types.ODateTime;
import com.odoo.core.orm.fields.types.OFloat;
import com.odoo.core.orm.fields.types.OSelection;
import com.odoo.core.orm.fields.types.OText;
import com.odoo.core.orm.fields.types.OVarchar;
import com.odoo.core.support.OUser;
import com.odoo.paid.model.PaidStudent;


/**
 * Created by Aymen on 11/04/2018.
 */

public class HistoryPayModel extends OModel {

    public static final String TAG = HistoryPayModel.class.getSimpleName();
    public static final String AUTHORITY = "com.odoo.HistoryPay.model.pos_order_line";

    OColumn name = new   OColumn ("namem", OVarchar.class).setSize(100);
    OColumn company_name = new OColumn("parent_id", ResPartner.class, OColumn.RelationType.ManyToOne);
    OColumn student_id = new OColumn("student_id", ResPartner.class, OColumn.RelationType.ManyToOne);
    OColumn class_id = new OColumn("class_id", Classes.class, OColumn.RelationType.ManyToOne);
    OColumn price_unit= new OColumn("price_unit", OFloat.class);

    OColumn state_order = new OColumn("state", OSelection.class)
            .addSelection("draft","New")
            .addSelection("cancel","Canceled")
            .addSelection("paid","Paid")
            .addSelection("done","Posted")
            .addSelection("invoiced","Invoiced");




    public HistoryPayModel(Context context, OUser user) {
        super(context,"pos.order.line", user);
        //    setHasMailChatter(true);

    }






    @Override
    public Uri uri() {
        return buildURI(AUTHORITY);
    }

}
