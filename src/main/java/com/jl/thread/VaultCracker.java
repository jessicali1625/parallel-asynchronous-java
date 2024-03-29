package com.jl.thread;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class VaultCracker {
    public static final int MAX_PASSWORD = 9999;

    public static void main(String[] args) {

        Random random = new Random();

        Vault vault = new Vault(random.nextInt(MAX_PASSWORD));

        List<Thread> threadList = new ArrayList<>();
        threadList.add(new AscendingHackerThread(vault));
        threadList.add(new DescendingHackerThread(vault));
        threadList.add(new PoliceThread());

        for (Thread thread : threadList) {
            thread.start();
        }
    }


    private static class Vault {
        private final int password;

        public Vault(int password) {
            this.password = password;
        }

        public boolean isCorrectPassword(int guessedPassword) {
            try {
                Thread.sleep(5);
            } catch (InterruptedException e) {

            }
            return this.password == guessedPassword;
        }
    }

    private static abstract class HackerThread extends Thread {
        protected final Vault vault;

        public HackerThread(Vault vault) {
            this.vault = vault;
        }

    }


    private static class AscendingHackerThread extends HackerThread {

        public AscendingHackerThread(Vault vault) {
            super(vault);
        }

        @Override
        public void run() {
            for (int guess = 0; guess < MAX_PASSWORD; guess++) {
                if (vault.isCorrectPassword(guess)) {
                    System.out.println(Thread.currentThread().getName() + " guessed the password " + guess);
                    System.exit(0);
                }
            }
        }
    }


    private static class DescendingHackerThread extends HackerThread {

        public DescendingHackerThread(Vault vault) {
            super(vault);
        }

        @Override
        public void run() {
            for (int guess = MAX_PASSWORD; guess > 0; guess--) {
                if (vault.isCorrectPassword(guess)) {
                    System.out.println(Thread.currentThread().getName()+ " guessed the password " + guess);
                    System.exit(0);
                }
            }
        }
    }

    private static class PoliceThread extends Thread {
        @Override
        public void run() {
            for (int i = 10; i > 0; i--) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                }
                System.out.println(i);
            }

            System.out.println("Game over for you hackers");
            System.exit(0);
        }
    }

}
