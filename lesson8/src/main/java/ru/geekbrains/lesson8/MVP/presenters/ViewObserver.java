package ru.geekbrains.lesson8.MVP.presenters;

import java.util.Date;

public interface ViewObserver {

    void onReservationTable(Date reservationDate, int tableNo, String name);

    void onEditReservationTable(int oldReservation, Date reservationDate, int tableNo, String name);
}
