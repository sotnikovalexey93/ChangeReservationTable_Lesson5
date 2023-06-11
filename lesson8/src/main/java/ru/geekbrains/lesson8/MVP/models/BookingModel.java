package ru.geekbrains.lesson8.MVP.models;

import ru.geekbrains.lesson8.MVP.presenters.Model;

import java.util.*;

public class BookingModel implements Model {


    private Collection<Table> tables;

    public Collection<Table> loadTables(){
        if (tables == null){
            tables = new ArrayList<>();

            tables.add(new Table());
            tables.add(new Table());
            tables.add(new Table());
            tables.add(new Table());
            tables.add(new Table());
        }

        return tables;
    }


    /**
     * Бронирование столика
     * @param reservationDate дата бронирования
     * @param tableNo номер столика
     * @param name имя
     * @return номер бронирования
     */
    public int reservationTable(Date reservationDate, int tableNo, String name){
        Optional<Table> table = loadTables().stream().filter(t -> t.getNo() == tableNo).findFirst();
        if (table.isPresent()){
            Reservation reservation = new Reservation(reservationDate, name);
            table.get().getReservations().add(reservation);
            return reservation.getId();
        }
        throw new RuntimeException("Некорректный номер столика.");
    }

    @Override
    public int[] editReservationTable(int oldReservation, Date reservationDate, int tableNo, String name) {
        int[] result = new int[2];
        for (Table el : loadTables()
        ) {
            Iterator<Reservation> i = el.getReservations().iterator();
            while (i.hasNext()) {
                Reservation e = i.next();
                if (e.getId() == oldReservation) {
                    i.remove();
                    result[0] = oldReservation;
                    result[1] = reservationTable(reservationDate, tableNo, name);
                    return result;
                }
            }
        }
        throw new RuntimeException("Некорректный номер брони.");
    }

}
