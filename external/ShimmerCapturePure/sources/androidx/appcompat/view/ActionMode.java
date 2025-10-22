package androidx.appcompat.view;

import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

/* loaded from: classes.dex */
public abstract class ActionMode {
    private Object mTag;
    private boolean mTitleOptionalHint;

    public abstract void finish();

    public abstract View getCustomView();

    public abstract void setCustomView(View view);

    public abstract Menu getMenu();

    public abstract MenuInflater getMenuInflater();

    public abstract CharSequence getSubtitle();

    public abstract void setSubtitle(int i);

    public abstract void setSubtitle(CharSequence charSequence);

    public Object getTag() {
        return this.mTag;
    }

    public void setTag(Object obj) {
        this.mTag = obj;
    }

    public abstract CharSequence getTitle();

    public abstract void setTitle(int i);

    public abstract void setTitle(CharSequence charSequence);

    public boolean getTitleOptionalHint() {
        return this.mTitleOptionalHint;
    }

    public void setTitleOptionalHint(boolean z) {
        this.mTitleOptionalHint = z;
    }

    public abstract void invalidate();

    public boolean isTitleOptional() {
        return false;
    }

    public boolean isUiFocusable() {
        return true;
    }

    public interface Callback {
        boolean onActionItemClicked(ActionMode actionMode, MenuItem menuItem);

        boolean onCreateActionMode(ActionMode actionMode, Menu menu);

        void onDestroyActionMode(ActionMode actionMode);

        boolean onPrepareActionMode(ActionMode actionMode, Menu menu);
    }
}
