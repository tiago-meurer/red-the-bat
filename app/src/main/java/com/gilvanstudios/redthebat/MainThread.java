package com.gilvanstudios.redthebat;

import android.graphics.Canvas;
import android.provider.Settings;
import android.view.SurfaceHolder;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;


public class MainThread extends Thread {
    private int FPS = 30;
    private SurfaceHolder surfaceHolder;
    private com.gilvanstudios.redthebat.GamePanel gamePanel;
    private boolean running;
    private static Canvas canvas;

    private InterstitialAd interstitial;


    public MainThread(SurfaceHolder surfaceHolder, com.gilvanstudios.redthebat.GamePanel gamePanel) {
        super();
        this.surfaceHolder = surfaceHolder;
        this.gamePanel = gamePanel;

    }


    @Override
    public void run() {

        long startTime;
        long timeMillis;
        long waitTime;
        int frameCount = 0;
        long targetTime = 1000 / FPS;

        while (running) {
            startTime = System.nanoTime();
            canvas = null;

            //try locking the canvas for pixel editing
            try {
                canvas = this.surfaceHolder.lockCanvas();
                synchronized (surfaceHolder) {
                    this.gamePanel.update();
                    this.gamePanel.draw(canvas);
                }
            } catch (Exception e) {
            } finally {
                if (canvas != null) {
                    try {
                        surfaceHolder.unlockCanvasAndPost(canvas);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }


            timeMillis = (System.nanoTime() - startTime) / 1000000;
            waitTime = targetTime - timeMillis;

            try {
                sleep(waitTime);
            } catch (Exception e) {
            }

            frameCount++;
            if (frameCount == FPS) {
                frameCount = 0;
            }

        }

    }



    public void setRunning(boolean running) {
        this.running = running;
    }

}
