package com.odoo.HistoryPay.model;

import android.content.Context;
import android.net.Uri;

import com.odoo.Etudiant.Model.Classes;
import com.odoo.base.addons.res.ResPartner;
import com.odoo.core.orm.OModel;
import com.odoo.core.orm.fields.OColumn;
import com.odoo.core.orm.fields.types.OFloat;
import com.odoo.core.orm.fields.types.OSelection;
import com.odoo.core.orm.fields.types.OVarchar;
import com.odoo.core.support.OUser;

/**
 * Created by Aymen on 02/06/2018.
 */

public class PosOrder extends OModel {

    public static final String TAG = PosOrder.class.getSimpleName();
    public static final String AUTHORITY = "com.odoo.HistoryPay.model.pos_order";

    OColumn name = new   OColumn ("name", OVarchar.class).setSize(100);

    OColumn amount_paid  = new OColumn("amount_paid", OFloat.class);






    public PosOrder(Context context, OUser user) {
        super(context,"pos.order", user);

    }






    @Override
    public Uri uri() {
        return buildURI(AUTHORITY);
    }

}
