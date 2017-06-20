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
