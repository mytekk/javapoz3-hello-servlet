package com.sda.todo;

/**
 * Created by mytek on 2017-06-12.
 */
abstract class AbstractTodoChainElementModel implements TodoChainElement {

    protected String path; //ścieżka jaką badamy w chain responsibility - ona określi jaką czynność wykonamy (jaką podstronę wyświetlimy)

    protected TodoDao todoDao; //obiekt dostępu do danych
    protected TodoView todoView; //obiekt prezentacji danych

    public AbstractTodoChainElementModel(String path, TodoDao todoDao, TodoView todoView) {
        this.path = path;
        this.todoDao = todoDao;
        this.todoView = todoView;
    }

    @Override
    public boolean isMyResponsibility(String path) {
        return this.path.equals(path);
        //jesli sciezka (która wcześniej, w konstruktorze zdefiniowałem
        // jako właściwą dla obsługiwania danego typu zdarzenia (np. wyświetlenia wszystkich todosów, lub dodawanie nowego todosa)
        // będzie taka sama jak ta,
        //która przyszła w requeście, to wtedy obsłużymy takie żądanie i zrobimy to w klasie
        //która będzie dziedziczyłą po tej klasie abstrakcyjnej
    }
}
