-- phpMyAdmin SQL Dump
-- version 4.8.5
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Czas generowania: 28 Kwi 2021, 09:40
-- Wersja serwera: 10.1.38-MariaDB
-- Wersja PHP: 7.3.2

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Baza danych: `flights`
--
CREATE DATABASE IF NOT EXISTS `flights` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;
USE `flights`;

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `flight`
--

CREATE TABLE `flight` (
  `Id` int(8) NOT NULL,
  `From_place` varchar(255) NOT NULL,
  `To_place` varchar(255) NOT NULL,
  `Departure` datetime NOT NULL,
  `Arrival` datetime NOT NULL,
  `Seats` int(4) NOT NULL,
  `Free_seats` int(4) NOT NULL,
  `Status` varchar(64) NOT NULL,
  `Fly` varchar(64) NOT NULL,
  `Price` int(8) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Zrzut danych tabeli `flight`
--

INSERT INTO `flight` (`Id`, `From_place`, `To_place`, `Departure`, `Arrival`, `Seats`, `Free_seats`, `Status`, `Fly`, `Price`) VALUES
(1, 'Barcelona', 'Madrid', '2021-05-12 07:22:00', '2021-05-12 07:04:00', 142, 17, 'Late 5 minutes', 'Direct', 66),
(3, 'Warsow', 'Berlin', '2021-06-01 08:15:00', '2021-06-01 09:02:00', 121, 33, 'On time', 'Direct', 124),
(5, 'Madrid', 'London', '2021-11-11 11:44:00', '2021-11-11 13:33:00', 150, 150, 'OK', 'Direct', 150);

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `reservation`
--

CREATE TABLE `reservation` (
  `Id` int(8) NOT NULL,
  `Fly_id` int(8) NOT NULL,
  `Pesel` varchar(32) NOT NULL,
  `Amount` int(8) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Zrzut danych tabeli `reservation`
--

INSERT INTO `reservation` (`Id`, `Fly_id`, `Pesel`, `Amount`) VALUES
(1, 1, '11111111111', 2);

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `users`
--

CREATE TABLE `users` (
  `Id` int(32) NOT NULL,
  `Nick` varchar(255) NOT NULL,
  `Password` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Zrzut danych tabeli `users`
--

INSERT INTO `users` (`Id`, `Nick`, `Password`) VALUES
(1, 'pracownik1', '9AEAB6643D73B7E2D210471A3E82112B074271226E5D1B17411F3413C925097B'),
(2, 'pracownik2', 'ABE31FE1A2113E7E8BF174164515802806D388CF4F394CCEACE7341A182271AB');

--
-- Indeksy dla zrzutów tabel
--

--
-- Indeksy dla tabeli `flight`
--
ALTER TABLE `flight`
  ADD PRIMARY KEY (`Id`);

--
-- Indeksy dla tabeli `reservation`
--
ALTER TABLE `reservation`
  ADD PRIMARY KEY (`Id`),
  ADD KEY `Fly_id` (`Fly_id`);

--
-- Indeksy dla tabeli `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`Id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT dla tabeli `flight`
--
ALTER TABLE `flight`
  MODIFY `Id` int(8) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT dla tabeli `reservation`
--
ALTER TABLE `reservation`
  MODIFY `Id` int(8) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT dla tabeli `users`
--
ALTER TABLE `users`
  MODIFY `Id` int(32) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- Ograniczenia dla zrzutów tabel
--

--
-- Ograniczenia dla tabeli `reservation`
--
ALTER TABLE `reservation`
  ADD CONSTRAINT `reservation_ibfk_1` FOREIGN KEY (`Fly_id`) REFERENCES `flight` (`Id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
