package com.odoo.HistoryPay.provider;

import com.odoo.HistoryPay.model.PosOrder;
import com.odoo.core.orm.provider.BaseModelProvider;

/**
 * Created by Aymen on 02/06/2018.
 */

public class PosProvider extends BaseModelProvider {
    public static final String TAG = PosProvider.class.getSimpleName();

    @Override
    public String authority() {
        return PosOrder.AUTHORITY;
    }
}