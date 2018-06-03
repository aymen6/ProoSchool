package com.odoo.Calendrier.views;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.desai.vatsal.mydynamiccalendar.MyDynamicCalendar;
import com.odoo.R;
import com.odoo.core.orm.ODataRow;

public class CalendActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calend);
        ODataRow row = new ODataRow();

        MyDynamicCalendar myCalendar = new MyDynamicCalendar(this);

        myCalendar.setCalendarBackgroundColor("#eeeeee");
        myCalendar.setHeaderBackgroundColor("#454265");
        myCalendar.setHeaderTextColor("#ffffff");
        myCalendar.setNextPreviousIndicatorColor("#245675");
        myCalendar.setWeekDayLayoutBackgroundColor("#965471");
        myCalendar.setWeekDayLayoutTextColor("#246245");
        myCalendar.setExtraDatesOfMonthBackgroundColor("#324568");
        myCalendar.setExtraDatesOfMonthTextColor("#756325");
        myCalendar.setDatesOfMonthBackgroundColor("#145687");
        myCalendar.setDatesOfMonthTextColor("#745632");
        myCalendar.setCurrentDateBackgroundColor(R.color.black);
        myCalendar.setCurrentDateTextColor("#00e600");
        myCalendar.setBelowMonthEventTextColor("#425684");
        myCalendar.setBelowMonthEventDividerColor("#635478");
        myCalendar.isSaturdayOff(true, "#ffffff", "#ff0000");
        myCalendar.isSundayOff(true, "#ffffff", "#ff0000");



    }
}
