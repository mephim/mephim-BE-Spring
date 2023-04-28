package com.example.mephim.repos;

import com.example.mephim.entity.Booking;
import com.example.mephim.response.IMovieStatisResponse;
import com.example.mephim.response.ITransStatisResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StatisRepo extends JpaRepository<Booking, Integer> {
    @Query(value = "SELECT COALESCE(DATE(booking.time), 'TOTAL') AS transaction_day,\n" +
            "sum(ticket.ticket_price) as 'value' from booking inner join ticket inner join users\n" +
            "where booking.ticket_id = ticket.ticket_id\n" +
            "and booking.user_id = users.id\n" +
            "and booking.time >= DATE(NOW() - INTERVAL ? MONTH)\n" +
            "group by DATE(booking.time)\n" +
            "WITH ROLLUP;", nativeQuery = true)
    List<ITransStatisResponse> getStatisTransactionByMonthAgo(Integer numMonth);
    @Query(value = "SELECT COALESCE(DATE(booking.time), 'TOTAL') AS transaction_day,\n" +
            "sum(ticket.ticket_price) as 'value' from booking inner join ticket inner join users\n" +
            "where booking.ticket_id = ticket.ticket_id\n" +
            "and booking.user_id = users.id\n" +
            "and booking.time >= DATE(NOW() - INTERVAL ? DAY)\n" +
            "group by DATE(booking.time)\n" +
            "WITH ROLLUP;", nativeQuery = true)
    List<ITransStatisResponse> getStatisTransactionByDayAgo(Integer numDay);
    @Query(value = "select movie.movie_name, count(movie.movie_id) as 'num_of_booking' from movie inner join ticket inner join booking\n" +
            "where movie.movie_id = ticket.ticket_id\n" +
            "and booking.ticket_id = ticket.ticket_id\n" +
            "and booking.time >= DATE(NOW() - INTERVAL 1 WEEK)\n" +
            "group by movie.movie_id;", nativeQuery = true)
    List<IMovieStatisResponse> getMovieTransactionBy1WeeksAgo();
}