package com.yizhaiyiju.app;

import android.media.MediaPlayer;

/* loaded from: classes.dex */
public final /* synthetic */ class w implements MediaPlayer.OnPreparedListener {
    @Override // android.media.MediaPlayer.OnPreparedListener
    public final void onPrepared(MediaPlayer mediaPlayer) {
        mediaPlayer.start();
    }
}
