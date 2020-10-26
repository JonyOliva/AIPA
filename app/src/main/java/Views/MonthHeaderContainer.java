package Views;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.aipa.R;
import com.kizitonwose.calendarview.ui.ViewContainer;

public class MonthHeaderContainer extends ViewContainer {

    private TextView textView;
    public MonthHeaderContainer(@NonNull View view) {
        super(view);
        textView = view.findViewById(R.id.monthHeaderText);
    }

    public TextView getTextView() {
        return textView;
    }
}
