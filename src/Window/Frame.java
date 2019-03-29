package Window;

import Data.GameLoader;
import Model.Events.*;
import Model.Events.Event;
import Model.Model;
import Model.Vector2;
import Objects.Ground;
import Objects.Meta;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentLinkedQueue;

import static java.awt.event.KeyEvent.*;

/**
 * Klasa tworząca główne okno aplikacji, menu itp.
 */
public class Frame extends JFrame implements ActionListener {

    ConcurrentLinkedQueue<Event> queue;
    GameStateProvider provider;
    JMenuBar menuBar;
    JMenu menuGRA, menuPomoc;
    JMenuItem mStart, mKulka, mLista, mPomoc, mKoniec, mNick, mPauza, mSterowanie, mAutor;

    /**
     *
     * @param provider
     * @param eventQueue Kolejka zdarzeń
     */
    public Frame(GameStateProvider provider, ConcurrentLinkedQueue<Event> eventQueue)
    {
        this.queue= eventQueue;
        this.provider=provider;

        setTitle("The Kulka");
        JPanel obrazPanel = new ObrazPanel();
        provider.setCanvas(obrazPanel);
        setPreferredSize(new Dimension(1000,900));
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        add(obrazPanel,BorderLayout.CENTER);
        pack();

        menuBar = new JMenuBar();
        setJMenuBar(menuBar);
        //Menu Gra
        menuGRA = new JMenu("GRA");
        menuBar.add(menuGRA);
        mNick = new JMenuItem("Nick");
        mStart = new JMenuItem("Start");
        mKulka = new JMenuItem("Wybór kulki");
        mLista = new JMenuItem("Lista najlepszych wyników");
        mKoniec = new JMenuItem("Koniec");

        menuGRA.add(mStart);
        mStart.addActionListener(this);
        mStart.setAccelerator(KeyStroke.getKeyStroke("ctrl S"));

        addKeyListener(new KeyListener() {

            Map<Integer, Boolean> pressed=new HashMap<>();

            @Override
            /**
             *
             */
            public void keyTyped(KeyEvent e) {
            }

            @Override
            /**
             *
             */
            public void keyPressed(KeyEvent e) {
                if ( ! pressed.getOrDefault(e.getKeyCode(), false)) {
                    pressed.put(e.getKeyCode(),true);
                    switch (e.getKeyCode()) {
                        case VK_UP:
                            eventQueue.add(new EventForce(new Vector2(0, -20)));
                            break;
                        case VK_DOWN:
                            eventQueue.add(new EventForce(new Vector2(0, 20)));
                            break;
                        case VK_LEFT:
                            eventQueue.add(new EventForce(new Vector2(-20, 0)));
                            break;
                        case VK_RIGHT:
                            eventQueue.add(new EventForce(new Vector2(20, 0)));
                            break;
                    }
                }
            }

            @Override
            /**
             *
             */
            public void keyReleased(KeyEvent e) {
                pressed.put(e.getKeyCode(),false);
                switch (e.getKeyCode()){
                    case VK_UP:
                        eventQueue.add(new EventForce(new Vector2(0,20)));
                        break;
                    case VK_DOWN:
                        eventQueue.add(new EventForce(new Vector2(0,-20)));
                        break;
                    case VK_LEFT:
                        eventQueue.add(new EventForce(new Vector2(20,0)));
                        break;
                    case VK_RIGHT:
                        eventQueue.add(new EventForce(new Vector2(-20,0)));
                        break;
                }
            }
        });

        menuGRA.add(mKulka);
        menuGRA.add(mLista);
        menuGRA.addSeparator();
        menuGRA.add(mKoniec);
        mKoniec.addActionListener(this);
        mKoniec.setAccelerator(KeyStroke.getKeyStroke("ctrl X"));

        //menu Pomoc
        menuPomoc = new JMenu("Pomoc");
        menuBar.add(menuPomoc);
        mPomoc = new JMenuItem("Fabuła: Jesteś kulką. Twój cel to meta, którą jest żółte koło");
        menuPomoc.add(mPomoc);
        mSterowanie = new JMenuItem("Sterowanie: strzałki");
        menuPomoc.add(mSterowanie);
        mAutor = new JMenuItem("Autor :  Piotr Lorens");
        menuPomoc.add(mAutor);
        mPauza = new JMenuItem("Pauza");
        menuPomoc.add(mPauza);
        mPauza.addActionListener(this);
        mPauza.setAccelerator(KeyStroke.getKeyStroke("P"));
        setVisible(true);
    }

    /**
     *
     */
    public void endGame()
    {
        getContentPane().removeAll();
        JPanel obrazPanel = new GamePanel(provider);
        provider.setCanvas(obrazPanel);
        add(obrazPanel,BorderLayout.CENTER);
        obrazPanel.setVisible(true);
        queue.add(new EventNewGame());
        pack();
        repaint();
    }

    @Override
    public void actionPerformed(ActionEvent u) {
        Object z = u.getSource();
        if (z == mKoniec)
        {
            queue.add(new EventCloseApplication());
            //dispose();
            System.exit(0);
        } else if (z == mStart)
        {
            getContentPane().removeAll();
            JPanel obrazPanel = new GamePanel(provider);
            provider.setCanvas(obrazPanel);
            add(obrazPanel,BorderLayout.CENTER);
            obrazPanel.setVisible(true);
            queue.add(new EventNewGame());
            pack();
            repaint();
        }
        else if (z == mPauza)
        {
            queue.add(new EventPause());
        }
    }
}
