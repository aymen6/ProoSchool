/**
 * Odoo, Open Source Management Solution
 * Copyright (C) 2012-today Odoo SA (<http:www.odoo.com>)
 * <p/>
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version
 * <p/>
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details
 * <p/>
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http:www.gnu.org/licenses/>
 * <p/>
 * Created on 30/12/14 3:11 PM
 */
package com.odoo.config;

import com.odoo.Avis.views.AvisFragment;
import com.odoo.Calendrier.views.CalendFragment;
import com.odoo.Etudiant.Views.SanctionFragment;
import com.odoo.Exercices.views.ExercicesFragment;
import com.odoo.HistoryPay.views.HistoryPayFragment;
import com.odoo.Notes.Views.NotesFragment;
import com.odoo.addons.customers.Customers;
import com.odoo.core.support.addons.AddonsHelper;
import com.odoo.core.support.addons.OAddon;

public class Addons extends AddonsHelper {

    /**
     * Declare your required module here
     * NOTE: For maintain sequence use object name in asc order.
     * Ex.:
     * OAddon partners = new OAddon(Partners.class).setDefault();
     * for maintain sequence call withSequence(int sequence)
     * OAddon partners = new OAddon(Partners.class).withSequence(2);
     */
    OAddon a_students = new OAddon(SanctionFragment.class);
    OAddon b_customers = new OAddon(Customers.class).setDefault();
    OAddon c_Avis = new OAddon(AvisFragment.class);
    OAddon d_Exercices = new OAddon(ExercicesFragment.class);
    OAddon e_payment = new OAddon(HistoryPayFragment.class);
    OAddon f_calend = new OAddon(CalendFragment.class);






}
