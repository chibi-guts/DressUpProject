package it.sephiroth.android.library.util.v16;

import android.view.View;

import it.sephiroth.android.library.util.v14.ViewHelper14;

public class ViewHelper16 extends ViewHelper14 {
	public ViewHelper16( View view ) {
		super( view );
	}

	@Override
	public void postOnAnimation( Runnable action ) {
		view.postOnAnimation(action);
	}
}