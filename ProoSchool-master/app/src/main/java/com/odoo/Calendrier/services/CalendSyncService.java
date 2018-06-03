package com.odoo.Calendrier.services;

import android.content.Context;
import android.os.Bundle;

import com.odoo.Calendrier.model.Calend;
import com.odoo.core.service.OSyncAdapter;
import com.odoo.core.service.OSyncService;
import com.odoo.core.support.OUser;

/**
 * Created by Aymen on 30/05/2018.
 */

public class CalendSyncService   extends OSyncService {
    @Override
    public OSyncAdapter getSyncAdapter(OSyncService service, Context context) {
        return new OSyncAdapter(getApplicationContext(), Calend.class, this, true);

    }

    @Override
    public void performDataSync(OSyncAdapter adapter, Bundle extras, OUser user) {
        adapter.syncDataLimit(80);

    }
}
