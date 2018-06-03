package com.odoo.Etudiant.services;

import android.content.Context;
import android.os.Bundle;

import com.odoo.Etudiant.Model.Classroom;
import com.odoo.core.service.OSyncAdapter;
import com.odoo.core.service.OSyncService;
import com.odoo.core.support.OUser;

/**
 * Created by Aymen on 01/06/2018.
 */


 public class ClassroomSyncService extends OSyncService {
    public static final String TAG = ClassroomSyncService.class.getSimpleName();

    public ClassroomSyncService() {
    }

    @Override
    public OSyncAdapter getSyncAdapter(OSyncService service, Context context) {
        return new OSyncAdapter(getApplicationContext(), Classroom.class, this, true);
    }

    @Override
    public void performDataSync(OSyncAdapter adapter, Bundle extras, OUser user) {
        adapter.syncDataLimit(80);
    }
}
