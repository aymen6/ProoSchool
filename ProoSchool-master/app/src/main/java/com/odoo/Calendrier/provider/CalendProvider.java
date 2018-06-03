package com.odoo.Calendrier.provider;


import com.odoo.Calendrier.model.Calend;
import com.odoo.core.orm.provider.BaseModelProvider;

/**
 * Created by Aymen on 30/05/2018.
 */

public class CalendProvider extends BaseModelProvider {
     public static final String TAG = CalendProvider.class.getSimpleName();

    @Override
    public String authority() {
        return Calend.AUTHORITY;
    }
}