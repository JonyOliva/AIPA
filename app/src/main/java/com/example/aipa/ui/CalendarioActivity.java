package com.example.aipa.ui;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.aipa.R;
import com.kizitonwose.calendarview.CalendarView;
import com.kizitonwose.calendarview.model.CalendarDay;
import com.kizitonwose.calendarview.model.CalendarMonth;
import com.kizitonwose.calendarview.model.DayOwner;
import com.kizitonwose.calendarview.ui.DayBinder;
import com.kizitonwose.calendarview.ui.MonthHeaderFooterBinder;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.time.YearMonth;
import java.time.format.TextStyle;
import java.time.temporal.WeekFields;
import java.util.Locale;

import Gestion.FichasGestion;
import Models.FichaDiaria;
import Views.DayViewContainer;
import Views.MonthHeaderContainer;

public class CalendarioActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendario);

        CalendarView calendarView = (CalendarView)findViewById(R.id.calendarView);
        YearMonth currentMonth = YearMonth.now();
        YearMonth firstMonth = currentMonth.minusMonths(12);
        YearMonth lastMonth = currentMonth;
        DayOfWeek firstDayOfWeek = WeekFields.of(Locale.getDefault()).getFirstDayOfWeek();
        calendarView.setup(firstMonth, lastMonth, firstDayOfWeek);
        calendarView.scrollToMonth(currentMonth);
        FichasGestion fg = new FichasGestion();
        calendarView.setDayBinder(new DayBinder<DayViewContainer>(){
            @Override
            public DayViewContainer create(View view) {
                return new DayViewContainer(view);
            }
            @Override
            public void bind(DayViewContainer dayViewContainer, CalendarDay calendarDay) {
                TextView textView = dayViewContainer.getTextView();
                textView.setText(String.valueOf(calendarDay.getDate().getDayOfMonth()));
                if (calendarDay.getOwner() != DayOwner.THIS_MONTH) {
                    textView.setOnClickListener(null);
                    textView.setTextColor(Color.GRAY);
                    textView.setBackgroundColor(Color.TRANSPARENT);
                    return;
                }
                textView.setTextColor(Color.BLACK);
                FichaDiaria fd = fg.get(calendarDay.getDate().toString());
                if(LocalDate.now().compareTo(calendarDay.getDate()) <= 0 || fd == null ){
                    textView.setOnClickListener(null);
                    textView.setBackgroundColor(Color.TRANSPARENT);
                    return;
                }
                textView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(getApplicationContext(), "ficha diaria", Toast.LENGTH_SHORT).show();
                    }
                });
                int coloropc = fd.getSintoma().getIdSintoma();
                switch (coloropc){
                    case 1:
                        textView.setBackgroundColor(getResources().getColor(R.color.verde));
                        break;
                    case 2:
                        textView.setBackgroundColor(getResources().getColor(R.color.amarillo));
                        break;
                    case 3:
                        textView.setBackgroundColor(getResources().getColor(R.color.naranja));
                        break;
                    case 4:
                        textView.setBackgroundColor(getResources().getColor(R.color.rojo));
                        break;
                }
            }
        });

        calendarView.setMonthHeaderBinder(new MonthHeaderFooterBinder<MonthHeaderContainer>() {
            @Override
            public MonthHeaderContainer create(View view) {
                return new MonthHeaderContainer(view);
            }

            @Override
            public void bind(MonthHeaderContainer viewContainer, CalendarMonth calendarMonth) {
                TextView txtview = viewContainer.getTextView();
                Month m = Month.of(calendarMonth.getMonth());
                String mes = m.getDisplayName(TextStyle.FULL, new Locale("es"));
                txtview.setText(calendarMonth.getYear() + " - " + mes.substring(0, 1).toUpperCase() + mes.substring(1));
            }
        });

    }

}