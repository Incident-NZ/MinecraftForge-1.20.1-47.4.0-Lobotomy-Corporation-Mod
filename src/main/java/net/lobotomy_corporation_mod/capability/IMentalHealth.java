package net.lobotomy_corporation_mod.capability;

public interface IMentalHealth {
    int getMentalHealth();
    void setMentalHealth(int value);
    void addMentalHealth(int amount);
    void consumeMentalHealth(int amount);
}