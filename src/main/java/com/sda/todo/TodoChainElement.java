package com.sda.todo;

/**
 * Created by RENT on 2017-06-09.
 *
 * interfejsdla chain of resposibility dla śceiżki relatywnej naszej aplikacji
 */
public interface TodoChainElement {

    boolean isMyResponsibility(String path);

    String action();

}
