package org.emp.gl.core.launcher;

import org.emp.gl.clients.Horloge;
import org.emp.gl.clients.CompteARebours;
import org.emp.gl.timer.service.TimerService;
import org.emp.gl.time.service.impl.DummyTimeServiceImpl;

/**
 * Hello world!
 *
 */
public class App {

    public static void main(String[] args) {

        //testDuTimeService();
        Decompeuteur();
    }

    private static void testDuTimeService() {
        TimerService timerService = new DummyTimeServiceImpl() ;
        Horloge h1 = new Horloge("H1", timerService);
        Horloge h2 = new Horloge("H2", timerService);
    }

    private static void Decompeuteur() {
        TimerService timerService = new DummyTimeServiceImpl() ;   
        CompteARebours c1 = new CompteARebours("C1", 5, timerService);

    }
    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}
