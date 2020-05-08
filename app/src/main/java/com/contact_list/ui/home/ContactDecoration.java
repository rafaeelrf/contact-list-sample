package com.contact_list.ui.home;

import android.content.Context;
import android.graphics.Rect;
import android.util.TypedValue;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ContactDecoration extends RecyclerView.ItemDecoration {

    private Context mContext;

    ContactDecoration(Context context) {
        this.mContext = context;
    }

    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);

        int adapterPosition = parent.getChildAdapterPosition(view);
        if (adapterPosition > 0) {
            outRect.top = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 10, mContext.getResources().getDisplayMetrics());
        }
    }

}
