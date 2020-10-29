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
import java.time.Month;
import java.time.YearMonth;
import java.time.format.TextStyle;
import java.time.temporal.WeekFields;
import java.util.Locale;

import Views.DayViewContainer;
import Views.MonthHeaderContainer;

public class CalendarioActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendario);

        CalendarView calendarView = (CalendarView)findViewById(R.id.calendarView);
        YearMonth currentMonth = YearMonth.now();
        YearMonth firstMonth = currentMonth.minusMonths(10);
        YearMonth lastMonth = currentMonth.plusMonths(10);
        DayOfWeek firstDayOfWeek = WeekFields.of(Locale.getDefault()).getFirstDayOfWeek();
        calendarView.setup(firstMonth, lastMonth, firstDayOfWeek);
        calendarView.scrollToMonth(currentMonth);
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
                    textView.setTextColor(Color.GRAY);
                    return;
                }
                textView.setTextColor(Color.BLACK);
                if((int)(Math.floor(Math.random()*2)) == 1){
                    int color = (int)Math.floor(Math.random()*3);
                    switch (color){
                        case 0:
                            textView.setBackgroundColor(getResources().getColor(R.color.verde));
                            textView.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    Toast.makeText(getApplicationContext(), "verde", Toast.LENGTH_SHORT).show();
                                }
                            });
                            break;
                        case 1:
                            textView.setBackgroundColor(getResources().getColor(R.color.amarillo));
                            break;
                        case 2:
                            textView.setBackgroundColor(getResources().getColor(R.color.rojo));
                            break;
                    }
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