package Sound;

import Screen.Home;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import java.net.URL;

public class SoundEffect {

    Clip clip;
    FloatControl fc;

    Float currentVolume = 0f;
    Float prevVolume = 0f;
    boolean mute = false;

    public void setFile(String soundName) {
        try {
            if (clip != null) {
                clip.close();
            }

            clip = null;
            fc = null;

            URL soundFileName =
                    Home.class.getClassLoader().getResource(soundName);

            if (soundFileName == null) {
                return;
            }

            try (AudioInputStream sound =
                         AudioSystem.getAudioInputStream(soundFileName)) {

                clip = AudioSystem.getClip();
                clip.open(sound);
            }

            if (clip.isControlSupported(FloatControl.Type.MASTER_GAIN)) {
                fc = (FloatControl)
                        clip.getControl(FloatControl.Type.MASTER_GAIN);
            }

        } catch (Exception ignored) {
            clip = null;
            fc = null;
        }
    }

    public void play() {
        if (clip == null) return;

        clip.setFramePosition(0);
        clip.start();
    }

    public void stop() {
        if (clip == null) return;

        clip.stop();
    }

    public void loop() {
        if (clip == null) return;

        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }

    long clipTimePosition;
    boolean pause = false;

    public void pause() {
        if (clip == null) return;

        if (!pause) {
            clipTimePosition = clip.getMicrosecondPosition();
            clip.stop();
            pause = true;
        }
    }

    public void resume() {
        if (clip == null) return;

        if (pause) {
            clip.setMicrosecondPosition(clipTimePosition);
            clip.start();
            loop();
            pause = false;
        }
    }

    public void mute() {
        if (fc == null) return;

        if (!mute) {
            prevVolume = currentVolume;
            currentVolume = -80.0f;
            fc.setValue(currentVolume);
            mute = true;
        } else {
            currentVolume = prevVolume;
            fc.setValue(currentVolume);
            mute = false;
        }
    }

    public void control(Float vol) {
        currentVolume = vol;

        if (currentVolume > 6f) {
            currentVolume = 6f;
        }

        if (fc != null) {
            fc.setValue(currentVolume);
        }
    }
}
