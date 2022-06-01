import java.util.Scanner;

class Device {
    public boolean on = false;
    public String name;

    protected void on() {
        if (on) {
            System.out.println(name + " is already on");
        } else {
            on = true;
            System.out.println(name + " is on");
        }
    }

    protected void off() {
        if (!on) {
            System.out.println(name + " is already off");
        } else {
            on = false;
            System.out.println(name + " is off");
        }
    }
}

class Amplifier extends Device {
    public int volume = 60;

    public Amplifier() {
        this.name = "Amplifier";
        setVolume(volume);
    }

    public void setVolume(int volume) {
        this.volume = volume;
        System.out.println("Volume is set to " + volume + "%\n");
    }
}

class CDPlayer extends Device {
    int cdDisc = 0;
    Amplifier amplifier;

    public CDPlayer(Amplifier amplifier) {
        this.amplifier = amplifier;
        this.name = "CD Player";
    }

    public void play() {
        System.out.println(name + " is playing");
    }

    public void pause() {
        System.out.println(name + " is paused");
    }

    public void insert(int cdDisc) {
        this.cdDisc = cdDisc;
    }

    public void eject() {
        System.out.println("Disc was ejected");
        cdDisc = 0;
    }
}

class DVDPlayer extends Device {
    int dvdDisc = 0;
    Amplifier amplifier;

    public DVDPlayer(Amplifier amplifier) {
        this.amplifier = amplifier;
        this.name = "DVD Player";
    }

    public void play() {
        System.out.println(name + " is playing");
    }

    public void pause() {
        System.out.println(name + " is paused");
    }

    public void insert(int dvdDisc) {
        this.dvdDisc = dvdDisc;
        System.out.println("Disc " + dvdDisc + " was inserted");
    }

    public void eject() {
        System.out.println("Disc was ejected");
    }
}

class PopcornPopper extends Device {
    public PopcornPopper() {
        this.name = "Popcorn popper";
    }

    @Override
    public void on() {
        System.out.println(name + " is on");
        on = true;
        pop();
    }


    public void pop() {
        System.out.println("pop pop pop");
    }
}

class Tuner extends Device {
    Amplifier amplifier;
    float fm, am;

    public Tuner(Amplifier amplifier) {
        this.amplifier = amplifier;
        this.name = "Tuner";
    }

    public void setFM(float fm) {
        this.fm = fm;
    }

    public void setAM(float am) {
        this.am = am;
    }
}

class HomeTheaterFacade {
    Amplifier amplifier;
    CDPlayer cdPlayer;
    DVDPlayer dvdPlayer;
    PopcornPopper popcornPopper;
    Tuner tuner;

    public HomeTheaterFacade(Amplifier amplifier, CDPlayer cdPlayer, DVDPlayer dvdPlayer, PopcornPopper popcornPopper, Tuner tuner) {
        this.amplifier = amplifier;
        this.cdPlayer = cdPlayer;
        this.dvdPlayer = dvdPlayer;
        this.popcornPopper = popcornPopper;
        this.tuner = tuner;
    }

    public void allOff() {
        System.out.println("Turning everything off:");
        amplifier.off();
        if (cdPlayer.cdDisc != 0) {
            cdPlayer.eject();
        }
        cdPlayer.off();
        if (dvdPlayer.dvdDisc != 0) {
            dvdPlayer.eject();
        }
        dvdPlayer.off();
        popcornPopper.off();
        tuner.off();
    }

    public void watchMovie() {
        System.out.println("Watching movies:");
        amplifier.on();
        dvdPlayer.on();
        popcornPopper.on();
        popcornPopper.off();
        tuner.off();
        cdPlayer.off();
    }

    public void listenToRadio() {
        System.out.println("Listening to radio:");
        amplifier.on();
        tuner.on();
        System.out.print("Choose between AM and FM\n1) AM\n2) FM\n");
        Scanner in = new Scanner(System.in);
        switch (in.nextInt()) {
            case 1 -> {
                System.out.print("AM is chosen. Set amplitude: ");
                float am = in.nextFloat();
                tuner.setAM(am);
            }
            case 2 -> {
                System.out.print("FM is chosen. Set frequency: ");
                float fm = in.nextFloat();
                tuner.setFM(fm);
            }
        }
        cdPlayer.off();
        dvdPlayer.off();
        popcornPopper.off();
    }

    public void listenToCD() {
        System.out.println("Listening to CD:");
        amplifier.on();
        cdPlayer.on();
        System.out.print("Insert disc by number: ");
        Scanner in = new Scanner(System.in);
        cdPlayer.insert(in.nextInt());
        cdPlayer.play();
        dvdPlayer.off();
        popcornPopper.off();
        tuner.off();
    }
}

public class Main {
    public static void main(String[] args) {
        Amplifier amplifier = new Amplifier();
        HomeTheaterFacade homeTheaterFacade = new HomeTheaterFacade(amplifier, new CDPlayer(amplifier), new DVDPlayer(amplifier), new PopcornPopper(), new Tuner(amplifier));
        homeTheaterFacade.watchMovie();
        System.out.println();
        homeTheaterFacade.listenToRadio();
        System.out.println();
        homeTheaterFacade.listenToCD();
        System.out.println();
        homeTheaterFacade.allOff();
    }
}
