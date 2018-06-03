package com.odoo.Exercices.provider;

import com.odoo.Exercices.model.homework;
import com.odoo.core.orm.provider.BaseModelProvider;

/**
 * Created by Aymen on 11/04/2018.
 */

public class homeworkProvider extends BaseModelProvider {
    public static final String TAG = homeworkProvider.class.getSimpleName();

    @Override
    public String authority() {
        return homework.AUTHORITY;
    }
}