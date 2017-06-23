package com.sda.sockets.kik;

/**
 * Created by RENT on 2017-06-20.
 */
public class Board {

    private String[] array;
    private int numberOfSuccessfulMoves;

    public Board() {

        this.array = new String[9];
        this.numberOfSuccessfulMoves  = 0;
    }

    public int getNumberOfSuccessfulMoves() {
        return numberOfSuccessfulMoves;
    }

    /**
     *
     * @param position values 1 - 9
     * @param sign
     * @return
     */
    public boolean addMove(int position, String sign) {
        boolean valueToReturn = false;
        if (checkRange(position) && isPositionEmpty(position)) {
            array[position - 1] = String.valueOf(sign);
            numberOfSuccessfulMoves++; //podbijam licznik zajetych pol na tablicy
            valueToReturn = true;
        }
        return valueToReturn;
    }

    private boolean checkRange(int position) {
        return position <= 9 && position >= 1;
    }

    private boolean isPositionEmpty(int position) {
        return array[position - 1] == null;
    }

    public boolean isGameFinished() {
        //wystarczy, ze ktorys z elementow bedzie true, wtedy gra sie konczy
        return isFullfilled() || checkRows() || checkColumns() || checkDiagonals();
    }

    //czy bylo juz 9 ruchow?
    private boolean isFullfilled() {

        return numberOfSuccessfulMoves == 9;
    }

    //porownuje czy elementy array[i], array[j], array[k] sa sobie rowne
    private boolean areValuesEquals(int i, int j, int k) {
        return array[i] != null && array[i].equals(array[j]) && array[i].equals(array[k]);
        //to pierwsze sprawdzenie zabezpiecza nas przed porownywaniem nulla do czegos
        //jesli array[i] bedzie nullem to od razu zwrocimy false, bez exceptiona
    }

    //czy jest jakis wiersz z wszystkimi takimi samymi wartosciami?
    private boolean checkRows() {
        //tak dobieramy iterator i przekazywane wartosci, ze beda
        //trzy iteracje i w kazdej dostane wspolrzedne kolejnego wiersza
        boolean flag = false;
        int i = 0;
        do {
            flag = areValuesEquals(i, i+1, i+2);
            i+=3;
        } while (i<9 && !flag);
        return flag;
    }

    //czy jest jakas kolumna z wszystkimi takimi samymi wartosciami?
    private boolean checkColumns() {
        //tak dobieramy iterator i przekazywane wartosci, ze beda
        //trzy iteracje i w kazdej dostane wspolrzedne kolejnej kolumny
        boolean flag = false;
        int i = 0;
        do {
            flag = areValuesEquals(i, i+3, i+6);
            i+=1;
        } while (i<3 && !flag);
        return flag;
    }

    private boolean checkFirstDiagonal() {

        return areValuesEquals(0, 4, 8);
    }

    private boolean checkSecondDiagonal() {

        return areValuesEquals(2, 4, 6);
    }

    //czy jest jakas przekatna z wszystkimi takimi samymi wartosciami?
    private boolean checkDiagonals() {
        //jesli srodkowe pole bedzie nullem, to od razu zwracam false
        return (array[4] != null) && (checkFirstDiagonal() || checkSecondDiagonal());
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < array.length; i++) {
            stringBuilder.append(array[i] == null ? (i + 1) : array[i]);
            stringBuilder.append((i+1) % 3 == 0 ? "\n" : "|");
        }

        return stringBuilder.toString();
    }
}
