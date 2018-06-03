package com.odoo.Notes.service;

import android.content.Context;
import android.os.Bundle;

import com.odoo.HistoryPay.model.HistoryPayModel;
import com.odoo.HistoryPay.services.HistoryPaySyncService;
import com.odoo.Notes.Model.note;
import com.odoo.core.service.OSyncAdapter;
import com.odoo.core.service.OSyncService;
import com.odoo.core.support.OUser;

/**
 * Created by Aymen on 31/05/2018.
 */

public class NoteSyncService  extends OSyncService {
    public static final String TAG = NoteSyncService.class.getSimpleName();

    @Override
    public OSyncAdapter getSyncAdapter(OSyncService service, Context context) {
        return new OSyncAdapter(getApplicationContext(), note.class, this, true);
    }

    @Override
    public void performDataSync(OSyncAdapter adapter, Bundle extras, OUser user) {
        adapter.syncDataLimit(80);
    }
}