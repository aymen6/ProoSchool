package com.odoo.HistoryPay.provider;

import com.odoo.HistoryPay.model.HistoryPayModel;
import com.odoo.core.orm.provider.BaseModelProvider;

/**
 * Created by Aymen on 11/04/2018.
 */

public class HistoryPayProvider extends BaseModelProvider {
    public static final String TAG = HistoryPayProvider.class.getSimpleName();

    @Override
    public String authority() {
        return HistoryPayModel.AUTHORITY;
    }
}