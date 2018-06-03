package com.odoo.Avis.provider;

import com.odoo.Avis.model.ParentAvis;
import com.odoo.core.orm.provider.BaseModelProvider;

/**
 * Created by Aymen on 10/04/2018.
 */

public class AvisProvider extends BaseModelProvider {
    public static final String TAG = AvisProvider.class.getSimpleName();

    @Override
    public String authority() {
        return ParentAvis.AUTHORITY;
    }
}