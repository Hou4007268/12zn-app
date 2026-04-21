package com.yizhaiyiju.app;

import android.media.MediaPlayer;

/* loaded from: classes.dex */
public final /* synthetic */ class x implements MediaPlayer.OnCompletionListener {
    @Override // android.media.MediaPlayer.OnCompletionListener
    public final void onCompletion(MediaPlayer mediaPlayer) {
        mediaPlayer.release();
    }
}
