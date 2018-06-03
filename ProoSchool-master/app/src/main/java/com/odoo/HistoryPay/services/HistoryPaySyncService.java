package com.odoo.HistoryPay.services;

import android.content.Context;
import android.os.Bundle;

import com.odoo.HistoryPay.model.HistoryPayModel;
import com.odoo.core.service.OSyncAdapter;
import com.odoo.core.service.OSyncService;
import com.odoo.core.support.OUser;

/**
 * Created by Aymen on 11/04/2018.
 */

public class HistoryPaySyncService extends OSyncService {
    public static final String TAG = HistoryPaySyncService.class.getSimpleName();

    @Override
    public OSyncAdapter getSyncAdapter(OSyncService service, Context context) {
        return new OSyncAdapter(getApplicationContext(), HistoryPayModel.class, this, true);
    }

    @Override
    public void performDataSync(OSyncAdapter adapter, Bundle extras, OUser user) {
         adapter.syncDataLimit(80);
    }
}