package com.odoo.Exercices.services;

import android.content.Context;
import android.os.Bundle;

import com.odoo.Exercices.model.homework;
import com.odoo.core.service.OSyncAdapter;
import com.odoo.core.service.OSyncService;
import com.odoo.core.support.OUser;

/**
 * Created by Aymen on 11/04/2018.
 */

public class homeworkSyncService extends OSyncService {
    public static final String TAG = homeworkSyncService.class.getSimpleName();

    @Override
    public OSyncAdapter getSyncAdapter(OSyncService service, Context context) {
        return new OSyncAdapter(getApplicationContext(), homework.class, this, true);
    }

    @Override
    public void performDataSync(OSyncAdapter adapter, Bundle extras, OUser user) {
        // adapter.syncDataLimit(80);
    }
}