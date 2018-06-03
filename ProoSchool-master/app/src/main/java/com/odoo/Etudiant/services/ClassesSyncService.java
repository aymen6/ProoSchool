package com.odoo.Etudiant.services;

import android.content.Context;
import android.os.Bundle;

import com.odoo.Etudiant.Model.Classes;
import com.odoo.core.service.OSyncAdapter;
import com.odoo.core.service.OSyncService;
import com.odoo.core.support.OUser;


public class ClassesSyncService extends OSyncService {
    public static final String TAG = ClassesSyncService.class.getSimpleName();

    public ClassesSyncService() {
    }

    @Override
    public OSyncAdapter getSyncAdapter(OSyncService service, Context context) {
        return new OSyncAdapter(getApplicationContext(), Classes.class, this, true);
    }

    @Override
    public void performDataSync(OSyncAdapter adapter, Bundle extras, OUser user) {
        adapter.syncDataLimit(80);
    }
}