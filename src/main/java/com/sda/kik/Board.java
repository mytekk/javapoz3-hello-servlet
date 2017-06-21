package com.sda.kik;

/**
 * Created by RENT on 2017-06-20.
 */
public class Board {

    private String[] array;

    public Board() {
        this.array = new String[9];
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
        return false;
    }

    //czy bylo juz 9 ruchow?
    private boolean isFullfilled() {
        return false;
    }

    //porownuje czy elementy i j k sa sobie rowne
    private boolean areValuesEquals(int i, int j, int k) {
        return array[i] != null && array[i].equals(array[j]) && array[i].equals(array[k]);
        //to pierwsze sprawdzenie zabezpiecza nas przed porownywaniem nulla do czegos
        //jesli array[i] bedzie nullem to od razu zwrocimy false, bez exceptiona
    }

    //czy jest jakis wiersz z wszystkimi takimi samymi wartosciami?
    private boolean checkRows() {
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
        return false;
    }

    private boolean checkFirstDiagonal() {
        return (array[0] == array[4] && array[0] == array[8]);
    }

    private boolean checkSecondDiagonal() {
        return (array[2] == array[4] && array[2] == array[6]);
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
