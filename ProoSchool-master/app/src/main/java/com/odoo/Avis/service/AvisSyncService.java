package com.odoo.Avis.service;

import android.content.Context;
import android.os.Bundle;

import com.odoo.Avis.model.ParentAvis;
import com.odoo.core.orm.provider.BaseModelProvider;
import com.odoo.core.service.OSyncAdapter;
import com.odoo.core.service.OSyncService;
import com.odoo.core.support.OUser;

/**
 * Created by Aymen on 10/04/2018.
 */

public class AvisSyncService extends OSyncService {
    public static final String TAG = AvisSyncService.class.getSimpleName();

    @Override
    public OSyncAdapter getSyncAdapter(OSyncService service, Context context) {
        return new OSyncAdapter(getApplicationContext(), ParentAvis.class, this, true);
    }

    @Override
    public void performDataSync(OSyncAdapter adapter, Bundle extras, OUser user) {
        // adapter.syncDataLimit(80);

    }

//    /**
//     * Created by Aymen on 10/04/2018.
//     */
//
//    public static class AvisProvider extends BaseModelProvider {
//        public static final String TAG = AvisProvider.class.getSimpleName();
//
//        @Override
//        public String authority() {
//            return ParentAvis.AUTHORITY;
//        }
//    }
}
