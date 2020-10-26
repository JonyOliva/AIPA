package Views;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.aipa.R;
import com.kizitonwose.calendarview.ui.ViewContainer;

public class DayViewContainer extends ViewContainer {
    private TextView textView;
    public DayViewContainer(@NonNull View view) {
        super(view);
        textView = view.findViewById(R.id.calendarDayText);
    }

    public TextView getTextView() {
        return textView;
    }
}
