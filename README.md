## osm20L_projekt2

**Przedmiot:** OSM

**Projekt:** 2

**Zadanie:** Celem projektu jest napisanie apliakcji realizującej jedno z zadań z listy w punkcie 4.

**Temat:** System elektronicznych kart badań kardiologicznych. Zadaniem aplikacji jest gromadzenie danych pacjentów oraz wyników pomiarów cisnienia krwi i tętna wykonanych w kolejnych punktach czasowych. Dla wybranego pacjenta program ma generować wykresy zmiennoćci mierzonych parametrów w czasie i wyznaczać ich proste statystyki (min, max, ćrednia). Informacje o pacjentach i pomiarach należy przechowywać w relacyjnej bazie danych (np. JavaDB w trybie embedded) lub pliku (sugerowane jest wykorzystanie standardowych formatów, np. XML lub JSON).

**Zespol:** Magdalena Kotynia Szymon Kruszewski 

**Biblioteki:** jfreechart, sqlite, apache commons, jcalendar 

**Uwagi dodatkowe:** Opis projektu: 
Aplikacja umozliwia przeglad danych pacjentow oraz badan pacjentow w tabelach. Lewy panel umozliwia dodanie pacjenta oraz dodanie badania do pacjenta po wybraniu pacjenta z tabeli pacjentow. Mozliwe jest usuniecie pacjenta wraz z jego badaniami po wybraniu pacjenta z listy i kliknieciu "delete" w panelu pacjenta. Mozna tez usunac pojedyncze badanie po wybraniu badania z tabeli badan i kliknieciu "delete" w panelu badania. Przyciski "cancel" w panelach badania i pacjenta umozliwiaja wyczyszczenie pol i tym samym anulowanie operacji. Po wybraniu pacjenta z tabeli w dolnej czesci okienka aplikacji pojawiaja sie statystyki badan pulsu i cisnienia pacjenta. Po wybraniu przycisku "pressure plot" lub "pulse plot" wyswietli sie wykres ze statystykami pacjenta odpowiednio cisnienia i pulsu.
