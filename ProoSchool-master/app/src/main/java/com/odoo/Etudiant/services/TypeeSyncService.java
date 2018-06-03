package com.odoo.Etudiant.services;

import android.content.Context;
import android.os.Bundle;

import com.odoo.Etudiant.Model.typee;
import com.odoo.core.service.OSyncAdapter;
import com.odoo.core.service.OSyncService;
import com.odoo.core.support.OUser;


public class TypeeSyncService extends OSyncService {
    public static final String TAG = TypeeSyncService.class.getSimpleName();

    public TypeeSyncService() {
    }

    @Override
    public OSyncAdapter getSyncAdapter(OSyncService service, Context context) {
        return new OSyncAdapter(getApplicationContext(), typee.class, this, true);
    }

    @Override
    public void performDataSync(OSyncAdapter adapter, Bundle extras, OUser user) {
          adapter.syncDataLimit(80);
    }
}