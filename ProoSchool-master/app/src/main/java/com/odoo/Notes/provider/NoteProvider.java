package com.odoo.Notes.provider;

import com.odoo.Notes.Model.note;
import com.odoo.core.orm.provider.BaseModelProvider;

/**
 * Created by Aymen on 31/05/2018.
 */

public class NoteProvider extends BaseModelProvider {
    public static final String TAG = com.odoo.Notes.provider.NoteProvider.class.getSimpleName();

    @Override
    public String authority() {
        return note.AUTHORITY;
    }
}