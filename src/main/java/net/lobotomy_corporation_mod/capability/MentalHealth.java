package net.lobotomy_corporation_mod.capability;

public class MentalHealth implements IMentalHealth {
    private int value = 20;

    @Override
    public int getMentalHealth() {
        return value;
    }

    @Override
    public void setMentalHealth(int value) {
        int max = 20; // ここは20のまま（ブーストは外からかけるからOK）
        this.value = Math.max(0, value); // 上限は外から制御するから無限にしても大丈夫♡
    }

    @Override
    public void addMentalHealth(int amount) {
        setMentalHealth(this.value + amount);
    }

    @Override
    public void consumeMentalHealth(int amount) {
        setMentalHealth(this.value - amount);
    }
}

